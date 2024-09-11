package com.neet.neetdesign.miscellaneous.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo03 {
    private static final int NUM_THREADS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> {
        System.out.println("All data loading threads have completed. Initiating further processing...");
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            new Thread(() -> {
                // Simulate data loading
                System.out.println("Thread " + threadId + " is loading data.");

                // Simulate data loading time
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread " + threadId + " has completed data loading.");

                try {
                    // Wait for all data loading threads to complete
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                // Perform further processing after data loading is complete
                System.out.println("Thread " + threadId + " is performing further processing.");
            }).start();
        }
    }
}
