package com.web.product.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.entity.product.ProductNameCharacters;
import com.web.entity.product.validation.AmountValidator;
import com.web.entity.product.validation.PriceValidator;
import com.web.entity.product.validation.ProductNameValidator;
import com.web.entity.product.validation.ProductValidator;
import com.web.valodation.StringValidator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductValidatorTest {

    @Test
    @Tag("ProductValidator")
    void Validate_correct_product(
        @Mock PriceValidator priceValidator,
        @Mock ProductNameValidator productNameValidator
    ) {
        //Arrange
        Product product = new Product("Name",
            new Price(new BigDecimal("15"), Currency.USD)
        );

        ProductValidator productValidator = new ProductValidator(
            productNameValidator,
            priceValidator
        );

        List<String> expectedResult = List.of();

        //Act
        List<String> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductValidator")
    void Validate_incorrect_product() {
        //Arrange
        String name = "name - мыло";
        Price price = new Price(new BigDecimal("-1000001.123"), Currency.USD);
        Product product = new Product(name, price);

        List<String> expectedResult = List.of(
            "Product name must begin with Uppercase symbol, but was set:" + name,
            "Product name must contain only English and Ukrainian alphabet characters,"
                + " digits and punctuation marks, but set: " + name,
            "Product price amount should be a positive number, but was:" + price.getAmount(),
            String.format(
                "Product price amount should have number of fraction digits"
                    + " no greater then %s for %s currency type, but was: %s",
                price.getCurrency().getFractionDigits(),
                price.getCurrency(),
                price.getAmount().scale()
            )
        );

        ProductValidator productValidator = new ProductValidator(
            new ProductNameValidator(
                new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
            ),
            new PriceValidator(new AmountValidator())
        );

        //Act
        List<String> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductValidator")
    void Invalid_to_validate_product_equal_null(
        @Mock ProductNameValidator productNameValidator,
        @Mock PriceValidator priceValidator
    ) {
        Product product = null;
        ProductValidator productValidator = new ProductValidator(
            productNameValidator,
            priceValidator
        );

        assertThatThrownBy(() -> productValidator.validate(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product should not be equal null, but was: " + product);
    }

    @ParameterizedTest
    @Tag("ProductValidator")
    @MethodSource("ArgsProviderFactory")
    void Invalid_to_validate_product_with_null_element(Product product) {
        ProductValidator productValidator = new ProductValidator(
            new ProductNameValidator(
                new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
            ),
            new PriceValidator(new AmountValidator())
        );

        assertThatThrownBy(() -> productValidator.validate(product))
            .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Product> ArgsProviderFactory() {
        return Stream.of(
            new Product(null, new Price(new BigDecimal("10"), Currency.USD)),
            new Product("Name", new Price(null, Currency.USD)),
            new Product("Name", new Price(new BigDecimal("10"), null))
        );
    }
}
