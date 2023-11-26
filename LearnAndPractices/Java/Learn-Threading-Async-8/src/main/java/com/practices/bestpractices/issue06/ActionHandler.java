package com.practices.bestpractices.issue06;

public abstract class ActionHandler {
    protected ActionHandler successor;
    void handler(String actionCode) {
        doHandler(actionCode);
    }
    protected ActionHandler setSuccessor(ActionHandler successor) {
        this.successor = successor;
        return this;
    }
    abstract void doHandler(String actionCode);

}
