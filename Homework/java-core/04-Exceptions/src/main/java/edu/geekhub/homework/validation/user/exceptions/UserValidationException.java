package edu.geekhub.homework.validation.user.exceptions;

import edu.geekhub.homework.collections.ValidationExceptionArrayList;
import edu.geekhub.homework.validation.exception.ValidationException;

public class UserValidationException extends ValidationException {

    private ValidationExceptionArrayList validationExceptionArrayList =
        new ValidationExceptionArrayList();

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserValidationException(Throwable cause) {
        super(cause);
    }

    public void addValidationException(ValidationException validationException) {
        validationExceptionArrayList.addToEnd(validationException);
    }

    public void printExceptionInfo() {
        System.out.println(this);
        System.out.println("Consist of:");
        printValidationExceptionList();
    }

    private void printValidationExceptionList() {
        for (int i = 0; i < validationExceptionArrayList.sizeOfList(); i++) {
            System.out.println(i + ": " + validationExceptionArrayList.get(i).getMessage());
        }
    }

    public boolean checkWhetherValidationErrorsPresent() {

        return (validationExceptionArrayList.sizeOfList() > 0);
    }
}
