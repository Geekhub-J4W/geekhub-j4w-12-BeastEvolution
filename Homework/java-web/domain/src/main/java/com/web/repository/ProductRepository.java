package com.web.repository;

import com.web.entity.product.Product;
import com.web.service.exceptions.ProductNotFoundException;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public Product saveToRepository(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("Can't add product equal null to repository");
        }

        products.add(product);

        return product;
    }

    public Product deleteFromRepository(String productName) {
        if (Objects.isNull(productName)) {
            throw new IllegalArgumentException(
                "Can't delete product with name equal null to repository"
            );
        }

        Product productToDelete = findByName(productName);
        products.remove(productToDelete);

        return productToDelete;
    }

    public List<Product> getAll() {
        return products;
    }

    public Product findByName(String productName) {
        Product productEntityDouble = new Product(productName, null);
        if (products.contains(productEntityDouble)) {
            return products.get(products.indexOf(productEntityDouble));
        }

        throw new ProductNotFoundException("Failed to find product with name: " + productName);
    }
}
