package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class AmountOfFollowersValidationException extends UserValidationException {
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
