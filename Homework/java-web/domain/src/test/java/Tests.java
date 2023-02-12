import static org.assertj.core.api.Assertions.assertThat;

import com.web.Price;
import com.web.product.Currency;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    void Create_price_with_currency_type() {
        BigDecimal value = new BigDecimal("10");
        Currency currency = Currency.USD;
        Price price = new Price(value, currency);

        assertThat(price.value())
            .isEqualTo(new BigDecimal(10));
        assertThat(price.currency())
            .isEqualTo(Currency.USD);
    }
}
