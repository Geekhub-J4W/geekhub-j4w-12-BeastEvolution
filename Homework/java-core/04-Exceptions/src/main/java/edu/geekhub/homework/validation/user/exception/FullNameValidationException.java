package edu.geekhub.homework.validation.user.exception;

import edu.geekhub.homework.validation.exception.ValidationException;

public class FullNameValidationException extends ValidationException {
    public FullNameValidationException(String message) {
        super(message);
    }

    public FullNameValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullNameValidationException(Throwable cause) {
        super(cause);
    }
}