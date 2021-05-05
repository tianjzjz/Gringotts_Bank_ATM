package GUI;

import BankAttributes.Account;
import BankAttributes.Currency;
import BankAttributes.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as a functional page for customer to apply a loan on his/her saving
 *   account.
 * - Most UI designs are inherited from parent class "GUICustomerApplyLoanWindow", and this class
 *   determines the function "apply-loan" on his/her saving account. The customer is able to apply
 *   for a loan only if he/she doesn't have a loan on saving account yet.
 */
public class GUISavingLoanWindow extends GUILoanWindow{

    public GUISavingLoanWindow(Customer customer){
        super(customer);

        // title
        Currency c;

        for (Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("SAVINGS")) {
                if (a.getLoan() != null) {
                    // If has loan
                    c = a.getLoan().getMoney();
                    setTitle("Loan in Savings Account (Current Loan Balance: " + c + ")");
                } else { setTitle("Loan in Savings Account"); }
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        lwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(lwPanel);
        lwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        lwPanel.add(backButton);

        // repay
        RepayListener rl = new RepayListener();
        repayButton.addActionListener(rl);

        // apply
        ApplyListener al = new ApplyListener();
        applyButton.addActionListener(al);


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


    /**
     * customer could get to repay page only if he/she has a loan
     */
    class RepayListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                // TODO: check whether have a loan
                boolean hasLoan = true;

                for (Account a : customer.getAccounts()) {
                    // Check if the customer's savings account already has a loan
                    if (a.getType().equalsIgnoreCase("SAVINGS") && a.getLoan() == null) {
                        hasLoan = false;
                        break;
                    }
                }

                if(hasLoan){
                    setVisible(false);
                    GUISavingRepayWindow repayWindow = new GUISavingRepayWindow(customer);
                    repayWindow.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(lwPanel,"You do not have a loan",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

    class ApplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                // TODO: check whether have a laon
                boolean hasLoan = true;

                for (Account a : customer.getAccounts()) {
                    // Check if the customer's savings account already has a loan
                    if (a.getType().equalsIgnoreCase("SAVINGS") && a.getLoan() == null) {
                        hasLoan = false;
                        break;
                    }
                }

                if (hasLoan) {
                    JOptionPane.showMessageDialog(lwPanel,"You already have a loan",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    setVisible(false);
                    GUISavingApplyLoanWindow savingApplyLoanWindow = new GUISavingApplyLoanWindow(customer);
                    savingApplyLoanWindow.setVisible(true);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

}
