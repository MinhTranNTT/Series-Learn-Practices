package com.jiashn.scheduleTask.practices.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleAtFixedRateDemo implements Runnable {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
                new ScheduleAtFixedRateDemo(),
                0,
                // 2000,
                1000,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void run() {
        log.warn(new Date() + " : Task ScheduleAtFixedRateDemo is executed。");
        try {
            Thread.sleep(5000L);
            // Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
