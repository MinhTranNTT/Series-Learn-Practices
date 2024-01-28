package com.example.springrest.otherWay02;

import com.example.springrest.enums.HelloReturnCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class HelloHibernate {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotEmpty(message = "Image list cannot be empty")
    private List<String> imageList;

    @Positive(message = "Age must be a positive number")
    private int age;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    @NotBlank(message = "Order name cannot be blank")
    private String orderName;

    @NotBlank(message = "Stock cannot be blank")
    @Positive(message = "Stock must be a positive number")
    private String stock;

    public HelloReturnCode checkValidation() {
        return ValidationUtil.validate(this);
    }
}
