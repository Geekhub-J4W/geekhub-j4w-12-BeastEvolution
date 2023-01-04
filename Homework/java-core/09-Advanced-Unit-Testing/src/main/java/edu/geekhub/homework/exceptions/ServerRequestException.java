package edu.geekhub.homework.exceptions;

public class ServerRequestException extends RuntimeException {
    public ServerRequestException(String message) {
        super(message);
    }
}
