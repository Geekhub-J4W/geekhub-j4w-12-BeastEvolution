package edu.geekhub.homework.reflection;

import java.lang.reflect.Field;
import java.util.Objects;

public class FieldUtil {
    private FieldUtil() {
    }

    public static void setFieldValue(Object target, String fieldName, Object fieldValue) {
        if (Objects.isNull(target) || Objects.isNull(fieldName) || Objects.isNull(fieldValue)) {
            throw new IllegalArgumentException(
                String.format("Passed argument:%s with value equal null", target)
            );
        }
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(
                String.format("Failed to set field value for field:%s in class:%s",
                    fieldName,
                    target.getClass().getName()
                ),
                e
            );
        }
    }
}
