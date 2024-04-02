package com.neet.neetdesign.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotBlankFieldsValidator.class})
public @interface NotBlankFields {
    String message() default "all fields is not blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
