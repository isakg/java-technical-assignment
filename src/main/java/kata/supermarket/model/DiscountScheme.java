package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountScheme {

    private final List<Product> products;

    private final BigDecimal discount;

    public DiscountScheme(List<Product> products, BigDecimal discount) {
        this.products = products;
        this.discount = discount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public boolean matches(List<Product> toMatch) {
        List<Product> productsList = new ArrayList<>(toMatch);
        boolean allMatch = true;
        for (Product p : this.getProducts()) {
            allMatch &= productsList.remove(p);
        }
        return allMatch;
    }
}
