package edu.geekhub.homework.reflection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PrimitiveClassUtil {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = new HashMap<>();

    static {
        PRIMITIVE_TO_WRAPPER.put(int.class, Integer.class);
        PRIMITIVE_TO_WRAPPER.put(long.class, Long.class);
        PRIMITIVE_TO_WRAPPER.put(short.class, Short.class);
        PRIMITIVE_TO_WRAPPER.put(float.class, Float.class);
        PRIMITIVE_TO_WRAPPER.put(double.class, Double.class);
        PRIMITIVE_TO_WRAPPER.put(byte.class, Byte.class);
        PRIMITIVE_TO_WRAPPER.put(boolean.class, Boolean.class);
        PRIMITIVE_TO_WRAPPER.put(char.class, Character.class);
    }

    private PrimitiveClassUtil() {
    }

    public static Class<?> getWrapperClass(Class<?> primetiveTypeClass) {
        if (Objects.isNull(primetiveTypeClass)) {
            throw new IllegalArgumentException(
                ("Passed class is equal null")
            );
        } else if(!PRIMITIVE_TO_WRAPPER.containsKey(primetiveTypeClass)) {
            throw new IllegalArgumentException(
                String.format("Passed class:%s is not a primitive type class", primetiveTypeClass)
            );
        }
        return PRIMITIVE_TO_WRAPPER.get(primetiveTypeClass);
    }
}
