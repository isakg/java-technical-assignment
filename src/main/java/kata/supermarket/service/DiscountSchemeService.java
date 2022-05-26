package kata.supermarket.service;

import kata.supermarket.model.DiscountScheme;

import java.util.Set;

public class DiscountSchemeService {

    private Set<DiscountScheme> discountSchemes;

    public DiscountSchemeService(Set<DiscountScheme> discountSchemes) {
        this.discountSchemes = discountSchemes;
    }
}
