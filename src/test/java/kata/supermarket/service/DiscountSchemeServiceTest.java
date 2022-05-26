package kata.supermarket.service;

import kata.supermarket.model.DiscountScheme;
import kata.supermarket.model.Item;
import kata.supermarket.model.ItemByUnit;
import kata.supermarket.model.UnitProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountSchemeServiceTest {

    @DisplayName("calculates discount total when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountsCalculatedFromListOfItems(String description, String expectedTotal, List<Item> items, List<DiscountScheme> discountSchemes) {
        DiscountSchemeService discountSchemeService = new DiscountSchemeService(discountSchemes);
        BigDecimal discounts = discountSchemeService.calculateDiscounts(items);
        assertEquals(new BigDecimal(expectedTotal), discounts);
    }

    static Stream<Arguments> discountsCalculatedFromListOfItems() {
        return Stream.of(noItemsWithNoDiscounts(), twoItemsWithBuyOneGetOneFreeDiscount());
    }

    private static Arguments noItemsWithNoDiscounts() {
        return Arguments.of("no items and no discounts", "0.00", Collections.emptyList(), Collections.emptyList());
    }

    private static Arguments twoItemsWithBuyOneGetOneFreeDiscount() {
        return Arguments.of("buy one get one free discount", "1.20",
                Arrays.asList(aBoxOfCornflakes(), aBoxOfCornflakes()), Arrays.asList(buyOneGetOneFreeDiscountScheme()));
    }

    private static Item aBoxOfCornflakes() {
        return new ItemByUnit(boxOfCornflakesProduct());
    }

    private static UnitProduct boxOfCornflakesProduct() {
        return new UnitProduct("A1", new BigDecimal("1.20"));
    }

    private static DiscountScheme buyOneGetOneFreeDiscountScheme() {
        return new DiscountScheme(Arrays.asList(boxOfCornflakesProduct(), boxOfCornflakesProduct()), new BigDecimal(1.20));
    }

}