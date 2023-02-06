import edu.geekhub.homework.entity.Property;
import edu.geekhub.homework.entity.PropertyService;
import edu.geekhub.homework.parsers.PropertyParser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class Tests {
    @Test
    @Tag("PropertyParser")
    void get_property_name_from_line() {
        String line = "name=value";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("name", "value");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }

    @Test
    @Tag("PropertyParser")
    void get_another_property_name1_from_line() {
        String line = "anotherName=another value";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("anotherName", "another value");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }

    @Test
    @Tag("PropertyParser")
    void get_property_that_have_prefix_from_line() {
        String line = "gh.inject.name=value";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("name", "value");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }

    @Test
    @Tag("PropertyService")
    void get_properties_from_file_data() {
        byte[] fileData = "gh.inject.name=value".getBytes(StandardCharsets.UTF_8);

        Logger logger = Logger.getLogger(PropertyService.class.getName());
        logger.addHandler(new ConsoleHandler());
        PropertyService propertyService = new PropertyService(logger);

        List<Property> expectedProperties = List.of(
            new Property("name", "value")
        );

        List<Property> properties = propertyService.getPropertiesFromFile(fileData);

        assertThat(properties)
            .isEqualTo(expectedProperties);
    }

    @Test
    @Tag("PropertyService")
    void Incorrect_to_get_properties_from_file_data_if_it_equal_to_null() {
        Logger logger = Logger.getLogger(PropertyService.class.getName());
        logger.addHandler(new ConsoleHandler());
        PropertyService propertyService = new PropertyService(logger);
        assertThatThrownBy(() -> propertyService.getPropertiesFromFile(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("File data can't be null");
    }

    @Test
    @Tag("PropertyService")
    void get_properties_from_file_incorrect_line() {
        byte[] fileData = "asd\nname=value".getBytes(StandardCharsets.UTF_8);

        Logger logger = mock(Logger.class);

        PropertyService propertyService = new PropertyService(logger);
        List<Property> expectedProperties = List.of(
            new Property("name", "value")
        );

        List<Property> properties = propertyService.getPropertiesFromFile(fileData);

        assertThat(properties)
            .isEqualTo(expectedProperties);

        verify(logger).warning(
            "asd:Incorrect input line format. It must contain exactly one '=' symbol\r\n"
        );
    }
}
