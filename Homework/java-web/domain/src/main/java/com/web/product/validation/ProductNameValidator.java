package com.web.product.validation;

import com.web.product.Product;
import com.web.product.ProductNameCharacters;
import com.web.product.validation.exceptions.ValidationException;
import com.web.product.validation.interfaces.Validator;
import com.web.valodation.StringValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductNameValidator<T extends Product> implements Validator<T> {

    private final StringValidator stringValidator = new StringValidator(
        ProductNameCharacters.getProductNameValidCharacters()
    );

    @Override
    public List<ValidationException> validate(T product) {
        List<ValidationException> validationExceptions = new ArrayList<>();
        if (Objects.isNull(product.getName())) {
            validationExceptions.add(new ValidationException(
                "Product name must be not equal null, but was set:" + product.getName()
            ));

            return validationExceptions;
        } else if (isNameEmpty(product.getName())) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must be not empty, but was set:" + product.getName()
                )
            );

            return validationExceptions;
        }

        if (isNotNameBeginWithUppercaseChar(product.getName())) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must begin with Uppercase symbol, but was set:"
                        + product.getName()
                )
            );
        }
        if (isNameContainIllegalSymbols(product.getName())) {
            validationExceptions.add(
                new ValidationException(
                    "Product name must contain only English and Ukrainian alphabet characters,"
                        + " digits and punctuation marks, but set: " + product.getName()
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
