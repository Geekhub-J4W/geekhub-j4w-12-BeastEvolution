package com.web;

import com.web.product.Currency;
import com.web.product.exceptions.InvalidPriceException;
import java.math.BigDecimal;

public class Price {

    private final BigDecimal value;
    private final Currency currency;

    public Price(BigDecimal value, Currency currency) {
        if (isPriceValueNegative(value)) {
            throw new InvalidPriceException("Price can't be negative, but set: " + value);
        }

        this.value = value;
        this.currency = currency;
    }

    private boolean isPriceValueNegative(BigDecimal value) {
        return value.signum() < 0;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
