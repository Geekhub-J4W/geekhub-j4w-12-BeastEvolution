package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.Optional;

public class ProductPriceValidator<T extends Product> implements ProductValidator<T> {

    @Override
    public Optional<ValidationException> validate(Product product) {
        return Optional.empty();
    }
}
