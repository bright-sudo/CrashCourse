package com.student.demo.exeptions;

public class ApiRequstExeption extends RuntimeException {
    public ApiRequstExeption(String message) {
        super(message);
    }

    public ApiRequstExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
