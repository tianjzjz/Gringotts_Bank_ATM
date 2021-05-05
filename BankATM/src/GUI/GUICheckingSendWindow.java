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
 * - This class serves as a functional page for customer to enter a target account number, and
 *   an amount of money to transfer from his/her checking account to target account.
 * - Most UI designs are inherited from parent class "GUISendWindow", and this class
 *   specifies the transfer details from checking account.
 */
public class GUICheckingSendWindow extends GUISendWindow{

    public GUICheckingSendWindow(Customer customer){
        super(customer);
        // title
        setTitle("Send Money from Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        swPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(swPanel);
        swPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        swPanel.add(backButton);

        // confirm
        ConfirmListener cl = new ConfirmListener();
        confirmButton.addActionListener(cl);
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

    // TODO: Send action

    /**
     * customer sends an amount of money from his checking account to a target account
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;
            try{
                amount = Double.valueOf(amountField.getText());
                String targetAccount = targetField.getText().trim();

                if(targetAccount.equals("")){
                    JOptionPane.showMessageDialog(swPanel,"You should input a valid account number",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(swPanel,"Input Must Be Positive!",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                double fee = amount* 0.2;
                int choice = JOptionPane.showConfirmDialog(swPanel,"You will be charged $"+fee+" as service fee from your balance.","Warning",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,IconUtil.warnIcon);
                if(choice == 0){
                    // TODO: check whether it's a valid amount,
                    //  whether we have this account in our bank and then send to account
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();
                    Account temp = null;

                    for (Account a : customer.getAccounts()) {
                        // Get the checking account of the customer
                        if (a.getType().equalsIgnoreCase("CHECKING")) { temp = a; }
                    }

                    double sendAmount = aData.transfer(temp, new Date(), amount);

                    if (sendAmount != 0.0) {
                        cData.updateBalance(customer, temp, temp.getBalance().getAmount() - sendAmount);
                        cData.readData();

                        for (Customer cus : cData.data) {
                            // Update the loan status of the checking account of the customer
                            if (cus.getUserName().equals(customer.getUserName())) { customer = cus; }
                        }

                        JOptionPane.showMessageDialog(swPanel,"Successfully send from Checking Account","Success",JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    } else {
                        JOptionPane.showMessageDialog(swPanel,"Not Enough Balance!",
                                "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    }

                }

            }catch(Exception ex){
                if(amount == null){
                    JOptionPane.showMessageDialog(swPanel,"You should input a number","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }
                JOptionPane.showMessageDialog(swPanel,"Failed to send");
            }
        }
    }


}
