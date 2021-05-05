package GUI;

import BankAttributes.*;
import Data.AccountDataManager;
import Data.CustomerDataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * - This class serves as a functional page for customer to deposit to his/her saving account.
 * - Most UI designs are inherited from parent class "GUIDepositWindow", and this class specifies
 *   the depositing details in saving account.
 */
public class GUISavingDepositWindow extends GUIDepositWindow{

    public GUISavingDepositWindow (Customer customer){
        super(customer);

        // title
        setTitle("Deposit to Saving Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        dwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(dwPanel);
        dwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        dwPanel.add(backButton);

        // confirm
        ConfirmListener cl = new ConfirmListener();
        confirmButtion.addActionListener(cl);

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
     * customer deposits an amount of money to his/her saving account
     * he/she could get an extra bonus money if over $1000 one time
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;
            String currencyType;
            try{
                amount = Double.valueOf(amountfield.getText());
                currencyType = currencyTypeBox.getSelectedItem().toString();

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(dwPanel,"You Should Input a Positive Number!",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                // TODO: deposit to savings account
                CustomerDataManager cData = new CustomerDataManager();
                AccountDataManager aData = new AccountDataManager();
                Account temp = null;
                Currency c;

                for (Account a : customer.getAccounts()) {
                    // Get the checking account of the customer
                    if (a.getType().equalsIgnoreCase("SAVINGS")) { temp = a; }
                }

                // Check the type of currency that the customer deposits
                if (currencyType.equalsIgnoreCase("CNY")) { c = new CNY(amount); }
                else if (currencyType.equalsIgnoreCase("HKD")) { c = new HKD(amount); }
                else { c = new USD(amount); }

                // Convert whatever type of the input currency to US dollars
                double exchangedAmount = c.getCurrencyExchangeBehavior().exchange("$");

                double balance = aData.deposit(temp, new Date(), exchangedAmount);

                cData.updateBalance(customer, temp, balance);
                cData.readData();

                for (Customer cus : cData.data) {
                    // Update the loan status of the checking account of the customer
                    if (cus.getUserName().equals(customer.getUserName())) { customer = cus; }
                }


                JOptionPane.showMessageDialog(dwPanel,"Successfully deposit to Saving account","Success",JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);

            }catch(Exception ex){
                if(amount == null){
                    JOptionPane.showMessageDialog(dwPanel,"You should input a number","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }
                JOptionPane.showMessageDialog(dwPanel,"Failed to deposit");
            }
        }
    }
}
