package mdk.mutils.registry;


import mdk.mutils.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Registry<T> extends Iterable<T> {
    List<Registry<?>> REGISTRIES = new ArrayList<Registry<?>>() {
        @Override
        public boolean add(Registry<?> registry) {
            if (!contains(registry)) {
                for (Registry<?> reg : this) {
                    if (registry.getId().hashCode() == reg.getId().hashCode()) {
                        return false;
                    }
                }
                return super.add(registry);
            }
            else {
                return false;
            }
        }
    };

    Identifier getAsObject(T value);
    T get(Identifier key);
    Optional<T> getO(Identifier key);
    Set<Identifier> getKeys();
    RegistryWrapper<T> getReadOnlyWrapper();

    default Optional<T> getOrEmpty(Identifier id) {
        return Optional.ofNullable(this.get(id));
    }

    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    boolean containsId(Identifier id);
    boolean containsValue(T value);
    static <V, T extends V> T register(Registry<V> registry, Identifier key, T value) {
        if (registry instanceof MutableRegistry) {
            ((MutableRegistry<V>) registry).add(key, value);
        }
        else {
            throw new RuntimeException("Registry is not Mutable!");
        }
        return value;
    }

    Registry<T> freeze();
    Stream<T> streamEntries();
    Identifier getId();
}
