package com.parallelstepbystepcn.clientcn;

import com.parallelstepbystepcn.util.CommonUtilCn;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompareParallelCompletableFuture {
    public static void main1(String[] args) {

        // execute main thread
        IntStream intStream = IntStream.range(0, 10);
        List<MyTask> tasks = intStream.mapToObj(item -> {
            return new MyTask(1);
        }).collect(Collectors.toList());

        long start = System.currentTimeMillis();
        // main
//        List<Integer> result = tasks.stream().map(myTask -> {
//            return myTask.doWork();
//        }).collect(Collectors.toList());

        List<Integer> result = tasks.parallelStream().map(myTask -> {
            return myTask.doWork();
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        double costTime = (end - start) / 1000.0;

        System.out.printf("processed %d tasks cost %.2f second",tasks.size(),costTime);
    }

    public static void main3(String[] args) {
       IntStream intStream = IntStream.range(0, 10);
        List<MyTask> tasks = intStream.mapToObj(item -> {
            return new MyTask(1);
        }).collect(Collectors.toList());

        long start = System.currentTimeMillis();
        List<CompletableFuture<Integer>> futures = tasks.stream().map(myTask -> {
            return CompletableFuture.supplyAsync(() -> {
                return myTask.doWork();
            });
        }).collect(Collectors.toList());

        List<Integer> results = futures.stream().map(future -> {
            return future.join();
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("processed %d tasks cost %.2f second",tasks.size(),costTime);
    }

    public static void main(String[] args) {
        IntStream intStream = IntStream.range(0, 10);
        List<MyTask> tasks = intStream.mapToObj(item -> {
            return new MyTask(1);
        }).collect(Collectors.toList());

        final int N_CPU = Runtime.getRuntime().availableProcessors();
        System.out.println("N_CPU: " + N_CPU);
        // Set the number of thread pools to a minimum of 10 and a maximum of 16
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), N_CPU * 2));

        long start = System.currentTimeMillis();
        List<CompletableFuture<Integer>> futures = tasks.stream().map(myTask -> {
            return CompletableFuture.supplyAsync(() -> {
                return myTask.doWork();
            },executor);
        }).collect(Collectors.toList());

        // step 3: When all tasks are completed,
        // obtain the execution results of each asynchronous task and store them in the List collection
        List<Integer> results = futures.stream().map(future -> {
            return future.join();
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("processed %d tasks cost %.2f second",tasks.size(),costTime);
        // 关闭线程池
        executor.shutdown();
    }
}

class MyTask {
    private int duration;

    public MyTask(int duration) {
        this.duration = duration;
    }

    public int doWork() {
        CommonUtilCn.printThreadLog("doWork");
        CommonUtilCn.sleepSecond(duration);
        return duration;
    }
}