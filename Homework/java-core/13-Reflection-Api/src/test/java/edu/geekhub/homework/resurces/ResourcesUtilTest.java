package edu.geekhub.homework.resurces;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResourcesUtilTest {
    @Test
    @Tag("ResourceUtil")
    void Get_data_from_empty_file_from_resources_by_name() {
        String fileName = "emptyApplication.properties";

        byte[] fileData = ResourcesUtil.getFileData(fileName, ResourcesUtilTest.class);

        assertThat(fileData)
            .isEmpty();
    }

    @Test
    @Tag("ResourceUtil")
    void Get_data_from_file_with_data_from_resources_by_name() {
        String fileName = "application.properties";

        byte[] fileData = ResourcesUtil.getFileData(fileName, ResourcesUtilTest.class);

        assertThat(new String(fileData))
            .isEqualTo("field=newValue");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_get_data_from_file_if_file_name_equal_null() {
        String fileName = null;

        assertThatThrownBy(() -> ResourcesUtil.getFileData(fileName, ResourcesUtilTest.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Passed fileName String with value equal null");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_get_data_from_file_if_file_package_class_equal_null() {
        String fileName = "application.properties";

        assertThatThrownBy(() -> ResourcesUtil.getFileData(fileName, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Passed cazz Class<?> with value equal null");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_get_data_from_file_if_file_not_exist() {
        String fileName = "non_existent_file.txt";

        assertThatThrownBy(() -> ResourcesUtil.getFileData(fileName, ResourcesUtilTest.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("File with name: " + fileName + " not exist" );
    }
}
