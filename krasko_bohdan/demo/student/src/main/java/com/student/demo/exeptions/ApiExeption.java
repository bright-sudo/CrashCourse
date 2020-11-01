package com.student.demo.exeptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExeption {

    private final ZonedDateTime timestamp;
    private final HttpStatus httpStatus;
    private final String error;
    private final String message;

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }

    public ApiExeption(ZonedDateTime timestamp,
                       HttpStatus httpStatus,
                       String error,
                       String message) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }
}
