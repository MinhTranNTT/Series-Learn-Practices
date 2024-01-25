package com.coding.schedulee;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // execute with delay time and only 1 time.
        // timer.schedule(new NewTimer("Demo01"), 1000L);

        // execute with delay time and execute again each 5000 mils~
        // timer.schedule(new NewTimer("Demo01"), 1000L, 5000L);
        // timer.schedule(new NewTimer("Demo01"), 1000L, 2000L);

        // will be executed the first time after 2 seconds and will then run continuously every 1 second
        // timer.scheduleAtFixedRate(new NewTimer("FixedRateDemo"),2000L,1000L);

        // timer.scheduleAtFixedRate(new NewTimer("FixedRateDemo"),0L,5000L);
        timer.schedule(new NewTimer("Demo01"), 0L, 5000L);
    }
}

@Slf4j
class NewTimer extends TimerTask {
    private String taskName;
    public NewTimer(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run() {
        log.info(new Date() + " : Task " + taskName + " is executed.");
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
