package com.example.guavacachedemo01.guava;

import com.example.guavacachedemo01.pojo.User;
import com.google.common.cache.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test02 {
    /**
     * Use guava's cache alone. Guava's cache is divided into two types:
     * Type 1: Cache<---LoadingCache<---com.google.common.cache.LocalCache.LocalLoadingCache
     * Features: If the value cannot be obtained from the cache, it will be loaded according to the specified loader and
     * automatically placed in the cache after loading.
     * Type 2: Cache<---com.google.common.cache.LocalCache.LocalManualCache
     * Features: similar to ehcache
     */
    @Test
    public void test1() throws InterruptedException {
        LoadingCache<Long, User> loadingCache = CacheBuilder.newBuilder()
                // Specify concurrency level
                .concurrencyLevel(8)
                // Initialize the size and use concurrencyLevel to do segmentation locks
                .initialCapacity(60)
                // The maximum number of elements that can be placed in the cache
                .maximumSize(10)
                // Calculated from writing, expires in 10s
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // Statistical hit rate
                .recordStats()
                // The elements in the cache will be automatically called back here after they are evicted.
                .removalListener((RemovalListener<Long, User>) notification -> {
                    Long key = notification.getKey();
                    RemovalCause cause = notification.getCause();
                    System.out.println("key:" + key + "Removed from cache, reason:" + cause);
                })
                // If the value cannot be obtained from the cache, it will be called back here.
                .build(new CacheLoader<Long, User>() {
                    // key: loadingCache.get(k) will not be able to obtain the k passed in the future
                    @Override
                    public User load(Long key) {
                        // You can load data here
                        System.out.println("Load from storage");
                        User user = new User();
                        user.setId(key);
                        user.setFirstname("http://www.google.com" + key);
                        return user;
                    }
                });

        for (long i = 0; i < 10; i++) {
            // get method throws exception
//            User user = loadingCache.get(i);
            User user = loadingCache.getUnchecked(999L);
            System.out.println(user);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(loadingCache.stats().toString());
    }

    @Test
    void test03() {
        Cache<Long, User> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        List<User> userList = getListUser();

        for (User user : userList) {
            loadingCache.put(user.getId(), user);
        }

        for (long i = 1; i <= userList.size(); i++) {
            User user = loadingCache.getIfPresent(i);
            System.out.println("User from cache: " + user);
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (long i = 1; i <= userList.size(); i++) {
            User user = loadingCache.getIfPresent(i);
            System.out.println("User from cache after expiration: " + user);
        }
    }

    private List<User> getListUser() {
        User user1= User.builder().id(1L).firstname("A").build();
        User user2= User.builder().id(2L).firstname("B").build();
        User user3= User.builder().id(3L).firstname("C").build();
        User user4= User.builder().id(4L).firstname("D").build();
        User user5= User.builder().id(5L).firstname("E").build();

        List<User> list = List.of(user1,user2,user3,user4,user5);
        return list;
    }
}
