package BankAttributes;

/**
 * This class manages the algorithm of exchanging HKD to other types of currencies.
 *
 * This is the implementation of Strategy Pattern!
 */
public class HKDExchangeBehavior implements CurrencyExchangeBehavior{
    private HKD hkd;

    public HKDExchangeBehavior(HKD hkd) {
        this.hkd = hkd;
    }


    @Override
    public double exchange(String targetCurrencyType) {
        if (targetCurrencyType.equals("¥")) { return hkd.amount * HKDToCNY; }
        else if (targetCurrencyType.equals("$")) { return hkd.amount * HKDToUSD; }
        else if (targetCurrencyType.equals("HK$")) { return hkd.amount; }

        invalidExchange();

        return -1.0;
    }
}
