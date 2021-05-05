package Data;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * This is a generic class that holds the data read from the database
 * @param <T> The type of data read from the database (i.e. Account/Customer/AccountFees).
 */
public abstract class DataManager<T> {
    protected String filePath;
    protected String fileName;

    public ArrayList<T> data;

    public DataManager(String filePath, String fileName) throws ParseException {
        this.filePath = filePath;
        this.fileName = fileName;
        this.data = new ArrayList<>();

        readData();
    }

    // Read the data from the database.
    public abstract void readData() throws ParseException;

    // Write the data to the database to keep it up-to-date.
    public abstract void writeData();
}
