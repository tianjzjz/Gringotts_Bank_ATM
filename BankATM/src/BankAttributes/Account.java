package BankAttributes;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class manages all the possible actions of a bank account such as deposit, withdraw, etc.
 */
public class Account implements Interest {
    // All possible actions inside an account (notice that "buy a stock" is not listed here since
    // it is another form of "account opened".
    public static final String TYPE1 = "Deposit";
    public static final String TYPE2 = "Withdraw";
    public static final String TYPE3 = "Transfer";
    public static final String TYPE4 = "Apply Loans";
    public static final String TYPE5 = "Repay Loans";
    public static final String TYPE6 = "Account Opened";
    public static final String TYPE7 = "Account Closed";
    public static final String TYPE8 = "Sell the Stock";

    protected String type;
    protected String accountId;

    protected Currency balance;
    protected Date openDate;
    protected Loan loan;
    protected Collateral collateral;
    protected ArrayList<Transaction> history;

    public Account(String type, String accountId, Currency balance, Date openDate) {
        this.type = type;
        this.accountId = accountId;

        this.balance = balance;
        this.openDate = openDate;
        this.loan = null;
        this.collateral = null;
        this.history = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Loan getLoan() {
        return loan;
    }

    public String getAccountId() {
        return accountId;
    }

    public Currency getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        this.balance.amount = amount;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Transaction> history) {
        this.history = history;
    }

    // Deposit money.
    public double deposit(Date date, double amount) {
        // If the deposit amount >= 1000$, pay interest
        if (amount >= 1000) { amount *= (1 + payInterestRate); }

        balance.amount += amount;
        history.add(new Transaction(date, new Currency(amount, balance.symbol), TYPE1)); // Add a deposit history

        return balance.amount;
    }

    // Withdraw money.
    public boolean withdraw(Date date, double amount) {
        if (amount <= 0.0) { return false; }

        // Charge fee whenever a withdrawal is made
        double temp = amount * (1 + chargeInterestRate);

        if (balance.amount >= temp) {
            balance.amount -= temp;
            history.add(new Transaction(date, new USD(amount), TYPE2));

            return true;
        }

        return false;
    }

    // Sell a stock.
    public boolean sellStock(Date date, double sellAmount) {
        if (sellAmount < 0.0) { return false; }

        balance.amount = 0.0;
        history.add(new Transaction(date, new USD(sellAmount), TYPE8));

        return true;
    }

    // Transfer money.
    public boolean transfer(Date date, double amount) {
        // Charge fee whenever a transfer is made
        double temp = amount * (1 + chargeInterestRate);

        if (balance.amount < temp || temp <= 0.0) { return false; }

        balance.amount -= temp;
        history.add(new Transaction(date, new USD(amount), TYPE3));

        return true;
    }

    // Apply loans.
    public boolean applyLoans(Date date, double amount, String collateralName) {
        // Cannot apply for a loan if the account has already applied for a loan ^
        if (loan != null) { return false; }

        balance.amount += amount;
        loan = new Loan(type, amount, date);
        collateral = new Collateral(type, collateralName); // Add the collateral
        history.add(new Transaction(date, new USD(amount), TYPE4));

        return true;
    }

    // Repay loans.
    public boolean repayLoans(Date date, double amount) {
        // Charge fee whenever a loan repay is made
        double temp = amount * (1 + chargeInterestRate);

        if (loan == null || amount > loan.getMoney().amount || balance.amount < temp) { return false; }

        balance.amount -= temp;
        loan.setMoney(amount);

        if (loan.getMoney().amount == 0) {
            // If the load is repaid, remove the loan and the collateral
            loan = null;
            collateral = null;
        }

        history.add(new Transaction(date, new USD(amount), TYPE5));

        return true;
    }

    // Open an account.
    public void open(Date date) {
        history.add(new Transaction(date, balance, TYPE6));
    }

    // Close an account.
    public void close(Date date) {
        history.add(new Transaction(date, balance, TYPE7));
    }

    @Override
    public String toString() {
        return type + "," + accountId + "," + balance + "," + Transaction.DATE_FORMAT.format(openDate);
    }
}
