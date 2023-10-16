package com.cj.concurrencystep.step17cache.core;

import com.cj.concurrencystep.step17cache.listener.RefreshListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RefreshThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(RefreshThread.class);
    private RefreshListener refreshListener;
    private int time;

    public RefreshThread(RefreshListener refreshListener, int time) {
        this.refreshListener = refreshListener;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Callback function to refresh data
                refreshListener.doRefresh();
                logger.debug("[RefreshThread] refresh success");
                TimeUnit.SECONDS.sleep(time);
            } catch (Exception e) {
                logger.error("[RefreshThread] error is ", e);
            }
        }
    }
}
