package com.jiashn.scheduleTask.practices.scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class Scheduler_01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new DoSomethingTimerTask("FixedRateDemo"), 2000L, 1000L);
    }
}

@Data
@AllArgsConstructor
@Slf4j
class DoSomethingTimerTask extends TimerTask {

    private String taskName;
    @Override
    public void run() {
        log.info(this.taskName + " run");
    }
}
