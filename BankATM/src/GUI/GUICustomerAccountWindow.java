package GUI;
import BankAttributes.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as a parent class of "GUICheckingWindow" and "GUISavingWindow".
 * - Due to some same UI designs but a few different details, we put most UI designs
 *   in this class and leave button listeners in child classes.
 * - It provides "Deposit", "Withdraw", "Send", "Loans", and "View Transaction History"
 *   for both Checking and Saving Accounts. Customer could choose each one of this button
 *   to go to its corresponding functional page.
 * - Customer could also choose the option "Close Account" to close current account.
 */
public class GUICustomerAccountWindow extends JFrame{

    protected JPanel swPanel;
    protected JLabel headLabel;
    protected JButton backButton;
    protected JButton depositButton;
    protected JButton withdrawButton;
    protected JButton sendButton;
    protected JButton loanButton;
    protected JButton historyButton;
    protected JButton closeAccountButton;

    protected Customer customer;


    public GUICustomerAccountWindow(Customer customer){
        this.customer = customer;

        swPanel = new JPanel();

        // deposit
        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        depositButton.setBounds(145, 180, 200, 60);
        depositButton.setIcon(IconUtil.depositIcon);
        swPanel.add(depositButton);


        // withdraw
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        withdrawButton.setBounds(480, 180, 200, 60);
        withdrawButton.setIcon(IconUtil.withdrawIcon);
        swPanel.add(withdrawButton);


        // send
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        sendButton.setBounds(145, 260, 200, 60);
        sendButton.setIcon(IconUtil.sendIcon);
        swPanel.add(sendButton);


        // loan
        loanButton = new JButton("Loan");
        loanButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        loanButton.setBounds(480, 260, 200, 60);
        loanButton.setIcon(IconUtil.loanIcon);
        swPanel.add(loanButton);


        // view Transaction history
        historyButton = new JButton("View Transaction History");
        historyButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        historyButton.setBounds(205, 340, 400, 60);
        historyButton.setIcon(IconUtil.documentIcon);
        swPanel.add(historyButton);

        // close account
        closeAccountButton = new JButton("Close Account");
        closeAccountButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        closeAccountButton.setBounds(205, 420, 400, 60);
        closeAccountButton.setIcon(IconUtil.closeIcon);
        swPanel.add(closeAccountButton);
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICustomerWindow customerWindow = new GUICustomerWindow(customer);
                customerWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }
}
