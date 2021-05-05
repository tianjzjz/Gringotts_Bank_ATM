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
 * - This class serves as a functional page for customer to withdraw from his/her saving
 *   account.
 * - Most UI designs are inherited from parent class "GUIWithdrawWindow", and this class
 *   specifies the withdrawing details in saving account.
 */
public class GUISavingWithdrawWindow extends GUIWithdrawWindow {

    public GUISavingWithdrawWindow(Customer customer){
        super(customer);

        // title
        setTitle("Withdraw from Saving Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        wwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(wwPanel);
        wwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        wwPanel.add(backButton);

        // confirm
        ConfirmListener cl = new ConfirmListener();
        confirmButton.addActionListener(cl);
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingWindow savingWindow = new GUISavingWindow(customer);
                savingWindow.setVisible(true);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

    // TODO: withdraw action

    /**
     * withdraw an amount of money and charge service fee
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;
            try{
                amount = Double.valueOf(amountfield.getText());

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(wwPanel,"You Should Input a Positive Number!",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                double fee = amount* 0.2;
                int choice = JOptionPane.showConfirmDialog(wwPanel,"You will be charged $" + fee +
                        " as service fee from your balance","Warning",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, IconUtil.warnIcon);

                if(choice == 0){
                    // TODO: check whether have enough money and then withdraw to account
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();
                    Account temp = null;

                    for (Account a : customer.getAccounts()) {
                        // Get the savings account of the customer
                        if (a.getType().equalsIgnoreCase("SAVINGS")) { temp = a; }
                    }

                    double withdrawAmount = aData.withdraw(temp, new Date(), amount);

                    if (withdrawAmount != 0.0) {
                        cData.updateBalance(customer, temp, temp.getBalance().getAmount() - withdrawAmount);
                        cData.readData();

                        for (Customer cus : cData.data) {
                            // Update the loan status of the checking account of [customer]
                            if (cus.getUserName().equals(customer.getUserName())) { customer = cus; }
                        }

                        JOptionPane.showMessageDialog(wwPanel,"Successfully withdraw from Savings account","Success",JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    } else {
                        JOptionPane.showMessageDialog(wwPanel,"Not Enough Balance!",
                                "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    }
                }


            }catch(Exception ex){
                if(amount == null){
                    JOptionPane.showMessageDialog(wwPanel,"You should input a number","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(wwPanel,"Failed to withdraw");
            }
        }
    }


}
