package com.samir.customerrewardprogram.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private Error error;

    public ApiResponse(T data, Error error) {
        this.data = data;
        this.error = error;
    }

    @Getter
    @Setter
    public static class Error {
        int statusCode;
        String errorCode;
        String errorMessage;

        public Error(int statusCode, String errorCode, String errorMessage) {
            this.statusCode = statusCode;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

    }
}
