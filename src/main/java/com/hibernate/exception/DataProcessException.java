package com.hibernate.exception;

public class DataProcessException extends RuntimeException {
    public DataProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
