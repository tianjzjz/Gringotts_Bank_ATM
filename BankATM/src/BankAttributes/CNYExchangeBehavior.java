package BankAttributes;

/**
 * This class manages the algorithm of exchanging CNY to other types of currencies.
 *
 * This is the implementation of Strategy Pattern!
 */
public class CNYExchangeBehavior implements CurrencyExchangeBehavior {
    private CNY cny;

    public CNYExchangeBehavior(CNY cny) {
        this.cny = cny;
    }

    // Exchange CNY to other currencies.
    @Override
    public double exchange(String targetCurrencyType) {
        if (targetCurrencyType.equals("HK$")) { return cny.amount * CNYToHKD; }
        else if (targetCurrencyType.equals("$")) { return cny.amount * CNYToUSD; }
        else if (targetCurrencyType.equals("¥")) { return cny.amount; }

        invalidExchange();

        return -1.0;
    }
}
