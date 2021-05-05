package BankAttributes;

import java.util.Date;

/**
 * This class represents the account opening fee that the bank manager can charge when a new account opened.
 * Because the manager can adjust the account opening fee at any time, we need a specific class to hold all
 * fees in history together with their set time.
 *
 * This class is associated with [BossDataManager.java] in the [Data package].
 */
public class AccountFees {
    private double openAccountFee;
    private Date feeSetTime;

    public AccountFees(double openAccountFee, Date feeSetTime) {
        this.openAccountFee = openAccountFee;
        this.feeSetTime = feeSetTime;
    }

    public double getOpenAccountFee() {
        return openAccountFee;
    }

    public void setOpenAccountFee(double openAccountFee) {
        this.openAccountFee = openAccountFee;
    }

    public Date getFeeSetTime() {
        return feeSetTime;
    }

    public void setFeeSetTime(Date feeSetTime) {
        this.feeSetTime = feeSetTime;
    }

    @Override
    public String toString() {
        return "" + openAccountFee + "," +
                Transaction.DATE_FORMAT.format(feeSetTime);
    }
}
