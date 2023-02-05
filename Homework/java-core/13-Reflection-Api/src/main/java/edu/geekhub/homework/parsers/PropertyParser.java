package edu.geekhub.homework.parsers;

import edu.geekhub.homework.entity.Property;

public class PropertyParser {
    public Property getProperty(String line) {
        String[] propertyData = line.split("=");
        propertyData[0] = deletePropertyNamePrefix(propertyData[0]);

        return new Property(propertyData[0], propertyData[1]);
    }

    private String deletePropertyNamePrefix(String namePath) {
        String[] elementsOfPropertyNamePath = namePath.split("\\.");
        return elementsOfPropertyNamePath[elementsOfPropertyNamePath.length - 1];
    }
}
