package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.validation.exceptions.ValidationException;

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
