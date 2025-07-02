package com.codehive.exceptions;

public class UserAlreadyExistsException extends Exception {
    private static final String message = "User with this email already exists";

    public UserAlreadyExistsException() {
        super(message);
    }

    public UserAlreadyExistsException(Throwable err) {
        super(message, err);
    }
}
