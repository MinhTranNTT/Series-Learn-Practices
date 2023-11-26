package com.example._01_state;

public abstract class LiftState {
    protected Context context;
    abstract void open();
    abstract void close();
    abstract void run();
    abstract void stop();

    public void setContext(Context context) {
        this.context = context;
    }

}
