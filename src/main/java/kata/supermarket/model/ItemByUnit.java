package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class ItemByUnit implements Item {

    private final UnitProduct unitProduct;

    public ItemByUnit(final UnitProduct unitProduct) {
        this.unitProduct = unitProduct;
    }

    public BigDecimal price() {
        return unitProduct.pricePerUnit();
    }

    @Override
    public Optional<BigDecimal> getWeight() {
        return Optional.empty();
    }

    @Override
    public Product getProduct() {
        return unitProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemByUnit that = (ItemByUnit) o;
        return Objects.equals(unitProduct, that.unitProduct);
    }

    @Override
    public int hashCode() {

        return Objects.hash(unitProduct);
    }
}
