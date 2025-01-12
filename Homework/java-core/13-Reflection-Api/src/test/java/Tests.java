import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import edu.geekhub.homework.entity.Property;
import edu.geekhub.homework.entity.PropertyService;
import edu.geekhub.homework.entity.PropertyUtil;
import edu.geekhub.homework.entity.exceptions.PropertyNotFoundException;
import edu.geekhub.homework.inject.InjectProcessor;
import edu.geekhub.homework.parsers.PropertyParser;
import edu.geekhub.homework.reflection.FieldUtil;
import edu.geekhub.homework.reflection.PrimitiveClassUtil;
import edu.geekhub.homework.reflection.StringConverter;
import edu.geekhub.homework.reflection.fields.UnsupportedTypeException;
import edu.geekhub.homework.resurces.ResourcesUtil;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
            "asd:Incorrect input line format. It must contain exactly one '=' symbol\n"
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

        byte[] fileData = ResourcesUtil.getFileData(fileName, Tests.class);

        assertThat(fileData)
            .isEmpty();
    }

    @Test
    @Tag("ResourceUtil")
    void Get_file_with_data_from_resources_by_name() {
        String fileName = "application.properties";

        byte[] fileData = ResourcesUtil.getFileData(fileName, Tests.class);

        assertThat(new String(fileData))
            .isEqualTo("field=newValue");
    }

    @Test
    @Tag("StringConverter")
    void Convert_string_variable_to_another_type() {
        String intInStringFormat = "1";
        int expectedResult = 1;

        int result = StringConverter.convert(intInStringFormat, Integer.class);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("StringConverter")
    void Invalid_to_convert_string_variable_into_illegal_type() {
        String intInStringFormat = "1";
        Class<?> type = int.class;

        assertThatThrownBy(() -> StringConverter.convert(intInStringFormat, int.class))
            .isInstanceOf(UnsupportedTypeException.class)
            .hasMessage("Unsupported type" + type.getName());
    }

    @Test
    @Tag("PropertyUtil")
    void Find_first_property_by_name_in_list_with_one_element() {
        String propertyName = "name";
        Property expectedResult = new Property(propertyName, "value");
        List<Property> properties = List.of(
            expectedResult
        );

        Property result = PropertyUtil.findFirstPropertyByName(properties, propertyName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PropertyUtil")
    void Find_first_property_by_name_in_list_with_two_element() {
        String propertyName = "name";
        Property expectedResult = new Property(propertyName, "value");
        List<Property> properties = List.of(
            new Property("anotherName", "anotherValue"),
            expectedResult
        );

        Property result = PropertyUtil.findFirstPropertyByName(properties, propertyName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PropertyUtil")
    void Find_first_property_by_name_in_list_with_three_element() {
        String propertyName = "name";
        Property expectedResult = new Property(propertyName, "value");
        List<Property> properties = List.of(
            new Property("anotherName", "anotherValue"),
            expectedResult,
            new Property("someAnotherName", "someAnotherValue")
        );

        Property result = PropertyUtil.findFirstPropertyByName(properties, propertyName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PropertyUtil")
    void Find_first_property_by_name_in_list_with_two_property_with_same_names() {
        String propertyName = "name";
        Property expectedResult = new Property(propertyName, "value");
        List<Property> properties = List.of(
            expectedResult,
            new Property(propertyName, "someAnotherValue")
        );

        Property result = PropertyUtil.findFirstPropertyByName(properties, propertyName);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("PropertyUtil")
    void Not_find_property_by_name_in_list_of_properties() {
        String searchedPropertyName = "name";
        List<Property> properties = List.of(
            new Property("anotherName", "value")
        );

        assertThatThrownBy(() -> PropertyUtil.findFirstPropertyByName(properties, searchedPropertyName))
            .isInstanceOf(PropertyNotFoundException.class)
            .hasMessage(String.format("Can't find property with name:%s in array:\n%s\n", searchedPropertyName, properties));
    }

    @Test
    @Tag("PrimitiveClassUtil")
    void Convert_primitive_type_class_to_it_wrapper_class() {
        Class<?> intClass = int.class;

        Class<?> result = PrimitiveClassUtil.getWrapperClass(intClass);

        assertThat(result)
            .isEqualTo(Integer.class);
    }

    @Test
    @Tag("PrimitiveClassUtil")
    void Invalid_to_convert_not_primitive_type_class_to_it_wrapper_class() {
        Class<?> stringClass = String.class;

        assertThatThrownBy(() -> PrimitiveClassUtil.getWrapperClass(stringClass))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("Passed class:%s is not a primitive type class", stringClass));
    }
    @Test
    @Tag("PrimitiveClassUtil")
    void Invalid_to_convert_primitive_type_class_equal_null() {
        assertThatThrownBy(() -> PrimitiveClassUtil.getWrapperClass(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Passed class is equal null");
    }

    @Test
    @Tag("InjectProcessor")
    void Set_values_of_class_instant_fields_with_injectable_annotation() {
        //Arrange
        InjectProcessor injectProcessor = new InjectProcessor("application.properties");

        ObjectWithAnnotatedField object = new ObjectWithAnnotatedField("value");
        ObjectWithAnnotatedField expectedResult = new ObjectWithAnnotatedField("newValue");

        //Act
        injectProcessor.process(object);

        //Assert
        assertThat(object)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("InjectProcessor")
    void Set_values_of_class_instant_fields_with_injectable_single_value_annotation() {
        InjectProcessor injectProcessor = new InjectProcessor("anotherApplication.properties");

        ObjectWithSingleValueAnnotatedField object = new ObjectWithSingleValueAnnotatedField("value");
        ObjectWithSingleValueAnnotatedField expectedResult = new ObjectWithSingleValueAnnotatedField("newValue");

        injectProcessor.process(object);

        assertThat(object)
            .isEqualTo(expectedResult);
    }
}
