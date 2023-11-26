package com.practices.bestpractices;

import com.practices.bestpractices.issue05.ActionServiceFactory;
import com.practices.bestpractices.issue05.ActionServiceFactory2;
import com.practices.bestpractices.issue06.ActionHandlerFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class _01_BestPractice {
    public static void main(String[] args) {
        method_04("code2");
        // method_04("code7");
    }

    public void method_01() {
        // bad
        if (1 == 1) {
            // doSomeThing
        } else {
            return;
        }

        if (1!=1) {
            return;
        }
        // doSomething
    }

    public void method_02() {
        String orderStatus = "Tom";
        if ("1".equals(orderStatus)) {
            orderStatus = "Tom" + 1;
        } else if ("2".equals(orderStatus)) {
            orderStatus = "Tom" + 2;
        } else if ("3".equals(orderStatus)) {
            orderStatus = "tom" + 3;
        } else if ("4".equals(orderStatus)) {
            orderStatus = "tom" + 4;
        } else if ("5".equals(orderStatus)) {
            orderStatus = "tom" + 5;
        }

        // good
        String statusDesc = OrderStatusEnum.of(orderStatus).getStatusDesc();
    }

    public String method_03() {
        // bad
        Order order = getOrderById(1);
        // if (order == null) {
        //     return "-1";
        // } else {
        //     return order.getOrderId();
        // }

        // good
        return Optional.ofNullable(order).map(o -> o.getOrderId()).orElse("-1");
    }

    private Order getOrderById(int i) {
        return new Order();
    }

    public static void method_04(String actionName) {
        // bad
        String action = actionName;
        if ("code1".equals(action)) {
            doAction1(null);
        } else if ("code2".equals(action)) {
            doAction2(null);
        } else if ("code3".equals(action)) {
            doAction3(null);
        } else if ("code4".equals(action)) {
            doAction4(null);
        } else if ("code5".equals(action)) {
            doAction5(null);
        }

        System.out.println("------------------------");
        // GOOD
        Map<String, Function<Void, Void>> actionMap = new HashMap<>();
        actionMap.put("code1", _01_BestPractice::doAction1);
        actionMap.put("code2", _01_BestPractice::doAction2);
        actionMap.put("code3", _01_BestPractice::doAction3);
        actionMap.put("code4", _01_BestPractice::doAction4);
        actionMap.put("code5", _01_BestPractice::doAction5);


        // actionMap.get(action).apply(null);
        actionMap.getOrDefault(action, Function.identity()).apply(null);
        System.out.println("sub ------------------------");
        // ActionServiceFactory.getInstance().doAction("action1");
        // ActionServiceFactory2.INSTANCE.doAction("action2");

        // ActionHandlerFactory.getInstance().doAction("action1");
    }

    private static Void doAction1(Void args) {
        System.out.println("doAction1");
        return null;
    }

    private static Void doAction2(Void args) {
        System.out.println("doAction2");
        return null;
    }

    private static Void doAction3(Void args) {
        System.out.println("doAction3");
        return null;
    }

    private static Void doAction4(Void args) {
        System.out.println("doAction4");
        return null;
    }

    private static Void doAction5(Void args) {
        System.out.println("doAction5");
        return null;
    }

}

@Getter
@Setter
class Order {
    private String orderId;
    private String orderCode;

}
