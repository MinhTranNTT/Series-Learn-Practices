package com.practices.bestpractices.issue06;

public class ActionHandler5 extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if ("action5".equals(actionCode)) {
            doAction5();
        } else {
            successor.doHandler(actionCode);
        }
    }

    private void doAction5() {
        System.out.println("DoAction5");
    }
}
