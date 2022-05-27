package kata.supermarket.service;

import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.Item;
import kata.supermarket.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountSchemeService {

    private final List<DiscountScheme> discountSchemes;

    public DiscountSchemeService(List<DiscountScheme> discountSchemes) {
        this.discountSchemes = discountSchemes;
    }

    public BigDecimal calculateDiscounts(List<Item> items) {
        final List<Item> currentItems = new ArrayList<>(items);
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (DiscountScheme discountScheme : discountSchemes) {
            if (discountScheme.matches(currentItems)) {
                totalDiscount = totalDiscount.add(discountScheme.getDiscount());
                discountScheme.getItems().forEach(currentItems::remove);
            }
        }

        return totalDiscount.setScale(2, RoundingMode.HALF_UP);
    }
}
