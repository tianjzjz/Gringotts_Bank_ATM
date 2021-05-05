package GUI;

import BankAttributes.*;
import Data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * - This class serves as a functional page for customer to open an account that he/she
 *   doesn't have one yet.
 * - It provides two options: "Open Saving Account" and "Open Checking Account".
 *   The option is playable only if the customer doesn't have one such account.
 */
public class GUIOpenAccountWindow extends JFrame {

    private JPanel owPanel;
    private JButton checkingButton;
    private JButton savingButton;
    private JButton backButton;

    private Customer customer;

    public GUIOpenAccountWindow(Customer customer){
        this.customer = customer;

        // title
        setTitle("Open New Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        owPanel = new JPanel();
        owPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(owPanel);
        owPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        owPanel.add(backButton);

        // head
        JLabel headLabel = new JLabel("Choose the Account You Want to Open!");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(103, 77, 650, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        owPanel.add(headLabel);

        // checking account
        checkingButton = new JButton("Checking Account");
        checkingButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        checkingButton.setBounds(210, 230, 400, 60);
        checkingButton.setIcon(IconUtil.moneyIcon);
        // TODO: check whether has a checking account
        boolean hasChecking = false;

        for (Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("CHECKING")) {
                hasChecking = true;
                break;
            }
        }

        if(hasChecking) { checkingButton.setEnabled(false); }
        owPanel.add(checkingButton);
        CheckingListener cl = new CheckingListener();
        checkingButton.addActionListener(cl);

        // saving account
        savingButton = new JButton("Saving    Account");
        savingButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        savingButton.setBounds(210, 310, 400, 60);
        savingButton.setIcon(IconUtil.moneyIcon);
        // TODO: check whether have a checking account
        boolean hasSaving = false;

        for (Account a : customer.getAccounts()) {
            if (a.getType().equalsIgnoreCase("SAVINGS")) {
                hasSaving = true;
                break;
            }
        }

        if(hasSaving) { savingButton.setEnabled(false);}
        owPanel.add(savingButton);
        SavingListener sl = new SavingListener();
        savingButton.addActionListener(sl);
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

    /**
     * open a checking account if she/he doesn't have one yet
     */
    class CheckingListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                BossDataManager bData = new BossDataManager();

                double openAccFee = bData.data.get(bData.data.size() - 1).getOpenAccountFee();

                int choice = JOptionPane.showConfirmDialog(owPanel,"You should pay $" + openAccFee
                                + " to open a Checking Account","Warning",
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);

                if(choice == 0){
                    // TODO: open checking account
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();

                    // Create a new checking account
                    Account newAcc = new Checking("Checking", IDCenter.randomID(),
                            new Currency(0.0, "$"), new Date());

                    // Add the new checking account to both csv files and the customer's account list
                    cData.openAccount(customer, newAcc, new Date());
                    aData.addAccount(newAcc);

                    checkingButton.setEnabled(false);
                    JOptionPane.showMessageDialog(owPanel,"Successfully Open Checking Account","Success",
                            JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);

                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open checking");
            }
        }
    }


    /**
     * open a saving account if she/he doesn't have one yet
     */
    class SavingListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{

                BossDataManager bData = new BossDataManager();

                double openAccFee = bData.data.get(bData.data.size() - 1).getOpenAccountFee();

                int choice = JOptionPane.showConfirmDialog(owPanel,"You should pay $" + openAccFee
                                + " to open a Savings Account","Warning",
                        JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);

                if (choice == 0) {
                    // TODO: open saving account
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();

                    // Create a new checking account
                    Account newAcc = new Savings("Savings", IDCenter.randomID(),
                            new Currency(0.0, "$"), new Date());

                    // Add the new checking account to both csv files and the customer's account list
                    cData.openAccount(customer, newAcc, new Date());
                    aData.addAccount(newAcc);

                    savingButton.setEnabled(false);
                    JOptionPane.showMessageDialog(owPanel,"Successfully Open Saving Account","Success",
                            JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);

                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open saving");
            }
        }
    }
}
