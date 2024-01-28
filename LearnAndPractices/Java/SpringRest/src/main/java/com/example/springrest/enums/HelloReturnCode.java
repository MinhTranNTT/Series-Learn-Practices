package com.example.springrest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum HelloReturnCode {
    NAME_INVALID("1001", "Name invalid"),
    IMAGES_INVALID("1002", "Image invalid"),
    AGE_INVALID("1003", "Age invalid"),
    CUSTOMER_NAME_INVALID("1004", "Customer name invalid"),
    ORDER_NAME_INVALID("1005", "Order name invalid"),
    STOCK_INVALID("1006", "Stock invalid"),
    SYSTEM_ERROR("9999", "System Error"),
    OK("9990", "OK");

    private String code;
    private String message;

    public static HelloReturnCode of(String code) {
        for (HelloReturnCode value : HelloReturnCode.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return SYSTEM_ERROR;
    }
}
