package com.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.web.controller.ProductController;
import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Response;
import java.math.BigDecimal;
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

}
