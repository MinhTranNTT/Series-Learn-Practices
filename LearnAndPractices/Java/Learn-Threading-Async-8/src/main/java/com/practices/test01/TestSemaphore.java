package com.practices.test01;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSemaphore {
    private static final Semaphore semaphore = new Semaphore(1);
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<Integer> data = getData(20);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < data.size() - 1; i++) {
            int value = data.get(i);
            executor.submit(() -> originValue(value, atomicInteger));
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    private static Integer originValue(Integer i, AtomicInteger atomic) {
        if (atomic.getAndIncrement() % 5 == 0) {
            try {
                semaphore.acquire();
                System.out.println();
                System.out.println("---------SLEEP------------");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
                System.out.println("---------END SLEEP------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
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
