package kata.supermarket.model;

import kata.supermarket.service.DiscountSchemeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;

    private final DiscountSchemeService discountSchemeService;

    public Basket(DiscountSchemeService discountSchemeService) {
        this.items = new ArrayList<>();
        this.discountSchemeService = discountSchemeService;
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal discounts() {
            return discountSchemeService.calculateDiscounts(items);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
