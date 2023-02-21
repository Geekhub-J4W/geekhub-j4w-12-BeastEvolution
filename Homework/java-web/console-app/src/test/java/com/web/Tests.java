package com.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.web.controller.ProductController;
import com.web.controller.RequestController;
import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Request;
import com.web.util.RequestParameter;
import com.web.util.RequestPath;
import com.web.util.RequestType;
import com.web.util.RequestUtil;
import com.web.util.Response;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Tests {

    @Test
    @Tag("ProductController")
    void Save_product(@Mock ProductService productService) {
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10"),
                new RequestParameter("currency", "USD")
            )
        );

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
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("parameter1", "value1"),
                new RequestParameter("parameter2", "value2"),
                new RequestParameter("parameter3", "value3")
            )
        );

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
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10")
            )
        );

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
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10"),
                new RequestParameter("currency", "USD")
            )
        );
        Product productToSave = new Product(
            "Product1",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Response response = Response.ok(productToSave);

        ProductController productController = new ProductController(productService);

        when(productService.saveToRepository(productToSave)).thenReturn(productToSave);

        //Act
        Response result = productController.handleRequest(request);

        //Assert
        assertThat(result)
            .isEqualTo(response);
    }

    @Test
    void Return_response_when_service_throw_exception(
        @Mock ProductService productService
    ) {
        //Arrange
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10"),
                new RequestParameter("currency", "USD")
            )
        );
        ProductController productController = new ProductController(productService);

        RuntimeException exception = new RuntimeException("Something go wrong");
        when(productService.saveToRepository(any(Product.class))).thenThrow(exception);
        Response expectedResult = Response.fail(exception.getMessage());

        //Act
        Response result = productController.handleRequest(request);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("ProductController")
    void Delete_product(@Mock ProductService productService) {
        Request request = new Request(
            RequestType.Delete,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1")
            )
        );

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
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("parameter1", "value1")
            )
        );

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
        Request request = new Request(
            RequestType.Delete,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1")
            )
        );
        String productName = "Product1";
        Product productToDelete = new Product(
            productName,
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Response response = Response.ok(productToDelete);

        ProductController productController = new ProductController(productService);

        when(productService.deleteFromRepository(productName)).thenReturn(productToDelete);

        //Act
        Response result = productController.handleRequest(request);

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
        Request request = new Request(
            RequestType.Get,
            RequestPath.product,
            List.of(
            )
        );

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
        Response result = productController.handleRequest(request);

        //Assert
        assertThat(result)
            .isEqualTo(expectedResponse);
    }

    @Test
    @Tag("RequestController")
    void Handle_product_request(
        @Mock ProductController productController
    ) {
        Request request = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10"),
                new RequestParameter("currency", "USD")
            )
        );

        Product productToSave = new Product(
            "Product1",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Response response = Response.ok(productToSave);

        RequestController requestController = new RequestController(productController);
        when(productController.handleRequest(request)).thenReturn(response);

        Response result = requestController.handleRequest(request);

        assertThat(result)
            .isEqualTo(response);
    }

    @ParameterizedTest
    @Tag("RequestParameter")
    @ValueSource(strings = {
        "Name1",
        "name1&",
        "імя1",
    })
    void Invalid_to_create_request_parameter_with_incorrect_field_value(
        String field
    ) {
        assertThatThrownBy(() -> new RequestParameter(field, "Value1"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Field must contain only lowercase characters, digits and underscore");
    }

    @Test
    @Tag("RequestUtil")
    void Convert_string_to_request() {
        String requestInStringFormat = "Post product name=Product1&amount=10&currency=USD";

        Request expectedResult = new Request(
            RequestType.Post,
            RequestPath.product,
            List.of(
                new RequestParameter("name", "Product1"),
                new RequestParameter("amount", "10"),
                new RequestParameter("currency", "USD")
            )
        );

        Request result = RequestUtil.convert(requestInStringFormat);

        assertThat(result)
            .isEqualTo(expectedResult);
    }

    @Test
    @Tag("RequestUtil")
    void Invalid_to_convert_incorrect_string_to_request() {
        String requestInStringFormat = "Text";

        assertThatThrownBy(() -> RequestUtil.convert(requestInStringFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "Request must bi in format:"
                    + " \"[request type][space][request path][space][request parameters]\""
            );
    }

    @Test
    @Tag("RequestUtil")
    void Invalid_to_convert_string_with_incorrect_request_type_to_request() {
        String requestType = "Type";
        String requestPath = "product";
        String requestParameters = "name=Product1&amount=10&currency=USD";
        String requestInStringFormat = String.format(
            "%s %s %s",
            requestType,
            requestPath,
            requestParameters
        );

        assertThatThrownBy(() -> RequestUtil.convert(requestInStringFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Incorrect request type: " + requestType);
    }

    @Test
    @Tag("RequestUtil")
    void Invalid_to_convert_string_with_incorrect_request_path_to_request() {
        String requestType = "Post";
        String requestPath = "path";
        String requestParameters = "name=Product1&amount=10&currency=USD";
        String requestInStringFormat = String.format(
            "%s %s %s",
            requestType,
            requestPath,
            requestParameters
        );

        assertThatThrownBy(() -> RequestUtil.convert(requestInStringFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Incorrect request path: " + requestPath);
    }

    @Test
    @Tag("RequestUtil")
    void Invalid_to_convert_string_with_incorrect_request_parameter_format_to_request() {
        String requestType = "Post";
        String requestPath = "product";
        String requestParameter = "name";
        String requestInStringFormat = String.format(
            "%s %s %s",
            requestType,
            requestPath,
            requestParameter
        );

        assertThatThrownBy(() -> RequestUtil.convert(requestInStringFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("RequestParameter must be in format: \"field=value\","
                + " but was: " + requestParameter);
    }
}
