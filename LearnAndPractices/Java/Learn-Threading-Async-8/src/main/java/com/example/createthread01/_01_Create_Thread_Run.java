package com.example.createthread01;

public class _01_Create_Thread_Run {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - Child");
            }
        };

        thread.start();
        System.out.println(Thread.currentThread().getName() + " - End Thread");
    }
}
