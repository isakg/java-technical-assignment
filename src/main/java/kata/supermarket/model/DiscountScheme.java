package kata.supermarket.model;

import java.util.List;

public interface DiscountScheme {

    Discount discount(List<Item> items);

}
