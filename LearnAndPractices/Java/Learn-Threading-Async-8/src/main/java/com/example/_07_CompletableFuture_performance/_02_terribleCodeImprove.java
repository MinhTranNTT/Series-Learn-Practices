package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class _02_terribleCodeImprove {
    public static void main(String[] args) {

        ThreadUtils.printTimeAndThread("Xiaobai and his friends go to the restaurant to order");
        long startTime = System.currentTimeMillis();
        // a la carte
        ArrayList<Dish> dishes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Dish dish = new Dish("vegetable" + i, 1);
            dishes.add(dish);
        }
        // cook
        ArrayList<CompletableFuture> cfList = new ArrayList<>();
        for (Dish dish : dishes) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> dish.make());
            cfList.add(cf);
        }
        // wait for all tasks to complete
        CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()])).join();

        ThreadUtils.printTimeAndThread("The dishes are ready, let's serve" + (System.currentTimeMillis() - startTime));

    }
}
