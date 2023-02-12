package com.web.product.validation;

import com.web.product.Product;
import com.web.product.ProductNameCharacters;
import com.web.product.validation.exceptions.ValidationException;
import com.web.valodation.StringValidator;
import java.util.Objects;
import java.util.Optional;

public class ProductNameValidator<T extends Product> implements ProductValidator<T> {

    private final StringValidator stringValidator = new StringValidator(
        ProductNameCharacters.getProductNameValidCharacters()
    );

    @Override
    public Optional<ValidationException> validate(T product) {
        if (Objects.isNull(product.getName())) {
            return Optional.of(
                new ValidationException(
                    "Product name must be not equal null, but was set:" + product.getName()
                )
            );
        } else if (isNameEmpty(product.getName())) {
            return Optional.of(
                new ValidationException(
                    "Product name must be not empty, but was set:" + product.getName()
                )
            );
        } else if (isNotNameBeginWithUppercaseChar(product.getName())) {
            return Optional.of(
                new ValidationException(
                    "Product name must begin with Uppercase symbol, but was set:"
                        + product.getName()
                )
            );
        } else if (isNameContainIllegalSymbols(product.getName())) {
            return Optional.of(
                new ValidationException(
                    "Product name must contain only English and Ukrainian alphabet characters,"
                        + " digits and punctuation marks, but set: " + product.getName()
                )
            );
        }
        return Optional.empty();
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
