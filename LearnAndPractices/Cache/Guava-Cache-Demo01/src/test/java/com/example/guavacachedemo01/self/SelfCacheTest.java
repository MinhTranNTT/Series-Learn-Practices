package com.example.guavacachedemo01.self;

import com.example.guavacachedemo01.pojo.Dept;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class SelfCacheTest {

    @Test
    void test001() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.put("a","a_value");
        linkedHashMap.put("b","b_value");
        linkedHashMap.put("c","c_value");
        System.out.println(linkedHashMap);
        String b = linkedHashMap.get("b");
        linkedHashMap.get("b: " + b);

        System.out.println(linkedHashMap);
    }

    /**
     * Test the memory sensitivity of soft references
     * -ea -Xms20M -Xmx20M -XX:+PrintGCDetails
     *
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        Cache cache = new CacheVersionFinal(999);
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println("Put in the " + i + " indivual");
            Dept dept = new Dept((long) i);
            cache.put(dept.getId(), dept);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * Test strong reference oom
     * -ea -Xms20M -Xmx20M -XX:+PrintGCDetails
     */
    @Test
    public void test2() throws InterruptedException {
        Cache cache = new CacheVersion1(9990);
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println("Put in the " + i + " indivual");
            Dept dept = new Dept((long) i);
            cache.put(dept.getId(), dept);
//            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void testCacheVersion2() {
        Cache cache = new CacheVersion2(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        // key:a,value:a_value-key:b,value:b_value-key:c,value:c_value-
        System.out.println(cache);
        // Visit the demo and change the order
        String bValue = (String) cache.get("b");
        System.out.println("bValue:" + bValue);
        // key:a,value:a_value-key:c,value:c_value-key:b,value:b_value-
        System.out.println(cache);
        // Test if it will be removed
        cache.put("d", "d_value");
        // key:c,value:c_value-key:b,value:b_value-key:d,value:d_value-
        System.out.println(cache);
    }

    @Test
    public void testCacheVersion1() {
        Cache cache = new CacheVersion1(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        // {a=a_value, b=b_value, c=c_value}
        System.out.println(cache);
        // Visit the demo and change the order
        String bValue = (String) cache.get("b");
        System.out.println("bValue:" + bValue);
        // {a=a_value, c=c_value, b=b_value}
        System.out.println(cache);
        // Test if it will be removed
        cache.put("d", "d_value");
        // CacheVersion1{capacity=3, internalCache={c=c_value, b=b_value, d=d_value}}
        System.out.println(cache);
    }

    /**
     * LRU:least recently used
     * 要实现基于LRU算法的溢出驱逐：
     * 1. 按访问时间来排序(a--b--c)
     * 2. 移除排在最前面(排序规则)的元素
     */
//    @Test
//    public void test1() {
//        LinkedHashMap<String, String> linkedHashMap =
//                new LinkedHashMap<>(16, 0.75f, true);
//        linkedHashMap.put("a", "a_value");
//        linkedHashMap.put("b", "b_value");
//        linkedHashMap.put("c", "c_value");
//        // {a=a_value, b=b_value, c=c_value}
//        System.out.println(linkedHashMap);
//        String bValue = linkedHashMap.get("b");
//        System.out.println("b的值:" + bValue);
//        // {a=a_value, c=c_value, b=b_value}
//        System.out.println(linkedHashMap);
//    }
}
