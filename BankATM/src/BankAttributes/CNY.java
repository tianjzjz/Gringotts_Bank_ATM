package BankAttributes;

/**
 * This class represents the CNY (Chinese Yuan, i.e. ¥) currency.
 */
public class CNY extends Currency{
    public CNY(double amount) {
        super(amount, "¥");

        currencyExchangeBehavior = new CNYExchangeBehavior(this);
    }
}
