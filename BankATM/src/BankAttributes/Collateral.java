package BankAttributes;

/**
 * This class denotes the collaterals of the customers' loans.
 */
public class Collateral {
    public static final String TYPE8 = "Add Collateral";
    public static final String TYPE9 = "Get Back Collateral";

    private String type;
    private String itemName;

    public Collateral(String type, String itemName) {
        this.type = type;
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return type + " - " + itemName;
    }
}
