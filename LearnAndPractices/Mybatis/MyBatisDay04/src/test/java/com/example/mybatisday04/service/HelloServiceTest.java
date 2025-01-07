package com.example.mybatisday04.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class HelloServiceTest {

    @Test
    public void testCaseHashSet() {
        HashSet<String> map = new HashSet<>();
        map.add("A");
        map.add("B");
        map.add("C");
        map.add("A");
        System.out.println("map = " + map);

        // map = [A, B, C]
    }

    @Test
    public void testCaseHashSet1() {
        
    }

}
