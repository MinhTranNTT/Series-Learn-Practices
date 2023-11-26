package com.practices.bestpractices.issue06;

public class ActionHandler1 extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if ("action1".equals(actionCode)) {
            doAction1();
        } else {
            successor.doHandler(actionCode);
        }
    }

    private void doAction1() {
        System.out.println("DoAction1");
    }
}
