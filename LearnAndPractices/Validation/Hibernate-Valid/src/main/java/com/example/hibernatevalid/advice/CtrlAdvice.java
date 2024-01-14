package com.example.hibernatevalid.advice;

import com.example.hibernatevalid.result.ErrorCode;
import com.example.hibernatevalid.result.ResultVo;
import com.example.hibernatevalid.validation.ListValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.ValidationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.example.hibernatevalid.controller")
@ResponseBody
public class CtrlAdvice {
    // Jump to this method after catching the exception
    @ExceptionHandler
    public ResultVo exceptionHandler(MethodArgumentNotValidException e){
        /**
         * MethodArgumentNotValidException: Capture parameter exceptions passed from the front end
         * id - the primary key cannot have a value
         * name - name cannot be empty
         * birthday - date of birth cannot be empty
         * age - age cannot be empty
         * email - the email format is incorrect
         */

        /**
         * Encapsulate the exception id (FieldError::getField) and value (getDefaultMessage)
         * into a map and return it to ResultVo
         */
        Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResultVo.fail(ErrorCode.PARAM_ERROR,collect);
    }

    //Verification corresponding to the service layer
    //Jump to this method after catching the ConstraintViolationException exception
    @ExceptionHandler
    public ResultVo exceptionHandler(ConstraintViolationException e){
        Map<Path, String> collect = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return ResultVo.fail(ErrorCode.PARAM_ERROR,collect);
    }

    @ExceptionHandler
    public ResultVo exceptionHandler(ValidationException e){
        Map<Integer, Map<Path, String>> map = new HashMap<>();

        ((ListValidException)e.getCause()).getErrors().forEach((integer, constraintViolations) -> {
            map.put(integer, constraintViolations.stream()
                    .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage)));
        });
        return ResultVo.fail(ErrorCode.PARAM_ERROR,map);
    }

    @ExceptionHandler
    public ResultVo exceptionHandler(Exception e){
        return ResultVo.fail(ErrorCode.SYSTEM_ERROR);
    }

    // {
    //     "name": "test",
    //         "age": 10,
    //         "friends": ["A", "B", "C"]
    // }

}
