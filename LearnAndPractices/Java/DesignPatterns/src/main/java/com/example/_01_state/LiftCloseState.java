package com.example._01_state;

public class LiftCloseState extends LiftState {
    @Override
    void open() {
        super.context.setState(Context.OPEN_STATE);
        super.context.getState().open();
    }

    @Override
    void close() {
        System.out.println("Close State");
    }

    @Override
    void run() {
        super.context.setState(Context.RUN_STATE);
        super.context.getState().run();
    }

    @Override
    void stop() {

    }
}
