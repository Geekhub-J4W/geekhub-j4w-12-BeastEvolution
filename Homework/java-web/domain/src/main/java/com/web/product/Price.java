package com.web.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Price implements Comparable<Price> {

    private final BigDecimal value;
    private final Currency currency;

    public Price(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Price convertTo(Currency targetCurrency) {
        BigDecimal conversionRate = currency.getConversionRate(targetCurrency);
        BigDecimal convertedValue = value.multiply(conversionRate)
            .setScale(targetCurrency.getFractionDigits(), RoundingMode.HALF_UP);

        return new Price(convertedValue, targetCurrency);
    }

    @Override
    public int compareTo(Price targetPrice) {
        Price targetPriceInSameCurrency = targetPrice.convertTo(this.currency);

        return this.value.compareTo(targetPriceInSameCurrency.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return Objects.equals(value, price.value) && currency == price.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Price{"
            + "value=" + value
            + ", currency=" + currency
            + '}';
    }
}
