package com.neet.neetdesign.validator.partitions;

import com.google.common.collect.Lists;

import java.util.List;

public class ClientPartition {
    public static void main(String[] args) {
        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> partition = Lists.partition(integers, 3);
        partition.forEach(s -> System.out.printf(s + " "));

        // System.out.println(partition.get(8));
        System.out.println(partition.size());

    }
}
