package edu.geekhub.homework.entity;

import edu.geekhub.homework.entity.exceptions.InvalidFieldValueException;
import edu.geekhub.homework.validation.StringValidator;

public record Property(String name, String value) {
    public Property {
        if (isNameNotValid(name)) {
            throw new InvalidFieldValueException(
                String.format("Input name:%s value contains illegal chars%n", name)
            );
        }

        if (isValueNotValid(value)) {
            throw new InvalidFieldValueException(
                String.format("Input value:%s value contains illegal chars%n", value)
            );
        }

    }

    private boolean isNameNotValid(String name) {
        StringValidator stringValidator = new StringValidator(
            PropertyCharacters.getNameValidCharacters()
        );

        return !stringValidator.validateString(name);
    }

    private boolean isValueNotValid(String value) {
        StringValidator stringValidator = new StringValidator(
            PropertyCharacters.getValueValidCharacters()
        );

        return !stringValidator.validateString(value);
    }
}
