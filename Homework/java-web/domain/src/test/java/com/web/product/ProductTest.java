package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @Tag("Product")
    void Create_product() {
        String name = "New product";
        Price price = new Price(new BigDecimal("10"), Currency.USD);

        Product product = new Product(name, price);

        assertThat(product.getName())
            .isEqualTo(name);
        assertThat(product.getPrice())
            .isEqualTo(price);
    }
}
