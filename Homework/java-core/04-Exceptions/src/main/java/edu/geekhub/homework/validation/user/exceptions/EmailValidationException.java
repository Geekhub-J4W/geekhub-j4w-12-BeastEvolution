package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.validation.exceptions.ValidationException;

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
