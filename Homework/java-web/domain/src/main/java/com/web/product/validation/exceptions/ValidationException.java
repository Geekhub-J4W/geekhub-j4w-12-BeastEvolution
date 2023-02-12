package com.web.product.validation.exceptions;

import java.util.Objects;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationException validationException = (ValidationException) o;
        return Objects.equals(this.getMessage(), validationException.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMessage());
    }
}
