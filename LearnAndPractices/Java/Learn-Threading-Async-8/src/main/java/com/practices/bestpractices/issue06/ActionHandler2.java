package com.practices.bestpractices.issue06;

public class ActionHandler2 extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if ("action2".equals(actionCode)) {
            doAction2();
        } else {
            successor.doHandler(actionCode);
        }
    }

    private void doAction2() {
        System.out.println("DoAction2");
    }
}
