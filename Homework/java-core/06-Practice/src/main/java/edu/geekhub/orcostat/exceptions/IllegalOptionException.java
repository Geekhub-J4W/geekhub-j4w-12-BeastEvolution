package edu.geekhub.orcostat.exceptions;

public class IllegalOptionException extends RuntimeException {
    public IllegalOptionException(String message) {
        super(message);
    }

    public IllegalOptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
