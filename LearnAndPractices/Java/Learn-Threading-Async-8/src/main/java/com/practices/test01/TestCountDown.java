package com.practices.test01;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestCountDown {
    private static final Semaphore semaphore = new Semaphore(1);
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<Integer> data = getData(20);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < data.size(); i += 5) {
            int endIndex = Math.min(i + 5, data.size());
            List<Integer> chunk = data.subList(i, endIndex);
            scheduler.schedule(() -> processChunk(chunk, atomicInteger), i / 5 * 5, TimeUnit.SECONDS);
        }

        scheduler.shutdown();
        scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    private static void processChunk(List<Integer> chunk, AtomicInteger atomic) {
        try {
            semaphore.acquire();
            System.out.println();
            System.out.println("---------SLEEP------------");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("---------END SLEEP------------");
            semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        for (Integer i : chunk) {
            System.out.println(Thread.currentThread().getName() + " VALUE: " + i);
        }
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
