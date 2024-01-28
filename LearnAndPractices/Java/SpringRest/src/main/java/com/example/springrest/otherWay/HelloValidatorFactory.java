package com.example.springrest.otherWay;

import com.example.springrest.enums.HelloReturnCode;

import java.util.List;

public class HelloValidatorFactory {
    public static HelloReturnCode isValidImages(Object value) {
        return (value == null || ((List<?>) value).isEmpty()) ? HelloReturnCode.IMAGES_INVALID : HelloReturnCode.OK;
    }

    public static HelloReturnCode isValidAge(Object value) {
        return (value == null || (value instanceof Integer && (int) value < 0)) ? HelloReturnCode.AGE_INVALID : HelloReturnCode.OK;
    }

    public static HelloReturnCode isValidStock(Object value) {
        if (value == null || value.toString().isEmpty()) {
            return HelloReturnCode.STOCK_INVALID;
        }
        try {
            int stockValue = Integer.parseInt(value.toString());
            return (stockValue < 0) ? HelloReturnCode.STOCK_INVALID : HelloReturnCode.OK;
        } catch (NumberFormatException e) {
            return HelloReturnCode.STOCK_INVALID;
        }
    }
}
