package edu.geekhub.orcostat.exeptions;

public class IllegalOptionException extends RuntimeException{
    public IllegalOptionException(String message) {
        super(message);
    }

    public IllegalOptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
