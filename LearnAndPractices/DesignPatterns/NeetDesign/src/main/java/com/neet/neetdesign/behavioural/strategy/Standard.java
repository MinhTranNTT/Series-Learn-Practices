package com.neet.neetdesign.behavioural.strategy;

public class Standard implements Openable {
    @Override
    public void open() {
        System.out.println("Pushing door open");
    }

    @Override
    public void close() {
        System.out.println("Pulling door closed");
    }
}
