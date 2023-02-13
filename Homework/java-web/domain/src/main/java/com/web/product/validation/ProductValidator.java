package com.web.product.validation;

import com.web.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductValidator {

    private final ProductNameValidator productNameValidator = new ProductNameValidator();
    private final PriceValidator priceValidator = new PriceValidator();


    public List<String> validate(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException(
                "Product should not be equal null, but was: " + product
            );
        }
        List<String> validationResults = new ArrayList<>();

        validationResults.addAll(productNameValidator.validate(product.getName()));
        validationResults.addAll(priceValidator.validate(product.getPrice()));
        return validationResults;
    }
}
