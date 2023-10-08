package com.practices.atomicc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic implements Runnable {
    AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        TestAtomic tAtomicTest = new TestAtomic();
        Thread t1 = new Thread(tAtomicTest);
        Thread t2 = new Thread(tAtomicTest);
        t1.start();
        t2.start();


    }

    @Override
    public void run() {
        for(int j = 1;j < 11; j++){
//            System.out.println(atomicInteger.getAndIncrement());
            System.out.println(atomicInteger.incrementAndGet());
        }
    }

    /*public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i = 1; i < 3; i++) {
            new Thread(() -> {
                for(int j = 1;j < 11; j++){
                    System.out.println(atomicInteger.getAndIncrement());
                }
            }, String.valueOf(i)).start();
        }
    }*/
}
