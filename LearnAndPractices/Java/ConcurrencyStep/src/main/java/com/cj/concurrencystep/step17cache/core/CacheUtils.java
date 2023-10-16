package com.cj.concurrencystep.step17cache.core;

import com.cj.concurrencystep.step17cache.listener.AddListener;
import com.cj.concurrencystep.step17cache.listener.ReadListener;
import com.cj.concurrencystep.step17cache.listener.RefreshListener;
import com.cj.concurrencystep.step17cache.listener.RemovalListener;
import io.netty.util.internal.StringUtil;

import java.util.Objects;

public class CacheUtils  implements ICache {
    // This mainly contains cached configuration data and stored map collections
    private CacheGlobal cacheGlobal;

    public CacheUtils() {
        this.initConfig(CacheGlobal.DEFAULT_CACHE());
    }

    public CacheUtils(int checkTime, boolean needClean) {
        cacheGlobal = new CacheGlobal(checkTime, needClean);
        this.initConfig(cacheGlobal);
    }

    /**
     * Initialize cache configuration
     * @param cacheGlobal
     */
    private void initConfig(CacheGlobal cacheGlobal) {
        if (cacheGlobal.isNeedClean()) {
            Thread cleanThread = new Thread(new CleanUpThread(cacheGlobal.getCheckTime(), this));
        }
    }

    @Override
    public void clear() {
        cacheGlobal.cacheConcurrentHashMap.clear();
    }

    /**
     * Put a key into the cache
     * @param key
     * @param value
     * @param needDelAfterRead
     * @param expire
     * @return
     */
    @Override
    public Object put(String key, Object value, boolean needDelAfterRead, long expire) {
        if (StringUtil.isNullOrEmpty(key)) {
            return value;
        }
        // If it exists, update the value of the cache key
        MyCache item = new MyCache();
        item.setKey(key);
        item.setDelAfterRead(needDelAfterRead);
        item.getHitCount().incrementAndGet();
        item.setLastTime(System.currentTimeMillis());
        item.setWriteTime(System.currentTimeMillis());
        item.setExpireTime(expire);
        item.setValue(value);
        cacheGlobal.cacheConcurrentHashMap.put(key, item);
        MyCache finalItem = item;
        cacheGlobal.getAddListeners().forEach(x -> {
            x.onAdd(key, finalItem);
        });
        return item;
    }

    /**
     * Put in cache
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public Object put(String key, Object value, long expire) {
        return this.put(key, value, false, expire);
    }

    /**
     * Remove cache
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        if (StringUtil.isNullOrEmpty(key)) {
            cacheGlobal.getReadListeners().forEach(x -> {
                x.onRead(key, null);
            });
            return null;
        }
        MyCache item = cacheGlobal.cacheConcurrentHashMap.get(key);
        if (item == null) {
            cacheGlobal.getReadListeners().forEach(x -> {
                x.onRead(key, null);
            });
            return null;
        }
        item.setLastTime(System.currentTimeMillis());
        item.getHitCount().incrementAndGet();
        // Lazy deletion to determine whether the cache has expired
        long timeoutTime = System.currentTimeMillis() - item.getWriteTime();
        if (item.getExpireTime() * 1000 < timeoutTime) {
            // Perform expiration caching
            remove(key);
            return null;
        }
        MyCache finalItem = item;
        cacheGlobal.getReadListeners().forEach(x -> {
            x.onRead(key, finalItem);
        });
        // If this key is set to be removed after reading, support is needed here.
        if (item.isDelAfterRead()) {
            remove(key);
        }
        return item.getValue();
    }

    /**
     * Whether to include cache key
     * @param key
     * @return
     */
    @Override
    public boolean containKey(String key) {
        MyCache item = cacheGlobal.cacheConcurrentHashMap.get(key);
        if (item == null) {
            return false;
        }
        // When requesting, it will be judged whether the key has expired, and if it expires, it will be recycled.
        long timeoutTime = System.currentTimeMillis() - item.getWriteTime();
        if (item.getExpireTime() * 1000 >= timeoutTime) {
            return true;
        }
        // Perform expiration caching
        remove(key);
        return false;
    }

    /**
     * Remove key in cache
     * @param key
     * @return
     */
    @Override
    public boolean remove(String key) {
        cacheGlobal.cacheConcurrentHashMap.remove(key);
        cacheGlobal.getRemovalListeners().forEach(x -> {
            x.onRemoval(key);
        });
        return true;
    }

    public CacheGlobal getCacheGlobal() {
        return cacheGlobal;
    }

    public CacheUtils registryAddListener(AddListener addListener) {
        this.cacheGlobal.addAddListener(addListener);
        return this;
    }

    public CacheUtils registryRefreshListener(RefreshListener refreshListener, int time) {
        Thread refreshThread = new Thread(new RefreshThread(refreshListener, time));
        refreshThread.start();
        return this;
    }

    public CacheUtils registryRemoveListener(RemovalListener removalListener) {
        this.cacheGlobal.addRemovalListener(removalListener);
        return this;
    }

    public CacheUtils registryReadListener(ReadListener readListener) {
        this.cacheGlobal.addReadListener(readListener);
        return this;
    }

    public int size() {
        return cacheGlobal.cacheConcurrentHashMap.size();
    }


    public CacheUtils maximumSize(int maximumSize) {
        this.cacheGlobal.maximumSize(maximumSize);
        return this;
    }

}
