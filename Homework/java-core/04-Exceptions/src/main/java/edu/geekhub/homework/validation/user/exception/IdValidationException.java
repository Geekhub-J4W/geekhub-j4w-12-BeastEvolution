package edu.geekhub.homework.validation.user.exception;

import edu.geekhub.homework.validation.exception.ValidationException;

public class IdValidationException extends ValidationException {
    public IdValidationException(String message) {
        super(message);
    }

    public IdValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdValidationException(Throwable cause) {
        super(cause);
    }
}
