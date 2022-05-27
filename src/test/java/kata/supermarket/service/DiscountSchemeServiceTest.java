package kata.supermarket.service;

import kata.supermarket.model.*;
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
        return Stream.of(noItemsWithNoDiscounts(),
                twoItemsWithBuyOneGetOneFreeDiscount(),
                twoDifferentItemsWithBuyOneGetOneFreeDiscount(),
                twoItemsWithTwoForOnePoundDiscount(),
                twoDifferentItemsWithTwoForOnePoundDiscount(),
                threeItemsWithThreeForPriceOfTwoDiscount(),
                threeDifferentItemsWithThreeForPriceOfTwoDiscount(),
                twoItemsWithThreeForPriceOfTwoDiscount(),
                twoItemsWithTwoForOnePoundWithAndTwoItemsWithBuyOneGetOneFreeDiscounts()
                );
    }

    private static Arguments noItemsWithNoDiscounts() {
        return Arguments.of("no items and no discounts", "0.00", Collections.emptyList(), Collections.emptyList());
    }

    private static Arguments twoItemsWithBuyOneGetOneFreeDiscount() {
        return Arguments.of("two items with buy one get one free discount", "1.20",
                Arrays.asList(aBoxOfCornflakes(), aBoxOfCornflakes()), Arrays.asList(buyOneGetOneFreeDiscountScheme()));
    }

    private static Arguments twoDifferentItemsWithBuyOneGetOneFreeDiscount() {
        return Arguments.of("two different items with buy one get one free discount", "0.00",
                Arrays.asList(aBoxOfCornflakes(), aBlockOfCheese()), Arrays.asList(buyOneGetOneFreeDiscountScheme()));
    }

    private static Arguments twoItemsWithTwoForOnePoundDiscount() {
        return Arguments.of("two items with two for one pound discount", "0.40",
                Arrays.asList(aBlockOfCheese(), aBlockOfCheese()), Arrays.asList(buyTwoItemsForOnePoundDiscountScheme()));
    }

    private static Arguments twoDifferentItemsWithTwoForOnePoundDiscount() {
        return Arguments.of("two items with two for one pound discount", "0.00",
                Arrays.asList(aBlockOfCheese(), aBoxOfCornflakes()), Arrays.asList(buyTwoItemsForOnePoundDiscountScheme()));
    }

    private static Arguments threeItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("three items with three for price of two discount", "1.20",
                Arrays.asList(aBoxOfCornflakes(), aBoxOfCornflakes(), aBoxOfCornflakes()), Arrays.asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments twoItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("two items with three for price of two discount", "0.00",
                Arrays.asList(aBoxOfCornflakes(), aBoxOfCornflakes()), Arrays.asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments threeDifferentItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("three different items with three for price of two discount", "0.00",
                Arrays.asList(aBoxOfCornflakes(), aBlockOfCheese(), aBoxOfCornflakes()), Arrays.asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments twoItemsWithTwoForOnePoundWithAndTwoItemsWithBuyOneGetOneFreeDiscounts() {
        return Arguments.of("two items with two for one pound and two items with buy one get one free discounts", "1.60",
                Arrays.asList(aBoxOfCornflakes(), aBlockOfCheese(), aBoxOfCornflakes(), aBlockOfCheese()), Arrays.asList(buyTwoItemsForOnePoundDiscountScheme(), buyOneGetOneFreeDiscountScheme()));
    }



    private static Item aBoxOfCornflakes() {
        return new ItemByUnit(boxOfCornflakesProduct());
    }

    private static UnitProduct boxOfCornflakesProduct() {
        return new UnitProduct("A1", new BigDecimal("1.20"));
    }

    private static Item aBlockOfCheese() {
        return new ItemByUnit(blockOfCheeseProduct());
    }

    private static UnitProduct blockOfCheeseProduct() {
        return new UnitProduct("B1", new BigDecimal("0.70"));
    }

    private static UnitDiscountScheme buyOneGetOneFreeDiscountScheme() {
        return new UnitDiscountScheme(Arrays.asList(boxOfCornflakesProduct(), boxOfCornflakesProduct()), new BigDecimal(1.20));
    }

    private static UnitDiscountScheme buyTwoItemsForOnePoundDiscountScheme() {
        return new UnitDiscountScheme(Arrays.asList(blockOfCheeseProduct(), blockOfCheeseProduct()), new BigDecimal(0.40));
    }

    private static UnitDiscountScheme buyThreeItemsForThePriceOfTwoDiscountScheme() {
        return new UnitDiscountScheme(Arrays.asList(boxOfCornflakesProduct(), boxOfCornflakesProduct(), boxOfCornflakesProduct()), new BigDecimal(1.20));
    }

}