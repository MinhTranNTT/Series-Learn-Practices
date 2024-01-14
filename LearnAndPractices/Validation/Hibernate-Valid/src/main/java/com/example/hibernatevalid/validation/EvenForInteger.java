package com.example.hibernatevalid.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenForInteger implements ConstraintValidator<Even, Integer> {  //表示作用于注解Even,要验证的类型为Integer
    @Override
    public void initialize(Even constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        //编写验证的逻辑
        if(value == null){//如果null直接验证成功，null交给NotNull注解去验证
            return true;
        }
        return value%2==0;
    }

}
