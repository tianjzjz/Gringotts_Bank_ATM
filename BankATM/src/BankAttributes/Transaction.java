package BankAttributes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds all attributes and print methods of a single transaction.
 */
public class Transaction {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy/hh:mm:ss");
    public DecimalFormat df;

    protected Date date;
    protected Currency currency;
    protected String type;

    public Transaction(Date date, Currency currency, String type) {
        this.date = date;
        this.currency = currency;
        this.type = type;
        this.df = new DecimalFormat("#.00");
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(date) + " - " + type + " - " + currency;
    }

    // Print customer's transaction history
    public String printHistory(String username, String accTYpe) {
        if (type.equals(Account.TYPE6)) {
            // If the user opens a Checking/Savings account
            if (!accTYpe.equalsIgnoreCase("SECURITIES")) {
                return username + " has OPENED her/his " + accTYpe +
                        " account on " + DATE_FORMAT.format(date);
            } else {
                // If the user opens a Securities account
                return username + " paid $1000 from her/his Savings account to open a " + accTYpe +
                        " account on " + DATE_FORMAT.format(date);
            }

        } else if (type.equals(Account.TYPE7)) {
            return username + " has CLOSED her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date) + " with remaining " +
                    "balance " + currency;
        } else if (type.equals(Account.TYPE1)) {
            return username + " has DEPOSITED " + currency + " to her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date);
        } else if (type.equals(Account.TYPE2)) {
            return username + " has WITHDRAWN " + currency + " to her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date);
        } else if (type.equals(Account.TYPE3)) {
            return username + " has TRANSFERRED " + currency + " from her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date);
        } else if (type.equals(Account.TYPE4)) {
            return username + " has APPLIED a " + currency + " LOAN to her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date);
        } else if (type.equals(Account.TYPE5)) {
            double paidAmount = currency.amount + currency.amount * Interest.chargeInterestRate;
            String res = df.format(paidAmount);

            return username + " has REPAID " + res +"$ of her/his " + accTYpe +
                    " account's LOAN on " + DATE_FORMAT.format(date);
        } else if (type.equals(Account.TYPE8)) {
            return username + " has SOLD her/his STOCK for " + currency + " on " +
                    DATE_FORMAT.format(date);
        }

        return "ERROR When Printing Transaction Histories!";
    }

    // Print the revenue history of the bank manager (i.e. our professor ^)
    public String printRevenue(String username, double openAccFee, String accTYpe) {
        if (type.equals(Account.TYPE6)) {
            // If the user opens a Checking/Savings account
            if (!accTYpe.equalsIgnoreCase("SECURITIES")) {
                return username + " has OPENED her/his " + accTYpe +
                        " account on " + DATE_FORMAT.format(date) + " -> Revenue: " + df.format(openAccFee)
                        + "$" + "\r\n\r\n";
            } else { return ""; }

        } else if (type.equals(Account.TYPE7)) {
//            return username + " has CLOSED her/his " + accTYpe +
//                    " account on " + DATE_FORMAT.format(date) + " with remaining " +
//                    "balance " + currency + " -> Revenue: 0$";
            return "";
        } else if (type.equals(Account.TYPE1)) {
//            return username + " has DEPOSITED " + currency + " to her/his " + accTYpe +
//                    " account on " + DATE_FORMAT.format(date) + " -> Revenue: 0$";
            return "";
        } else if (type.equals(Account.TYPE2)) {
            return username + " has WITHDRAWN " + currency + " to her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date) + " -> Revenue: " +
                    df.format(currency.amount * Interest.chargeInterestRate) + "$" + "\r\n\r\n";
        } else if (type.equals(Account.TYPE3)) {
            return username + " has TRANSFERRED " + currency + " from her/his " + accTYpe +
                    " account on " + DATE_FORMAT.format(date) + " -> Revenue: " +
                    df.format(currency.amount * Interest.chargeInterestRate) + "$" + "\r\n\r\n";
        } else if (type.equals(Account.TYPE4)) {
//            return username + " has APPLIED a " + currency + " LOAN to her/his " + accTYpe +
//                    " account on " + DATE_FORMAT.format(date) + " -> Revenue: 0$";
            return "";
        } else if (type.equals(Account.TYPE5)) {
            double paidAmount = currency.amount + currency.amount * Interest.chargeInterestRate;
            String res = df.format(paidAmount);

            return username + " has REPAID " + res + "$ of her/his " + accTYpe +
                    " account's LOAN on " + DATE_FORMAT.format(date) +
                    " -> Revenue: " + df.format(currency.amount * Interest.chargeInterestRate) + "$" + "\r\n\r\n";
        } else if (type.equals(Account.TYPE8)) {
            return username + " has SOLD her/his STOCK for " + df.format(currency.amount) + "$ on " +
                    DATE_FORMAT.format(date) + " -> Revenue: " + df.format(currency.amount * 0.25) + "$"
                    + "\r\n\r\n";
        }

        return "ERROR When Printing Revenue Histories!";
    }

    // Get the revenue of a transaction for the bank manager.
    public double revenueAmount(double openAccFee) {
        if (type.equals(Account.TYPE6)) { return openAccFee; }

        else if (type.equals(Account.TYPE7)) { return 0.0; }

        else if (type.equals(Account.TYPE1)) { return 0.0; }

        else if (type.equals(Account.TYPE2)) { return currency.amount * Interest.chargeInterestRate; }

        else if (type.equals(Account.TYPE3)) { return currency.amount * Interest.chargeInterestRate; }

        else if (type.equals(Account.TYPE4)) { return 0.0; }

        else if (type.equals(Account.TYPE5)) { return currency.amount * Interest.chargeInterestRate; }

        else if (type.equals(Account.TYPE8)) { return currency.amount * 0.25; }

        return -1.0;
    }
}
