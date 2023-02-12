package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.Optional;

public class ProductNameValidator<T extends Product> implements ProductValidator<T> {

    @Override
    public Optional<ValidationException> validate(T object) {
        return Optional.empty();
    }
}
