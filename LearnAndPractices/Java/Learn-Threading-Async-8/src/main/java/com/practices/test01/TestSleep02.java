package com.practices.test01;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSleep02 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<Integer> data = getData(24);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // List<List<Integer>> partition = Lists.partition(data, 5);
        List<CompletableFuture<Integer>> collect = data.stream()
                // .flatMap(Collection::stream)
                .map(item -> CompletableFuture.supplyAsync(() -> originValue(item, atomicInteger), executor))
                .collect(Collectors.toList());


    }

    private static Integer originValue(Integer i, AtomicInteger atomic) {
        try {
            lock.lock();
            if (atomic.getAndIncrement() % 5 == 0) {
                TimeUnit.SECONDS.sleep(5);
                System.out.println();
                System.out.println("---------SLEEP------------");
                System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
                System.out.println("---------END SLEEP------------");

            } else {
                System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
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
