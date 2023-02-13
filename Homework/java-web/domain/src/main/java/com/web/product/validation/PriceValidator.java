package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.List;
import java.util.Objects;

public class PriceValidator {

    private final AmountValidator amountValidator = new AmountValidator();


    public List<ValidationException> validate(Product product) {

        if (Objects.isNull(product.getPrice().getCurrency())) {
            throw new IllegalArgumentException(
                "Product price currency should not be equal null, but was: "
                    + product.getPrice().getCurrency()
            );
        }

        return amountValidator.validate(product.getPrice());
    }
}
