package com.web.product.validation;

import com.web.product.Price;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class PriceValidator {

    private final AmountValidator amountValidator;

    public PriceValidator(AmountValidator amountValidator) {
        this.amountValidator = amountValidator;
    }

    public List<String> validate(Price price) {
        if (Objects.isNull(price)) {
            throw new IllegalArgumentException(
                "Product price should not be equal null, but was: " + price
            );
        } else if (Objects.isNull(price.getCurrency())) {
            throw new IllegalArgumentException(
                "Product price currency should not be equal null, but was: "
                    + price.getCurrency()
            );
        }

        return amountValidator.validate(price);
    }
}
