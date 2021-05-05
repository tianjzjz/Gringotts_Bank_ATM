package GUI;

import BankAttributes.*;
import Data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * - This class serves as a homepage for checking account of customer.
 * - This class has all inherited functions from paren class "GUICustomerAccountWindow" but
 *   have different action listeners.
 *
 */
public class GUICheckingWindow extends GUICustomerAccountWindow {

    public GUICheckingWindow(Customer customer){
        super(customer);

        // title
        Double c = null;
        for (Account a : customer.getAccounts()) {
            // Get the balance of the customer's checking account
            if (a.getType().equalsIgnoreCase("CHECKING")) {
                c = a.getBalance().getAmount();
            }
        }

        setTitle("Your Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        swPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(swPanel);
        swPanel.setLayout(null);

        // balance
        DecimalFormat df = new DecimalFormat("#.00");
        JLabel balanceLabel = new JLabel("Your Balance: $"+df.format(c));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        balanceLabel.setBounds(150, 77, 509, 90);
        swPanel.add(balanceLabel);


        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        swPanel.add(backButton);

        // deposit
        DepositListener dl = new DepositListener();
        depositButton.addActionListener(dl);

        // withdraw
        WithdrawListener wl = new WithdrawListener();
        withdrawButton.addActionListener(wl);

        // send
        SendListener sl = new SendListener();
        sendButton.addActionListener(sl);

        // loan
        LoanListener ll = new LoanListener();
        loanButton.addActionListener(ll);

        // history
        HistoryListener hl = new HistoryListener();
        historyButton.addActionListener(hl);

        // close account
        CloseAccountListener cl = new CloseAccountListener();
        closeAccountButton.addActionListener(cl);

    }

    class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingDepositWindow checkingDepositWindow = new GUICheckingDepositWindow(customer);
                checkingDepositWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open deposit window");
            }
        }
    }

    class WithdrawListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingWithdrawWindow checkingWithdrawWindow = new GUICheckingWithdrawWindow(customer);
                checkingWithdrawWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open withdraw window");
            }
        }
    }

    class SendListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingSendWindow checkingSendWindow = new GUICheckingSendWindow(customer);
                checkingSendWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open send window");
            }
        }
    }

    class LoanListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingLoanWindow checkingLoanWindow = new GUICheckingLoanWindow(customer);
                checkingLoanWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open loan window");
            }
        }
    }

    class HistoryListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckingHistoryWindow checkingHistoryWindow = new GUICheckingHistoryWindow(customer);
                checkingHistoryWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open withdraw window");
            }
        }
    }

    // TODO: close account action

    /**
     * close this customer's checking account
     */
    class CloseAccountListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                int choice = JOptionPane.showConfirmDialog(swPanel,"Do you want to close your checking account?","Warning",JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);
                if(choice == JOptionPane.YES_OPTION){
                    // TODO: close account action
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();

                    Account target = null;

                    for (Account a : customer.getAccounts()) {
                        // Find the savings account of the customer
                        if (a.getType().equalsIgnoreCase("CHECKING")) { target = a; }
                    }

                    // Close this account in all places
                    cData.closeAccount(customer, target, new Date());
                    aData.removeAccount(target);

                    JOptionPane.showMessageDialog(swPanel,"Successfully Close Checking Account!",
                            "Success", JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    setVisible(false);
                    GUICustomerWindow customerWindow = new GUICustomerWindow(customer);
                    customerWindow.setVisible(true);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(swPanel,"Failed to close account");
            }
        }
    }


}
