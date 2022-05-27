package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountScheme {

    private final List<Item> items;

    private final BigDecimal discount;

    public DiscountScheme(List<Item> items, BigDecimal discount) {
        this.items = items;
        this.discount = discount;
    }


    public List<Product> getProducts() {
        return items.stream().map(Item::getProduct).collect(Collectors.toList());
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
