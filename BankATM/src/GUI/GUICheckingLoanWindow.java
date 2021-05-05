package GUI;

import BankAttributes.Account;
import BankAttributes.Currency;
import BankAttributes.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as a homepage for customer to do any action on a loan on his/her checking
 *   account.
 * - Most UI designs are inherited from parent class "GUICustomerLoanWindow", and this class
 *   determines the feasibility of functions "apply-loan" and "repay-loan" on his/her saving account.
 */
public class GUICheckingLoanWindow extends GUILoanWindow{

    public GUICheckingLoanWindow(Customer customer){
        super(customer);

        // title
        Currency c;

        for (Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("CHECKING")) {
                if (a.getLoan() != null) {
                    // If has loan
                    c = a.getLoan().getMoney();
                    setTitle("Loan in Checking Account (Current Loan Balance: " + c + ")");
                } else { setTitle("Loan in Checking Account"); }
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
                GUICheckingWindow checkingWindow = new GUICheckingWindow(customer);
                checkingWindow.setVisible(true);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

    // TODO: repay window

    /**
     * customer is able to get to repay page only if he/she has a loan
     */
    class RepayListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                // TODO: check whether have a loan
                boolean hasLoan = true;

                for (Account a : customer.getAccounts()) {
                    // Check if the customer's checking account already has a loan
                    if (a.getType().equalsIgnoreCase("CHECKING") && a.getLoan() == null) {
                        hasLoan = false;
                        break;
                    }
                }

                if(hasLoan){
                    setVisible(false);
                    GUICheckingRepayWindow checkingRepayWindow = new GUICheckingRepayWindow(customer);
                    checkingRepayWindow.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(lwPanel,"You do not have a loan","Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

    // TODO
    class ApplyListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                // TODO: check whether have a loan
                boolean hasLoan = true;

                for (Account a : customer.getAccounts()) {
                    // Check if the customer's checking account already has a loan
                    if (a.getType().equalsIgnoreCase("CHECKING") && a.getLoan() == null) {
                        hasLoan = false;
                        break;
                    }
                }

                if (hasLoan) {
                    JOptionPane.showMessageDialog(lwPanel,"You Have an Unpaid Loan!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    setVisible(false);
                    GUICheckingApplyLoanWindow checkingApplyLoanWindow = new GUICheckingApplyLoanWindow(customer);
                    checkingApplyLoanWindow.setVisible(true);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }

}
