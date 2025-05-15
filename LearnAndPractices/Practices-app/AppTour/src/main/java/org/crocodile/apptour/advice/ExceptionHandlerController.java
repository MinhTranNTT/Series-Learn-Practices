package org.crocodile.apptour.advice;

import lombok.extern.slf4j.Slf4j;
import org.crocodile.apptour.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
// @ControllerAdvice
public class ExceptionHandlerController {

    // @ExceptionHandler(value = {NoResourceFoundException.class, Exception.class})
    public ResponseEntity<?> handlerExceptionController() {
        Result<Object> fail = Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.badRequest().body(fail);
    }

}

