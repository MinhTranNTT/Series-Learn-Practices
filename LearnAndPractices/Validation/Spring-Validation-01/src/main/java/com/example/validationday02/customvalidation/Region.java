package com.example.validationday02.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegionValidator.class)
public @interface Region {
    String message() default "Region value is not in optional range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
