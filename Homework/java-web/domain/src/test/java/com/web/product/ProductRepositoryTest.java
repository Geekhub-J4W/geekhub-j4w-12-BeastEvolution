package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.repository.ProductRepository;
import com.web.repository.exceptions.RepositoryException;
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
        when(products.remove(product)).thenReturn(true);

        productRepository.deleteFromRepository(product);

        verify(products).remove(product);
    }

    @Test
    @Tag("ProductRepository")
    void Invalid_delete_product_that_not_exist_in_repository(
        @Mock List<Product> products
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductRepository productRepository = new ProductRepository(products);
        when(products.remove(product)).thenReturn(false);

        assertThatThrownBy(() -> productRepository.deleteFromRepository(product))
            .isInstanceOf(RepositoryException.class)
            .hasMessage("Failed to remove product from the repository");

        verify(products).remove(product);
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
