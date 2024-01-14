package com.example.hibernatevalid.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidListValidator implements ConstraintValidator<ValidList, List> {
    Class<?>[] groupings;
    boolean quickFail;

    @Override
    public void initialize(ValidList validList) {
        groupings = validList.groupings();
        quickFail = validList.quickFail();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        Map<Integer, Set<ConstraintViolation<Object>>> errors = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            Set<ConstraintViolation<Object>> error = ValidatorUtils.validator.validate(object, groupings);
            if (error.size()>0) {
                errors.put(i, error);
                if (quickFail){
                    throw new ListValidException(errors);
                }
            }
        }

        if (errors.size()>0){
            throw new ListValidException(errors);
        }
        return true;
    }

}
