package com.web.product;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public String saveToRepository(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("Can't add product equal null to repository");
        }

        products.add(product);

        return "Product was added to repository.";
    }

    public String deleteFromRepository(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException(
                "Can't delete product equal null to repository"
            );
        }
        if (!products.contains(product)) {
            return "Failed to remove product from the repository,"
                + " because repository not contain it";
        }
        if (products.remove(product)) {
            return "Product was deleted from repository";
        }
        return "Failed to remove product from the repository";
    }

    public List<Product> getAll() {
        return products;
    }
}
