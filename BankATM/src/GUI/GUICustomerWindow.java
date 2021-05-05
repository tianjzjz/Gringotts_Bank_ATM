package GUI;

import BankAttributes.Account;
import BankAttributes.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * - This class serves as a homepage for customers.
 * - It provides three functional options: "Saving Account", "Checking Account" and
 *   "Open Accounts".
 * - Customers could choose to go for "Saving Account" or "Checking Account" as long
 *   as they have opened corresponding account(s).
 * - Customers could log-out to Customer Login page as long as they want.
 */
public class GUICustomerWindow extends JFrame {
    private JPanel cwPanel;
    private JButton checkingButton;
    private JButton savingButton;

    private JButton transferButton;
    private JButton openNewButton;
    private JButton logoutButton;

    private Customer customer;

    public GUICustomerWindow(Customer customer){
        this.customer = customer;

        // title
        setTitle("Gringotts Bank ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        cwPanel = new JPanel();
        cwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(cwPanel);
        cwPanel.setLayout(null);

        // Logout
        logoutButton = new JButton();
        logoutButton.setBounds(5,5,120,60);
        LogoutListener ll = new LogoutListener();
        logoutButton.addActionListener(ll);
        logoutButton.setIcon(IconUtil.backIcon);
        cwPanel.add(logoutButton);

        // head TODO: retrieve username
        String username = customer.getUserName(); // Retrieve the customer's username
        JLabel headLabel = new JLabel("Hello, " + username + "!");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setIcon(IconUtil.heartIcon);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));

        cwPanel.add(headLabel);

        // checking account TODO: retrieve checking amount
        checkingButton = new JButton("Checking Account");
        checkingButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        checkingButton.setBounds(210, 200, 400, 60);
        checkingButton.setIcon(IconUtil.moneyIcon);
        // TODO: check whether has a checking account
        boolean hasChecking = false;

        for(Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("CHECKING")) {
                // If the customer has a checking account
                hasChecking = true;
                break;
            }
        }

        if(hasChecking){ checkingButton.setEnabled(true); }
        cwPanel.add(checkingButton);
        CheckingListener cl = new CheckingListener();
        checkingButton.addActionListener(cl);

        // saving account TODO: retrieve savings amount
        savingButton = new JButton("Saving    Account");

        savingButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        savingButton.setBounds(210, 280, 400, 60);
        savingButton.setIcon(IconUtil.moneyIcon);
        // TODO: check whether has a savings account
        boolean hasSaving = false;

        for(Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("SAVINGS")) {
                // If the customer has a checking account
                hasSaving = true;
                break;
            }
        }

        if(hasSaving) { savingButton.setEnabled(true);}
        cwPanel.add(savingButton);
        SavingListener sl = new SavingListener();
        savingButton.addActionListener(sl);


        // Open new account
        openNewButton = new JButton("Open New Account");
        openNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        openNewButton.setBounds(210, 360, 400, 60);
        openNewButton.setIcon(IconUtil.addIcon);
        cwPanel.add(openNewButton);
        OpenNewListener ol = new OpenNewListener();
        openNewButton.addActionListener(ol);
    }

    class LogoutListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                int choice = JOptionPane.showConfirmDialog(cwPanel,"Continue to Logout?",
                        "Warning", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);

                if (choice ==0) {
                    setVisible(false);
                    GUIHomepage homepage = new GUIHomepage();
                    homepage.setVisible(true);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(cwPanel,"Failed to logout");
            }
        }
    }

    // TODO: open new account
    class OpenNewListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIOpenAccountWindow openAccountWindow = new GUIOpenAccountWindow(customer);
                openAccountWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to open new account");
            }
        }
    }

    // TODO: check whether has a checking window
    /**
     *  open checking account page if he/she has one
     */
    class CheckingListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                boolean hasCheckingAcc = false;

                for (Account a : customer.getAccounts()) {
                    if (a.getType().equalsIgnoreCase("CHECKING")) {
                        setVisible(false);
                        GUICheckingWindow checkingWindow = new GUICheckingWindow(customer);
                        checkingWindow.setVisible(true);
                        hasCheckingAcc = true;

                        break;
                    }
                }

                if (!hasCheckingAcc) {
                    JOptionPane.showMessageDialog(cwPanel, "You don't have a checking account!",
                            "Error", JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to open checking");
            }
        }
    }

    // TODO: check whether has a saving window
    /**
     * open saving account page if he/she has one
     */
    class SavingListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                boolean hasSavingAcc = false;

                for (Account a : customer.getAccounts()) {
                    if (a.getType().equalsIgnoreCase("SAVINGS")) {
                        setVisible(false);
                        GUISavingWindow savingWindow = new GUISavingWindow(customer);
                        savingWindow.setVisible(true);
                        hasSavingAcc = true;

                        break;
                    }
                }

                if (!hasSavingAcc) {
                    JOptionPane.showMessageDialog(cwPanel, "You don't have a savings account!",
                            "Error", JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to open saving");
            }
        }
    }


}
