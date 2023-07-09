package com.example._08_Interrupt;



import com.example.ThreadUtils;

import java.util.Random;

public class _02_TwoCarCrossBridge {
    public static void main(String[] args) {

        Thread carTwo = new Thread(() -> {
            ThreadUtils.printTimeAndThread("Cardin 2 ready to cross the bridge");
            ThreadUtils.printTimeAndThread("Found that No. 1 is passing, and began to wait");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("Cardin 2 starts crossing the bridge");
            }
            ThreadUtils.printTimeAndThread("Cardin 2 has crossed the bridge");
        });


        Thread carOne = new Thread(() -> {
            ThreadUtils.printTimeAndThread("Cardin 1 starts to cross the bridge");
            int timeSpend = new Random().nextInt(500) + 1000;
            ThreadUtils.sleepMillis(timeSpend);
            ThreadUtils.printTimeAndThread("Carding No. 1 completed the bridge crossing time:" + timeSpend);
//            ThreadUtils.printTimeAndThread("Status of Cardin 2" + carTwo.getState());
            carTwo.interrupt();
        });

        carOne.start();
        carTwo.start();

    }
}
