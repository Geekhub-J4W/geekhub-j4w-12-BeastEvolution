package edu.geekhub.homework.task3.exceptions;

public class CalculationException extends RuntimeException {
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
