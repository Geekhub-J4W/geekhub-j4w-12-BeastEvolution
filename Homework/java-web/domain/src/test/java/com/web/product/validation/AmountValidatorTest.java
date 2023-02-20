package com.web.product.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.product.Currency;
import com.web.product.Price;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AmountValidatorTest {

    @Test
    @Tag("AmountValidator")
    void Validate_price_with_correct_amount() {
        //Arrange
        Price price = new Price(new BigDecimal("10"), Currency.USD);

        AmountValidator amountValidator = new AmountValidator();
        List<String> expectedResult = List.of();

        //Act
        List<String> result = amountValidator.validate(price);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_negative_price_amount() {
        //Arrange
        Price price = new Price(new BigDecimal("-10"), Currency.USD);

        AmountValidator amountValidator = new AmountValidator();
        List<String> expectedResult = List.of(
            "Product price amount should be a positive number, but was:"
                + price.getAmount()
        );

        //Act
        List<String> result = amountValidator.validate(price);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("AmountValidator")
    void Invalid_to_validate_product_with_price_amount_equal_null() {
        //Arrange
        Price price = new Price(null, Currency.USD);

        AmountValidator amountValidator = new AmountValidator();

        //Act
        //Assert
        assertThatThrownBy(() -> amountValidator.validate(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product price amount should not null, but was:"
                + price.getAmount());
    }

    @Test
    @Tag("AmountValidator")
    void Validate_product_with_price_amount_greater_than_allowed() {
        //Arrange
        Price price = new Price(new BigDecimal(1_000_001), Currency.USD);

        Price maxPrice = new Price(new BigDecimal(1_000_000), Currency.USD);

        AmountValidator amountValidator = new AmountValidator();
        List<String> expectedResult = List.of(
            String.format("Product price amount should not be greater then %s, but was: %s",
                maxPrice,
                price.convertTo(maxPrice.getCurrency())
            )
        );

        //Act
        List<String> result = amountValidator.validate(price);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("AmountValidator")
    void Validate_product_with_price_amount_with_illegal_number_of_fraction_digits() {
        //Arrange
        Currency currency = Currency.USD;
        BigDecimal amount = new BigDecimal("10.234");
        Price price = new Price(amount, currency);

        AmountValidator priceValidator = new AmountValidator();
        List<String> expectedResult = List.of(
            String.format(
                "Product price amount should have number of fraction digits no greater then %s "
                    + "for %s currency type, but was: %s",
                currency.getFractionDigits(),
                currency,
                amount.scale()
            )
        );

        //Act
        List<String> result = priceValidator.validate(price);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("AmountValidator")
    void Validate_price_amount_equal_zero() {
        Price price = new Price(new BigDecimal("0"), Currency.USD);
        AmountValidator amountValidator = new AmountValidator();
        List<String> expectedResult = List.of(
            "Product price amount should not be 0, but was: "
                + price.getAmount()
        );

        List<String> result = amountValidator.validate(price);

        assertThat(result)
            .isEqualTo(expectedResult);
    }
}
