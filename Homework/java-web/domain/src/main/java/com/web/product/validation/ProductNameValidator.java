package com.web.product.validation;

import com.web.product.ProductNameCharacters;
import com.web.valodation.StringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductNameValidator {

    private final StringValidator stringValidator = new StringValidator(
        ProductNameCharacters.getProductNameValidCharacters()
    );


    public List<String> validate(String productName) {
        if (Objects.isNull(productName)) {
            throw new IllegalArgumentException(
                "Product name must be not equal null, but was: " + productName
            );
        }

        List<String> validationResults = new ArrayList<>();

        if (isNameEmpty(productName)) {
            validationResults.add(
                "Product name must be not empty, but was set:" + productName
            );

            return validationResults;
        }

        if (isNotNameBeginWithUppercaseChar(productName)) {
            validationResults.add(
                "Product name must begin with Uppercase symbol, but was set:"
                    + productName
            );
        }
        if (isNameContainIllegalSymbols(productName)) {
            validationResults.add(
                "Product name must contain only English and Ukrainian alphabet characters,"
                    + " digits and punctuation marks, but set: " + productName
            );
        }
        return validationResults;
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
