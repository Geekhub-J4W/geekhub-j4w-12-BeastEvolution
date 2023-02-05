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

        return string.chars().allMatch(stringChar -> isCharacterValid((char) stringChar));
    }

    private boolean isCharacterValid(char stringChar) {
        return String.valueOf(validChars).indexOf(stringChar) != -1;
    }
}
