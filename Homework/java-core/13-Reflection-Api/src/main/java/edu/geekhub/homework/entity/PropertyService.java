package edu.geekhub.homework.entity;

import edu.geekhub.homework.parsers.PropertyParser;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PropertyService {
    public List<Property> getPropertiesFromFile(byte[] fileData) {
        if (Objects.isNull(fileData)) {
            throw new IllegalArgumentException("File data can't be null");
        }
        String fileText = new String(fileData, StandardCharsets.UTF_8);
        String[] fileLines = fileText.split(System.getProperty("line.separator"));

        PropertyParser propertyParser = new PropertyParser();
        return Arrays.stream(fileLines)
            .map(propertyParser::getProperty)
            .toList();
    }

}
