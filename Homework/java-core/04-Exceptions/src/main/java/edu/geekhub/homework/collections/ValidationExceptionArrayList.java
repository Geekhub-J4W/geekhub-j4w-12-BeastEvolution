package edu.geekhub.homework.collections;

import edu.geekhub.homework.validation.exceptions.ValidationException;
import java.util.Arrays;

public class ValidationExceptionArrayList {
    private ValidationException[] exceptions = new ValidationException[0];

    public void addToEnd(ValidationException e) {
        ValidationException[] newExceptionsArray = Arrays.copyOf(exceptions, exceptions.length + 1);
        newExceptionsArray[newExceptionsArray.length - 1] = e;

        exceptions = newExceptionsArray;
    }

    public Exception get(int index) {
        return exceptions[index];
    }

    public int sizeOfList() {
        return exceptions.length;
    }
}
