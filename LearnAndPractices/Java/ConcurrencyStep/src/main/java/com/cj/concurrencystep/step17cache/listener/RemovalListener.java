package com.cj.concurrencystep.step17cache.listener;

@FunctionalInterface
public interface RemovalListener {
    void onRemoval(String key);
}
