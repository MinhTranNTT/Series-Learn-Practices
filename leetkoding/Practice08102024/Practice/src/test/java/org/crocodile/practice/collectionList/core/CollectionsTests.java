package org.crocodile.practice.collectionList.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class CollectionsTests {
    @Test
    public void testCase01() {
        List<String> listA = new ArrayList<>();
        listA.add("Apple");
        listA.add("Banana");
        listA.add("Cherry");
        listA.add("Date");
        List<String> listB = new ArrayList<>();
        listB.add("Banana");
        listB.add("Date");
        listB.add("Fig");
        listB.add("Grape");
        listA.retainAll(listB);
        System.out.println("listA: " + listA);
        System.out.println("listB: " + listB);
        System.out.println("=============================================");
        listA.retainAll(listB);
        System.out.println(listA);
        System.out.println(listA.stream().filter(listB::contains).collect(Collectors.toList()));
        List<String> listC = listA.stream().filter(itemA -> listB.stream().anyMatch(itemB -> itemB.equals(itemA))).collect(Collectors.toList());
        System.out.println(listC);
    }
}
