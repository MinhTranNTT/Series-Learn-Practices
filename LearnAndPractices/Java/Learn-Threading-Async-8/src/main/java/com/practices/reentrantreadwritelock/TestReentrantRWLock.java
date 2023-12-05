package com.practices.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantRWLock {
    private static ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> read(), "Thread1").start();
        new Thread(() -> read(), "Thread2").start();
        new Thread(() -> write(), "Thread3").start();
        new Thread(() -> write(), "Thread4").start();

    }

    public static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Obtain the read lock and start execution");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " Release read lock");
        }
    }

    // Define a method for write operations
    public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Obtain the write lock and start execution");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " Release write lock");
        }
    }

}


