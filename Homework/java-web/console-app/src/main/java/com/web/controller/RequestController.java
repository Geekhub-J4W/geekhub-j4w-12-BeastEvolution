package com.web.controller;

import com.web.util.Request;
import com.web.util.RequestPath;
import com.web.util.Response;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController {

    private final ProductController productController;

    public RequestController(ProductController productController) {
        this.productController = productController;
    }

    public Response handleRequest(Request request) {
        if (request.requestPath().equals(RequestPath.product)) {
            return productController.handleRequest(request);
        }

        return Response.fail("Invalid request path");
    }
}
