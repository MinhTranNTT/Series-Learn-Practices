package com.example.testjar;

public class Init {
    public static void init() {
        try {
            System.out.println("Init AFTER MAIN");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
