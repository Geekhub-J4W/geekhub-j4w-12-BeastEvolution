package edu.geekhub.homework.validation.exeption;

import java.util.LinkedList;
import java.util.List;

public class UserValidationException extends RuntimeException {

//    private List<Exception> userValidationExceptions = new LinkedList<>();
    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserValidationException(Throwable cause) {
        super(cause);
    }

//    public void addUserDataValidationException (UserValidationException userValidationException) {
//        userValidationExceptions.add(userValidationException);
//    }
//
//    public List<Exception> getUserValidationExceptions () {
//        return userValidationExceptions;
//    }
}
