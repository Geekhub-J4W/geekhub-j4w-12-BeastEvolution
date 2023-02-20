package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CurrencyTest {

    @Test
    @Tag("Currency")
    void Get_rate_for_conversion_UAH_to_EUR() {
        Currency uah = Currency.UAH;
        Currency eur = Currency.EUR;
        BigDecimal expectedResult = new BigDecimal("0.025");

        BigDecimal uahToEurConversationRate = uah.getConversionRate(eur);

        assertThat(uahToEurConversationRate)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Get_rate_for_conversion_EUR_to_UAH() {
        Currency uah = Currency.UAH;
        Currency eur = Currency.EUR;
        BigDecimal expectedResult = new BigDecimal("39.630");

        BigDecimal uahToEurConversationRate = eur.getConversionRate(uah);

        assertThat(uahToEurConversationRate)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Get_rate_for_conversion_UAH_to_UAH() {
        Currency uah = Currency.UAH;
        BigDecimal expectedResult = new BigDecimal("1.000");

        BigDecimal uahToEurConversationRate = uah.getConversionRate(uah);

        assertThat(uahToEurConversationRate)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Convert_price_in_USD_to_price_in_EUR() {
        Price priceInUsd = new Price(new BigDecimal("10"), Currency.USD);
        Currency targetCurrency = Currency.EUR;
        Price expectedResult = new Price(new BigDecimal("9.35"), Currency.EUR);

        Price priceInEur = priceInUsd.convertTo(targetCurrency);

        assertThat(priceInEur)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Convert_price_in_UAH_to_price_in_EUR() {
        Price priceInUsd = new Price(new BigDecimal("1000"), Currency.UAH);
        Currency targetCurrency = Currency.EUR;
        Price expectedResult = new Price(new BigDecimal("25.00"), Currency.EUR);

        Price priceInEur = priceInUsd.convertTo(targetCurrency);

        assertThat(priceInEur)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Convert_price_with_fraction_digits_in_USD_to_price_in_EUR() {
        Price priceInUsd = new Price(new BigDecimal("31.794"), Currency.USD);
        Currency targetCurrency = Currency.EUR;
        Price expectedResult = new Price(new BigDecimal("29.73"), Currency.EUR);

        Price priceInEur = priceInUsd.convertTo(targetCurrency);

        assertThat(priceInEur)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Convert_price_with_fraction_digits_in_UAH_to_price_in_EUR() {
        Price priceInUsd = new Price(new BigDecimal("1379.9371"), Currency.UAH);
        Currency targetCurrency = Currency.EUR;
        Price expectedResult = new Price(new BigDecimal("34.50"), Currency.EUR);

        Price priceInEur = priceInUsd.convertTo(targetCurrency);

        assertThat(priceInEur)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Currency")
    void Convert_price_in_EUR_to_price_in_EUR() {
        Price priceInUsd = new Price(new BigDecimal("37.43"), Currency.EUR);
        Currency targetCurrency = Currency.EUR;
        Price expectedResult = new Price(new BigDecimal("37.43"), Currency.EUR);

        Price priceInEur = priceInUsd.convertTo(targetCurrency);

        assertThat(priceInEur)
            .isEqualTo(expectedResult);
    }
}
