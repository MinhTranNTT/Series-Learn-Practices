package com.practices.bestpractices.issue06;

public class HeadHandler extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if (actionCode == null || actionCode.length() == 0) {
            throw new RuntimeException("actionCode is blank");
        }
        successor.doHandler(actionCode);
    }
}
