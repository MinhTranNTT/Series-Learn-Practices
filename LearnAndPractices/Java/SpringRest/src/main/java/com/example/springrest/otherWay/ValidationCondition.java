package com.example.springrest.otherWay;

import com.example.springrest.domain.Hello;
import com.example.springrest.enums.HelloReturnCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@Getter
// @NoArgsConstructor
// @AllArgsConstructor
public enum ValidationCondition {
    INVALID_NAME(Hello::getName, ValidationCondition::isValidString, HelloReturnCode.NAME_INVALID),
    INVALID_IMAGES(Hello::getImageList, HelloValidatorFactory::isValidImages, HelloReturnCode.IMAGES_INVALID),
    INVALID_AGE(Hello::getAge, HelloValidatorFactory::isValidAge, HelloReturnCode.AGE_INVALID),
    INVALID_CUSTOMER_NAME(Hello::getCustomerName, ValidationCondition::isValidString, HelloReturnCode.CUSTOMER_NAME_INVALID),
    INVALID_ORDER_NAME(Hello::getOrderName, ValidationCondition::isValidString, HelloReturnCode.ORDER_NAME_INVALID),
    INVALID_STOCK(Hello::getStock, HelloValidatorFactory::isValidStock, HelloReturnCode.STOCK_INVALID);

    private final Function<Hello, ?> valueExtractor;
    private final Function<Object, HelloReturnCode> validatorFunction;
    private final HelloReturnCode invalidCode;

    ValidationCondition(Function<Hello, ?> valueExtractor,
                        Function<Object, HelloReturnCode> validatorFunction,
                        HelloReturnCode invalidCode) {
        this.valueExtractor = valueExtractor;
        this.validatorFunction = validatorFunction;
        this.invalidCode = invalidCode;
    }

    public HelloReturnCode validate(Hello hello) {
        Object value = valueExtractor.apply(hello);
        return validatorFunction.apply(value);
    }

    // This method is used for string validation
    private static HelloReturnCode isValidString(Object value) {
        return (value == null || value.toString().isEmpty()) ? HelloReturnCode.NAME_INVALID : HelloReturnCode.OK;
    }
}
