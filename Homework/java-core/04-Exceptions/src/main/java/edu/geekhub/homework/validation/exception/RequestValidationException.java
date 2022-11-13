package edu.geekhub.homework.validation.exception;

public class RequestValidationException extends ValidationException {
    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestValidationException(Throwable cause) {
        super(cause);
    }
}
