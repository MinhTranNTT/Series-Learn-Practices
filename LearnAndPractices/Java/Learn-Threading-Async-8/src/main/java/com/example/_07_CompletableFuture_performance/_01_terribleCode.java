package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class _01_terribleCode {
    public static void main(String[] args) {

        ThreadUtils.printTimeAndThread("Xiaobai and his friends go to the restaurant to order");
        long startTime = System.currentTimeMillis();

        ArrayList<Dish> dishes = new ArrayList<>();
        // a la carte
        for (int i = 1; i <= 10; i++) {
            Dish dish = new Dish("vegetable" + i, 1);
            dishes.add(dish);
        }
        // cook
        for (Dish dish : dishes) {
            CompletableFuture.runAsync(() -> dish.make()).join();
        }

        ThreadUtils.printTimeAndThread("The dishes are ready, let's serve" + (System.currentTimeMillis() - startTime));

    }
}
