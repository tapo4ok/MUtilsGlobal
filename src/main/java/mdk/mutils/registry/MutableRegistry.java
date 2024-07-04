package mdk.mutils.registry;


import mdk.mutils.Identifier;

public interface MutableRegistry<T> extends Registry<T> {
    boolean isEmpty();
    boolean add(Identifier identifier, T value);
}
