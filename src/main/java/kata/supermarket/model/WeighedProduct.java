package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;

public class WeighedProduct implements Product {

    private final String code;

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final String code, final BigDecimal pricePerKilo) {
        this.code = code;
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WeighedProduct that = (WeighedProduct) o;
//        return Objects.equals(code, that.code);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(code);
//    }
}
