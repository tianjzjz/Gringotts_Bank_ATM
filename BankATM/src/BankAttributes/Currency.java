package BankAttributes;

import java.text.DecimalFormat;

/**
 * This class is the top class of all types of currencies.
 */
public class Currency {
    protected double amount;
    protected String symbol; // i.e. ¥/$/HK$, etc
    protected CurrencyExchangeBehavior currencyExchangeBehavior;
    public DecimalFormat df;

    public Currency(double amount, String symbol) {
        this.amount = amount;
        this.symbol = symbol;
        this.df = new DecimalFormat("#.00"); // We only reserve two decimal places for a double variable
    }

    public double getAmount() {
        return amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public CurrencyExchangeBehavior getCurrencyExchangeBehavior() {
        return currencyExchangeBehavior;
    }

    @Override
    public String toString() {
        return "" + df.format(amount) + symbol;
    }
}
