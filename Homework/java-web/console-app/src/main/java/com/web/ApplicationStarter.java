package com.web;

import com.web.config.DomainConfig;
import com.web.controller.RequestController;
import com.web.util.RequestUtil;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {

    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(DomainConfig.class);

        RequestController requestController = applicationContext.getBean(RequestController.class);
        Scanner scanner = new Scanner(System.in);

        printRequestsInfo();
        while (true) {
            System.out.println("Enter request:");
            String request = scanner.nextLine();
            try {
                System.out.println(requestController.handleRequest(RequestUtil.convert(request)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            }
        }
    }

    private static void printRequestsInfo() {
        System.out.println("Available requests:");

        System.out.println(
            "Save product name=[product name]&amount=[product amount]&currency=[product currency]"
        );
        System.out.println(
            "Example: \"Post product name=Product&amount=10&currency=USD\""
        );
        System.out.println();

        System.out.println(
            "Delete product name=[product name]"
        );
        System.out.println(
            "Example: \"Delete product name=Product\""
        );
        System.out.println();

        System.out.println(
            "Get product[space]"
        );
        System.out.println(
            "Example: \"Get product \""
        );
        System.out.println();

        System.out.println(
            "Get product sort_by=[product field name]&sort_type=[sort type]"
        );
        System.out.println(
            "Example: \"Get product sort_by=Name&sort_type=Natural\""
        );
        System.out.println();

    }
}
