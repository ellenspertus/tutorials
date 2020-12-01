package edu.mills.cs180a.refactory;

public class TextReceiptGenerator extends ReceiptGenerator {
    private static final String BAG_TEMPLATE =
            "%s\n\tquantity: %d\n\tprice each: $%1s\n" + "\ttotal: %1s\n";
    private static final String FOOTER_TEMPLATE =
            "TOTAL: $%1s \nThank you for shopping at the Bagel Refactory!\n";
    private static final String DISCOUNT_TEMPLATE =
            "\tYou saved $%1s through our volume discount program.\n";

    public static final TextReceiptGenerator INSTANCE = new TextReceiptGenerator();

    public static TextReceiptGenerator getInstance() {
        return INSTANCE;
    }

    private TextReceiptGenerator() {
        super(BAG_TEMPLATE, FOOTER_TEMPLATE, DISCOUNT_TEMPLATE);
    }
}
