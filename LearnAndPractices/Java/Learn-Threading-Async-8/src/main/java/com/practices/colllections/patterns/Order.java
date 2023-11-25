package com.practices.colllections.patterns;

public class Order {
    private OrderStateEnum state;

    public Order() {
        this.state = OrderStateEnum.UNPAY;
    }
    public OrderStateEnum getState() {
        return this.state;
    }
    public void setState(OrderStateEnum state) {
        this.state = state;
    }
    public void nextState() {
        state.nextState(this);
    }

}
