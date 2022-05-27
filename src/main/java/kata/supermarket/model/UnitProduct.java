package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;

import static kata.supermarket.model.ProductType.OTHER;

public class UnitProduct implements Product {

    private final String code;

    private final BigDecimal pricePerUnit;

    private final ProductType productType;

    public UnitProduct(final String code, final BigDecimal pricePerUnit) {
        this(code, pricePerUnit, OTHER);
    }

    public UnitProduct(final String code, final BigDecimal pricePerUnit, final ProductType productType) {
        this.code = code;
        this.pricePerUnit = pricePerUnit;
        this.productType = productType;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    @Override
    public ProductType getProductType() {
        return productType;
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
