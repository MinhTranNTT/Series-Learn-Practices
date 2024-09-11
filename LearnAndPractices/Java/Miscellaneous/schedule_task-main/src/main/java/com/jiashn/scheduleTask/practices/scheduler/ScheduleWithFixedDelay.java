package com.jiashn.scheduleTask.practices.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleWithFixedDelay implements Runnable {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                new ScheduleWithFixedDelay(),
                0,
                1000,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void run() {
        log.warn(new Date() + " : 任务「ScheduleAtFixedRateDemo」被执行。");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
