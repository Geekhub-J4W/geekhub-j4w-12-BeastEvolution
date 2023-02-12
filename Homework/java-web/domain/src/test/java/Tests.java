import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.web.Price;
import com.web.product.Currency;
import com.web.product.exceptions.InvalidPriceException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    void Create_price_with_currency_type() {
        BigDecimal value = new BigDecimal("10");
        Currency currency = Currency.USD;
        Price price = new Price(value, currency);

        assertThat(price.getValue())
            .isEqualTo(new BigDecimal(10));
        assertThat(price.getCurrency())
            .isEqualTo(Currency.USD);
    }

    @Test
    void Invalid_to_create_price_with_negative_price() {
        BigDecimal value = new BigDecimal("-1");

        assertThatThrownBy(() -> new Price(value, Currency.USD))
            .isInstanceOf(InvalidPriceException.class)
            .hasMessage("Price can't be negative, but set: " + value);
    }
}
