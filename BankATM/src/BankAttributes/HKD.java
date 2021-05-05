package BankAttributes;

/**
 * This class represents the HKD (Hong Kong Dollar, i.e. HK$) currency.
 */
public class HKD extends Currency{
    public HKD(double amount) {
        super(amount, "HK$");

        currencyExchangeBehavior = new HKDExchangeBehavior(this);
    }
}
