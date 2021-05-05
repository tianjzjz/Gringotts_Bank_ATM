package BankAttributes;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Securities account (for stock buying or selling), which is also an account.
 */
public class Securities extends Account {
    public Securities(String type, String accountId, Currency c, Date openDate) {
        super("Securities", accountId, c, openDate);
    }
}
