package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.concurrent.TimeUnit;


public class Dish {
    // dish name
    private String name;
    // production time (seconds)
    private Integer productionTime;

    public Dish(String name, Integer productionTime) {
        this.name = name;
        this.productionTime = productionTime;
    }

    // cook
    public void make() {
        ThreadUtils.sleepMillis(TimeUnit.SECONDS.toMillis(this.productionTime));
        ThreadUtils.printTimeAndThread(this.name + " Finished, come and eat me");
    }

    // cook
    public void makeUseCPU() {
        ThreadUtils.printTimeAndThread(this.name + " Finished, come and eat me" + compute());
    }

    /**
     * Used to simulate a time-consuming operation of 1 second
     * If your computer is stronger, you can increase the number of cycles, otherwise, you need to reduce the number of cycles
     */
    private static long compute() {
        long startTime = System.currentTimeMillis();
        long result = 0;
        // It's just used to simulate time-consuming operations, it doesn't make any sense
        for (int i = 0; i < Integer.MAX_VALUE / 3; i++) {
            result += i * i % 3;
        }
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        System.out.println(compute());
    }


}
