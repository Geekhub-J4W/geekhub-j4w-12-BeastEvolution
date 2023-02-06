package edu.geekhub.homework.parsers;

import edu.geekhub.homework.entity.Property;

public class PropertyParser {
    public Property getProperty(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Input line cannot be null or empty");
        }

        String[] propertyData = line.split("=", -1);
        if (propertyData.length != 2) {
            throw new IllegalArgumentException("Incorrect input line format. It must contain exactly one '=' symbol");
        }

        String name = validateName(propertyData[0]);
        String value = validateValue(propertyData[1]);

        return new Property(name, value);
    }

    private String validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        String[] elementsOfPropertyNamePath = name.split("\\.", -1);
        for (String element : elementsOfPropertyNamePath) {
            if (element.isEmpty()) {
                throw new IllegalArgumentException("Namespace cannot contain empty elements separated by '.'");
            }
        }
        return elementsOfPropertyNamePath[elementsOfPropertyNamePath.length - 1];
    }

    private String validateValue(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be empty");
        }
        return value;
    }
}
