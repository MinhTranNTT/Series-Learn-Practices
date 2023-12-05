package com.practices.test01;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSleep {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<Integer> data = getData(24);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<CompletableFuture<Integer>> collect = data.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> originValue(item, atomicInteger), executor))
                .collect(Collectors.toList());

        // for (int i = 0; i < data.size() - 1; i++) {
        //     originValue(data.get(i), atomicInteger);
        // }

    }

    private static Integer originValue(Integer i, AtomicInteger atomic) {
        if (atomic.getAndIncrement() % 5 == 0) {
            // lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println();
                System.out.println("---------SLEEP------------");
                System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
                System.out.println("---------END SLEEP------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            // } finally {
            //     lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
        }
        return i;
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
