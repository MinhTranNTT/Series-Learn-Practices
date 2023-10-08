package com.example.guavacachedemo01.self;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheVersionFinal implements Cache {
    // cache capacity
    private int capacity;
    // Hold an internal real cache object through the composition relationship
    private InternalCache internalCache;

    public CacheVersionFinal(int capacity){
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
         * @param eldest oldest
         * @return true: Clear and remove the elements at the top (sorting rule) false: Do not clear
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
            return size() > capacity;
        }
    }
    @Override
    public void put(Object key, Object value) {
        SoftReference<Object> softReference = new SoftReference<>(value);
        internalCache.put(key,softReference);
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
        Object o = internalCache.get(key);
        if (o == null) {
            return null;
        }
        SoftReference<Object> softReference = (SoftReference<Object>) o;
        // Returns the real object referenced
        return softReference.get();
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
