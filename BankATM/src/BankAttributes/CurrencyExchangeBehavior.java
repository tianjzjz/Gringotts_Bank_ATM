package BankAttributes;

/**
 * This class manages the algorithm of exchanging one type of currency to others.
 *
 * This is the implementation of Strategy Pattern!
 */
public interface CurrencyExchangeBehavior {
    double CNYToUSD = 0.17; // Chinese Yuan to US dollars
    double USDToCNY = 6.00; // US dollars to Chinese Yuan

    double HKDToUSD = 0.125; // Hong Kong dollars to US dollars
    double USDToHKD = 8.00; // US dollars to Hong Kong dollars

    double CNYToHKD = 1.3; // Chinese Yuan to Hong Kong dollars
    double HKDToCNY = 0.75; // Hong Kong dollars to Chinese Yuan

    /*
        Exchange the current currency to the target currency.
     */
    double exchange(String targetCurrencyType);

    default void invalidExchange() {
        System.out.println("Invalid Operation!");
    }
}
