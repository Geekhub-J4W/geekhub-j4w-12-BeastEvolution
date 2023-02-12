package com.web.product.validation;

import com.web.product.Currency;
import com.web.product.Price;
import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class ProductPriceValidator<T extends Product> implements ProductValidator<T> {

    private static final Price MAX_PRICE_VALUE = new Price(
        new BigDecimal(1_000_000),
        Currency.USD
    );

    @Override
    public Optional<ValidationException> validate(Product product) {
        if (Objects.isNull(product.getPrice().getValue())) {

            return Optional.of(
                new ValidationException("Product price value should not null, but was:"
                    + product.getPrice().getValue()));
        } else if (Objects.isNull(product.getPrice().getCurrency())) {

            return Optional.of(
                new ValidationException(
                    "Product price currency should not be equal null, but was: "
                        + product.getPrice().getCurrency()
                )
            );
        } else if (isPriceValueNegativeNumber(product.getPrice().getValue())) {

            return Optional.of(
                new ValidationException("Product price value should be a positive number, but was:"
                    + product.getPrice().getValue())
            );
        } else if (isPriceValueGreaterThenMaxValue(product.getPrice())) {

            return Optional.of(
                new ValidationException(
                    String.format("Product price value should not be greater then %s, but was: %s",
                        MAX_PRICE_VALUE,
                        product.getPrice().convertTo(MAX_PRICE_VALUE.getCurrency())
                    )
                )
            );
        } else if (isValueHaveInvalidScale(product.getPrice())) {

            return Optional.of(
                new ValidationException(
                    String.format(
                        "Product price value should have number of fraction digits"
                            + " no greater then %s for %s currency type, but was: %s",
                        product.getPrice().getCurrency().getFractionDigits(),
                        product.getPrice().getCurrency(),
                        product.getPrice().getValue().scale()
                    )
                )
            );
        }

        return Optional.empty();
    }

    private boolean isPriceValueNegativeNumber(BigDecimal priceValue) {
        return priceValue.signum() == -1;
    }

    private boolean isPriceValueGreaterThenMaxValue(Price productPrice) {
        return productPrice.compareTo(MAX_PRICE_VALUE) > 0;
    }

    private boolean isValueHaveInvalidScale(Price productPrice) {
        return productPrice.getValue().scale() > productPrice.getCurrency().getFractionDigits();
    }
}
