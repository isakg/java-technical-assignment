package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;

public class UnitProduct implements Product {

    private final String code;

    private final BigDecimal pricePerUnit;

    public UnitProduct(final String code, final BigDecimal pricePerUnit) {
        this.code = code;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitProduct that = (UnitProduct) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code);
    }
}
