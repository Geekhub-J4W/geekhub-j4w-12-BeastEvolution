package edu.geekhub.homework.parsers;

import edu.geekhub.homework.entity.Property;

public class PropertyParser {
    public Property getProperty(String line) {
        String[] propertyData = line.split("=");
        return new Property(propertyData[0], propertyData[1]);
    }
}
