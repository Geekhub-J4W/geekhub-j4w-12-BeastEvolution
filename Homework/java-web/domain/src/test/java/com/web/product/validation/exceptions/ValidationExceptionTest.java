package com.web.product.validation.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ValidationExceptionTest {

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
}
