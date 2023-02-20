package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.entity.product.ProductNameCharacters;
import com.web.entity.product.validation.AmountValidator;
import com.web.entity.product.validation.PriceValidator;
import com.web.entity.product.validation.ProductNameValidator;
import com.web.entity.product.validation.ProductValidator;
import com.web.repository.ProductRepository;
import com.web.service.ProductService;
import com.web.service.exceptions.ProductAlreadyExistException;
import com.web.service.exceptions.ProductNotFoundException;
import com.web.service.exceptions.ValidationException;
import com.web.valodation.StringValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Test
    @Tag("ProductService")
    void Save_valid_product(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        productService.saveToRepository(product);

        verify(productRepository).saveToRepository(product);
    }

    @Test
    @Tag("ProductService")
    void Get_correct_result_when_save_valid_product(@Mock ProductValidator productValidator) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        ProductService productService = new ProductService(
            new ProductRepository(new ArrayList<>()),
            productValidator
        );
        String expectedResult = "Product was added to repository.";
        String result = productService.saveToRepository(product);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductService")
    void Save_invalid_product(@Mock ProductRepository productRepository) {
        String name = "name - мыло";
        Price price = new Price(new BigDecimal("-1000001.123"), Currency.USD);
        Product product = new Product(name, price);

        ProductService productService = new ProductService(
            productRepository,
            new ProductValidator(
                new ProductNameValidator(
                    new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
                ),
                new PriceValidator(new AmountValidator())
            )
        );

        try {
            productService.saveToRepository(product);
        } catch (Exception e) {
        }

        verify(productRepository, never()).saveToRepository(product);
    }

    @Test
    @Tag("ProductService")
    void Invalid_to_save_incorrect_product() {
        //Arrange
        String name = "name - мыло";
        Price price = new Price(new BigDecimal("-1000001.123"), Currency.USD);
        Product product = new Product(name, price);

        String expectedExceptionMassage = "Product was not save to the repository because:\n"
            + "Product name must begin with Uppercase symbol, but was set:name - мыло\n"
            + "Product name must contain only English and Ukrainian alphabet characters,"
            + " digits and punctuation marks, but set: name - мыло\n"
            + "Product price amount should be a positive number, but was:-1000001.123\n"
            + "Product price amount should have number of fraction digits no greater then 2"
            + " for USD currency type, but was: 3";

        ProductService productService = new ProductService(
            new ProductRepository(new ArrayList<>()),
            new ProductValidator(
                new ProductNameValidator(
                    new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
                ),
                new PriceValidator(new AmountValidator())
            )
        );

        //Act
        //Assert
        assertThatThrownBy(() -> productService.saveToRepository(product))
            .isInstanceOf(ValidationException.class)
            .hasMessage(expectedExceptionMassage);
    }

    @Test
    @Tag("ProductService")
    void Invalid_to_save_product_equal_null() {
        Product product = null;
        ProductService productService = new ProductService(
            new ProductRepository(new ArrayList<>()),
            new ProductValidator(
                new ProductNameValidator(
                    new StringValidator(ProductNameCharacters.getProductNameValidCharacters())
                ),
                new PriceValidator(new AmountValidator())
            )
        );

        assertThatThrownBy(() -> productService.saveToRepository(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Product should not be equal null, but was: " + product);
    }

    @Test
    @Tag("ProductService")
    void Delete_valid_product(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(
            List.of(product)
        );

        productService.deleteFromRepository(product);

        verify(productRepository).deleteFromRepository(product);
    }

    @Test
    @Tag("ProductService")
    void Invalid_to_delete_product_equal_null(@Mock ProductValidator productValidator) {
        Product product = null;
        ProductService productService = new ProductService(
            new ProductRepository(new ArrayList<>()),
            productValidator
        );

        assertThatThrownBy(() -> productService.deleteFromRepository(product))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't delete product equal null");
    }

    @Test
    @Tag("ProductService")
    void Get_all_products(@Mock ProductValidator productValidator) {
        List<Product> productList = List.of(
            new Product(
                "ProductOne",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );
        ProductService productService = new ProductService(
            new ProductRepository(productList),
            productValidator
        );

        List<Product> result = productService.getAll();

        assertThat(result)
            .isEqualTo(productList);
    }

    @Test
    @Tag("ProductService")
    void Get_all_products_sorted_by_product_name_in_natural_order(
        @Mock ProductValidator productValidator
    ) {
        List<Product> productList = List.of(
            new Product(
                "Product2",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product1",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "AProduct",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );
        ProductService productService = new ProductService(
            new ProductRepository(productList),
            productValidator
        );
        List<Product> expectedResult = List.of(
            new Product(
                "AProduct",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product1",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product2",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );

        List<Product> result = productService.getAllSorted(Comparator.comparing(Product::getName));

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductService")
    void Get_all_products_sorted_by_product_name_in_reversed_order(
        @Mock ProductValidator productValidator
    ) {
        List<Product> productList = List.of(
            new Product(
                "Product2",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product1",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "AProduct",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );
        ProductService productService = new ProductService(
            new ProductRepository(productList),
            productValidator
        );
        List<Product> expectedResult = List.of(
            new Product(
                "Product2",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product1",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "AProduct",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );

        List<Product> result = productService.getAllSorted(
            Comparator.comparing(Product::getName).reversed()
        );

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductService")
    void Get_all_products_sorted_by_product_price_in_natural_order(
        @Mock ProductValidator productValidator
    ) {
        List<Product> productList = List.of(
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.EUR)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.UAH)
            )
        );
        ProductService productService = new ProductService(
            new ProductRepository(productList),
            productValidator
        );
        List<Product> expectedResult = List.of(
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.UAH)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.EUR)
            )
        );

        List<Product> result = productService.getAllSorted(Comparator.comparing(Product::getPrice));

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductService")
    void Get_all_products_sorted_by_product_price_in_reversed_order(
        @Mock ProductValidator productValidator
    ) {
        List<Product> productList = List.of(
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.EUR)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.UAH)
            )
        );
        ProductService productService = new ProductService(
            new ProductRepository(productList),
            productValidator
        );
        List<Product> expectedResult = List.of(
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.EUR)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.USD)
            ),
            new Product(
                "Product",
                new Price(new BigDecimal("10"), Currency.UAH)
            )
        );

        List<Product> result = productService.getAllSorted(
            Comparator.comparing(Product::getPrice).reversed()
        );

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductService")
    void Add_product_that_already_exist_in_repository(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(
            List.of(
                product
            )
        );

        //Act
        try {
            productService.saveToRepository(product);
        } catch (Exception e) {
        }

        //Assert
        verify(productRepository, never()).saveToRepository(product);
    }

    @Test
    @Tag("ProductService")
    void Invalid_to_add_valid_product_that_already_exist_in_repository(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(
            List.of(
                product
            )
        );

        //Act
        //Assert
        assertThatThrownBy(() -> productService.saveToRepository(product))
            .isInstanceOf(ProductAlreadyExistException.class)
            .hasMessage("Product was not added to the repository because it is already there.");
    }

    @Test
    @Tag("ProductService")
    void Invalid_to_add_valid_product_that_have_same_name_with_another_product_in_repository(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        //Arrange
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Product product1 = new Product(
            "Name",
            new Price(new BigDecimal("11"), Currency.UAH)
        );

        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(
            List.of(
                product
            )
        );

        //Act
        //Assert
        assertThatThrownBy(() -> productService.saveToRepository(product1))
            .isInstanceOf(ProductAlreadyExistException.class)
            .hasMessage("Product was not added to the repository because it is already there.");
    }

    @Test
    void Invalid_to_delete_product_that_not_exist_in_repository(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(List.of());

        assertThatThrownBy(() -> productService.deleteFromRepository(product))
            .isInstanceOf(ProductNotFoundException.class)
            .hasMessage(
                "Failed to remove product from the repository,"
                    + " because repository not contain it"
            );
    }

    @Test
    void Try_to_delete_product_that_not_exist_in_repository(
        @Mock ProductRepository productRepository,
        @Mock ProductValidator productValidator
    ) {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );

        ProductService productService = new ProductService(
            productRepository,
            productValidator
        );

        when(productRepository.getAll()).thenReturn(List.of());

        try {
            productService.deleteFromRepository(product);
        } catch (Exception e) {
        }

        verify(productRepository, never()).deleteFromRepository(product);
    }
}