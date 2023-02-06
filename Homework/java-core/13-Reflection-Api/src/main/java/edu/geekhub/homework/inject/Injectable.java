package edu.geekhub.homework.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Injectable {
    String propertyName() default "";
}
