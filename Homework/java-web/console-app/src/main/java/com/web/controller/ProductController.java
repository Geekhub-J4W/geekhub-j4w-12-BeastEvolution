package com.web.controller;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Response;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Response saveProduct(String request) {
        if (isParametersNotValid(request)) {
            return Response.fail("Entered request with invalid parameters");
        }
        String[] productParameters = request.split("&");

        String productName = productParameters[0].split("=")[1];
        BigDecimal productAmount = new BigDecimal(productParameters[1].split("=")[1]);
        Currency productCurrency = Currency.valueOf(productParameters[2].split("=")[1]);

        Product productToSave = new Product(
            productName,
            new Price(productAmount, productCurrency)
        );

        return Response.ok(productService.saveToRepository(productToSave));
    }

    private boolean isParametersNotValid(String parameters) {
        return !parameters.matches("^name=.+&amount=.+&currency=.+$");
    }
}
