package com.example.goodcontroller.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum implements IResult {
    SUCCESS(2001, "Interface call successful"),
    VALIDATE_FAILED(2002, "Parameter verification failed"),
    COMMON_FAILED(2003, "Interface call failed"),
    FORBIDDEN(2004, "No permission to access resource");

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String message;

}
