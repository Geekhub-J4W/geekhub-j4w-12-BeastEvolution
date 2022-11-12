package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class IdValidationException extends UserValidationException {
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
