package com.cj.concurrencystep.step17cache.core;

import com.cj.concurrencystep.step17cache.listener.AddListener;
import com.cj.concurrencystep.step17cache.listener.ReadListener;
import com.cj.concurrencystep.step17cache.listener.RemovalListener;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class CacheGlobal {
    public ConcurrentHashMap<String, MyCache> cacheConcurrentHashMap = new ConcurrentHashMap<String, MyCache>();

    /**
     * Get default cache lasts for 5 second
     * @return
     */
    public static CacheGlobal DEFAULT_CACHE() {
        return new CacheGlobal(5, true);
    }

    /**
     * How often to check cache expiration (unit: seconds)
     */
    private int checkTime;

    /**
     * Whether it is necessary to support cleaning
     */
    private boolean needClean;

    /**
     * The maximum number that can be stored in the cache. If it is reached, it needs to be recycled.
     */
    private int maximumSize;

    /**
     * Listener for monitoring key
     * */
    private List<AddListener> addListeners = new ArrayList<>();
    private List<RemovalListener> removalListeners = new ArrayList<>();
    private List<ReadListener> readListeners = new ArrayList<>();

    public CacheGlobal(int checkTime, boolean needClean) {
        this.checkTime = checkTime;
        this.needClean = needClean;
    }

    public CacheGlobal maximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
        return this;
    }

    public CacheGlobal addAddListener(AddListener addListener) {
        this.addListeners.add(addListener);
        return this;
    }

    public CacheGlobal addReadListener(ReadListener readListener) {
        this.readListeners.add(readListener);
        return this;
    }

    public CacheGlobal addRemovalListener(RemovalListener removalListener) {
        this.removalListeners.add(removalListener);
        return this;
    }

}
