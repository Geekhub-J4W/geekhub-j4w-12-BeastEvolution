package com.web.entity.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Price implements Comparable<Price> {

    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Price convertTo(Currency targetCurrency) {
        BigDecimal conversionRate = currency.getConversionRate(targetCurrency);
        BigDecimal convertedValue = amount.multiply(conversionRate)
            .setScale(targetCurrency.getFractionDigits(), RoundingMode.HALF_UP);

        return new Price(convertedValue, targetCurrency);
    }

    @Override
    public int compareTo(Price targetPrice) {
        Price targetPriceInSameCurrency = targetPrice.convertTo(this.currency);

        return this.amount.compareTo(targetPriceInSameCurrency.amount);
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
        return Objects.equals(amount, price.amount) && currency == price.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Price{"
            + "value=" + amount
            + ", currency=" + currency
            + '}';
    }
}
