package com.example.hibernatevalid.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//生成文档
@Target({ElementType.FIELD})//表示该注解作用于字段
@Retention(RetentionPolicy.RUNTIME)//表示在运行是依然生效
@Constraint(
        validatedBy = {EvenForInteger.class,EvenForList.class}//添加实现类
)
public @interface Even {
    String message() default "Must be an even number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
