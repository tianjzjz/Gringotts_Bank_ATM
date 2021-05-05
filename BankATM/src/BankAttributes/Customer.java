package BankAttributes;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class holds all operations of a customer.
 * It extends from User since a customer is also a user.
 */
public class Customer extends User{
    protected ArrayList<Account> accounts;

    public Customer(String customerName, String password) {
        super(customerName, password);

        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    // Open an account.
    public boolean openAccount(Account account, Date date, boolean shouldAddToHistory) {
        accounts.add(account);

        // Prevent from adding the same operation multiple times to the transaction history!
        if (shouldAddToHistory) { account.open(date); }

        return true;
    }

    // Close an account.
    public boolean closeAccount(String type, Date date, boolean shouldAddToHistory) {
        for (Account a : accounts) {
            if (a.type.equalsIgnoreCase(type)) {
                accounts.remove(a);

                // Prevent from adding the same operation multiple times to the transaction history!
                if (shouldAddToHistory) { a.close(date); }

                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (accounts.isEmpty()) {
            return userName + "," + password;
        } else if (accounts.size() == 1) {
            Account a = accounts.get(0);
            return userName + "," + password + "," + a.type + "," + a.balance + "," + a.accountId;
        } else if (accounts.size() == 2) {
            Account a1 = accounts.get(0);
            Account a2 = accounts.get(1);

            return userName + "," + password + "," + a1.type + "," + a1.balance + "," + a1.accountId + "," +
                    a2.type + "," + a2.balance + "," + a2.accountId;
        } else {
            StringBuilder res = new StringBuilder(userName + "," + password);

            for (Account a : accounts) {
                res.append(",").append(a.type).append(",").append(a.balance).append(",").append(a.accountId);
            }

            return res.toString();
        }
    }
}