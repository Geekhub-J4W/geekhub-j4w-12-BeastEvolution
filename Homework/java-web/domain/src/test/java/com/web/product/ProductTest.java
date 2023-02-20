package com.web.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
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

    @Test
    void Products_that_only_have_same_name_must_be_equals() {
        Product product = new Product(
            "Name",
            new Price(new BigDecimal("10"), Currency.USD)
        );
        Product product1 = new Product(
            "Name",
            new Price(new BigDecimal("11"), Currency.UAH)
        );

        boolean result = product.equals(product1);

        assertThat(result)
            .isTrue();
    }
}
