package edu.geekhub.homework.validation.user.exception;

import edu.geekhub.homework.validation.exception.ValidationException;

public class EmailValidationException extends ValidationException {
    public EmailValidationException(String message) {
        super(message);
    }

    public EmailValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailValidationException(Throwable cause) {
        super(cause);
    }
}
