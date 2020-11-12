package cache;

/**
 * Interface define cache for some objects with some key for any container.
 * @param <V> - some object.
 * @param <K> - some key.
 */
public interface Cache<K, V> {

    boolean containsKey(K key);

    /**
     * Put the element with definite key.
     * @param value - element.
     * @param key - key.
     * @return - just added element.
     */
    V put(K key, V value);

    /**
     * @param key - the key of the desired element.
     * @return - the element associated with the key.
     */
    V get(K key);
}
