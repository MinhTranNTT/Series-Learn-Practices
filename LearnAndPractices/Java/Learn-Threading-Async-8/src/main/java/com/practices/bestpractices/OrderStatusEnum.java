package com.practices.bestpractices;

import com.practices.colllections.patterns.OrderStateEnum;

public enum OrderStatusEnum {
    UN_PAID("1","Order not paid"),
    PAIDED("2","Order Paid"),
    SENDED("3","Order has been shipped"),
    SINGED("4","Order has been signed"),
    EVALUATED("5","Order has been evaluated");
    private String status;
    private String statusDesc;

    OrderStatusEnum() {
    }

    OrderStatusEnum(String status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public static OrderStatusEnum of(String status) {
        for (OrderStatusEnum stateEnum : OrderStatusEnum.values()) {
            if (stateEnum.getStatus().equals(status)) {
                return stateEnum;
            }
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
