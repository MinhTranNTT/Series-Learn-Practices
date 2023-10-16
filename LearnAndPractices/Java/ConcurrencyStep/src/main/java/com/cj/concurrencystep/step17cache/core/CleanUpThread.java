package com.cj.concurrencystep.step17cache.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


public class CleanUpThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanUpThread.class);

    private int time;
    private CacheUtils cacheUtils;

    public CleanUpThread(int time, CacheUtils cacheUtils) {
        this.time = time;
        this.cacheUtils = cacheUtils;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(time);
                // Clean up expired data
                expireTimeCleanUp();
                // If the stored data reaches the maximum value, cleanup work will be performed here.
                maximumSizeCleanUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Expired data cleaning work
     */
    private void expireTimeCleanUp() {
        LOGGER.debug("[expireTimeCleanUp] start clean up");
        CacheGlobal cacheGlobal = cacheUtils.getCacheGlobal();
        ConcurrentHashMap<String, MyCache> tempMap = cacheGlobal.cacheConcurrentHashMap;
        for (String cacheKey : tempMap.keySet()) {
            MyCache myCache = tempMap.get(cacheKey);
            if (myCache == null) {
                continue;
            }
            long timeoutTime = System.currentTimeMillis() - myCache.getWriteTime();
            if (myCache.getExpireTime() * 1000 > timeoutTime) {
                continue;
            }
            // Clear expired cache keys
            cacheUtils.remove(cacheKey);
        }
    }

    /**
     * Memory space reclamation and cleaning work
     */
    private void maximumSizeCleanUp() {
        LOGGER.debug("[maximumSizeCleanUp] start clean up");
        int maximumSize = cacheUtils.getCacheGlobal().getMaximumSize();
        // If the maximum capacity is configured, recycling will be performed here.
        int currentSize = cacheUtils.getCacheGlobal().cacheConcurrentHashMap.size();
        if (maximumSize == 0 || currentSize < maximumSize) {
            return;
        }
        CacheGlobal cacheGlobal = cacheUtils.getCacheGlobal();
        ConcurrentHashMap<String, MyCache> tempMap = cacheGlobal.cacheConcurrentHashMap;
        List<Map.Entry<String, MyCache>> entries = new ArrayList<>(tempMap.entrySet());

        entries.sort((o1, o2) -> (int) (o2.getValue().getHitCount().get() - o1.getValue().getHitCount().get()));
//        Collections.sort(entries, new Comparator<Map.Entry<String, MyCache>>() {
//            @Override
//            public int compare(Map.Entry<String, MyCache> o1, Map.Entry<String, MyCache> o2) {
//                return (int) (o2.getValue().getHitCount().get() - o1.getValue().getHitCount().get());
//            }
//        });

        // Retain only half of the cached data
        int delSize = (currentSize - maximumSize) + (currentSize / 2);
        int i = 0;
//        ConcurrentHashMap<String, MyCache> newCacheMap = new ConcurrentHashMap<>();
        // Retain half of the hotspot data
        for (Map.Entry<String, MyCache> entry : entries) {
            if (i >= delSize) {
                break;
            }
            // The cold data after accessing the math test needs to be removed
            cacheUtils.remove(entry.getKey());
            i++;
        }
    }
}
