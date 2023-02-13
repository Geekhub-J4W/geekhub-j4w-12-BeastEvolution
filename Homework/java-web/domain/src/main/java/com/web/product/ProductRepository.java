package com.web.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public String saveToRepository(Product product) {
        if (products.contains(product)) {
            return "Product was not added to the repository because it is already there.";
        }

        products.add(product);

        return "Product was added to repository.";
    }
}
