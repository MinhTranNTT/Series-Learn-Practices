package com.jiashn.scheduleTask.practices.interfacetest;

public class Duck extends Animal implements IFlying, IRunning, ISwimming {

    public Duck(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println("Duck fly()");
    }

    @Override
    public void run() {
        System.out.println("Duck run()");
    }

    @Override
    public void swimming() {
        System.out.println("Duck swimming()");
    }
}
