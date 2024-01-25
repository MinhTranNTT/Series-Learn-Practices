package com.coding.schedulee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicSchedule {
    public static void main(String[] args) {
        // run in a second
        final long timeInterval = 5000;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info("Hello !!");
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
}
