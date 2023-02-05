package edu.geekhub.homework.validation;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringValidatorTest {
    @Test
    @Tag("StringValidator")
    void Validate_correct_string_in_string() {
        String string = "ab";
        char[] validChars = {'a', 'b'};

        StringValidator stringValidator = new StringValidator(validChars);
        boolean result = stringValidator.validateString(string);

        assertThat(result)
            .isTrue();
    }

    @Test
    @Tag("StringValidator")
    void Validate_incorrect_string_in_string() {
        String string = "abc";
        char[] validChars = {'a', 'b'};

        StringValidator stringValidator = new StringValidator(validChars);
        boolean result = stringValidator.validateString(string);

        assertThat(result)
            .isFalse();
    }

    @Test
    @Tag("StringValidator")
    void Invalid_to_validate_string_if_it_equal_null() {
        StringValidator stringValidator = new StringValidator(new char[0]);

        assertThatThrownBy(() -> stringValidator.validateString(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input string can't be null");
    }
}
