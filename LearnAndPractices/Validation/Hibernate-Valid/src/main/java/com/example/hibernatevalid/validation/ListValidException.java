package com.example.hibernatevalid.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Map;
import java.util.Set;

public class ListValidException extends RuntimeException {
    private Map<Integer, Set<ConstraintViolation<Object>>> errors;

    public ListValidException(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
        this.errors = errors;
    }

    public Map<Integer, Set<ConstraintViolation<Object>>> getErrors() {
        return errors;
    }

    public void setErrors(Map<Integer, Set<ConstraintViolation<Object>>> errors) {
        this.errors = errors;
    }
}
