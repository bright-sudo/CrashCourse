package com.student.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExeptionHandler {

    @ExceptionHandler(ApiRequstExeption.class)
    public ResponseEntity<Object> handleApiRequestExeption(ApiRequstExeption e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExeption apiExeption = new ApiExeption(
                ZonedDateTime.now(),
                badRequest,
                "Please wait for a few minutes, we try to fix it",
                e.getMessage());
        return new ResponseEntity<>(apiExeption, badRequest);
    }

    @ExceptionHandler(ApiEmailExeption.class)
    public ResponseEntity<Object> handleApiEmailExeption(ApiEmailExeption e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExeption apiExeption = new ApiExeption(
                ZonedDateTime.now(),
                badRequest,
                e.getMessage(),
                "Error");
        return new ResponseEntity<>(apiExeption, badRequest);
    }
}
