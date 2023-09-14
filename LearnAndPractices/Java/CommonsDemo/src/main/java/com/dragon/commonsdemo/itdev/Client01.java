package com.dragon.commonsdemo.itdev;

import java.util.concurrent.atomic.AtomicInteger;

public class Client01 {
//    public static int num = 0;
    static AtomicInteger num = new AtomicInteger(0);
    /*public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (Client01.class) {
                        while (num < 5) {
                            System.out.println("thread name: " + Thread.currentThread().getName() + ":" + num++);
                        }
                    }
                }
            });
            thread.start();
        }
    }*/

    /*public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (num.get() < 5) {
                        System.out.println("thread name: " + Thread.currentThread().getName() + ":" + num.incrementAndGet());
                    }
                }
            });
            thread.start();
        }
    }*/

    /*public static int num2 = 0;
    public static void main(String[] args) {
        final Object lock = new Object();

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        while (num2 < 5) {
                            System.out.println("Thread name: " + Thread.currentThread().getName() + ":" + num2++);
                            lock.notify(); // Thông báo cho luồng khác để chạy
                            try {
                                lock.wait(); // Chờ cho lần lượt thực thi
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            thread.start();
        }
    }*/
}
