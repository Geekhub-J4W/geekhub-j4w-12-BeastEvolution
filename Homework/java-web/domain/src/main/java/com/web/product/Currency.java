package com.web.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Currency {
    USD(new BigDecimal("1.0")),
    UAH(new BigDecimal("0.027")),
    EUR(new BigDecimal("1.07"));


    private final BigDecimal rate;

    Currency(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getConversionRate(Currency targetCurrency) {
        return this.rate.divide(targetCurrency.rate, 3, RoundingMode.HALF_UP);
    }
}
