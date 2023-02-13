package com.web.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public String saveToRepository(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("Can't add product equal null to repository");
        }
        if (products.contains(product)) {
            return "Product was not added to the repository because it is already there.";
        }

        products.add(product);

        return "Product was added to repository.";
    }
}
