package com.practices.bestpractices.issue05;

import java.util.HashMap;
import java.util.Map;

public enum ActionServiceFactory2 {
    INSTANCE;

    private final Map<String, ActionService> ACTION_SERVICE_MAP = new HashMap<>();

    ActionServiceFactory2() {
        ACTION_SERVICE_MAP.put("action1", new ActionService1());
        ACTION_SERVICE_MAP.put("action2", new ActionService2());
        ACTION_SERVICE_MAP.put("action3", new ActionService3());
        ACTION_SERVICE_MAP.put("action4", new ActionService4());
        ACTION_SERVICE_MAP.put("action5", new ActionService5());
    }

    public ActionService getActionService(String actionCode) {
        ActionService actionService = ACTION_SERVICE_MAP.get(actionCode);
        if (actionService == null) {
            throw new IllegalArgumentException("Invalid actionCode: " + actionCode);
        }
        return actionService;
    }

    public void doAction(String actionCode) {
        getActionService(actionCode).doAction();
    }
}
