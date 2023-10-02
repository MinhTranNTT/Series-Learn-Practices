package com.practices;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main01 {
    public static void main1(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Integer> data = getData(100); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        List<List<Integer>> partition = Lists.partition(data, 10);
        List<List<Integer>> lists = Collections.synchronizedList(partition);
        // [1, 2, 3] ; [4, 5, 6] ; [7, 8, 9], [10]

        /* WAY 1
        List<CompletableFuture<List<Integer>>> future = partition.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> doubleValue(item), executor))
                .collect(Collectors.toList());
        List<Integer> collect = future.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
         */
        List<CompletableFuture<List<Integer>>> collect = partition.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> doubleValue(item), executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(collect.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Integer>> combineFuture = allOf.thenApply(future -> collect.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList()));
        List<Integer> join = combineFuture.join();

        executor.shutdownNow();

//        System.out.println("Final: " + collect);
        System.out.println("Final: " + join);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Integer> data = getData(10); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        List<List<Integer>> partition = Lists.partition(data, 3);
        List<List<Integer>> lists = Collections.synchronizedList(partition);
        // [1, 2, 3] ; [4, 5, 6] ; [7, 8, 9], [10]

        List<CompletableFuture<List<Integer>>> collect = partition.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> doubleValue(item), executor))
                .collect(Collectors.toList());

        List<CompletableFuture<List<Integer>>> collectTriple = partition.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> tripleValue(item), executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(collect.toArray(new CompletableFuture[0]));
        CompletableFuture<Void> allOfTriple = CompletableFuture.allOf(collectTriple.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Integer>> combineFuture = allOf.thenCombine(allOfTriple, (iDouble, iTriple) -> {
            List<Integer> doubleResult = collect.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            List<Integer> trippleResult = collectTriple.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            List<Integer> combinedResult = new ArrayList<>(doubleResult);
            combinedResult.addAll(trippleResult);
            Collections.sort(combinedResult);
            return combinedResult;
        });
        List<Integer> join = combineFuture.join();

        executor.shutdownNow();

        System.out.println("Final: " + join);
    }

    private static List<Integer> doubleValue(List<Integer> list) {
        System.out.println("---------------------------");
        System.out.println("Origin" + list);
        List<Integer> collect = list.stream().map(i -> i * 5).collect(Collectors.toList());
        System.out.println("Transfer x5: " + collect);
        System.out.println("---------------------------");
        return collect;
    }

    private static List<Integer> tripleValue(List<Integer> list) {
        List<Integer> collect = list.stream().map(i -> i * 3).collect(Collectors.toList());
        System.out.println("Triple x3: " + collect);
        return collect;
    }

    private static List<Integer> getData(int n) {
        List<Integer> randomList = IntStream.rangeClosed(1, n)
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Origin:" + randomList);
        return randomList;
    }
}
