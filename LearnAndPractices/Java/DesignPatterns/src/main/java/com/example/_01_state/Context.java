package com.example._01_state;

public class Context {
    private LiftState state;
    public static final LiftState OPEN_STATE = new LiftOpenState();
    public static final LiftState CLOSE_STATE = new LiftCloseState();
    public static final LiftState RUN_STATE = new LiftRunState();
    public static final LiftState STOP_STATE = new LiftStopState();

    public void setState(LiftState state) {
        this.state = state;
        this.state.setContext(this);
    }

    public LiftState getState() {
        return this.state;
    }

    public void open() {
        this.state.open();
    }

    public void close() {
        this.state.close();
    }

    public void run() {
        this.state.run();
    }

    public void stop() {
        this.state.stop();
    }
}
