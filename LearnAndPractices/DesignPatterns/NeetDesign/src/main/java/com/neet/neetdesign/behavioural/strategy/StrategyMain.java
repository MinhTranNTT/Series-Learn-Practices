package com.neet.neetdesign.behavioural.strategy;

public class StrategyMain {
    public static void main(String[] args) {
        Door c;
        c = new ClosetDoor();
        c.setOpenBehavior(new Standard());
        c.setLockBehavior(new NonLocking());

        c.performOpen();
        c.performClose();

        c.performLock();
        c.performUnlock();

        // upgrade the door to a password protected door
        c.setLockBehavior(new Password());
        c.performLock();
        c.performUnlock();

    }
}
