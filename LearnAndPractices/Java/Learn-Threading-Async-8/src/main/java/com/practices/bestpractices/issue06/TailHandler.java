package com.practices.bestpractices.issue06;

public class TailHandler extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        // throw new RuntimeException("The current code[" + actionCode + "] has no specific Handler processing");
    }
}
