package com.neet.neetdesign.behavioural.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public abstract class Door {
    private Lockable lockBehavior;
    private Openable openBehavior;

    public void setLockBehavior(Lockable lockable) {
        this.lockBehavior = lockable;
    }

    public void setOpenBehavior(Openable openable) {
        this.openBehavior = openable;
    }

    public void performLock() {
        this.lockBehavior.lock();
    }

    public void performUnlock() {
        this.lockBehavior.unlock();
    }

    public void performOpen() {
        this.openBehavior.open();
    }

    public void performClose() {
        this.openBehavior.close();
    }

    public float getDimensions() {
        return 10f;
    }
}
