package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyThreeForTwoDiscountScheme implements DiscountScheme{

    private final UnitProduct product;

    public BuyThreeForTwoDiscountScheme(UnitProduct product) {
        this.product = product;
    }

    @Override
    public Discount discount(List<Item> items) {
        List<Item> matches = items.stream().filter(item -> item.getProduct().equals(this.product)).collect(Collectors.toList());
        BigDecimal discount = new BigDecimal(matches.size() / 3).multiply(product.pricePerUnit());
        return new Discount(matches.subList(0, matches.size() - matches.size() % 3), discount);

    }
}
