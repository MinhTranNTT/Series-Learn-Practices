package com.practices.test01;

import java.util.concurrent.locks.ReentrantLock;

public class TestTryLock {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main1(String[] args) {
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired. Performing some work.");
            } finally {
                lock.unlock();
                System.out.println("Lock released.");
            }
        } else {
            System.out.println("Lock not acquired. Do something else.");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1 acquired the lock.");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Thread 1 released the lock.");
            }
        }).start();

        new Thread(() -> {
            boolean lockAcquired = lock.tryLock();
            try {
                if (lockAcquired) {
                    System.out.println("Thread 2 acquired the lock.");
                } else {
                    System.out.println("Thread 2 couldn't acquire the lock.");
                }
            } finally {
                if (lockAcquired) {
                    lock.unlock();
                    System.out.println("Thread 2 released the lock.");
                }
            }
        }).start();
    }
}
