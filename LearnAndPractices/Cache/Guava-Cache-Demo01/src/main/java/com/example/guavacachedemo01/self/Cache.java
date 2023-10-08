package com.example.guavacachedemo01.self;

public interface Cache {
    void put(Object key, Object value);

    void remove(Object key);

    void clear();

    Object get(Object key);

    int size();
}
