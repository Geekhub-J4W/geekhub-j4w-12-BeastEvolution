package edu.geekhub.homework.validation.exeption;

import edu.geekhub.homework.validation.exeption.UserValidationException;

public class NotesValidationException extends UserValidationException {
    public NotesValidationException(String message) {
        super(message);
    }

    public NotesValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotesValidationException(Throwable cause) {
        super(cause);
    }
}
