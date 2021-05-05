package Data;

import BankAttributes.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is responsible for managing all the data read & write from the boss database,
 * i.e. [Fees.csv].
 */
public class BossDataManager extends DataManager<AccountFees> {
    public BossDataManager() throws ParseException { super("Boss", "Fees.csv"); }

    @Override
    public void readData() throws ParseException {
        ArrayList<String> feesInfo = new ArrayList<>();

        try {
            // Read all the contents in the file and add every line into [accountsInfo]
            BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
            String line;

            while ((line = reader.readLine()) != null) { feesInfo.add(line); }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.clear();

        for (String info : feesInfo) {
            String[] elements = info.split(",");

            double fee = Double.parseDouble(elements[0]);
            Date date = Transaction.DATE_FORMAT.parse(elements[1]);

            data.add(new AccountFees(fee, date));
        }
    }

    @Override
    public void writeData() {
        try {
            File dir = new File(filePath);
            if (!dir.exists()) { dir.mkdir(); }

            File csvFile = new File(filePath + "/" + fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));

            for (AccountFees f : data) {
                // Write all the user information to the csv file
                writer.write("" + f);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The bank manager can adjust the account opening fee.
    public void adjustOpenAccountFee(double fee) {
        data.add(new AccountFees(fee, new Date()));

        writeData();
    }
}

