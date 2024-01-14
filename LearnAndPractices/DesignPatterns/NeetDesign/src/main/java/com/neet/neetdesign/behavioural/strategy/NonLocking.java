package com.neet.neetdesign.behavioural.strategy;

public class NonLocking implements Lockable {
    @Override
    public void lock() {
        System.out.println("Door does not lock - ignoring");
    }

    @Override
    public void unlock() {
        System.out.println("Door cannot unlock because it cannot lock");
    }
}
