package kata.supermarket.model;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final UnitProduct unitProduct;

    public ItemByUnit(final UnitProduct unitProduct) {
        this.unitProduct = unitProduct;
    }

    public BigDecimal price() {
        return unitProduct.pricePerUnit();
    }

    @Override
    public Product getProduct() {
        return unitProduct;
    }
}
