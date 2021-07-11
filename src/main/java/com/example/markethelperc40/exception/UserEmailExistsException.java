package com.example.markethelperc40.exception;

public class UserEmailExistsException extends RuntimeException {
    public UserEmailExistsException() {
    }

    public UserEmailExistsException(String message) {
        super(message);
    }

    public UserEmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailExistsException(Throwable cause) {
        super(cause);
    }

}
