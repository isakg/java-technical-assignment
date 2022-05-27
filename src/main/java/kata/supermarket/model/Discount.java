package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;

public class Discount {

    private List<Item> matches;

    private BigDecimal value;

    Discount(List<Item> matches, BigDecimal value) {
        this.matches = matches;
        this.value = value;
    }

    public List<Item> getMatches() {
        return matches;
    }

    public BigDecimal getValue() {
        return value;
    }

}
