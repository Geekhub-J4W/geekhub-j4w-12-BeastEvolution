package com.web.valodation;

import org.springframework.stereotype.Component;

@Component
public class StringValidator {

    private final char[] validChars;

    public StringValidator(char[] validChars) {
        this.validChars = validChars;
    }

    public boolean validateString(String string) {
        return string.chars().allMatch(stringChar -> isCharacterValid((char) stringChar));
    }

    private boolean isCharacterValid(char stringChar) {
        return String.valueOf(validChars).indexOf(stringChar) != -1;
    }
}
