package com.dragon.commonsdemo.itdev;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CollectionUtilsTest {
    @Test
    void test1() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("1");
        list2.add("2");

        System.out.println("list1" + list1);
        System.out.println("list2" + list2);
        // intersection: giao diem
        System.out.println(CollectionUtils.intersection(list1, list2));

        // union: hop
        System.out.println(CollectionUtils.union(list1, list2));

        System.out.println(CollectionUtils.subtract(list1, list2));
        System.out.println(CollectionUtils.subtract(list2, list1));
    }
}
