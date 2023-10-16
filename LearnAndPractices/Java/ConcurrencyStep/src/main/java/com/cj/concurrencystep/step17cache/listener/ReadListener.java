package com.cj.concurrencystep.step17cache.listener;

@FunctionalInterface
public interface ReadListener {
    void onRead(String key, Object value);
}
