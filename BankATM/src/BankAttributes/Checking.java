package BankAttributes;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Checking account, which is also an account.
 */
public class Checking extends Account{
    public Checking(String type, String accountId, Currency c, Date openDate) {
        super("Checking", accountId, c, openDate);
    }
}
