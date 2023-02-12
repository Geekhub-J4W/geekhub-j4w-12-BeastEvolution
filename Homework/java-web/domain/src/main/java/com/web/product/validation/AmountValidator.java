package com.web.product.validation;

import com.web.product.Currency;
import com.web.product.Price;
import com.web.product.Product;
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

    public List<ValidationException> validate(Product product) {
        List<ValidationException> validationExceptions = new ArrayList<>();

        if (Objects.isNull(product.getPrice().getAmount())) {
            validationExceptions.add(
                new ValidationException("Product price amount should not null, but was:"
                    + product.getPrice().getAmount())
            );
            return validationExceptions;
        }

        if (isPriceValueNegativeNumber(product.getPrice().getAmount())) {
            validationExceptions.add(
                new ValidationException("Product price amount should be a positive number, but was:"
                    + product.getPrice().getAmount())
            );
        }

        if (isPriceValueGreaterThenMaxValue(product.getPrice())) {
            validationExceptions.add(
                new ValidationException(
                    String.format(
                        "Product price amount should not be greater then %s, but was: %s",
                        MAX_PRICE_VALUE,
                        product.getPrice().convertTo(MAX_PRICE_VALUE.getCurrency())
                    )
                )
            );
        }

        if (isValueHaveInvalidScale(product.getPrice())) {
            validationExceptions.add(
                new ValidationException(
                    String.format(
                        "Product price amount should have number of fraction digits"
                            + " no greater then %s for %s currency type, but was: %s",
                        product.getPrice().getCurrency().getFractionDigits(),
                        product.getPrice().getCurrency(),
                        product.getPrice().getAmount().scale()
                    )
                )
            );
        }

        return validationExceptions;
    }

    private boolean isPriceValueNegativeNumber(BigDecimal priceValue) {
        return priceValue.signum() == -1;
    }

    private boolean isPriceValueGreaterThenMaxValue(Price productPrice) {
        return productPrice.compareTo(MAX_PRICE_VALUE) > 0;
    }

    private boolean isValueHaveInvalidScale(Price productPrice) {
        return productPrice.getAmount().scale() > productPrice.getCurrency().getFractionDigits();
    }
}
