package com.web.product.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.web.product.ProductNameCharacters;
import com.web.valodation.StringValidator;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductNameValidatorTest {

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
    void Validate_product_name(String productName) {
        ProductNameValidator productValidator = new ProductNameValidator(
            new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
        );
        List<String> expectedResult = List.of();

        List<String> result = productValidator.validate(productName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @Tag("ProductNameValidator")
    @ValueSource(strings = {
        "Мыло",
        "Name\n",
        "Name÷"
    })
    void Validate_product_name_with_incorrect_symbols(String productName) {
        ProductNameValidator productValidator = new ProductNameValidator(
            new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
        );
        List<String> expectedResult = List.of(
            "Product name must contain only English and Ukrainian alphabet characters,"
                + " digits and punctuation marks, but set: " + productName
        );

        List<String> result = productValidator.validate(productName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_name_with_lowercase_first_symbols(@Mock StringValidator stringValidator) {
        //Arrange
        String productName = "name";

        when(stringValidator.validateString(productName)).thenReturn(true);
        ProductNameValidator productValidator = new ProductNameValidator(stringValidator);

        List<String> expectedResult = List.of(
            "Product name must begin with Uppercase symbol, but was set:" + productName
        );

        //Act
        List<String> result = productValidator.validate(productName);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_with_empty_name(@Mock StringValidator stringValidator) {
        //Arrange
        String productName = "";

        ProductNameValidator productValidator = new ProductNameValidator(stringValidator);

        List<String> expectedResult = List.of(
            "Product name must be not empty, but was set:" + productName
        );

        //Act
        List<String> result = productValidator.validate(productName);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductNameValidator")
    void Validate_product_with_name_equal_null(@Mock StringValidator stringValidator) {
        //Arrange
        String productName = null;

        ProductNameValidator productValidator = new ProductNameValidator(stringValidator);

        //Act
        //Assert
        assertThatThrownBy(() -> productValidator.validate(productName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product name must be not equal null, but was: " + productName);
    }
}
