package com.example.springrest.enums;

import com.example.springrest.domain.Hello;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Function;

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

    public static HelloReturnCode isValidName(Hello hello) {
        return Condition.INVALID_NAME.getFunc().apply(hello.getName());
    }

    public static HelloReturnCode isValidAge(Hello hello) {
        return Condition.INVALID_AGE.getFunc().apply(hello.getAge());
    }

    public static HelloReturnCode isValidImages(Hello hello) {
        return Condition.INVALID_IMAGES.getFunc().apply(hello.getImageList());
    }

    public static HelloReturnCode isValidCustomerName(Hello hello) {
        return Condition.INVALID_CUSTOMER_NAME.getFunc().apply(hello.getCustomerName());
    }

    public static HelloReturnCode isValidOrderName(Hello hello) {
        return Condition.INVALID_ORDER_NAME.getFunc().apply(hello.getOrderName());
    }

    public static HelloReturnCode isValidStock(Hello hello) {
        return Condition.INVALID_STOCK.getFunc().apply(hello.getStock());
    }

}
