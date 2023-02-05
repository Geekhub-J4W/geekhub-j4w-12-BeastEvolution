package edu.geekhub.homework.entity;

import edu.geekhub.homework.entity.exceptions.InvalidFieldValueException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PropertyTest {
    @Test
    void Invalid_to_create_property_with_incorrect_name() {
        String name = "name1";

        assertThatThrownBy(() -> new Property("name1", "value"))
            .isInstanceOf(InvalidFieldValueException.class)
            .hasMessage(
                String.format("Input name:%s value contains illegal chars%n", name)
            );
    }

    @Test
    void Invalid_to_create_property_with_incorrect_value() {
        String value = "value;";

        assertThatThrownBy(() -> new Property("name", value))
            .isInstanceOf(InvalidFieldValueException.class)
            .hasMessage(
                String.format("Input value:%s value contains illegal chars%n", value)
            );
    }
}
