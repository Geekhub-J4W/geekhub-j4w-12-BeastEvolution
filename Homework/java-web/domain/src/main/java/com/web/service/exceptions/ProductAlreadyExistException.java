package com.web.service.exceptions;

public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
