import com.web.Price;
import com.web.product.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class Tests {
    @Test
    void Create_price_with_currency_type(){
        BigDecimal value = new BigDecimal("10");
        Currency currency = Currency.USD;
        Price price = new Price(value, currency);

        assertThat(price.value())
            .isEqualTo(new BigDecimal(10));
        assertThat(price.currency())
            .isEqualTo(Currency.USD);
    }
//    @Test
//    void Create_store_product() {
//        String name = "new product";
//        Price
//        double price = 10;
//        Product product = new Prduct(name, )
//    }
}
