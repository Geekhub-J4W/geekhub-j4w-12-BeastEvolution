package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void Add_product_that_already_exist_in_repository(@Mock List<Product> products) {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);

        when(products.contains(product)).thenReturn(true);

        //Act
        productRepository.saveToRepository(product);

        //Assert
        verify(products, never()).add(product);
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
        String expectedResult = "Product was added to repository.";

        String result = productRepository.saveToRepository(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductRepository")
    void Get_correct_result_when_add_valid_product_that_already_exist_in_repository() {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(new ArrayList<>());
        String expectedResult =
            "Product was not added to the repository because it is already there.";

        productRepository.saveToRepository(product);

        //Act
        String result = productRepository.saveToRepository(product);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductRepository")
    void Get_correct_result_when_delete_product_from_repository() {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(new ArrayList<>());
        productRepository.saveToRepository(product);

        String expectedResult = "Product was deleted from repository";

        String result = productRepository.deleteFromRepository(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductRepository")
    void Delete_product_from_repository(@Mock List<Product> products) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);
        when(products.contains(product)).thenReturn(true);

        productRepository.deleteFromRepository(product);

        verify(products).remove(product);
    }

    @Test
    @Tag("ProductRepository")
    void Get_correct_result_when_delete_product_that_not_exist_in_repository(
        @Mock List<Product> products
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);
        when(products.contains(product)).thenReturn(false);

        String expectedResult = "Failed to remove product from the repository,"
            + " because repository not contain it";

        String result = productRepository.deleteFromRepository(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductRepository")
    void Delete_product_that_not_exist_in_repository(@Mock List<Product> products) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);
        when(products.contains(product)).thenReturn(false);

        productRepository.deleteFromRepository(product);

        verify(products, never()).remove(product);
    }

    @Test
    @Tag("ProductRepository")
    void Invalid_to_delete_product_that_not_exist_in_repository() {
        Product product = null;

        ProductRepository productRepository = new ProductRepository(new ArrayList<>());

        assertThatThrownBy(() -> productRepository.deleteFromRepository(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't delete product equal null to repository");
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

}
