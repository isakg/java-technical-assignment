package kata.supermarket.service;

import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

public class DiscountSchemeService {

    private Set<DiscountScheme> discountSchemes;

    public DiscountSchemeService(Set<DiscountScheme> discountSchemes) {
        this.discountSchemes = discountSchemes;
    }

    public BigDecimal calculateDiscounts(List<Item> items) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}
