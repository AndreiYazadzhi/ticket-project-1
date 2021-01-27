package com.hibernate.exception;

public class DataProcessException extends RuntimeException {
    public DataProcessException(String message, Exception e) {
        super(message);
    }
}
