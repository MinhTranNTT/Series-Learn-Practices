package com.cj.concurrencystep.step17cache.client;

import com.cj.concurrencystep.step17cache.core.CacheUtils;
import com.cj.concurrencystep.step17cache.listener.AddListener;
import com.cj.concurrencystep.step17cache.listener.ReadListener;
import com.cj.concurrencystep.step17cache.listener.RefreshListener;
import com.cj.concurrencystep.step17cache.listener.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TestCacheTest {
    private CacheUtils cache = null;

    @Test
    public void testExpire() throws InterruptedException {
        cache = new CacheUtils(-1, false);
        cache.put("key1", "value1", 2);
        System.out.println(cache.get("key1"));
        Thread.sleep(2500);
        // Thread.sleep(1900);
        System.out.println(cache.get("key1"));
    }

    @Test
    public void testExpireThread() throws InterruptedException {
        cache = new CacheUtils(5, true);
        // cache.put("key1", "value1", 2);
        cache.put("key1", "value1", 7);
        log.info("{}", cache.containKey("key1"));
        // Thread.sleep(4900);
        Thread.sleep(7000);
        log.info("{}", cache.containKey("key1"));
    }

    @Test
    public void testAddListener() throws InterruptedException {
        cache = new CacheUtils(5, true).registryAddListener(new AddListener() {
            @Override
            public void onAdd(String key, Object value) {
                System.out.println("key :" + key + " value :" + value);
            }
        });
        cache.put("key1", "value1", 3);
        Thread.sleep(100);
        System.out.println(cache.get("key1"));
    }

    @Test
    public void testRemoveListener() throws InterruptedException {
        cache = new CacheUtils(2, true).registryRemoveListener(new RemovalListener() {
            @Override
            public void onRemoval(String key) {
                System.out.println("key :" + key);
            }
        });
        cache.put("key1", "value1", 1);
        Thread.sleep(6000);
    }

    @Test
    public void testReadListener() throws InterruptedException {
        cache = new CacheUtils(5, true).registryReadListener(new ReadListener() {
            @Override
            public void onRead(String key, Object value) {
                System.out.println("read key :" + key + " value :" + value);
            }
        });
        cache.put("key1", "value1", 5);
        cache.get("key1");
        Thread.sleep(2000);
        System.out.println("after");
        cache.get("key1");
    }

    @Test
    public void testMaximumSizeCleanUp() throws InterruptedException {
        cache = new CacheUtils(5, true).registryRemoveListener(new RemovalListener() {
            @Override
            public void onRemoval(String key) {
                System.out.println("remove key:" + key);
            }
        }).maximumSize(4);
        cache.put("key1","value1",200);
        cache.put("key2","value2",200);
        cache.put("key3","value2",200);
        cache.put("key4","value2",200);
        cache.put("key5","value2",200);
        Thread.sleep(10000);
        System.out.println(cache.size());
    }

    @Test
    public void testRefreshListener() throws InterruptedException {
        cache = new CacheUtils(5, true);
        cache.registryRefreshListener(new RefreshListener() {
            @Override
            public void doRefresh() {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("k1", 1);
                tempMap.put("k2", 2);
                tempMap.put("k3", 3);
                for (String key : tempMap.keySet()) {
                    cache.put(key, tempMap.get(key), 100);
                }
            }
        },5);
        System.out.println(cache.size());
        Thread.sleep(6000);
        System.out.println(cache.size());
    }

    // Test using local cache component
    @Test
    public void testUseCache() throws InterruptedException {
        cache = new CacheUtils(5, true);
        cache.registryRefreshListener(new RefreshListener() {

            @Override
            public void doRefresh() {
                cache.clear();
                cache.put("key1", 1, 100);
                cache.put("key2", 2, 100);
                cache.put("key3", 3, 100);
            }

        },3).registryReadListener(new ReadListener() {
            @Override
            public void onRead(String key, Object value) {
                System.out.println("key :" + key + " value :" + value);
            }
        }).registryAddListener(new AddListener() {
            @Override
            public void onAdd(String key, Object value) {
                System.out.println("key :" + key + " value :" + value);
            }
        }).registryRemoveListener(new RemovalListener() {
            @Override
            public void onRemoval(String key) {
                System.out.println("remove key:" + key);

            }
        });
        Thread.sleep(7000);
        cache.put("key4",4,100);
        cache.put("key5",5,100);
        System.out.println(cache.size());
        System.out.println(cache.get("key4"));
    }
}