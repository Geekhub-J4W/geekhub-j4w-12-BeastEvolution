package edu.geekhub.homework.reflection;

import java.lang.reflect.Field;

public class FieldUtil {
    private FieldUtil() {
    }

    public static void setFieldValue(Object target, String fieldName, Object fieldValue) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
