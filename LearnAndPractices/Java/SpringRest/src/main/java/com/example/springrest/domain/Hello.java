package com.example.springrest.domain;

import com.example.springrest.enums.HelloCondition;
import com.example.springrest.enums.HelloReturnCode;
import com.example.springrest.otherWay.ValidationCondition;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "age","name","images","customerName","orderName","stock"})
public class Hello {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("images")
    private List<String> imageList;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("orderName")
    private String orderName;

    @JsonProperty("stock")
    private Integer stock;

    public static Predicate<Hello> validateHello = hello ->
            validateString(hello.getName()) &&
            validateInteger(hello.getAge()) &&
            validateImageList(hello.getImageList()) &&
            validateString(hello.getCustomerName()) &&
            validateString(hello.getOrderName()) &&
            validateInteger(hello.getStock());

    private static boolean validateString(String value) {
        return value != null && !value.isEmpty();
    }

    private static boolean validateInteger(Integer value) {
        return value != null && value > 0;
    }

    private static boolean validateImageList(List<String> imageList) {
        return imageList != null && imageList.stream().allMatch(Hello::validateString);
    }

    // advanced

    public HelloReturnCode checkValidation() {
        HelloReturnCode helloReturnCode = null;
        for (HelloCondition condition : HelloCondition.values()) {
            helloReturnCode = condition.getFunction().apply(this);
            if (helloReturnCode != HelloReturnCode.OK) {
                return helloReturnCode;
            }
        }
        return HelloReturnCode.OK;
    }

    public HelloReturnCode checkValidation2() {
        List<HelloReturnCode> validationResults = Arrays.stream(ValidationCondition.values())
                .map(condition -> condition.validate(this))
                .collect(Collectors.toList());

        return validationResults.stream().filter(result -> result != HelloReturnCode.OK).findFirst()
                .orElse(HelloReturnCode.OK);
    }

}
