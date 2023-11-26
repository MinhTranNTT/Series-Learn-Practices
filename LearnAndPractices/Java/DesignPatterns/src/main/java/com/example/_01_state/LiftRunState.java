package com.example._01_state;

public class LiftRunState extends LiftState {
    @Override
    void open() {

    }

    @Override
    void close() {

    }

    @Override
    void run() {
        System.out.println("Run State");
    }

    @Override
    void stop() {
        super.context.setState(Context.STOP_STATE);
        super.context.getState().stop();
    }
}
