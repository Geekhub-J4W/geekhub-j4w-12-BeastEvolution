package edu.geekhub.homework.reflection;

import java.lang.reflect.Field;

public class ReflectionSetter {
    public void setFieldValue(Object target, String fieldName, Object fieldValue) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle exception
        }
    }
}
