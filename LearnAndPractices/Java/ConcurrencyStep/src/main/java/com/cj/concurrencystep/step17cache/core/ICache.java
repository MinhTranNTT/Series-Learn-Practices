package com.cj.concurrencystep.step17cache.core;

import java.util.Objects;

public interface ICache {

    /**
     * Clear all caches
     */
    void clear();

    /**
     * Put in cache
     * @param key
     * @param value
     * @param needDelAfterRead
     * @param expire
     * @return
     */
    Object put(String key, Object value, boolean needDelAfterRead, long expire);

    /**
     * Put in cache
     * @param key
     * @param value
     * @param expire
     * @return
     */
    Object put(String key, Object value, long expire);

    /**
     * Get data from cache
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * Whether the key exist in cache
     * @param key
     * @return
     */
    boolean containKey(String key);

    /**
     * Remove key
     * @param key
     * @return
     */
    boolean remove(String key);
}
