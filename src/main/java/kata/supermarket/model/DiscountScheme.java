package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class DiscountScheme {

    private Set<Product> products;

    private BigDecimal price;

    public DiscountScheme(Set<Product> products, BigDecimal price) {
        this.products = products;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountScheme that = (DiscountScheme) o;
        return Objects.equals(products, that.products) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, price);
    }
}
