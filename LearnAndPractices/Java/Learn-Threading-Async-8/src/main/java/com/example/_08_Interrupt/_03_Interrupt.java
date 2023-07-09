package com.example._08_Interrupt;


import com.example.ThreadUtils;

public class _03_Interrupt {
    public static void main(String[] args) {
        Thread carOne = new Thread(() -> {
            long startMills = System.currentTimeMillis();
            while (System.currentTimeMillis() - startMills < 3) {
//                if (Thread.currentThread().isInterrupted()) {
                if (Thread.interrupted()) {
                    ThreadUtils.printTimeAndThread("1 meter to the left");
                } else {
                    ThreadUtils.printTimeAndThread("1 meter ahead");
                }
            }
        });

        carOne.start();

        ThreadUtils.sleepMillis(1);
        carOne.interrupt();

    }
}
