package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Optional;

public interface Item {
    BigDecimal price();

    Product getProduct();

    Optional<BigDecimal> getWeight();

}
