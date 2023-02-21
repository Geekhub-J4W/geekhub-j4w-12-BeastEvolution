package com.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.web.controller.ProductController;
import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Response;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Tests {

    @Test
    @Tag("ProductController")
    void Save_product(@Mock ProductService productService) {
        String request = "name=Product1&amount=10&currency=USD";

        ProductController productController = new ProductController(productService);
        Product productToSave = new Product(
            "Product1",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        when(productService.saveToRepository(productToSave)).thenReturn(productToSave);

        Response expectedResponse = Response.ok(
            productToSave
        );

        Response response = productController.saveProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    @Tag("ProductController")
    void Handle_request_to_save_product_with_invalid_parameters(
        @Mock ProductService productService
    ) {
        String request = "parameter1=value1&parameter2=value2&parameter3=value3";

        ProductController productController = new ProductController(productService);
        Response expectedResponse = Response.fail(
            "Entered request with invalid parameters"
        );

        Response response = productController.saveProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    @Tag("ProductController")
    void Handle_request_to_save_product_with_invalid_number_of_parameters(
        @Mock ProductService productService
    ) {
        String request = "name=Product1&amount=10";

        ProductController productController = new ProductController(productService);
        Response expectedResponse = Response.fail(
            "Entered request with invalid parameters"
        );

        Response response = productController.saveProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    void Handle_save_type_request(
        @Mock ProductService productService
    ) {
        //Arrange
        String requestType = "Save";
        String requestParameters = "name=Product1&amount=10&currency=USD";

        Product productToSave = new Product(
            "Product1",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Response response = Response.ok(productToSave);

        ProductController productController = new ProductController(productService);

        when(productService.saveToRepository(productToSave)).thenReturn(productToSave);

        //Act
        Response result = productController.handleRequest(requestType, requestParameters);

        //Assert
        assertThat(result)
            .isEqualTo(response);
    }

    @Test
    void Handle_incorrect_type_request(
        @Mock ProductService productService
    ) {
        //Arrange
        String requestType = "IncorrectType";
        String requestParameters = "name=Product1&amount=10&currency=USD";

        Response response = Response.fail("Invalid request type");

        ProductController productController = new ProductController(productService);

        //Act
        Response result = productController.handleRequest(requestType, requestParameters);

        //Assert
        assertThat(result)
            .isEqualTo(response);
    }

    @Test
    void Return_response_when_service_throw_exception(
        @Mock ProductService productService
    ) {
        //Arrange
        String requestType = "Save";
        String requestParameters = "name=product1&amount=-10&currency=USD";

        ProductController productController = new ProductController(productService);

        RuntimeException exception = new RuntimeException("Something go wrong");
        when(productService.saveToRepository(any(Product.class))).thenThrow(exception);
        Response expectedResult = Response.fail(exception.getMessage());

        //Act
        Response result = productController.handleRequest(requestType, requestParameters);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductController")
    void Delete_product(@Mock ProductService productService) {
        String request = "name=Product1";

        ProductController productController = new ProductController(productService);
        String productName = "Product1";
        Product productToDelete = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );
        when(productService.deleteFromRepository(productName)).thenReturn(productToDelete);

        Response expectedResponse = Response.ok(
            productToDelete
        );

        Response response = productController.deleteProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    @Tag("ProductController")
    void Handle_request_to_delete_product_with_invalid_parameters(
        @Mock ProductService productService
    ) {
        String request = "parameter1=value1&parameter2=value2&parameter3=value3";

        ProductController productController = new ProductController(productService);
        Response expectedResponse = Response.fail(
            "Entered request with invalid parameters"
        );

        Response response = productController.deleteProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    void Handle_delete_type_request(
        @Mock ProductService productService
    ) {
        //Arrange
        String requestType = "Delete";
        String requestParameters = "name=Product1";

        String productName = "Product1";
        Product productToDelete = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Response response = Response.ok(productToDelete);

        ProductController productController = new ProductController(productService);

        when(productService.deleteFromRepository(productName)).thenReturn(productToDelete);

        //Act
        Response result = productController.handleRequest(requestType, requestParameters);

        //Assert
        assertThat(result)
            .isEqualTo(response);
    }

    @Test
    @Tag("ProductController")
    void Get_all_products(@Mock ProductService productService) {
        //Arrange
        ProductController productController = new ProductController(productService);
        String productName = "Product1";
        Product productToGet = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );
        List<Product> products = List.of(productToGet);
        when(productService.getAll()).thenReturn(products);

        Response expectedResponse = Response.ok(
            products
        );

        //Act
        Response response = productController.getAll();

        //Assert
        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    void Handle_get_type_request(
        @Mock ProductService productService
    ) {
        //Arrange
        String requestType = "Get";
        String requestParameters = "";

        ProductController productController = new ProductController(productService);
        String productName = "Product1";
        Product productToGet = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );
        List<Product> products = List.of(productToGet);
        when(productService.getAll()).thenReturn(products);

        Response expectedResponse = Response.ok(
            products
        );

        //Act
        Response result = productController.handleRequest(requestType, requestParameters);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResponse);
    }
}
