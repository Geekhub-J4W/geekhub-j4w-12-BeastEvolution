package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class EmailValidationException extends UserValidationException {
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
