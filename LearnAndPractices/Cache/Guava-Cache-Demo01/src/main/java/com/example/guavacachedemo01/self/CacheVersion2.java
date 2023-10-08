package com.example.guavacachedemo01.self;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// Implementing LRU eviction algorithm based on LinkedList
public class CacheVersion2 implements Cache {
    private int capacity;
    // Used to maintain the order of cache keys
    private LinkedList<Object> keyList;
    // Holds an internal real cache object through a composition relationship
    private Map<Object,Object> internalCache;

    public CacheVersion2(int capacity){
        this.capacity = capacity;
        keyList = new LinkedList<>();
        internalCache = new HashMap<>();
    }

    @Override
    public void put(Object key, Object value) {
        // When calling the put method, the number of existing elements == capacity
        if (size() == capacity) {
            // Remove the first one, because the last one accessed in get has been set at the end.
            Object firstKey = keyList.removeFirst();
            internalCache.remove(firstKey);
        }
        keyList.addLast(key);
        internalCache.put(key,value);
    }

    @Override
    public void remove(Object key) {
        keyList.remove(key);
        internalCache.remove(key);
    }

    @Override
    public void clear() {
        keyList.clear();
        internalCache.clear();
    }

    @Override
    public Object get(Object key) {
        // true:key exist false:not exist in keylist
        boolean removeResult = keyList.remove(key);
        if (removeResult) {
            Object value = internalCache.get(key);
            // Sort the keys currently accessed, with the last accessed last.
            keyList.addLast(key);
            return value;
        }
        return null;
    }


    @Override
    public int size() {
//        internalCache.size();
        return keyList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object key : keyList) {
            sb.append("key:").append(key).append(",")
                    .append("value:").append(internalCache.get(key))
                    .append("-");
        }
        return sb.toString();
    }
}
