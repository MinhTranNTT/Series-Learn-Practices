package com.example._01_state;

public class TestContext {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(Context.CLOSE_STATE);

        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
