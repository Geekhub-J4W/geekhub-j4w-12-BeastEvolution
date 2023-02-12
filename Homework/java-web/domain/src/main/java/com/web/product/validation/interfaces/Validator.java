package com.web.product.validation.interfaces;

import com.web.product.Product;
import com.web.product.validation.exceptions.ValidationException;
import java.util.Optional;

public interface Validator<T extends Product> {

    Optional<ValidationException> validate(T product);
}
