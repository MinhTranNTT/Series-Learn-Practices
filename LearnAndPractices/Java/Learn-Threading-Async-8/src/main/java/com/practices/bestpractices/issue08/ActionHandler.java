package com.practices.bestpractices.issue08;

import com.practices.bestpractices.issue05.ActionService;

@FunctionalInterface
public interface ActionHandler {
    void doActionHandler(ActionService trueActionService, ActionService falseActionService);
}
