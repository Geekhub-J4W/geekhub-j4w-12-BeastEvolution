package edu.geekhub.homework.inject;

import edu.geekhub.homework.entity.Property;
import edu.geekhub.homework.entity.PropertyService;
import edu.geekhub.homework.entity.exceptions.PropertyNotFoundException;
import edu.geekhub.homework.files.ResourceUtil;
import edu.geekhub.homework.reflection.FieldUtil;
import edu.geekhub.homework.reflection.PrimitiveClassUtil;
import edu.geekhub.homework.reflection.StringConverter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static edu.geekhub.homework.entity.PropertyUtil.findFirstPropertyByName;

public class InjectProcessor {
    private static final Logger logger = Logger.getLogger(InjectProcessor.class.getName());
    static {
        logger.setUseParentHandlers(false);
        logger.addHandler(new ConsoleHandler());
    }

    private final List<Property> properties;

    public InjectProcessor(String resourceFileName) {
        byte[] propertiesFileData = ResourceUtil.getFileData(resourceFileName, InjectProcessor.class);

        PropertyService propertyService = new PropertyService(logger);
        this.properties = propertyService.getPropertiesFromFile(propertiesFileData);
    }

    public void process(Object object) {
        Field[] objectDeclaredFields = object.getClass().getDeclaredFields();
        Arrays.stream(objectDeclaredFields)
            .filter(field -> field.isAnnotationPresent(Injectable.class))
            .forEach(field -> setValueForInjectableField(field, object));
    }

    private void setValueForInjectableField(Field injectableField, Object object) {
        if (injectableField.isAnnotationPresent(Injectable.class)) {
            Injectable injectable = injectableField.getAnnotation(Injectable.class);
            String fieldValueInStringFormat;
            try {
                if (injectable.propertyName().isEmpty()) {
                    fieldValueInStringFormat = findFirstPropertyByName(properties, injectableField.getName()).value();
                } else {
                    fieldValueInStringFormat = findFirstPropertyByName(properties, injectable.propertyName()).value();
                }

                FieldUtil.setFieldValue(
                    object,
                    injectableField.getName(),
                    StringConverter.convert(fieldValueInStringFormat, getFieldType(injectableField))
                );
            } catch (PropertyNotFoundException e) {
                logger.warning(e.getMessage());
            }
        }
    }

    private Class<?> getFieldType(Field field) {
        Class<?> fieldType = field.getType();
        if (fieldType.isPrimitive()) {
            fieldType = PrimitiveClassUtil.getWrapperClass(fieldType);
        }

        return fieldType;
    }

}
