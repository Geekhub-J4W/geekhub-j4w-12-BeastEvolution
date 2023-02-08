package edu.geekhub.homework.entity;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class PropertyServiceTest {
    @Test
    @Tag("Correct")
    void get_properties_from_file_data() {
        //Arrange
        byte[] fileData = "gh.inject.name=value".getBytes(StandardCharsets.UTF_8);

        Logger logger = mock(Logger.class);
        PropertyService propertyService = new PropertyService(logger);

        List<Property> expectedProperties = List.of(
            new Property("name", "value")
        );
        //Act
        List<Property> properties = propertyService.getPropertiesFromFile(fileData);

        //Assert
        assertThat(properties)
            .isEqualTo(expectedProperties);

        verifyNoInteractions(logger);
    }

    @Test
    @Tag("Exception")
    void Incorrect_to_get_properties_from_file_data_if_it_equal_to_null() {
        //Arrange
        Logger logger = mock(Logger.class);
        PropertyService propertyService = new PropertyService(logger);

        //Act,Assert
        assertThatThrownBy(() -> propertyService.getPropertiesFromFile(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("File data can't be null");

        verifyNoInteractions(logger);
    }

    @Test
    @Tag("Correct")
    void get_properties_with_incorrect_format_form_file_data() {
        //Arrange
        byte[] fileData = "asd".getBytes(StandardCharsets.UTF_8);

        Logger logger = mock(Logger.class);

        PropertyService propertyService = new PropertyService(logger);
        List<Property> expectedProperties = List.of();

        //Act
        List<Property> properties = propertyService.getPropertiesFromFile(fileData);

        //Assert
        assertThat(properties)
            .isEqualTo(expectedProperties);
    }

    @Test
    @Tag("Logging")
    void create_log_when_file_text_have_incorrect_property_line() {
        byte[] fileData = "asd".getBytes(StandardCharsets.UTF_8);
        Logger logger = mock(Logger.class);
        PropertyService propertyService = new PropertyService(logger);

        propertyService.getPropertiesFromFile(fileData);

        verify(logger).warning(
            "asd:Incorrect input line format. It must contain exactly one '=' symbol\r\n"
        );
    }
}
