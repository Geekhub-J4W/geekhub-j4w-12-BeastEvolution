package edu.geekhub.homework.reflection;

import edu.geekhub.homework.reflection.fields.UnsupportedTypeException;

import java.util.HashMap;
import java.util.Map;

public class StringConverter {
    private static final Map<Class<?>, Converter> CONVERTERS = new HashMap<>();

    static {
        CONVERTERS.put(String.class, value -> value);
        CONVERTERS.put(Integer.class, Integer::parseInt);
        CONVERTERS.put(Double.class, Double::parseDouble);
        CONVERTERS.put(Float.class, Float::parseFloat);
        CONVERTERS.put(Long.class, Long::parseLong);
        CONVERTERS.put(Short.class, Short::parseShort);
        CONVERTERS.put(Boolean.class, Boolean::parseBoolean);
        CONVERTERS.put(Byte.class, Byte::parseByte);
    }

    private StringConverter() {
    }

    public static <T> T convert(String value, Class<T> type) {
        if (!CONVERTERS.containsKey(type)) {
            throw new UnsupportedTypeException("Unsupported type" + type.getName());
        }

        return type.cast(CONVERTERS.get(type).convert(value));
    }
}
