package com.web.product.validation;

import com.web.product.Currency;
import com.web.product.Price;
import com.web.product.validation.exceptions.ValidationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AmountValidator {

    private static final Price MAX_PRICE_VALUE = new Price(
        new BigDecimal(1_000_000),
        Currency.USD
    );


    public List<ValidationException> validate(Price price) {

        if (Objects.isNull(price.getAmount())) {
            throw new IllegalArgumentException("Product price amount should not null, but was:"
                + price.getAmount());
        }

        List<ValidationException> validationExceptions = new ArrayList<>();

        if (isPriceValueNegativeNumber(price.getAmount())) {
            validationExceptions.add(
                new ValidationException("Product price amount should be a positive number, but was:"
                    + price.getAmount())
            );
        }

        if (isPriceValueGreaterThenMaxValue(price)) {
            validationExceptions.add(
                new ValidationException(
                    String.format(
                        "Product price amount should not be greater then %s, but was: %s",
                        MAX_PRICE_VALUE,
                        price.convertTo(MAX_PRICE_VALUE.getCurrency())
                    )
                )
            );
        }

        if (isValueHaveInvalidScale(price)) {
            validationExceptions.add(
                new ValidationException(
                    String.format(
                        "Product price amount should have number of fraction digits"
                            + " no greater then %s for %s currency type, but was: %s",
                        price.getCurrency().getFractionDigits(),
                        price.getCurrency(),
                        price.getAmount().scale()
                    )
                )
            );
        }

        return validationExceptions;
    }

    private boolean isPriceValueNegativeNumber(BigDecimal priceAmount) {
        return priceAmount.signum() == -1;
    }

    private boolean isPriceValueGreaterThenMaxValue(Price productPrice) {
        return productPrice.compareTo(MAX_PRICE_VALUE) > 0;
    }

    private boolean isValueHaveInvalidScale(Price productPrice) {
        return productPrice.getAmount().scale() > productPrice.getCurrency().getFractionDigits();
    }
}
