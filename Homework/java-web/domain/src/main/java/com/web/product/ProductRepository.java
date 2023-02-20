package com.web.product;

import com.web.exceptions.RepositoryException;
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
        if (products.remove(product)) {
            return "Product was deleted from repository";
        }
        throw new RepositoryException("Failed to remove product from the repository");
    }

    public List<Product> getAll() {
        return products;
    }
}
