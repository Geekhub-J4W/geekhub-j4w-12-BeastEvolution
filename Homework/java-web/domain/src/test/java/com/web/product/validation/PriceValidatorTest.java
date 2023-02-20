package com.web.product.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.validation.AmountValidator;
import com.web.entity.product.validation.PriceValidator;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceValidatorTest {

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_price_currency_equal_null(@Mock AmountValidator amountValidator) {
        //Arrange
        Currency currency = null;
        Price price = new Price(new BigDecimal("10"), currency);

        PriceValidator priceValidator = new PriceValidator(amountValidator);

        //Act
        //Assert
        assertThatThrownBy(() -> priceValidator.validate(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product price currency should not be equal null, but was: " + currency);
    }

    @Test
    @Tag("PriceValidator")
    void Invalid_to_validate_product_price_if_price_equal_null(
        @Mock AmountValidator amountValidator) {
        Price price = null;
        PriceValidator priceValidator = new PriceValidator(amountValidator);

        assertThatThrownBy(() -> priceValidator.validate(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product price should not be equal null, but was: " + price);
    }
}
