package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.validation.exception.ValidationException;

public class AmountOfFollowersValidationException extends ValidationException {
    public AmountOfFollowersValidationException(String message) {
        super(message);
    }

    public AmountOfFollowersValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountOfFollowersValidationException(Throwable cause) {
        super(cause);
    }
}
