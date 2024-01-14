package com.example.hibernatevalid.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.groups.Default;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ValidListValidator.class})
public @interface ValidList {
    /**
     * 要验证的分组
     */
    Class<?>[] groupings() default {Default.class};

    boolean quickFail() default false;//快速失败模式，一旦有校验失败的情况就不会再往下校验


    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
