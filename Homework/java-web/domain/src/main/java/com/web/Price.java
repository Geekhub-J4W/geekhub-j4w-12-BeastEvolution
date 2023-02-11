package com.web;

import com.web.product.Currency;

import java.math.BigDecimal;

public record Price(BigDecimal value, Currency currency) {
}
