package com.neet.neetdesign.behavioural.strategy;

public class Password implements Lockable {
    @Override
    public void lock() {
        System.out.print("Door locked using password!");
    }

    @Override
    public void unlock() {
        System.out.print("Door unlocked using password!");
    }
}
