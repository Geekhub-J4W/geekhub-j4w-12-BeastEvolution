package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    @Tag("Price")
    void Create_price_with_currency_type() {
        BigDecimal amount = new BigDecimal("10");
        Currency currency = Currency.USD;

        Price price = new Price(amount, currency);

        assertThat(price.getAmount())
            .isEqualTo(new BigDecimal(10));
        assertThat(price.getCurrency())
            .isEqualTo(Currency.USD);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount() {
        Price priceInUsd = new Price(new BigDecimal("10"), Currency.USD);
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isPositive();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_EUR_that_have_equal_amount() {
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);

        int result = priceInEur.compareTo(priceInEur);

        assertThat(result)
            .isZero();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_UAH_with_price_in_USD_that_have_equal_amount() {
        Price priceInUah = new Price(new BigDecimal("10"), Currency.UAH);
        Price priceInUsd = new Price(new BigDecimal("10"), Currency.USD);

        int result = priceInUah.compareTo(priceInUsd);

        assertThat(result)
            .isNegative();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_UAH_with_price_in_USD_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInUah = new Price(new BigDecimal("321.27"), Currency.UAH);
        Price priceInUsd = new Price(new BigDecimal("321.27"), Currency.USD);

        int result = priceInUah.compareTo(priceInUsd);

        assertThat(result)
            .isNegative();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInUsd = new Price(new BigDecimal("2323.13"), Currency.USD);
        Price priceInEur = new Price(new BigDecimal("2323.13"), Currency.EUR);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isPositive();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_EUR_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInEur = new Price(new BigDecimal("329.8"), Currency.EUR);

        int result = priceInEur.compareTo(priceInEur);

        assertThat(result)
            .isZero();
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount_in_one_currency() {
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);
        Price priceInUsd = new Price(new BigDecimal("10.70"), Currency.USD);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isZero();
    }

}
