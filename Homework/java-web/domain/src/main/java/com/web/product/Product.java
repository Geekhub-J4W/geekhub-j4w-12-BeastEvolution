package com.web.product;

public class Product {

    private final String name;
    private final Price price;

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
