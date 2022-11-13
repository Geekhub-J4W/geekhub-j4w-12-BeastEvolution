package edu.geekhub.homework.validation.user.exception;

import edu.geekhub.homework.validation.exception.ValidationException;

public class UserAgeValidationException extends ValidationException {
    public UserAgeValidationException(String message) {
        super(message);
    }

    public UserAgeValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAgeValidationException(Throwable cause) {
        super(cause);
    }
}
