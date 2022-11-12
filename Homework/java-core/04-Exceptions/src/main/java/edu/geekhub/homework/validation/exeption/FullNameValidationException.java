package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class FullNameValidationException extends UserValidationException {
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
