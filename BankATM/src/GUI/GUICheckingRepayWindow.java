package GUI;

import BankAttributes.Account;
import BankAttributes.Customer;
import BankAttributes.USD;
import Data.AccountDataManager;
import Data.CustomerDataManager;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;
import java.util.Date;

/**
 * - This class serves as a functional page for customer to repay the loan of his/her saving
 *   account.
 * - Most UI designs are inherited from parent class "GUIRepayLoanWindow", and this class
 *   determines the function "repay-loan" on his/her checking account.
 *
 */
public class GUICheckingRepayWindow extends GUIRepayLoanWindow {

    public GUICheckingRepayWindow(Customer customer){
        super(customer);

        // hint
        // TODO: retrieve loan balance
        USD loan = null;

        for (Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("CHECKING")) {
                loan = a.getLoan().getMoney();
                break;
            }
        }

        hintLabel = new JLabel("You have " + loan + " loan balance in total");
        hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hintLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
        hintLabel.setBounds(143, 150, 509, 70);
        rwPanel.add(hintLabel);

        // title
        setTitle("Repay the Loan in Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,120,800,600);
        rwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(rwPanel);
        rwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        rwPanel.add(backButton);

        // confirm
        ConfirmListener cl = new ConfirmListener();
        confirmButton.addActionListener(cl);
    }
    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingLoanWindow checkingLoanWindow = new GUICheckingLoanWindow(customer);
                checkingLoanWindow.setVisible(true);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

    // TODO: repay action

    /**
     * customer could repay any amount of money to his/her loan
     * in his checking account but not exceed total loan amount
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;
            try{
                amount = Double.valueOf(amountField.getText());

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(rwPanel,"You Should Input a Positive Number!",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                double interest = amount*0.2;
                int choice = JOptionPane.showConfirmDialog(rwPanel,"Your balance will be automatically deducted the loan interest of $"+interest,"Warning", JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);

                // TODO: check whether it's a valid amount and then repay to account
                CustomerDataManager cData = new CustomerDataManager();
                AccountDataManager aData = new AccountDataManager();
                Account temp = null;

                for (Account a : customer.getAccounts()) {
                    // Get the checking account of the customer
                    if (a.getType().equalsIgnoreCase("CHECKING")) { temp = a; }
                }

                double repayAmount = aData.repayLoans(temp, new Date(), amount);


                if (repayAmount != 0.0) {
                    cData.updateBalance(customer, temp, temp.getBalance().getAmount() - repayAmount);
                    cData.readData();

                    for (Customer c : cData.data) {
                        // Update the loan status of the checking account of the customer
                        if (c.getUserName().equals(customer.getUserName())) { customer = c; }
                    }

                    JOptionPane.showMessageDialog(rwPanel,"Successfully Repay For Your Checking Account", "Success",JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                } else {
                    JOptionPane.showMessageDialog(rwPanel,"Not Enough Balance OR Payment Larger Than Your"
                            + " Loan!", "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                }

                setVisible(false);
                GUICheckingLoanWindow checkingLoanWindow = new GUICheckingLoanWindow(customer);
                checkingLoanWindow.setVisible(true);

            }catch(Exception ex){
                if(amount == null){
                    JOptionPane.showMessageDialog(rwPanel,"You should input a number","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }
                JOptionPane.showMessageDialog(rwPanel,"Failed to repay");
            }
        }
    }


}
