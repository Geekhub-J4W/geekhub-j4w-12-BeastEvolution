package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductValidator {

    private final ProductNameValidator productNameValidator = new ProductNameValidator();
    private final PriceValidator priceValidator = new PriceValidator();


    public List<ValidationException> validate(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException(
                "Product should not be equal null, but was: " + product
            );
        }
        List<ValidationException> validationExceptions = new ArrayList<>();

        validationExceptions.addAll(productNameValidator.validate(product.getName()));
        validationExceptions.addAll(priceValidator.validate(product.getPrice()));
        return validationExceptions;
    }
}
