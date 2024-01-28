package com.example.springrest.enums;

import com.example.springrest.domain.Hello;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum HelloCondition {
    INVALID_NAME("name", HelloCondition::isValidName),
    INVALID_IMAGES("images", HelloCondition::isValidImages),
    INVALID_AGE("age", HelloCondition::isValidAge),
    INVALID_CUSTOMER_NAME("customerName", HelloCondition::isValidCustomerName),
    INVALID_ORDER_NAME("orderName", HelloCondition::isValidOrderName),
    INVALID_STOCK("stock", HelloCondition::isValidStock);

    private String field;
    private Function<Hello, HelloReturnCode> function;

    // public static HelloReturnCode isValidName(Hello hello) {
    //     return Condition.INVALID_NAME.getFunc().apply(hello.getName());
    // }

    public static HelloReturnCode isValidName(Hello hello) {
        return isValidString(hello.getName(), HelloReturnCode.NAME_INVALID.getCode());
    }

    public static HelloReturnCode isValidAge(Hello hello) {
        return Condition.INVALID_AGE.getFunc().apply(hello.getAge());
    }

    public static HelloReturnCode isValidImages(Hello hello) {
        return Condition.INVALID_IMAGES.getFunc().apply(hello.getImageList());
    }

    // public static HelloReturnCode isValidCustomerName(Hello hello) {
    //     return Condition.INVALID_CUSTOMER_NAME.getFunc().apply(hello.getCustomerName());
    // }

    public static HelloReturnCode isValidCustomerName(Hello hello) {
        return isValidString(hello.getCustomerName(), HelloReturnCode.CUSTOMER_NAME_INVALID.getCode());
    }

    // public static HelloReturnCode isValidOrderName(Hello hello) {
    //     return Condition.INVALID_ORDER_NAME.getFunc().apply(hello.getOrderName());
    // }

    public static HelloReturnCode isValidOrderName(Hello hello) {
        return isValidString(hello.getOrderName(), HelloReturnCode.ORDER_NAME_INVALID.getCode());
    }

    public static HelloReturnCode isValidStock(Hello hello) {
        return Condition.INVALID_STOCK.getFunc().apply(hello.getStock());
    }

    private static final Map<String, Function<Hello, HelloReturnCode>> VALIDATION_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(HelloCondition::getField, HelloCondition::getFunction));

    public static HelloReturnCode getValidationForField(String field, Hello hello) {
        return VALIDATION_MAP.getOrDefault(field, unused -> HelloReturnCode.SYSTEM_ERROR).apply(hello);
    }

    public static HelloReturnCode isValidString(String value, String errorCode) {
        return (value != null && !value.isEmpty()) ? HelloReturnCode.OK : HelloReturnCode.of(errorCode);
    }
}
