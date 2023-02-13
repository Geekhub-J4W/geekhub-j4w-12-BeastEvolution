package com.web.product;

import com.web.product.validation.ProductValidator;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator = new ProductValidator();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String saveToRepository(Product product) {
        List<String> validationResults = productValidator.validate(product);
        if (!validationResults.isEmpty()) {
            return String.join("\n", validationResults);
        }

        return productRepository.saveToRepository(product);
    }
}
