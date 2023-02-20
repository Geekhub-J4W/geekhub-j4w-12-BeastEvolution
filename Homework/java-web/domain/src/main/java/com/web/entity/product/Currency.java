package com.web.entity.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Currency {
    USD(2, new BigDecimal("1.0")),
    UAH(2, new BigDecimal("0.027")),
    EUR(2, new BigDecimal("1.07"));

    private final int fractionDigits;
    private final BigDecimal rate;

    Currency(int fractionDigits, BigDecimal rate) {
        this.fractionDigits = fractionDigits;
        this.rate = rate;
    }

    public BigDecimal getConversionRate(Currency targetCurrency) {
        return this.rate.divide(targetCurrency.rate, 3, RoundingMode.HALF_UP);
    }

    public int getFractionDigits() {
        return fractionDigits;
    }
}
