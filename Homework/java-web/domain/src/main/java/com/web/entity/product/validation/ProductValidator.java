package com.web.entity.product.validation;

import com.web.entity.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductNameValidator productNameValidator;
    private final PriceValidator priceValidator;

    public ProductValidator(ProductNameValidator productNameValidator,
        PriceValidator priceValidator) {
        this.productNameValidator = productNameValidator;
        this.priceValidator = priceValidator;
    }

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
