package mdk.mutils.registry;


import mdk.mutils.Identifier;

import java.util.*;
import java.util.stream.Stream;

public class SimpleRegistry<T> implements MutableRegistry<T> {
    private final Map<Identifier, T> registry;
    private final List<T> values;
    private final Identifier id;

    public SimpleRegistry(Identifier id) {
        this(id, new HashMap<>(), new ArrayList<>());
    }

    public SimpleRegistry(String id) {
        this(new Identifier(id), new HashMap<>(), new ArrayList<>());
    }

    public SimpleRegistry(String id, Map<Identifier, T> map, List<T> list) {
        this(new Identifier(id), map, list);
    }
    public SimpleRegistry(Identifier id, Map<Identifier, T> map, List<T> list) {
        this.registry = map;
        this.values = list;
        this.id = id;
        Registry.REGISTRIES.add(this);
    }
    @Override
    public Identifier getAsObject(T value) {
        for (Identifier key : registry.keySet()) {
            if (registry.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    @Override
    public Optional<T> getO(Identifier key) {
        return Optional.ofNullable(registry.get(key));
    }


    @Override
    public T get(Identifier key) {
        return registry.get(key);
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    @Override
    public boolean containsId(Identifier id) {
        return registry.containsKey(id);
    }

    @Override
    public boolean containsValue(T value) {
        return registry.containsValue(value);
    }

    @Override
    public Set<Identifier> getKeys() {
        return registry.keySet();
    }

    @Override
    public boolean add(Identifier identifier, T value) {
        if (registry.containsKey(identifier)) {
            return false;
        }
        registry.put(identifier, value);
        values.add(value);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return registry.isEmpty();
    }

    @Override
    public RegistryWrapper<T> getReadOnlyWrapper() {
        return new RegistryWrapper<T>() {

            @Override
            public Identifier getAsObject(T value) {
                return SimpleRegistry.this.getAsObject(value);
            }

            @Override
            public T get(Identifier key) {
                return SimpleRegistry.this.get(key);
            }

            @Override
            public Optional<T> getO(Identifier key) {
                return SimpleRegistry.this.getO(key);
            }

            @Override
            public Set<Identifier> getKeys() {
                return SimpleRegistry.this.getKeys();
            }
        };
    }

    @Override
    public Registry<T> freeze() {
        return new SimpleRegistry<T>(id) {
            private final Map<Identifier, T> frozenRegistry = new HashMap<>(registry);

            @Override
            public boolean add(Identifier identifier, T value) {
                throw new UnsupportedOperationException("This registry is frozen and cannot be modified");
            }

            @Override
            public boolean isEmpty() {
                return frozenRegistry.isEmpty();
            }

            @Override
            public T get(Identifier key) {
                return frozenRegistry.get(key);
            }

            @Override
            public Set<Identifier> getKeys() {
                return Collections.unmodifiableSet(frozenRegistry.keySet());
            }

            @Override
            public Iterator<T> iterator() {
                return Collections.unmodifiableList(new ArrayList<>(frozenRegistry.values())).iterator();
            }

            @Override
            public Stream<T> streamEntries() {
                return frozenRegistry.values().stream();
            }

            @Override
            public Registry<T> freeze() {
                return this;
            }
        };
    }

    @Override
    public Stream<T> streamEntries() {
        return values.stream();
    }

    @Override
    public Identifier getId() {
        return id;
    }
}