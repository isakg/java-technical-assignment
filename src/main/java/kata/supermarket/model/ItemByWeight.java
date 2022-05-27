package kata.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    public ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public Optional<BigDecimal> getWeight() {
        return Optional.of(weightInKilos);
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemByWeight that = (ItemByWeight) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product);
    }
}
