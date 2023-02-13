import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.product.Currency;
import com.web.product.Price;
import com.web.product.Product;
import com.web.product.validation.PriceValidator;
import com.web.product.validation.ProductNameValidator;
import com.web.product.validation.exceptions.ValidationException;
import com.web.valodation.StringValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Tests {

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
    @Tag("Product")
    void Create_product() {
        String name = "New product";
        Price price = new Price(new BigDecimal("10"), Currency.USD);

        Product product = new Product(name, price);

        assertThat(product.getName())
            .isEqualTo(name);
        assertThat(product.getPrice())
            .isEqualTo(price);
    }

    @ParameterizedTest
    @Tag("StringValidator")
    @ValueSource(strings =
        {
            "a",
            "aa"
        })
    void Validate_string_with_valid_chars(String validString) {
        char[] validChars = {'a'};
        StringValidator stringValidator = new StringValidator(validChars);

        boolean result = stringValidator.validateString(validString);

        assertThat(result)
            .isTrue();
    }

    @ParameterizedTest
    @Tag("StringValidator")
    @ValueSource(strings =
        {
            "b",
            "bb",
            "ab",
            "abc"
        })
    void Validate_string_with_invalid_chars(String invalidString) {
        char[] validChars = {'a'};
        StringValidator stringValidator = new StringValidator(validChars);

        boolean result = stringValidator.validateString(invalidString);

        assertThat(result)
            .isFalse();
    }


    @ParameterizedTest
    @Tag("ProductNameValidator")
    @ValueSource(strings = {
        "Name",
        "NAME",
        "SOME NAME",
        "Some name",
        "Some Name",
        "Name with version 01",
        "Name: Some name",
        "Name(add info)",
        "Name value = 10",
        "Ім'я",
        "ІМ'Я",
        "Якесь ім'я",
        "ЯКЕСЬ ІМ'Я",
        "Якесь Ім'я",
        "Ім'я з версією 01",
        "Ім'я: Якесь ім'я",
        "Ім'я(додаткова інформація)",
        "Значення імені = 10",
    })
    void Validate_product_name(String name) {
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );
        ProductNameValidator<Product> productValidator = new ProductNameValidator<>();
        List<ValidationException> expectedResult = new ArrayList<>();

        List<ValidationException> result = productValidator.validate(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ValidationException")
    void Validation_exceptions_is_equal_if_it_massages_is_equal() {
        ValidationException someException = new ValidationException("Massage");
        ValidationException anotherException = new ValidationException("Massage");

        boolean result = someException.equals(anotherException);

        assertThat(result)
            .isTrue();
    }

    @Test
    @Tag("ValidationException")
    void Validation_exceptions_not_equal_if_it_massages_not_equal() {
        ValidationException someException = new ValidationException("Some massage");
        ValidationException anotherException = new ValidationException("Another massage");

        boolean result = someException.equals(anotherException);

        assertThat(result)
            .isFalse();
    }

    @Test
    @Tag("ValidationException")
    void Validation_exceptions_hashcode_is_equal_if_it_massages_is_equal() {
        //Arrange
        ValidationException someException = new ValidationException("Massage");
        ValidationException anotherException = new ValidationException("Massage");

        Integer someExceptionHashcode = someException.hashCode();
        Integer anotherExceptionHashcode = anotherException.hashCode();

        //Act
        boolean result = someExceptionHashcode.equals(anotherExceptionHashcode);

        //Assert
        assertThat(result)
            .isTrue();
    }

    @Test
    @Tag("ValidationException")
    void Validation_exceptions_hashcode_not_equal_if_it_massages_not_equal() {
        //Arrange
        ValidationException someException = new ValidationException("Some massage");
        ValidationException anotherException = new ValidationException("Another massage");

        Integer someExceptionHashcode = someException.hashCode();
        Integer anotherExceptionHashcode = anotherException.hashCode();

        //Act
        boolean result = someExceptionHashcode.equals(anotherExceptionHashcode);

        //Assert
        assertThat(result)
            .isFalse();
    }

    @ParameterizedTest
    @Tag("ProductNameValidator")
    @ValueSource(strings = {
        "Мыло",
        "Name\n",
        "Name÷"
    })
    void Validate_product_name_with_incorrect_symbols(String name) {
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );
        ProductNameValidator<Product> productValidator = new ProductNameValidator<>();
        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                "Product name must contain only English and Ukrainian alphabet characters,"
                    + " digits and punctuation marks, but set: " + name
            )
        );

        List<ValidationException> result = productValidator.validate(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_name_with_lowercase_first_symbols() {
        //Arrange
        String name = "name";
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductNameValidator<Product> productValidator = new ProductNameValidator<>();

        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                "Product name must begin with Uppercase symbol, but was set:" + name
            )
        );

        //Act
        List<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_with_empty_name() {
        //Arrange
        String name = "";
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductNameValidator<Product> productValidator = new ProductNameValidator<>();

        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                "Product name must be not empty, but was set:" + name
            )
        );

        //Act
        List<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_with_name_equal_null() {
        //Arrange
        String name = null;
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductNameValidator<Product> productValidator = new ProductNameValidator<>();

        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                "Product name must be not equal null, but was set:" + name
            )
        );

        //Act
        List<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

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

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_correct_price() {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        PriceValidator<Product> priceValidator = new PriceValidator<>();
        List<ValidationException> expectedResult = List.of();

        //Act
        List<ValidationException> result = priceValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_negative_price_amount() {
        //Arrange
        Price price = new Price(new BigDecimal("-10"), Currency.USD);
        Product product = new Product(
            "Name",
            price
        );

        PriceValidator<Product> priceValidator = new PriceValidator<>();
        List<ValidationException> expectedResult = List.of(
            new ValidationException("Product price amount should be a positive number, but was:"
                + price.getAmount())
        );

        //Act
        List<ValidationException> result = priceValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_price_amount_equal_null() {
        //Arrange
        Price price = new Price(null, Currency.USD);
        Product product = new Product(
            "Name",
            price
        );

        PriceValidator<Product> priceValidator = new PriceValidator<>();

        //Act
        //Assert
        assertThatThrownBy(() -> priceValidator.validate(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product price amount should not null, but was:"
                + price.getAmount());
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_price_amount_greater_than_allowed() {
        //Arrange
        Price price = new Price(new BigDecimal(1_000_001), Currency.USD);
        Product product = new Product(
            "Name",
            price
        );

        Price maxPrice = new Price(new BigDecimal(1_000_000), Currency.USD);

        PriceValidator<Product> priceValidator = new PriceValidator<>();
        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                String.format("Product price amount should not be greater then %s, but was: %s",
                    maxPrice,
                    price.convertTo(maxPrice.getCurrency())
                )
            )
        );

        //Act
        List<ValidationException> result = priceValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_price_currency_equal_null() {
        //Arrange
        Currency currency = null;
        Price price = new Price(new BigDecimal("10"), currency);
        Product product = new Product(
            "Name",
            price
        );

        PriceValidator<Product> priceValidator = new PriceValidator<>();
        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                "Product price currency should not be equal null, but was: " + currency
            )
        );

        //Act
        List<ValidationException> result = priceValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount() {
        Price priceInUsd = new Price(new BigDecimal("10"), Currency.USD);
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isGreaterThan(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_EUR_that_have_equal_amount() {
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);

        int result = priceInEur.compareTo(priceInEur);

        assertThat(result)
            .isEqualTo(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_UAH_with_price_in_USD_that_have_equal_amount() {
        Price priceInUah = new Price(new BigDecimal("10"), Currency.UAH);
        Price priceInUsd = new Price(new BigDecimal("10"), Currency.USD);

        int result = priceInUah.compareTo(priceInUsd);

        assertThat(result)
            .isLessThan(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_UAH_with_price_in_USD_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInUah = new Price(new BigDecimal("321.27"), Currency.UAH);
        Price priceInUsd = new Price(new BigDecimal("321.27"), Currency.USD);

        int result = priceInUah.compareTo(priceInUsd);

        assertThat(result)
            .isLessThan(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInUsd = new Price(new BigDecimal("2323.13"), Currency.USD);
        Price priceInEur = new Price(new BigDecimal("2323.13"), Currency.EUR);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isGreaterThan(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_EUR_that_have_equal_amount_that_have_fraction_digits() {
        Price priceInEur = new Price(new BigDecimal("329.8"), Currency.EUR);

        int result = priceInEur.compareTo(priceInEur);

        assertThat(result)
            .isEqualTo(0);
    }

    @Test
    @Tag("Price")
    void Compare_price_in_EUR_with_price_in_USD_that_have_equal_amount_in_one_currency() {
        Price priceInEur = new Price(new BigDecimal("10"), Currency.EUR);
        Price priceInUsd = new Price(new BigDecimal("10.70"), Currency.USD);

        int result = priceInEur.compareTo(priceInUsd);

        assertThat(result)
            .isEqualTo(0);
    }

    @Test
    @Tag("PriceValidator")
    void Validate_product_with_price_amount_with_illegal_number_of_fraction_digits() {
        //Arrange
        Currency currency = Currency.USD;
        BigDecimal amount = new BigDecimal("10.234");
        Price price = new Price(amount, currency);
        Product product = new Product(
            "Name",
            price
        );

        PriceValidator<Product> priceValidator = new PriceValidator<>();
        List<ValidationException> expectedResult = List.of(
            new ValidationException(
                String.format(
                    "Product price amount should have number of fraction digits no greater then %s "
                        + "for %s currency type, but was: %s",
                    currency.getFractionDigits(),
                    currency,
                    amount.scale()
                )
            )
        );

        //Act
        List<ValidationException> result = priceValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }
}
