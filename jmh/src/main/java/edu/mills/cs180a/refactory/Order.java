package edu.mills.cs180a.refactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * A Bagel Refactory order consisting of one or more instances of {@link Bag}.
 *
 * @author Ellen Spertus
 */
public class Order {
    private final List<Bag> bags;

    /**
     * Constructs an order with any number of bags of bagels.
     *
     * @param b the list of bags
     */
    private Order(List<Bag> b) {
        bags = b;
    }

    /**
     * Creates an order with a list of any number of bags.
     *
     * @param b the list of bags
     * @return the order
     */
    public static Order of(Bag... b) {
        return new Order(List.of(b));
    }

    /**
     * Gets the price of an order.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (Bag bag : bags) {
            price = price.add(bag.getTotalPrice());
        }
        return price;
    }

    /**
     * Generates a receipt string that contains the contents, discounts, and total final price.
     *
     * @return the receipt string
     */
    public String generateReceipt() {
        return TextReceiptGenerator1.getInstance().generateReceipt(this);
    }

    /**
     *
     * @return the list of bags
     */
    public List<Bag> getBags() {
        return bags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Order)) {
            return false;
        }
        return ((Order) o).bags.equals(bags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Bag b : bags) {
            sb.append(b).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bags);
    }
}
