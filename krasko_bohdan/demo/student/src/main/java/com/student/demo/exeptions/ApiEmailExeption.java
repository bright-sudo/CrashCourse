package com.student.demo.exeptions;

public class ApiEmailExeption extends RuntimeException {

    public ApiEmailExeption(String message) {
        super(message);
    }

    public ApiEmailExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
