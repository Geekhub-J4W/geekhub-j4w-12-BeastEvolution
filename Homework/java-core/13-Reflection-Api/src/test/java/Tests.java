import edu.geekhub.homework.entity.Property;
import edu.geekhub.homework.parsers.PropertyParser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Tests {
    @Test
    @Tag("Correct")
    void get_property_name_from_line() {
        String line = "name=value";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("name", "value");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }

    @Test
    @Tag("Correct")
    void get_another_property_name1_from_line() {
        String line = "name1=value1";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("name1", "value1");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }

    @Test
    @Tag("Correct")
    void get_property_that_have_attachment_from_line() {
        String line = "gh.inject.name1=value1";
        PropertyParser propertyParser = new PropertyParser();

        Property expectedProperty = new Property("name1", "value1");

        Property property = propertyParser.getProperty(line);

        assertThat(property)
            .isEqualTo(expectedProperty);
    }
}
