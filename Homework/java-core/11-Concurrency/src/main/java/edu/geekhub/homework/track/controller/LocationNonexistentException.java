package edu.geekhub.homework.track.controller;

public class LocationNonexistentException extends RuntimeException{
    public LocationNonexistentException(String message) {
        super(message);
    }
}
