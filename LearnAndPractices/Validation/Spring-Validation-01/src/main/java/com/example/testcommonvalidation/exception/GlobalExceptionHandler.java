package com.example.testcommonvalidation.exception;


import com.example.testcommonvalidation.controller.ExceptionController;
import com.example.testcommonvalidation.entity.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice(assignableTypes = { ExceptionController.class })
public class GlobalExceptionHandler {
    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("Error Parameter"));
    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("Sorry, the resource not found!"));

    @ExceptionHandler(value = Exception.class)  // handle all exception
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        } else if (e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(resourceNotFoundResponse);
        }
        return null;
    }
}
