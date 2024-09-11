package com.neet.neetdesign.miscellaneous.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int NUM_SUBTASKS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_SUBTASKS, () -> {
        System.out.println("All subtasks have been completed. Merging results...");
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_SUBTASKS; i++) {
            final int subtaskId = i;
            new Thread(() -> {
                // Perform individual subtask
                System.out.println("Subtask " + subtaskId + " is processing.");

                // Simulate some computation for the subtask
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Subtask " + subtaskId + " has completed.");

                try {
                    // Wait for all subtasks to complete
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
