package Data;

import BankAttributes.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is responsible for managing all the data read & write from the customer database,
 * i.e. [Customers.csv].
 */
public class CustomerDataManager extends DataManager<Customer> {

    public CustomerDataManager() throws ParseException { super("Customers", "Customers.csv"); }

    @Override
    public void readData() throws ParseException {
        ArrayList<String> customersInfo = new ArrayList<>();

        try {
            // Read all the contents in the file and add every line into [accountsInfo]
            BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
            String line;

            while ((line = reader.readLine()) != null) { customersInfo.add(line); }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.clear();

        for (String info : customersInfo) {
            String[] elements = info.split(",");

            data.add(new Customer(elements[0], elements[1]));
            ArrayList<Account> allAccounts = new ArrayList<>();

            for (int i = 4; i < elements.length; i += 3) {
                // RRetrieve all accounts according to the account Id
                stringToAccounts(allAccounts, elements[i]);
            }

            data.get(data.size() - 1).setAccounts(allAccounts);
        }
    }

    public void stringToAccounts(ArrayList<Account> res, String accountId) throws ParseException {
        AccountDataManager accData = new AccountDataManager();

        for (Account a : accData.data) {
            if (a.getAccountId().equals(accountId)) {
                res.add(a);
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

            for (Customer c : data) {
                // Write all the user information to the csv file
                writer.write("" + (Customer) c);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open an account.
    public void openAccount(Customer c, Account account, Date date) throws ParseException {
        c.openAccount(account, date, true);

        for (Customer temp : data) {
            // Also add the account to the same customer in [data] list
            if (temp.getUserName().equals(c.getUserName())) {
                temp.openAccount(account, date, false);
                break;
            }
        }

        writeData();
    }

    // Close an account.
    public void closeAccount(Customer c, Account account, Date date) {
        c.closeAccount(account.getType(), new Date(), true);

        for (Customer temp : data) {
            // Also add the account to the same customer in [data] list
            if (temp.getUserName().equals(c.getUserName())) {
                temp.closeAccount(account.getType(), date, false);
                break;
            }
        }

        writeData();
    }

    // When an account has any transactions (e.g. deposit, transfer),
    // update the balance of it.
    public void updateBalance(Customer c, Account account, double newBalance) {
        for (Account a : c.getAccounts()) {
            if (a.getAccountId().equals(account.getAccountId())) { a.setBalance(newBalance); }
        }

        for (Customer temp : data) {
            // For every customer in [data]
            if (temp.getUserName().equals(c.getUserName())) {
                // If the customer in [data] has the same username as [c]
                for (Account a : temp.getAccounts()) {
                    // For every account of the customer in [data]
                    if (a.getAccountId().equals(account.getAccountId())) {
                        // Update the balance of the account of the customer in [data]
                        a.setBalance(newBalance);
                    }
                }
            }
        }

        writeData();
    }
}
