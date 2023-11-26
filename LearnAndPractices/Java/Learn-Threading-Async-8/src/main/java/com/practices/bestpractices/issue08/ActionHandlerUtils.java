package com.practices.bestpractices.issue08;

public class ActionHandlerUtils {
    public static ActionHandler isTrue(Boolean flag) {
        return (trueActionService,falseActionService) -> {
            if (flag) {
                trueActionService.doAction();
            } else {
                falseActionService.doAction();
            }
        };
    }
}
