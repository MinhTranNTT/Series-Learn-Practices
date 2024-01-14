package com.example.hibernatevalid.validation;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUtils {
    public static Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        ValidatorUtils.validator = validator;
    }
}
