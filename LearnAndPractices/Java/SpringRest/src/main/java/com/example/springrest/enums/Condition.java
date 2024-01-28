package com.example.springrest.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

@Getter
public enum Condition {
    INVALID_NAME(Condition::isValidName),
    INVALID_IMAGES(Condition::isValidImages),
    INVALID_AGE(Condition::isValidAge),
    INVALID_CUSTOMER_NAME(Condition::isValidCustomerName),
    INVALID_ORDER_NAME(Condition::isValidOrderName),
    INVALID_STOCK(Condition::isValidStock);

    private final Function<Object, HelloReturnCode> func;

    Condition(Function<Object, HelloReturnCode> func) {
        this.func = func;
    }


    private static HelloReturnCode isValidName(Object value) {
        if (value == null || StringUtils.isEmpty((String) value)) {
            return HelloReturnCode.NAME_INVALID;
        }
        return HelloReturnCode.OK;
    }

    private static HelloReturnCode isValidCustomerName(Object value) {
        if (value == null || StringUtils.isEmpty((String) value)) {
            return HelloReturnCode.CUSTOMER_NAME_INVALID;
        }
        return HelloReturnCode.OK;
    }

    private static HelloReturnCode isValidOrderName(Object value) {
        if (value == null || StringUtils.isEmpty((String) value)) {
            return HelloReturnCode.ORDER_NAME_INVALID;
        }
        return HelloReturnCode.OK;
    }

    private static HelloReturnCode isValidStock(Object value) {
        if (value == null || StringUtils.isEmpty((String) value)) {
            return HelloReturnCode.STOCK_INVALID;
        }
        try {
            int i = Integer.parseInt((String) value);
            if (i < 0) {
                return HelloReturnCode.STOCK_INVALID;
            }
        } catch (NumberFormatException e) {
            return HelloReturnCode.STOCK_INVALID;
        }

        return HelloReturnCode.OK;
    }

    private static HelloReturnCode isValidAge(Object value) {
        if (value == null) {
            return HelloReturnCode.AGE_INVALID;
        }
        try {
            Integer i = (Integer) value;
            if (i.intValue() < 0) {
                return HelloReturnCode.AGE_INVALID;
            }
        } catch (NumberFormatException e) {
            return HelloReturnCode.AGE_INVALID;
        }

        return HelloReturnCode.OK;
    }

    private static HelloReturnCode isValidImages(Object value) {
        if (value == null) {
            return HelloReturnCode.IMAGES_INVALID;
        }
        List<String> images = (List<String>) value;
        if (CollectionUtils.isEmpty(images)) {
            return HelloReturnCode.IMAGES_INVALID;
        }

        return HelloReturnCode.OK;
    }

}
