package com.web.config;

import com.web.product.ProductNameCharacters;
import com.web.valodation.StringValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.web")
public class DomainConfig {

    @Bean
    public StringValidator stringValidator() {
        return new StringValidator(ProductNameCharacters.getProductNameValidCharacters());
    }
}
