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
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    public static final String ENTERED_REQUEST_WITH_INVALID_PARAMETERS =
        "Entered request with invalid parameters";
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
                return getAllSorted(request);
            }
        }
        return Response.fail("Invalid request type");
    }

    public Response saveProduct(Request request) {
        if (isParametersToSaveNotValid(request.requestParameters())) {
            return Response.fail(ENTERED_REQUEST_WITH_INVALID_PARAMETERS);
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
            return Response.fail(ENTERED_REQUEST_WITH_INVALID_PARAMETERS);
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

    public Response getAllSorted(Request request) {
        if (isParametersToGetNotValid(request.requestParameters())) {
            return Response.fail(ENTERED_REQUEST_WITH_INVALID_PARAMETERS);
        }

        if (request.requestParameters().get(0).value().equals("Name")) {
            if (request.requestParameters().get(1).value().equals("Natural")) {
                return Response.ok(
                    productService.getAllSorted(
                        Comparator.comparing(Product::getName)
                    )
                );
            } else if (request.requestParameters().get(1).value().equals("Reversed")) {
                return Response.ok(
                    productService.getAllSorted(
                        Comparator.comparing(Product::getName)
                            .reversed()
                    )
                );
            } else {
                return Response.fail(
                    "Invalid sort type value: "
                        + request.requestParameters().get(1).value()
                );
            }
        } else if (request.requestParameters().get(0).value().equals("Price")) {
            if (request.requestParameters().get(1).value().equals("Natural")) {
                return Response.ok(
                    productService.getAllSorted(
                        Comparator.comparing(Product::getPrice)
                    )
                );
            } else if (request.requestParameters().get(1).value().equals("Reversed")) {
                return Response.ok(
                    productService.getAllSorted(
                        Comparator.comparing(Product::getPrice)
                            .reversed()
                    )
                );
            } else {
                return Response.fail(
                    "Invalid sort type value: "
                        + request.requestParameters().get(1).value()
                );
            }
        } else {
            return Response.fail(
                "Invalid sort by value: "
                    + request.requestParameters().get(0).value()
            );
        }
    }

    private boolean isParametersToGetNotValid(List<RequestParameter> parameters) {
        if (parameters.size() != 2) {
            return true;
        }

        RequestParameter sortByParameter = parameters.get(0);
        RequestParameter sortTypeParameter = parameters.get(1);

        return !(sortByParameter.field().equals("sort_by")
            && sortTypeParameter.field().equals("sort_type"));
    }
}
