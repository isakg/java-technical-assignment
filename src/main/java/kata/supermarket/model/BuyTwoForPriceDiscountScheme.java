package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyTwoForPriceDiscountScheme implements DiscountScheme {

    private final UnitProduct product;

    private final BigDecimal priceForTwo;

    public BuyTwoForPriceDiscountScheme(UnitProduct product, BigDecimal priceForTwo) {
        this.product = product;
        this.priceForTwo = priceForTwo;
    }


    @Override
    public Discount discount(List<Item> items) {
        List<Item> matches = items.stream().filter(item -> item.getProduct().equals(this.product)).collect(Collectors.toList());
        int size = matches.size();
        BigDecimal total = new BigDecimal(size / 2).multiply(priceForTwo)
                .add(new BigDecimal(size % 2).multiply(product.pricePerUnit()));
        BigDecimal discount = product.pricePerUnit().multiply(new BigDecimal(size)).subtract(total);
        return new Discount(matches.subList(0, size - size % 2), discount);

    }
}
