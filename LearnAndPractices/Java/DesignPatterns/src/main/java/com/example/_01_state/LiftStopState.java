package com.example._01_state;

public class LiftStopState extends LiftState {
    @Override
    void open() {
        super.context.setState(Context.OPEN_STATE);
        super.context.getState().open();
    }

    @Override
    void close() {

    }

    @Override
    void run() {

    }

    @Override
    void stop() {
        System.out.println("Top State");
    }
}
