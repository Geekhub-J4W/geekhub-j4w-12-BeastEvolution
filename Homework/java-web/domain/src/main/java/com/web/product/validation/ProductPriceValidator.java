package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductPriceValidator<T extends Product> implements ProductValidator<T> {

    @Override
    public Optional<ValidationException> validate(Product product) {
        if (isPriceValueNegativeNumber(product.getPrice().getValue())) {
            return Optional.of(
                new ValidationException("Product price value should be a positive number, but was:"
                    + product.getPrice().getValue())
            );
        }
        return Optional.empty();
    }

    private boolean isPriceValueNegativeNumber(BigDecimal priceValue) {
        return priceValue.signum() == -1;
    }
}
