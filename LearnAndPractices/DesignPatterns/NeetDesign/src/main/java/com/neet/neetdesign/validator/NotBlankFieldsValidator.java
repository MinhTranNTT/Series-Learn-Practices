package com.neet.neetdesign.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotBlankFieldsValidator implements ConstraintValidator<NotBlankFields, List<String>> {

    @Override
    public void initialize(NotBlankFields constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (String s : value) {
            if (s == null || s.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
