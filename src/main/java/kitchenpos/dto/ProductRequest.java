package kitchenpos.dto;

import java.math.BigDecimal;
import kitchenpos.domain.Product;
import kitchenpos.product.domain.ProductV2;

public class ProductRequest {
    private String name;
    private BigDecimal price;

    public ProductRequest() {
    }

    public ProductRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductV2 toProduct() {
        return new ProductV2(null, this.name, this.price.longValue());
    }
}
