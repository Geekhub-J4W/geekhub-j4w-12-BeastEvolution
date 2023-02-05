package edu.geekhub.homework.validation;

import java.util.Objects;

public class StringValidator {
    private final char[] validChars;

    public StringValidator(char[] validChars) {
        this.validChars = validChars;
    }

    public boolean validateString(String string) {
        if (Objects.isNull(string)) {
            throw new IllegalArgumentException("Input string can't be null");
        }

        char[] stringChars = string.toCharArray();

        for (char stringChar : stringChars) {
            if (isCharacterInvalid(stringChar)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCharacterInvalid(char stringChar) {
        boolean found = false;

        for (char validChar : validChars) {
            if (stringChar == validChar) {
                found = true;
                break;
            }
        }

        return !found;
    }
}
