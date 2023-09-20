package com.ecommerce.exceptions;

public class SecurityException extends RuntimeException {
    public SecurityException(String message) {
        super(message);
    }
}