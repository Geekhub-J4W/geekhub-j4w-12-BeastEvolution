package com.web.product.validation;

import com.web.product.ProductNameCharacters;
import com.web.product.validation.exceptions.ValidationException;
import com.web.valodation.StringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductNameValidator {

    private final StringValidator stringValidator = new StringValidator(
        ProductNameCharacters.getProductNameValidCharacters()
    );


    public List<ValidationException> validate(String productName) {
        if (Objects.isNull(productName)) {
            throw new IllegalArgumentException(
                "Product name must be not equal null, but was: " + productName
            );
        }

        List<ValidationException> validationExceptions = new ArrayList<>();

        if (isNameEmpty(productName)) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must be not empty, but was set:" + productName
                )
            );

            return validationExceptions;
        }

        if (isNotNameBeginWithUppercaseChar(productName)) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must begin with Uppercase symbol, but was set:"
                        + productName
                )
            );
        }
        if (isNameContainIllegalSymbols(productName)) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must contain only English and Ukrainian alphabet characters,"
                        + " digits and punctuation marks, but set: " + productName
                )
            );
        }
        return validationExceptions;
    }

    private boolean isNameEmpty(String name) {
        return name.isEmpty();
    }

    private boolean isNotNameBeginWithUppercaseChar(String name) {
        char firstChar = name.charAt(0);
        return !Character.isUpperCase(firstChar);
    }

    private boolean isNameContainIllegalSymbols(String name) {
        return !stringValidator.validateString(name);
    }
}
