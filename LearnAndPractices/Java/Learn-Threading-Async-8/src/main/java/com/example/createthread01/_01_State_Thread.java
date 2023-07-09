package com.example.createthread01;

import java.util.concurrent.TimeUnit;

public class _01_State_Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println("1 - " + thread.getState());
        thread.start();
        System.out.println("2 - " +thread.getState());
        TimeUnit.SECONDS.sleep(10);
        System.out.println("3 - " +thread.getState());
    }
}
