package com.web.controller;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Request;
import com.web.util.RequestParameter;
import com.web.util.RequestType;
import com.web.util.Response;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Response handleRequest(Request request) {

        if (request.requestType().equals(RequestType.Post)) {
            return saveProduct(request);
        } else if (request.requestType().equals(RequestType.Delete)) {
            return deleteProduct(request);
        } else if (request.requestType().equals(RequestType.Get)) {
            if (request.requestParameters().isEmpty()) {
                return getAll();
            } else {
                return Response.fail("Entered request must not contain parameters");
            }
        }
        return Response.fail("Invalid request type");
    }

    public Response saveProduct(Request request) {
        if (isParametersToSaveNotValid(request.requestParameters())) {
            return Response.fail("Entered request with invalid parameters");
        }
        String productName = request.requestParameters().get(0).value();
        BigDecimal productAmount = new BigDecimal(request.requestParameters().get(1).value());
        Currency productCurrency;
        try {
            productCurrency = Currency.valueOf(request.requestParameters().get(2).value());
        } catch (IllegalArgumentException e) {
            return Response.fail(
                "Entered currency parameter value not supported"
            );
        }

        Product productToSave = new Product(
            productName,
            new Price(productAmount, productCurrency)
        );

        try {
            return Response.ok(productService.saveToRepository(productToSave));
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    private boolean isParametersToSaveNotValid(List<RequestParameter> parameters) {
        if (parameters.size() != 3) {
            return true;
        }

        RequestParameter nameParameter = parameters.get(0);
        RequestParameter amountParameter = parameters.get(1);
        RequestParameter currencyParameter = parameters.get(2);

        return !(nameParameter.field().equals("name")
            && amountParameter.field().equals("amount")
            && currencyParameter.field().equals("currency"));
    }

    public Response deleteProduct(Request request) {
        if (isParametersToDeleteNotValid(request.requestParameters())) {
            return Response.fail("Entered request with invalid parameters");
        }

        String productName = request.requestParameters().get(0).value();

        try {
            return Response.ok(productService.deleteFromRepository(productName));
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    private boolean isParametersToDeleteNotValid(List<RequestParameter> parameters) {
        if (parameters.size() != 1) {
            return true;
        }

        RequestParameter nameParameter = parameters.get(0);

        return !nameParameter.field().equals("name");
    }

    public Response getAll() {
        try {
            return Response.ok(productService.getAll());
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }
}
