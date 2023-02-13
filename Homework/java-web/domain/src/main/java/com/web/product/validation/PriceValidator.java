package com.web.product.validation;

import com.web.product.Price;
import com.web.product.validation.exceptions.ValidationException;
import java.util.List;
import java.util.Objects;

public class PriceValidator {

    private final AmountValidator amountValidator = new AmountValidator();


    public List<ValidationException> validate(Price price) {

        if (Objects.isNull(price.getCurrency())) {
            throw new IllegalArgumentException(
                "Product price currency should not be equal null, but was: "
                    + price.getCurrency()
            );
        }

        return amountValidator.validate(price);
    }
}
