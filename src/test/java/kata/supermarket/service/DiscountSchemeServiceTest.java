package kata.supermarket.service;

import kata.supermarket.model.Basket;
import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DiscountSchemeServiceTest {

    @DisplayName("calculates discounts when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountsCalculatedFromListOfItems(String description, String expectedTotal, List<Item> items, Set<DiscountScheme> discountSchemes) {
        DiscountSchemeService discountSchemeService = new DiscountSchemeService(discountSchemes);
        BigDecimal discounts = discountSchemeService.calculateDiscounts(items);
        assertEquals(new BigDecimal(expectedTotal), discounts);
    }

    static Stream<Arguments> discountsCalculatedFromListOfItems() {
        return Stream.of(noItemsNoDiscounts());
    }

    private static Arguments noItemsNoDiscounts() {
        return Arguments.of("no items and no discounts", "0.00", Collections.emptyList(), Collections.emptySet());
    }


}