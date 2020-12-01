package edu.mills.cs180a.refactory;

public class ReceiptGenerator {
    private final String bagTemplate;
    private final String footerTemplate;
    private final String discountTemplate;

    protected ReceiptGenerator(String bag, String footer, String discount) {
        bagTemplate = bag;
        footerTemplate = footer;
        discountTemplate = discount;
    }

    public String generateReceipt(Order order) {
        StringBuilder sb = new StringBuilder();
        addBagsToReceipt(sb, order);
        sb.append(generateFooter(order));
        return sb.toString();
    }

    private void addBagsToReceipt(StringBuilder sb, Order order) {
        for (Bag bag : order.getBags()) {
            sb.append(String.format(bagTemplate, bag.getBagel().getType(), bag.getQuantity(),
                    bag.getPerBagelPrice(), bag.getTotalPrice()));
            if (bag.isDiscounted()) {
                sb.append(String.format(discountTemplate, bag.getDiscount()));
            }
        }
    }

    private String generateFooter(Order order) {
        return String.format(footerTemplate, order.getPrice());
    }

}
