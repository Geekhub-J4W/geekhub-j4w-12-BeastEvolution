package com.web.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public String saveToRepository(Product product) {
        products.add(product);

        return "Product was added to repository.";
    }
}
