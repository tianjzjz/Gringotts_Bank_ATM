package BankAttributes;

import java.util.Date;

/**
 * THis class represents the loan that can be applied and repaid by a customer.
 */
public class Loan {
    private String accountType;
    private USD money;
    private Date loanDate;

    public Loan(String accountType, double amount, Date loanDate) {
        this.accountType = accountType;
        this.money = new USD(amount);
        this.loanDate = loanDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public USD getMoney() {
        return money;
    }

    public void setMoney(double amount) {
        this.money.amount -= amount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public String toString() {
        return "Current Loan" + " - " + accountType + " - " + money + " - " +
                Transaction.DATE_FORMAT.format(loanDate);
    }
}
