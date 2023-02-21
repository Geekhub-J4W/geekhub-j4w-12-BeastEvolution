package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.repository.ProductRepository;
import com.web.service.exceptions.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Test
    @Tag("ProductRepository")
    void Add_product_to_repository(@Mock List<Product> products) {
        //Assert
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);

        //Act
        productRepository.saveToRepository(product);

        //Assert
        verify(products).add(product);
    }

    @Test
    @Tag("ProductRepository")
    void Invalid_to_add_product_equal_null_repository() {
        Product product = null;
        ProductRepository productRepository = new ProductRepository(new ArrayList<>());

        assertThatThrownBy(() -> productRepository.saveToRepository(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't add product equal null to repository");
    }

    @Test
    @Tag("ProductRepository")
    void Get_correct_result_when_add_valid_product_to_repository() {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        ProductRepository productRepository = new ProductRepository(new ArrayList<>());

        Product result = productRepository.saveToRepository(product);

        assertThat(result)
            .isEqualTo(product);
    }

    @Test
    @Tag("ProductRepository")
    void Get_correct_result_when_delete_product_from_repository() {
        String productName = "Name";
        Product product = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );

        List<Product> products = new ArrayList<>();
        products.add(product);

        ProductRepository productRepository = new ProductRepository(
            products
        );

        Product result = productRepository.deleteFromRepository(productName);

        assertThat(result)
            .isEqualTo(product);
    }

    @Test
    @Tag("ProductRepository")
    void Delete_product_from_repository() {
        String productName = "Name";
        Product product = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );

        List<Product> products = new ArrayList<>();
        products.add(product);

        ProductRepository productRepository = new ProductRepository(
            products
        );

        productRepository.deleteFromRepository(productName);

        assertThat(products)
            .isEmpty();
    }

    @Test
    @Tag("ProductRepository")
    void Invalid_delete_product_that_not_exist_in_repository(
        @Mock List<Product> products
    ) {
        String productName = "Name";
        Product product = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);
        when(products.contains(product)).thenReturn(false);

        assertThatThrownBy(() -> productRepository.deleteFromRepository(productName))
            .isInstanceOf(ProductNotFoundException.class)
            .hasMessage("Failed to find product with name: Name");

        verify(products, never()).remove(product);
    }

    @Test
    @Tag("ProductRepository")
    void Invalid_to_delete_product_with_name_that_equal_null_from_repository() {
        String productName = null;

        ProductRepository productRepository = new ProductRepository(new ArrayList<>());

        assertThatThrownBy(() -> productRepository.deleteFromRepository(productName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't delete product with name equal null to repository");
    }

    @Test
    @Tag("ProductRepository")
    void Get_all_products_from_repository_with_one_element() {
        List<Product> productList = List.of(
            new Product(
                "ProductOne",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );
        ProductRepository productRepository = new ProductRepository(productList);

        List<Product> result = productRepository.getAll();

        assertThat(result)
            .isEqualTo(productList);
    }

    @Test
    void Find_product_by_name() {
        //Arrange
        String productName = "Product";

        Product product = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(
            List.of(
                product
            )
        );

        //Act
        Product result = productRepository.findByName(productName);

        //Assert
        assertThat(result)
            .isEqualTo(product);
    }

    @Test
    void Invalid_to_find_product_by_name_if_repository_not_contain_product_with_this_name() {
        //Arrange
        String productName = "Product";

        ProductRepository productRepository = new ProductRepository(
            List.of()
        );

        //Act
        //Assert
        assertThatThrownBy(() -> productRepository.findByName(productName))
            .isInstanceOf(ProductNotFoundException.class)
            .hasMessage("Failed to find product with name: " + productName);
    }

}
