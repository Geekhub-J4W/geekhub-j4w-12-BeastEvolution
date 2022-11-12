package edu.geekhub.homework.validation.exeption;

public class UserAgeValidationException extends UserValidationException{
    public UserAgeValidationException(String message) {
        super(message);
    }

    public UserAgeValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAgeValidationException(Throwable cause) {
        super(cause);
    }
}
