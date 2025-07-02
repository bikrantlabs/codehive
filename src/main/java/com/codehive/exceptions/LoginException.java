package com.codehive.exceptions;

public class LoginException extends Exception {
    private static final String message = "Incorrect Email or Password!";

    public LoginException() {
        super(message);
    }

    public LoginException(Throwable err) {
        super(message, err);
    }
}
