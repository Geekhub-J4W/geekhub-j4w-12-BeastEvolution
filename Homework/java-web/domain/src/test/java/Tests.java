import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.Price;
import com.web.product.Currency;
import com.web.product.Product;
import com.web.product.exceptions.InvalidPriceException;
import com.web.product.validation.ProductNameValidator;
import com.web.product.validation.ProductValidator;
import com.web.product.validation.exceptions.ValidationException;
import com.web.valodation.StringValidator;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Tests {

    @Test
    void Create_price_with_currency_type() {
        BigDecimal value = new BigDecimal("10");
        Currency currency = Currency.USD;

        Price price = new Price(value, currency);

        assertThat(price.getValue())
            .isEqualTo(new BigDecimal(10));
        assertThat(price.getCurrency())
            .isEqualTo(Currency.USD);
    }

    @Test
    void Invalid_to_create_price_with_negative_price() {
        BigDecimal value = new BigDecimal("-1");

        assertThatThrownBy(() -> new Price(value, Currency.USD))
            .isInstanceOf(InvalidPriceException.class)
            .hasMessage("Price can't be negative, but set: " + value);
    }

    @Test
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


    @Test
    void Validate_product_name() {
        Product product = new Product(
            "New product",
            new Price(new BigDecimal("10"), Currency.UAH)
        );
        ProductValidator<Product> productValidator = new ProductNameValidator<>();
        Optional<ValidationException> expectedResult = Optional.empty();

        Optional<ValidationException> result = productValidator.validate(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    void Validation_exceptions_is_equal_if_it_massages_is_equal() {
        ValidationException someException = new ValidationException("Massage");
        ValidationException anotherException = new ValidationException("Massage");

        boolean result = someException.equals(anotherException);

        assertThat(result)
            .isTrue();
    }

    @Test
    void Validation_exceptions_not_equal_if_it_massages_not_equal() {
        ValidationException someException = new ValidationException("Some massage");
        ValidationException anotherException = new ValidationException("Another massage");

        boolean result = someException.equals(anotherException);

        assertThat(result)
            .isFalse();
    }

    @Test
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
        ProductValidator<Product> productValidator = new ProductNameValidator<>();
        Optional<ValidationException> expectedResult = Optional.of(
            new ValidationException(
                "Product name must contain only English and Ukrainian alphabet characters,"
                    + " digits and punctuation marks, but set: " + name
            )
        );

        Optional<ValidationException> result = productValidator.validate(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    void Validate_product_name_with_lowercase_first_symbols() {
        //Arrange
        String name = "name";
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductValidator<Product> productValidator = new ProductNameValidator<>();

        Optional<ValidationException> expectedResult = Optional.of(
            new ValidationException(
                "Product name must begin with Uppercase symbol, but was set:" + name
            )
        );

        //Act
        Optional<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    void Validate_product_with_empty_name() {
        //Arrange
        String name = "";
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductValidator<Product> productValidator = new ProductNameValidator<>();

        Optional<ValidationException> expectedResult = Optional.of(
            new ValidationException(
                "Product name must be not empty, but was set:" + name
            )
        );

        //Act
        Optional<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    void Validate_product_with_name_equal_null() {
        //Arrange
        String name = null;
        Product product = new Product(
            name,
            new Price(new BigDecimal("10"), Currency.UAH)
        );

        ProductValidator<Product> productValidator = new ProductNameValidator<>();

        Optional<ValidationException> expectedResult = Optional.of(
            new ValidationException(
                "Product name must be not equal null, but was set:" + name
            )
        );

        //Act
        Optional<ValidationException> result = productValidator.validate(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }
}
