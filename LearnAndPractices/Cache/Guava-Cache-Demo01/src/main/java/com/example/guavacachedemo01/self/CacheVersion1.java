package com.example.guavacachedemo01.self;

import java.util.LinkedHashMap;
import java.util.Map;

// Implementing LRU eviction algorithm based on LinkedHashMap
public class CacheVersion1 implements Cache {
    // cache capacity
    private int capacity;
    // Holds an internal real cache object through a composition relationship
    private InternalCache internalCache;

    public CacheVersion1(int capacity){
        this.capacity = capacity;
        internalCache = new InternalCache(capacity);
    }

    private static class InternalCache extends LinkedHashMap<Object,Object> {
        private int capacity;
        public InternalCache(int capacity) {
            super(16,0.75f,true);
            this.capacity = capacity;
        }
        /**
         * In java.util.LinkedHashMap#afterNodeInsertion(boolean) callback
         * @param eldest eldest
         * @return true: Clear and remove the elements at the top (sorting rule) false: Do not clear
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
            return size() > capacity;
        }
    }
    @Override
    public void put(Object key, Object value) {
        internalCache.put(key,value);
    }

    @Override
    public void remove(Object key) {
        internalCache.remove(key);
    }

    @Override
    public void clear() {
        internalCache.clear();
    }

    @Override
    public Object get(Object key) {
        return internalCache.get(key);
    }

    @Override
    public int size() {
        return internalCache.size();
    }

    @Override
    public String toString() {
        return "CacheVersion1{" +
                "capacity=" + capacity +
                ", internalCache=" + internalCache +
                '}';
    }
}
