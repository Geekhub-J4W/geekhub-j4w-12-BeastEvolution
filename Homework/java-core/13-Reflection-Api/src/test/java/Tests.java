import edu.geekhub.homework.entity.Property;
import edu.geekhub.homework.entity.PropertyService;
import edu.geekhub.homework.files.ResourceUtil;
import edu.geekhub.homework.parsers.PropertyParser;
import edu.geekhub.homework.reflection.FieldUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

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
    @Tag("PropertyService")
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
    @Tag("PropertyService")
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
    @Tag("PropertyService")
    void create_log_when_file_text_have_incorrect_property_line() {
        byte[] fileData = "asd".getBytes(StandardCharsets.UTF_8);
        Logger logger = mock(Logger.class);
        PropertyService propertyService = new PropertyService(logger);

        propertyService.getPropertiesFromFile(fileData);

        verify(logger).warning(
            "asd:Incorrect input line format. It must contain exactly one '=' symbol\r\n"
        );
    }

    @Test
    @Tag("FieldUtil")
    void Set_value_to_class_declared_field() {
        //Arrange
        ObjectWithField target = new ObjectWithField("value");
        String fieldName = "field";
        String fieldValue = "newValue";

        ObjectWithField expectedResult = new ObjectWithField("newValue");

        //Act
        FieldUtil.setFieldValue(target, fieldName, fieldValue);

        //Assert
        assertThat(target)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ResourceUtil")
    void Get_file_from_resources_by_name() {
        String fileName = "emptyApplication.properties";

        byte[] fileData = ResourceUtil.getFileData(fileName, Tests.class);

        assertThat(fileData)
            .isEmpty();
    }

    @Test
    @Tag("ResourceUtil")
    void Get_file_with_data_from_resources_by_name() {
        String fileName = "application.properties";

        byte[] fileData = ResourceUtil.getFileData(fileName, Tests.class);

        assertThat(new String(fileData))
            .isEqualTo("field=newValue");
    }

}
