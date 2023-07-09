package com.example._08_Interrupt;



import com.example.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class _01_ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("1- " + thread.getState());
        thread.start();
        System.out.println("2- " + thread.getState());

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("generate interrupt" + e.getMessage());
        }

        System.out.println("3- " + thread.getState());
    }
}
