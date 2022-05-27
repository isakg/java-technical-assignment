package kata.supermarket.service;

import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.UnitDiscountScheme;
import kata.supermarket.model.Item;
import kata.supermarket.model.Product;

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
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (DiscountScheme discountScheme : discountSchemes) {
            List<Item> matches = discountScheme.matches(remainingItems);
            if (!matches.isEmpty()) {
                matches.forEach(remainingItems::remove);
                totalDiscount = totalDiscount.add(discountScheme.getDiscountForMatches(matches));
            }
        }

        return totalDiscount.setScale(2, RoundingMode.HALF_UP);
    }
}
