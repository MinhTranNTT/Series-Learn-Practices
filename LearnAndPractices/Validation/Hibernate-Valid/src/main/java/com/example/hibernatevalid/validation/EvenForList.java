package com.example.hibernatevalid.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EvenForList implements ConstraintValidator<Even, List> {
    @Override
    public void initialize(Even constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List value, ConstraintValidatorContext constraintValidatorContext) {
        //编写验证的逻辑
        if(value == null){//如果null直接验证成功，null交给NotNull注解去验证
            return true;
        }
        return value.size() %2==0;//表示要验证的list的列表个数为偶数
    }
}
