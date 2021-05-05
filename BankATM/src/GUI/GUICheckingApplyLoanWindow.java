package GUI;

import BankAttributes.Account;
import BankAttributes.Customer;
import Data.AccountDataManager;
import Data.CustomerDataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * - This class serves as a functional page for customer to apply a loan on his/her checking
 *   account.
 * - Most UI designs are inherited from parent class "GUICustomerApplyLoanWindow", and this class
 *   determines the function "apply-loan" on his/her checking account. The customer is able to apply
 *   for a loan only if he/she doesn't have a loan on checking account yet.
 */
public class GUICheckingApplyLoanWindow extends GUIApplyLoanWindow{

    public GUICheckingApplyLoanWindow(Customer customer){
        super(customer);

        // title
        setTitle("Apply For a Loan in Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        alPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(alPanel);
        alPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        alPanel.add(backButton);

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

    // TODO: confirm action

    /**
     * customer could apply a loan only if he/she has not a loan yet
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;
            String collateral;
            try {
                collateral = collateralField.getText();
                if(collateral.trim().equals("")){
                    JOptionPane.showMessageDialog(alPanel,"You should claim a collateral",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }
                amount = Double.valueOf(amountfield.getText());

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(alPanel,"You Should Input a Positive Number!",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                double total = amount* 1.2;
                int choice = JOptionPane.showConfirmDialog(alPanel,"You will should repay $"+total+" including interest.","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);
                if(choice == 0){
                    // TODO: approve this loan
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();
                    Account temp = null;

                    for (Account a : customer.getAccounts()) {
                        // Get the checking account of the customer
                        if (a.getType().equalsIgnoreCase("CHECKING")) { temp = a; }
                    }

                    if (aData.applyLoans(temp, new Date(), amount, collateral)) {
                        cData.updateBalance(customer, temp, temp.getBalance().getAmount() + amount);
                        cData.readData();

                        for (Customer c : cData.data) {
                            // Update the loan status of the checking account of the customer
                            if (c.getUserName().equals(customer.getUserName())) { customer = c; }
                        }

                        JOptionPane.showMessageDialog(alPanel,"Successfully Apply a Loan For Your " +
                                "Checking Account","Success",JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    } else {
                        JOptionPane.showMessageDialog(alPanel,"You Can Only Apply One Loan At a Time!",
                                "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    }
                }


            } catch (Exception ex) {
                if (amount == null) {
                    JOptionPane.showMessageDialog(alPanel,"You should input a number for amount",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }
                JOptionPane.showMessageDialog(alPanel,"Failed to loan");
            }
        }
    }
}
