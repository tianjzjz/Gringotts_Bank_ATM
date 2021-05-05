package Data;

import BankAttributes.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is responsible for managing all the data read & write from the account database,
 * i.e. [Accounts.csv].
 */
public class AccountDataManager extends DataManager<Account> implements Interest {

    public AccountDataManager() throws ParseException { super("Customers", "Accounts.csv"); }

    @Override
    public void readData() throws ParseException {
        ArrayList<String> accountsInfo = new ArrayList<>();

        try {
            // Read all the contents in the file and add every line into [accountsInfo]
            BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
            String line;

            while ((line = reader.readLine()) != null) { accountsInfo.add(line); }

        } catch (Exception e) {
            e.printStackTrace();
        }

        data.clear();

        for (String info : accountsInfo) {
            String[] elements = info.split(",");

            // Get the up-to-date currency of this account
            String symbol = String.valueOf(elements[2].charAt(elements[2].length() - 1));
            double amount = Double.parseDouble((String) elements[2].subSequence(0, elements[2].length() - 1));
            Currency c;

            if (symbol.equals("¥")) { c = new CNY(amount); }
            else if (symbol.equals("HK$")) { c = new HKD(amount); }
            else { c = new USD(amount); }

            // Get the account open date
            Date openDate = Transaction.DATE_FORMAT.parse(elements[3]);

            // Add the account to [data]
            if (elements[0].equalsIgnoreCase("SAVINGS")) {
                data.add(new Savings(elements[0], elements[1], c, openDate));
            } else if (elements[0].equalsIgnoreCase("CHECKING")) {
                data.add(new Checking(elements[0], elements[1], c, openDate));
            } else if (elements[0].equalsIgnoreCase("SECURITIES")) {
                data.add(new Securities(elements[0], elements[1], c, openDate));
            }

            // If the account has transactions history
            if (elements.length > 4) {
                data.get(data.size() - 1).setHistory(stringToTransactions(elements[4]));
                data.get(data.size() - 1).setLoan(stringToLoan(elements[4], elements[0]));
            }
        }
    }

    @Override
    public void writeData() {
        try {
            File dir = new File(filePath);
            if (!dir.exists()) { dir.mkdir(); }

            File csvFile = new File(filePath + "/" + fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));

            for (Account a : data) {
                // Write all the account information to the csv file
                writer.write(a + "," + transactionsToString(a));
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            // File catch exception when initialization
            e.printStackTrace();
        }
    }

    public String transactionsToString(Account a) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < a.getHistory().size(); ++i) {
            // Merge all transaction histories into one StringBuilder object
            if (i == a.getHistory().size() - 1) { res.append(a.getHistory().get(i)); }
            else { res.append(a.getHistory().get(i)).append("|"); }
        }

        return res.toString();
    }

    public ArrayList<Transaction> stringToTransactions(String allTransactions) {
        ArrayList<Transaction> result = new ArrayList<>();

        try {
            // All the transaction records of this account
            String[] temp = allTransactions.split("\\|");

            for (String s : temp) {
                // For every transaction record

                // All the params of a single transaction
                String[] params = s.split(" - ");

                // Get the currency of this account
                String symbol = String.valueOf(params[2].charAt(params[2].length() - 1));
                double amount = Double.parseDouble((String) params[2].subSequence(0, params[2].length() - 1));
                Currency c;

                if (symbol.equals("¥")) { c = new CNY(amount); }
                else if (symbol.equals("HK$")) { c = new HKD(amount); }
                else { c = new USD(amount); }

                Date date = Transaction.DATE_FORMAT.parse(params[0]);

                result.add(new Transaction(date, c, params[1]));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Loan stringToLoan(String allTransactions, String type) {
        Loan result = null;

        try {
            // All the transaction records of this account
            String[] temp = allTransactions.split("\\|");

            // Attributes of a loan
            double amount = 0.0;
            Date date = null;

            for (String s : temp) {
                // For every transaction record

                // All the params (i.e. elements) of a single transaction
                String[] params = s.split(" - ");

                if (params[1].equals(Account.TYPE4)) {
                    // Request a loan
                    amount += Double.parseDouble((String) params[2].subSequence(0, params[2].length() - 1));
                } else if (params[1].equals(Account.TYPE5)) {
                    // Repay a loan
                    amount -= Double.parseDouble((String) params[2].subSequence(0, params[2].length() - 1));
                }

                date = Transaction.DATE_FORMAT.parse(params[0]);
            }

            // If at the end of the day, there still exists an unpaid loan, initialize the lone
            if (amount > 0) { result = new Loan(type, amount, date); }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    // Add a new Checking/Savings/Securities account
    public void addAccount(Account target) {
        data.add(target);
        writeData();
    }

    // Close a Checking/Savings/Securities account
    public void removeAccount(Account target) {
        data.removeIf(a -> a.getAccountId().equals(target.getAccountId()));
        writeData();
    }

    // Deposit money.
    public double deposit(Account self, Date date, double amount) {
        for (Account a : data) {
            if (a.getAccountId().equals(self.getAccountId())) {
                double newBalance = a.deposit(date, amount);
                writeData();

                return newBalance;
            }
        }

        return 0.0;
    }

    // Withdraw money.
    public double withdraw(Account self, Date date, double amount) {
        for (Account a : data) {
            if (a.getAccountId().equals(self.getAccountId())) {
                if (a.withdraw(date, amount)) {
                    writeData();

                    return amount * (1 + chargeInterestRate);
                }
            }
        }

        return 0.0;
    }

    // Sell a stock.
    public double sellStock(Account securities, Account savings, Date date, double sellAmount) {
        for (Account a : data) {
            // For every account in our account database
            if (a.getAccountId().equals(securities.getAccountId())) {
                // Find the securities account in the database
                if (a.sellStock(date, sellAmount)) {
                    // If successfully sell the stock of that securities account
                    for (Account aa : data) {
                        if (aa.getAccountId().equals(savings.getAccountId())) {
                            // Update the balance of the corresponding savings account in the database
                            aa.setBalance(savings.getBalance().getAmount() - 1000 + sellAmount);
                        }
                    }

                    writeData();
                }

                return 0.0;
            }
        }

        return -1.0;
    }

    // Transfer money.
    public double transfer(Account self, Date date, double amount) {
        for (Account a : data) {
            if (a.getAccountId().equals(self.getAccountId())) {
                if (a.transfer(date, amount)) {
                    writeData();

                    return amount * (1 + chargeInterestRate); // Can transfer (i.e. send)
                }
            }
        }

        return 0.0;
    }

    // Apply for loans.
    public boolean applyLoans(Account self, Date date, double amount, String collateralName) {
        for (Account a : data) {
            if (a.getAccountId().equals(self.getAccountId())) {
                if (a.applyLoans(date, amount, collateralName)) {
                    writeData();
                    return true;
                }
            }
        }

        return false;
    }

    // Repay the loans.
    public double repayLoans(Account self, Date date, double amount) {
        for (Account a : data) {
            if (a.getAccountId().equals(self.getAccountId())) {
                if (a.repayLoans(date, amount)) {
                    writeData();

                    return amount * (1 + chargeInterestRate);
                }
            }
        }

        return 0.0;
    }
}