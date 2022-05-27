package kata.supermarket.service;

import kata.supermarket.model.Discount;
import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountSchemeService {

    private final List<DiscountScheme> discountSchemes;

    public DiscountSchemeService(List<DiscountScheme> discountSchemes) {
        this.discountSchemes = discountSchemes;
    }

    public BigDecimal calculateDiscounts(List<Item> items) {
        final List<Item> remainingItems = new ArrayList<>(items);
        BigDecimal total = BigDecimal.ZERO;

        for (DiscountScheme scheme : discountSchemes) {
            Discount discount = scheme.discount(remainingItems);
            discount.getMatches().forEach(remainingItems::remove);
            total = total.add(discount.getValue());
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
