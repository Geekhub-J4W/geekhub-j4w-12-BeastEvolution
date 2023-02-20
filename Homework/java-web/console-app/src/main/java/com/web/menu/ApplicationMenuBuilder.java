package com.web.menu;

import com.web.config.DomainConfig;
import com.web.product.Currency;
import com.web.product.Price;
import com.web.product.Product;
import com.web.product.ProductService;
import com.web.product.validation.exceptions.ValidationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMenuBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMenuBuilder.class);

    private ApplicationMenuBuilder(MenuNode menuRoot) {
    }

    public static Menu buildMenu() {
        return MenuBuilder.build(createMenuNodes());
    }

    private static List<MenuNode> createMenuNodes() {
        var applicationContext = new AnnotationConfigApplicationContext(DomainConfig.class);
        List<MenuNode> menuNodes = new ArrayList<>();
        ProductService productService = applicationContext.getBean(ProductService.class);
        Scanner scanner = new Scanner(System.in);

        // lvl 1
        menuNodes.add(new ActionMenuNode(
            "0. Add product",
            new int[]{0},
            () -> {
                System.out.println("Entered product name: ");
                final String productName = scanner.nextLine();

                System.out.println("Entered product price currency: ");
                Arrays.stream(Currency.values())
                    .forEach(currency -> {
                        System.out.println(currency.ordinal() + ". " + currency.name());
                    });

                int currencyNumber = Integer.parseInt(scanner.nextLine());

                System.out.println("Entered product price: ");
                BigDecimal productAmount = new BigDecimal(scanner.nextLine());
                Product product = new Product(
                    productName,
                    new Price(productAmount, Currency.values()[currencyNumber]));
                try {
                    System.out.println(productService.saveToRepository(product));
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
            }
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Delete product",
            new int[]{1},
            () -> {
                System.out.println("Entered product name: ");
                final String productName = scanner.nextLine();

                System.out.println("Entered product price currency: ");
                Arrays.stream(Currency.values())
                    .forEach(currency -> {
                        System.out.println(currency.ordinal() + ". " + currency.name());
                    });

                int currencyNumber = Integer.parseInt(scanner.nextLine());

                System.out.println("Entered product price: ");
                BigDecimal productAmount = new BigDecimal(scanner.nextLine());
                Product product = new Product(
                    productName,
                    new Price(productAmount, Currency.values()[currencyNumber]));
                System.out.println(productService.deleteFromRepository(product));
            }
        ));
        menuNodes.add(new PassthroughMenuNode(
            "2. Show product list",
            new int[]{2}
        ));
        menuNodes.add(new ActionMenuNode(
            "3. Exit",
            new int[]{3},
            () -> System.exit(0)
        ));
        // lvl 2
        // item 2
        menuNodes.add(new PassthroughMenuNode(
            "0. Sorted by name",
            new int[]{2, 0}
        ));
        menuNodes.add(new PassthroughMenuNode(
            "1. Sorted by price",
            new int[]{2, 1}
        ));
        menuNodes.add(new ActionMenuNode(
            "2. Not sorted",
            new int[]{2, 2},
            () -> {
                productService.getAllSorted(Comparator.comparing(Product::getName))
                    .stream()
                    .forEach(System.out::println);
            }
        ));
        menuNodes.add(new BackMenuNode(
            "3. Beck",
            new int[]{2, 3})
        );
        // lvl 3
        // item 0
        menuNodes.add(new ActionMenuNode(
            "0. In natural order",
            new int[]{2, 0, 0},
            () -> {
                productService.getAllSorted(Comparator.comparing(Product::getName))
                    .stream()
                    .forEach(System.out::println);
            }
        ));
        menuNodes.add(new ActionMenuNode(
            "1. In reversed order",
            new int[]{2, 0, 1},
            () -> {
                productService.getAllSorted(Comparator.comparing(Product::getName).reversed())
                    .stream()
                    .forEach(System.out::println);
            }
        ));
        menuNodes.add(new BackMenuNode(
            "2. Beck",
            new int[]{2, 0, 2})
        );
        // item 1
        menuNodes.add(new ActionMenuNode(
            "0. In natural order",
            new int[]{2, 1, 0},
            () -> {
                productService.getAllSorted(Comparator.comparing(Product::getPrice))
                    .stream()
                    .forEach(System.out::println);
            }
        ));
        menuNodes.add(new ActionMenuNode(
            "1. In reversed order",
            new int[]{2, 1, 1},
            () -> {
                productService.getAllSorted(Comparator.comparing(Product::getPrice).reversed())
                    .stream()
                    .forEach(System.out::println);
            }
        ));
        menuNodes.add(new BackMenuNode(
            "2. Beck",
            new int[]{2, 1, 2})
        );

        return menuNodes;
    }
}
