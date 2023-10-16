package com.cj.concurrencystep.step17cache.core;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class MyCache {
    private Object key;
    private Object value;
    private long lastTime;
    private long writeTime;
    private long expireTime;
    private boolean delAfterRead;
    private AtomicLong hitCount = new AtomicLong(0);
}
