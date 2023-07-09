package com.example._08_Interrupt;



import com.example.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class _05_QAndA {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            ThreadUtils.printTimeAndThread("start sleeping");
            forceSleep(3);
            ThreadUtils.printTimeAndThread("end sleep");
        });
        thread.start();
        thread.interrupt();
    }

    @SuppressWarnings("BusyWait")
    public static void forceSleep(int second) {
        long startTime = System.currentTimeMillis();
        long sleepMills = TimeUnit.SECONDS.toMillis(second);

        while ((startTime + sleepMills) > System.currentTimeMillis()) {
            long sleepTime = startTime + sleepMills - System.currentTimeMillis();
            if (sleepTime <= 0) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
