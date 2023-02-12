package com.web.product.validation.interfaces;

import com.web.product.validation.exceptions.ValidationException;
import java.util.List;

public interface Validator<T> {

    List<ValidationException> validate(T product);
}
