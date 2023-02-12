package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PriceValidator {
    PriceValueValidator priceValueValidator = new PriceValueValidator();

    public List<ValidationException> validate(Product product) {
        List<ValidationException> validationExceptions = new ArrayList<>();

        if (Objects.isNull(product.getPrice().getCurrency())) {

            return List.of(
                new ValidationException(
                    "Product price currency should not be equal null, but was: "
                        + product.getPrice().getCurrency()
                )
            );
        }

        validationExceptions.addAll(priceValueValidator.validate(product));

        return validationExceptions;
    }
}
