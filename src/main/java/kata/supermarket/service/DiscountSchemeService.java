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
        List<Product> products = items.stream().map(Item::getProduct).collect(Collectors.toList());
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (DiscountScheme discountScheme : discountSchemes) {
            List<Product> preAppliedProducts = new ArrayList<>(products);
            boolean allMatch = true;
            for (Product p : discountScheme.getProducts()) {
                allMatch &= products.remove(p);
            }
            if (allMatch) {
                totalDiscount = totalDiscount.add(discountScheme.getDiscount());
            } else {
                products = preAppliedProducts;
            }
        }

        return totalDiscount.setScale(2, RoundingMode.HALF_UP);
    }
}
