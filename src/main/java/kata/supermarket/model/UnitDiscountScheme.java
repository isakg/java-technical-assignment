package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnitDiscountScheme implements DiscountScheme {

    private final List<Product> products;

    private final BigDecimal discount;

    public UnitDiscountScheme(List<Product> products, BigDecimal discount) {
        this.products = products;
        this.discount = discount;
    }

    private List<Product> getProducts() {
        return products;
    }

    public Discount discount(List<Item> items) {
        List<Item> remainingItems = new ArrayList<>(items);
        List<Item> matches = new ArrayList<>();
        for (Product product : this.getProducts()) {
            Optional<Item> match = remainingItems.stream().filter(item -> item.getProduct().equals(product)).findFirst();
            if (!match.isPresent()) {
                return new Discount(new ArrayList<>(), BigDecimal.ZERO);
            }
            remainingItems.remove(match.get());
            matches.add(match.get());

        }
        return new Discount(matches, discount);
    }
}
