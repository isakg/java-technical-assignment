package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WeightOfTypeDiscountScheme implements DiscountScheme {

    private final ProductType productType;

    private final BigDecimal minWeightCriteria;

    private final BigDecimal percentageDiscount;

    public WeightOfTypeDiscountScheme(BigDecimal minWeightCriteria, BigDecimal percentageDiscount, ProductType productType) {
        this.minWeightCriteria = minWeightCriteria;
        this.percentageDiscount = percentageDiscount;
        this.productType = productType;
    }

    public List<Item> matches(List<Item> toMatch) {
        List<Item> matches = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : toMatch) {
            if (item.getProduct().getProductType() == this.productType && item.getWeight().isPresent()) {
                total = total.add(item.getWeight().get());
                matches.add(item);
            }
        }
        if (total.compareTo(minWeightCriteria) >= 0) {
            return matches;
        }
        return new ArrayList<>();
    }

    @Override
    public BigDecimal getDiscountForMatches(List<Item> matches) {
        return matches.stream().map(Item::price).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(percentageDiscount);
    }
}