package com.codehive.exceptions;

public class InvalidSessionException extends Exception {
    private static final String message = "Invalid Session";

    public InvalidSessionException() {
        super(message);
    }

    public InvalidSessionException(Throwable err) {
        super(message, err);
    }
}
