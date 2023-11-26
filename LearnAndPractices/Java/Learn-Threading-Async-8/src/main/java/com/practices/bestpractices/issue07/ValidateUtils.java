package com.practices.bestpractices.issue07;

public class ValidateUtils {
    public static ThrowExceptionFunction isTrue(Boolean flag) {
        return (errorMessage) -> {
            if (flag) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

}
