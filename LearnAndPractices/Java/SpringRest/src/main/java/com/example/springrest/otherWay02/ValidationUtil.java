package com.example.springrest.otherWay02;

import com.example.springrest.enums.HelloReturnCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
public class ValidationUtil {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> HelloReturnCode validate(T object) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            // You can customize how you handle validation errors here
            for (ConstraintViolation<T> violation : violations) {
                String propertyName = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                System.out.println("Validation error for property '" + propertyName + "': " + message);
            }
            return HelloReturnCode.SYSTEM_ERROR; // Or another appropriate code
        }

        return HelloReturnCode.OK;
    }
}
