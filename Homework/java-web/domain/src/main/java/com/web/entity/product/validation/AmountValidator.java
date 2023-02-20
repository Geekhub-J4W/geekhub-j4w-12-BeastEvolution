package com.web.entity.product.validation;

import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class AmountValidator {

    private static final Price MAX_PRICE_VALUE = new Price(
        new BigDecimal(1_000_000),
        Currency.USD
    );


    public List<String> validate(Price price) {

        if (Objects.isNull(price.getAmount())) {
            throw new IllegalArgumentException("Product price amount should not null, but was:"
                + price.getAmount());
        }

        List<String> validationResults = new ArrayList<>();

        if (isPriceValueNegativeNumber(price.getAmount())) {
            validationResults.add(
                "Product price amount should be a positive number, but was:"
                    + price.getAmount()
            );
        }

        if (isPriceValueGreaterThenMaxValue(price)) {
            validationResults.add(
                String.format(
                    "Product price amount should not be greater then %s, but was: %s",
                    MAX_PRICE_VALUE,
                    price.convertTo(MAX_PRICE_VALUE.getCurrency())
                )
            );
        }

        if (price.getAmount().equals(new BigDecimal(0))) {
            validationResults.add(
                "Product price amount should not be 0, but was: "
                    + price.getAmount()
            );
        }

        if (isValueHaveInvalidScale(price)) {
            validationResults.add(
                String.format(
                    "Product price amount should have number of fraction digits"
                        + " no greater then %s for %s currency type, but was: %s",
                    price.getCurrency().getFractionDigits(),
                    price.getCurrency(),
                    price.getAmount().scale()
                )
            );
        }

        return validationResults;
    }

    private boolean isPriceValueNegativeNumber(BigDecimal priceAmount) {
        return priceAmount.signum() == -1;
    }

    private boolean isPriceValueGreaterThenMaxValue(Price productPrice) {
        return productPrice.compareTo(MAX_PRICE_VALUE) > 0;
    }

    private boolean isValueHaveInvalidScale(Price productPrice) {
        return productPrice.getAmount().scale() > productPrice.getCurrency().getFractionDigits();
    }
}
