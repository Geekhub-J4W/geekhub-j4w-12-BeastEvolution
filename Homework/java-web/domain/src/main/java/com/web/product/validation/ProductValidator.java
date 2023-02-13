package com.web.product.validation;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    private final ProductNameValidator<Product> productNameValidator = new ProductNameValidator<>();
    private final PriceValidator priceValidator = new PriceValidator();


    public List<ValidationException> validate(Product product) {
        List<ValidationException> validationExceptions = new ArrayList<>();

        validationExceptions.addAll(productNameValidator.validate(product));
        validationExceptions.addAll(priceValidator.validate(product.getPrice()));
        return validationExceptions;
    }
}
