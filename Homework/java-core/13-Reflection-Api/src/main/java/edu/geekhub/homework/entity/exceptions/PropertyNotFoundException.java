package edu.geekhub.homework.entity.exceptions;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException(String message) {
        super(message);
    }
}
