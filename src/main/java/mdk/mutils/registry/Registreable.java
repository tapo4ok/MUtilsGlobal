package mdk.mutils.registry;

public interface Registreable<T extends Registry<?>> {
    void register(T REGISTRY);
}
