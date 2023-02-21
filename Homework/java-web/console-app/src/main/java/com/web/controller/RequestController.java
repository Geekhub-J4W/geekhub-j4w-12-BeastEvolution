package com.web.controller;

import com.web.util.Response;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController {

    private final ProductController productController;

    public RequestController(ProductController productController) {
        this.productController = productController;
    }

    public Response handleRequest(String request) {
        String[] requestElements = request.split(" ");
        if (requestElements[1].equals("product")) {
            return productController.handleRequest(requestElements[0], requestElements[2]);
        }

        return Response.fail("Invalid request path");
    }
}
