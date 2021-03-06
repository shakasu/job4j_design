package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Cache`s implementation, based on HashMap.
 * @param <K> generic key.
 * @param <V> generic value.
 */
public class HashMapCache<K, V> implements Cache<K, V>{
    private final HashMap<K, SoftReference<V>> container;

    public HashMapCache() {
        this.container = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return container.containsKey(key);
    }

    /**
     * Method create soft reference for value.
     * @param key - key.
     * @param value - element.
     * @return - the value retrieved from the container of SoftReference.
     */
    @Override
    public V put(K key, V value) {
        return (container.put(key, new SoftReference<>(value)) == null) ? container.get(key).get() : null;
    }

    /**
     * @param key - the key of the desired element.
     * @return - the element associated with the key.
     */
    @Override
    public V get(K key) {
        return container.get(key).get();
    }
}
