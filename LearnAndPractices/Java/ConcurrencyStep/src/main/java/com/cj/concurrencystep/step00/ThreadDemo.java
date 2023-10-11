package com.cj.concurrencystep.step00;

import java.util.concurrent.CountDownLatch;

public class ThreadDemo {
    private static int j = 0;

    public static class IncrementTask implements Runnable {
        CountDownLatch start;
        CountDownLatch end;

        public IncrementTask(CountDownLatch start, CountDownLatch end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public void run() {
            try {
                start.await();
                for (int i = 0; i < 10; i++) {
                    j++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(100);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new IncrementTask(start, end));
            t.start();
        }
        start.countDown();
        end.await();
        System.out.println("result is :" + j);
    }
}
