package com.example.validationday02.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)  return true;

        String regex = "(84|0[3|5|7|8|9])([0-9]{8})\\b";
        return value.matches(regex);
    }
}
