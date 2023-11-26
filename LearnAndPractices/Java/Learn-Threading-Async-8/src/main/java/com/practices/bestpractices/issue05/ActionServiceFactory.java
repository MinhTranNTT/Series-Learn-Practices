package com.practices.bestpractices.issue05;

import java.util.HashMap;
import java.util.Map;

public class ActionServiceFactory {
    private ActionServiceFactory(){}

    private static class SingletonHolder {
        private static ActionServiceFactory instance=new ActionServiceFactory();
    }

    public static ActionServiceFactory getInstance(){
        return SingletonHolder.instance;
    }

    private static final Map<String,ActionService> ACTION_SERVICE_MAP = new HashMap<>();

    static {
        ACTION_SERVICE_MAP.put("action1",new ActionService1());
        ACTION_SERVICE_MAP.put("action2",new ActionService2());
        ACTION_SERVICE_MAP.put("action3",new ActionService3());
        ACTION_SERVICE_MAP.put("action4",new ActionService4());
        ACTION_SERVICE_MAP.put("action5",new ActionService5());
    }

    public static ActionService getActionService(String actionCode) {
        ActionService actionService = ACTION_SERVICE_MAP.get(actionCode);
        if (actionService == null) {
            throw new RuntimeException("error actionCode");
        }
        return actionService;
    }

    public void doAction(String actionCode) {
        getActionService(actionCode).doAction();
    }

}
