package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.validation.exceptions.ValidationException;

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
