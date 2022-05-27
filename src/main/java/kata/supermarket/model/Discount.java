package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.List;

public class Discount {

    private List<Item> matches;

    private BigDecimal discount;

    public Discount(List<Item> matches, BigDecimal discount) {
        this.matches = matches;
        this.discount = discount;
    }

    public List<Item> getMatches() {
        return matches;
    }

    public void setMatches(List<Item> matches) {
        this.matches = matches;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
