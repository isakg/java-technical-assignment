package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountScheme {

    List<Item> matches(List<Item> toMatch);

    BigDecimal getDiscountForMatches(List<Item> matches);

}
