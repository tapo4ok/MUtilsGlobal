package mdk.mutils.registry;

import mdk.mutils.Identifier;

import java.util.Optional;
import java.util.Set;

public interface RegistryWrapper<T> {
    Identifier getAsObject(T value);
    T get(Identifier key);
    Optional<T> getO(Identifier key);
    Set<Identifier> getKeys();
}
