package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyOneGetOneFreeDiscountScheme implements DiscountScheme {

    private final UnitProduct product;

    public BuyOneGetOneFreeDiscountScheme(UnitProduct product) {
        this.product = product;
    }

    @Override
    public Discount discount(List<Item> items) {
        List<Item> matches = items.stream().filter(item -> item.getProduct().equals(this.product)).collect(Collectors.toList());
        BigDecimal discount = new BigDecimal(matches.size() / 2).multiply(product.pricePerUnit());
        return new Discount(matches.subList(0, matches.size() - matches.size() % 2), discount);

    }
}
