package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class UserNameValidationException extends UserValidationException {
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
