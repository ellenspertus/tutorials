package edu.mills.cs180a.refactory;

public class TextReceiptGenerator1 extends ReceiptGenerator1 {
    private static final String BAG_TEMPLATE =
            "%s\n\tquantity: %d\n\tprice each: $%1s\n" + "\ttotal: %1s\n";
    private static final String FOOTER_TEMPLATE =
            "TOTAL: $%1s \nThank you for shopping at the Bagel Refactory!\n";
    private static final String DISCOUNT_TEMPLATE =
            "\tYou saved $%1s through our volume discount program.\n";

    public static final TextReceiptGenerator1 INSTANCE = new TextReceiptGenerator1();

    public static TextReceiptGenerator1 getInstance() {
        return INSTANCE;
    }

    private TextReceiptGenerator1() {
        super(BAG_TEMPLATE, FOOTER_TEMPLATE, DISCOUNT_TEMPLATE);
    }
}
