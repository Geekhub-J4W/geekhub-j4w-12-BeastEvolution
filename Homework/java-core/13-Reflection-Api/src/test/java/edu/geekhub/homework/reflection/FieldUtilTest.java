package edu.geekhub.homework.reflection;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FieldUtilTest {
    @Test
    @Tag("Correct")
    void Set_value_to_object_declared_field() {
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
    @Tag("Exception")
    void Invalid_to_set_value_to_object_field_if__object_not_contain_field_with_same_field_name() {
        ObjectWithField target = new ObjectWithField("value");
        String fieldName = "someName";
        String fieldValue = "newValue";

        assertThatThrownBy(() -> FieldUtil.setFieldValue(target, fieldName, fieldValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("Failed to set field value for field:%s in class:%s",
                fieldName,
                target.getClass().getName()
            ));
    }

    @Test
    @Tag("Exception")
    void Invalid_to_set_value_to_constant_field_of_object() {
        ObjectWithConstantField target = new ObjectWithConstantField();
        String fieldName = "someName";
        String fieldValue = "newValue";

        assertThatThrownBy(() -> FieldUtil.setFieldValue(target, fieldName, fieldValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("Failed to set field value for field:%s in class:%s",
                fieldName,
                target.getClass().getName()
            ));
    }

    @Test
    @Tag("Exception")
    void Invalid_to_set_value_to_object_field_if_object_equal_null() {
        ObjectWithField target = null;
        String fieldName = "field";
        String fieldValue = "newValue";

        assertThatThrownBy(() -> FieldUtil.setFieldValue(target, fieldName, fieldValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "Passed target Object with value equal null"
            );
    }

    @Test
    @Tag("Exception")
    void Invalid_to_set_value_to_object_field_if_field_name_equal_null() {
        ObjectWithField target = new ObjectWithField("value");
        String fieldName = null;
        String fieldValue = "newValue";

        assertThatThrownBy(() -> FieldUtil.setFieldValue(target, fieldName, fieldValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "Passed fieldName String with value equal null"
            );
    }

    @Test
    @Tag("Exception")
    void Invalid_to_set_value_to_object_field_if_field_value_equal_null() {
        ObjectWithField target = new ObjectWithField("value");
        String fieldName = "field";
        String fieldValue = null;

        assertThatThrownBy(() -> FieldUtil.setFieldValue(target, fieldName, fieldValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "Passed fieldName Object fieldValue value equal null"
            );
    }
}
