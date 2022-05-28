# Notes

Limitations:

If item/s could apply to multiple discount schemes, the first it applies to takes precedence. A more complete algorithm could be written to find best out of all applications.


Assumptions:

The service iterates through each discount scheme totaling the discount. Items that applied to that discount are removed so each item can only have one discount applied to it.


Design decisions:

A DiscountScheme and implementations of discount() method uses a decorator pattern so each apply discount based on internal state/logic.
Clearly polymorphism used with DiscountScheme, Item and Product to remove messy if/else case logic. Encapsulation employed as standard throughout the model.
The Item#getWeight works ok with the Optional but with more time I'd find a way to not have to define that for ItemByUnit for example.
