package edu.geekhub.homework.parsers;

import edu.geekhub.homework.entity.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PropertyParserTest {
    PropertyParser propertyParser;

    @BeforeEach
    void setUp() {
        propertyParser = new PropertyParser();
    }

    @Test
    @Tag("Correct")
    void Parse_line_to_property() {
        String line = "gh.inject.name=value";

        Property expectedResult = new Property("name", "value");

        Property actualResult = propertyParser.getProperty(line);

        assertThat(actualResult)
            .isEqualTo(expectedResult);
    }
    @Test
    @Tag("Exception")
    void Invalid_to_parse_if_input_line_is_null() {
        assertThatThrownBy(() -> propertyParser.getProperty(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input line cannot be null or empty");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_parse_if_input_line_is_empty() {
        assertThatThrownBy(() -> propertyParser.getProperty(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input line cannot be null or empty");
    }

    @ParameterizedTest
    @Tag("Exception")
    @ValueSource(strings = {
        "text",
        "text=text=text",
        "text=text=text=text"
    })
    void Invalid_to_parse_if_input_line_contain_illegal_number_of_name_value_separators(String line) {
        assertThatThrownBy(() -> propertyParser.getProperty(line))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Incorrect input line format. It must contain exactly one '=' symbol");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_parse_if_property_name_in_input_line_is_empty() {
        assertThatThrownBy(() -> propertyParser.getProperty("=value"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Name cannot be empty");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_parse_if_property_value_in_input_line_is_empty() {
        assertThatThrownBy(() -> propertyParser.getProperty("name="))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Value cannot be empty");
    }

    @ParameterizedTest
    @Tag("Exception")
    @ValueSource(strings = {
        ".text=text",
        "text.=text",
        "text..text=text"
    })
    void Invalid_to_parse_if_property_namespace_is_contain_empty_elements(String line) {
        assertThatThrownBy(() -> propertyParser.getProperty(line))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Namespace cannot contain empty elements separated by '.'");
    }

}
