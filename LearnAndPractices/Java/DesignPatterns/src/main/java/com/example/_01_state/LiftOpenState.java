package com.example._01_state;

public class LiftOpenState extends LiftState {
    @Override
    void open() {
        System.out.println("Open State");
    }

    @Override
    void close() {
        super.context.setState(Context.CLOSE_STATE);
        super.context.getState().close();
    }

    @Override
    void run() {

    }

    @Override
    void stop() {

    }
}
