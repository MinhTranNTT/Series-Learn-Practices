package com.example.guavacachedemo01.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class TestOther01 {
    @Test
    public void guavaCacheTest001(){
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(2)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key+" Really calculated！");
                        return "cache-"+key;
                    }
                });

        System.out.println(loadingCache.getUnchecked("key1"));
        System.out.println(loadingCache.getUnchecked("key1"));

        System.out.println(loadingCache.getUnchecked("key2"));
        System.out.println(loadingCache.getUnchecked("key2"));
    }

    @Test
    public void testCallable() throws ExecutionException {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        Object cacheKey1 = cache.get("key1", () -> {
            System.out.println("key1 actually calculated");
            return "key1 calculation method 1";
        });
        System.out.println(cacheKey1);

        cacheKey1 = cache.get("key1",()->{
            System.out.println("key1 actually calculated");
            return "key1 calculation method 1";
        });
        System.out.println(cacheKey1);

        Object cacheKey2 = cache.get("key2", () -> {
            System.out.println("key1 actually calculated");
            return "key1 calculation method 2";
        });
        System.out.println(cacheKey2);

        cacheKey2 = cache.get("key2",()->{
            System.out.println("key1 actually calculated");
            return "key1 calculation method 2";
        });
        System.out.println(cacheKey2);
    }

    @Test
    public void testDirectInsert() throws ExecutionException {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("key1","cache-key1");
        System.out.println(cache.get("key1",()->"callable cache-key1"));
    }

    @Test
    public void testSizeBasedEviction(){
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key+"Really calculated");
                        return "cached-" + key;
                    }
                });

        System.out.println("first visit");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("second visit");
        loadingCache.getUnchecked("key1");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key3");

        System.out.println("Start culling");
        loadingCache.getUnchecked("key4");

        System.out.println("Third visit");
        loadingCache.getUnchecked("key3");
        loadingCache.getUnchecked("key2");
        loadingCache.getUnchecked("key1");
    }


}
