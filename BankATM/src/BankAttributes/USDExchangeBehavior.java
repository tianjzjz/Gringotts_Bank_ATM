package BankAttributes;

/**
 * This class manages the algorithm of exchanging USD to other types of currencies.
 *
 * This is the implementation of Strategy Pattern!
 */
public class USDExchangeBehavior implements CurrencyExchangeBehavior{
    private USD usd;

    public USDExchangeBehavior(USD usd) {
        this.usd = usd;
    }

    @Override
    public double exchange(String targetCurrencyType) {
        if (targetCurrencyType.equals("¥")) { return usd.amount * USDToCNY; }
        else if (targetCurrencyType.equals("HK$")) { return usd.amount * USDToHKD; }
        else if (targetCurrencyType.equals("$")) { return usd.amount; }

        invalidExchange();

        return -1.0;
    }
}
