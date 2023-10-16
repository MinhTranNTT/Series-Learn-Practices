package com.cj.concurrencystep.step17cache.listener;

@FunctionalInterface
public interface AddListener {
    void onAdd(String key, Object value);
}
