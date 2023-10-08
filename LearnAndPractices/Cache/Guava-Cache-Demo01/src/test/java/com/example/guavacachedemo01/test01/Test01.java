package com.example.guavacachedemo01.test01;

import com.example.guavacachedemo01.pojo.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Test01 {
    @Test
    void test01 () {
        String path = "https://www.google.com/";
        // Used to manage multiple Cache, user_cache, item_cache, store_cache
        CacheManager cacheManager = CacheManager.create();
        // Get all caches managed by CacheManager
        String[] cacheNames = cacheManager.getCacheNames();

        System.out.println("The names of all caches managed by CacheManager: " + Arrays.toString(cacheNames));

        // Get a specific Cache by cache name
        Cache userCache = cacheManager.getCache("user_cache");
        User user = new User();
        user.setId(1L);
        user.setFirstname("Coder");

        Element element = new Element(user.getId(), user);
        userCache.put(element);
        // Get the cached object by key
        Element resultEle = userCache.get(1L);
        System.out.println("resultEle obtained：" + resultEle);
        System.out.println("The value of resultEle obtained：" + resultEle.getObjectValue());

    }
}
