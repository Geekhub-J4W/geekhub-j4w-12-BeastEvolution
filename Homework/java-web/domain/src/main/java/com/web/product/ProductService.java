package com.web.product;

import com.web.product.validation.ProductValidator;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator = new ProductValidator();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String saveToRepository(Product product) {
        List<String> validationResults = productValidator.validate(product);
        if (!validationResults.isEmpty()) {
            return "Product was not save to the repository because:\n"
                + String.join("\n", validationResults);
        }

        return productRepository.saveToRepository(product);
    }

    public String deleteFromRepository(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException(
                "Can't delete product equal null"
            );
        }

        return productRepository.deleteFromRepository(product);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public List<Product> getAllSorted(Comparator<Product> comparator) {

        return productRepository.getAll().stream()
            .sorted(comparator)
            .toList();
    }
}
