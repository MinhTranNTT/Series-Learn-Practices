package com.example.way02.exception;

import com.example.way02.result.ResultStatus;
import lombok.Getter;

@Getter
public class ResultException extends Exception {
    ResultStatus resultStatus;

    public ResultException() {
        this(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.resultStatus = resultStatus;
    }
}
