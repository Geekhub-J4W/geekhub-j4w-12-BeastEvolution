import static org.assertj.core.api.Assertions.assertThat;

import com.web.controller.ProductController;
import com.web.entity.product.Currency;
import com.web.entity.product.Price;
import com.web.entity.product.Product;
import com.web.service.ProductService;
import com.web.util.Response;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class Tests {

    @Test
    void Save_product(@Mock ProductService productService) {
        String request = "name=Product1&amount=10&currency=USD";

        ProductController productController = new ProductController(productService);

        Response expectedResponse = Response.ok(
            new Product("Product1",
                new Price(new BigDecimal("10"), Currency.USD)
            )
        );

        String response = productController.saveProduct(request);

        assertThat(response)
            .isEqualTo(expectedResponse);
    }

    @Test
    void productString() {
        Product product =
            new Product("Product1", new Price(new BigDecimal("10"), Currency.USD));

        System.out.println(product);
    }

}
