package BankAttributes;

/**
 * This class represents the USD (US Dollar, i.e. $) currency.
 */
public class USD extends Currency{
    public USD(double amount) {
        super(amount, "$");

        currencyExchangeBehavior = new USDExchangeBehavior(this);
    }
}
