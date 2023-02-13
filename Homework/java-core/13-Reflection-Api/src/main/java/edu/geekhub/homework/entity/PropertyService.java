package edu.geekhub.homework.entity;

import edu.geekhub.homework.parsers.PropertyParser;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class PropertyService {
    private final Logger logger;

    public PropertyService(Logger logger) {
        this.logger = logger;
    }

    public List<Property> getPropertiesFromFile(byte[] fileData) {
        if (Objects.isNull(fileData)) {
            throw new IllegalArgumentException("File data can't be null");
        }
        String fileText = new String(fileData, StandardCharsets.UTF_8);
        String[] fileLines = fileText.split("\n");

        PropertyParser propertyParser = new PropertyParser();
        return Arrays.stream(fileLines)
            .map(line -> {
                try {
                    return propertyParser.getProperty(line);
                } catch (IllegalArgumentException e) {
                    logger.warning(
                        String.format("%s:%s\n", line, e.getMessage())
                    );
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .toList();

    }

}
