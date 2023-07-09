package com.example._08_Interrupt;


import com.example.ThreadUtils;

public class _04_BeforehandInterrupt {
    public static void main(String[] args) {

        Thread.currentThread().interrupt();

        try {
            ThreadUtils.printTimeAndThread("start sleeping");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("interruption occurred");
        }

        ThreadUtils.printTimeAndThread("end sleep");

    }
}
