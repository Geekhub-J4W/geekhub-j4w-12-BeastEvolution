package edu.geekhub.homework.entity;

import edu.geekhub.homework.entity.exceptions.PropertyNotFoundException;

import java.util.List;

public class PropertyUtil {
    private PropertyUtil() {
    }
    public static Property findFirstPropertyByName(List<Property> properties, String name) {
        return properties.stream()
            .filter(property -> property.name().equals(name))
            .findFirst().orElseThrow(() -> new PropertyNotFoundException(
                String.format("Can't find property with name:%s in array:\n%s\n", name, properties)
            ));
    }
}
