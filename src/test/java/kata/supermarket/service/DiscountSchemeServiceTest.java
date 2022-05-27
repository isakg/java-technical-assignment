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

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static kata.supermarket.model.ProductType.VEGETABLE;
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
                twoItemsWithTwoForOnePoundAndTwoItemsWithBuyOneGetOneFreeDiscounts(),
                oneKgOfCarrotsWithKgVegetablesHalfPriceDiscount(),
                oneKgOfVegetablesWithKgVegetablesHalfPriceDiscount()
        );
    }

    private static Arguments noItemsWithNoDiscounts() {
        return Arguments.of("no items and no discounts", "0.00", emptyList(), emptyList());
    }

    private static Arguments twoItemsWithBuyOneGetOneFreeDiscount() {
        return Arguments.of("two items with buy one get one free discount", "1.20",
                asList(aBoxOfCornflakes(), aBoxOfCornflakes()), asList(buyOneGetOneFreeDiscountScheme()));
    }

    private static Arguments twoDifferentItemsWithBuyOneGetOneFreeDiscount() {
        return Arguments.of("two different items with buy one get one free discount", "0.00",
                asList(aBoxOfCornflakes(), aBlockOfCheese()), asList(buyOneGetOneFreeDiscountScheme()));
    }

    private static Arguments twoItemsWithTwoForOnePoundDiscount() {
        return Arguments.of("two items with two for one pound discount", "0.40",
                asList(aBlockOfCheese(), aBlockOfCheese()), asList(buyTwoItemsForOnePoundDiscountScheme()));
    }

    private static Arguments twoDifferentItemsWithTwoForOnePoundDiscount() {
        return Arguments.of("two items with two for one pound discount", "0.00",
                asList(aBlockOfCheese(), aBoxOfCornflakes()), asList(buyTwoItemsForOnePoundDiscountScheme()));
    }

    private static Arguments threeItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("three items with three for price of two discount", "1.20",
                asList(aBoxOfCornflakes(), aBoxOfCornflakes(), aBoxOfCornflakes()), asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments twoItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("two items with three for price of two discount", "0.00",
                asList(aBoxOfCornflakes(), aBoxOfCornflakes()), asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments threeDifferentItemsWithThreeForPriceOfTwoDiscount() {
        return Arguments.of("three different items with three for price of two discount", "0.00",
                asList(aBoxOfCornflakes(), aBlockOfCheese(), aBoxOfCornflakes()), asList(buyThreeItemsForThePriceOfTwoDiscountScheme()));
    }

    private static Arguments twoItemsWithTwoForOnePoundAndTwoItemsWithBuyOneGetOneFreeDiscounts() {
        return Arguments.of("two items with two for one pound and two items with buy one get one free discounts", "1.60",
                asList(aBoxOfCornflakes(), aBlockOfCheese(), aBoxOfCornflakes(), aBlockOfCheese()), asList(buyTwoItemsForOnePoundDiscountScheme(), buyOneGetOneFreeDiscountScheme()));
    }

    private static Arguments oneKgOfCarrotsWithKgVegetablesHalfPriceDiscount() {
        return Arguments.of("1kg of carrots with kg vegetables half price discount", "5.00",
                asList(aKgOfCarrots()), asList(buyOneKgVegetablesGetHalfPriceScheme()));
    }

    private static Arguments oneKgOfVegetablesWithKgVegetablesHalfPriceDiscount() {
        return Arguments.of("1kg of vegetables with kg vegetables half price discount", "20.00",
                asList(aHalfKgOfCarrots(), aHalfKgOfPotatoes()), asList(buyOneKgVegetablesGetHalfPriceScheme()));
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

    private static Item aKgOfCarrots() {
        return new ItemByWeight(carrotProduct(), new BigDecimal(1.0));
    }

    private static Item aHalfKgOfCarrots() {
        return new ItemByWeight(carrotProduct(), new BigDecimal(0.5));
    }

    private static Item aHalfKgOfPotatoes() {
        return new ItemByWeight(potatoProduct(), new BigDecimal(0.5));
    }

    private static WeighedProduct carrotProduct() {
        return new WeighedProduct("C1", new BigDecimal(10.00), VEGETABLE);
    }

    private static WeighedProduct potatoProduct() {
        return new WeighedProduct("P1", new BigDecimal(70.00), VEGETABLE);
    }


    private static BuyOneGetOneFreeDiscountScheme buyOneGetOneFreeDiscountScheme() {
        return new BuyOneGetOneFreeDiscountScheme(boxOfCornflakesProduct());
    }

    private static BuyTwoForPriceDiscountScheme buyTwoItemsForOnePoundDiscountScheme() {
        return new BuyTwoForPriceDiscountScheme(blockOfCheeseProduct(), new BigDecimal(1.00));
    }

    private static BuyThreeForTwoDiscountScheme buyThreeItemsForThePriceOfTwoDiscountScheme() {
        return new BuyThreeForTwoDiscountScheme(boxOfCornflakesProduct());
    }

    private static WeightOfTypeDiscountScheme buyOneKgVegetablesGetHalfPriceScheme() {
        return new WeightOfTypeDiscountScheme(new BigDecimal(1.0), new BigDecimal(0.5), VEGETABLE);
    }

}