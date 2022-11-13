package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.validation.exceptions.ValidationException;

public class NotesValidationException extends ValidationException {
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
