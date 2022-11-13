package edu.geekhub.homework.exceptions;

public class RepositorySavingException extends RuntimeException {
    public RepositorySavingException(String message) {
        super(message);
    }

    public RepositorySavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositorySavingException(Throwable cause) {
        super(cause);
    }
}
