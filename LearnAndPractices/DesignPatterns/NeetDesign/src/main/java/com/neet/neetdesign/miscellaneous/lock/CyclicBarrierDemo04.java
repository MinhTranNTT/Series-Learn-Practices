package com.neet.neetdesign.miscellaneous.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo04 {
    private static final int NUM_THREADS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> {
        System.out.println("All threads have reached the barrier. Let's continue!");
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(() -> {
                try {
                    // Perform individual tasks
                    System.out.println(Thread.currentThread().getName() + " is performing individual tasks.");

                    // Wait for all threads to reach the barrier
                    barrier.await();

                    // Continue with collective tasks after reaching the barrier
                    System.out.println(Thread.currentThread().getName() + " is performing collective tasks.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
