package edu.geekhub.homework.validation.user.exception;

import edu.geekhub.homework.validation.exception.ValidationException;

public class UserNameValidationException extends ValidationException {
    public UserNameValidationException(String message) {
        super(message);
    }

    public UserNameValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameValidationException(Throwable cause) {
        super(cause);
    }
}
