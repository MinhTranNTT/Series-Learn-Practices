package com.practices.bestpractices.issue06;

public class ActionHandler4 extends ActionHandler {
    @Override
    void doHandler(String actionCode) {
        if ("action4".equals(actionCode)) {
            doAction4();
        } else {
            successor.doHandler(actionCode);
        }
    }

    private void doAction4() {
        System.out.println("DoAction4");
    }
}
