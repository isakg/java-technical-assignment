package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class DiscountScheme {

    private List<Product> products;

    private BigDecimal discount;

    public DiscountScheme(List<Product> products, BigDecimal discount) {
        this.products = products;
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountScheme that = (DiscountScheme) o;
        return Objects.equals(products, that.products) &&
                Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, discount);
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
