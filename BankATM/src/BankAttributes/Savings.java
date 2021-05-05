package BankAttributes;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a Savings account, which is also an account.
 */
public class Savings extends Account{
    public Savings(String type, String accountId, Currency c, Date openDate) {
        super("Savings", accountId, c, openDate);
    }
}
