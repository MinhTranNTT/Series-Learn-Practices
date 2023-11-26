package com.practices.bestpractices.issue06;

public class ActionHandler3 extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if ("action3".equals(actionCode)) {
            doAction3();
        } else {
            successor.doHandler(actionCode);
        }
    }

    private void doAction3() {
        System.out.println("DoAction3");
    }
}
