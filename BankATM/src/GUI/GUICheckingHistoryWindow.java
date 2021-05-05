package GUI;

import BankAttributes.Account;
import BankAttributes.Customer;
import BankAttributes.Transaction;
import Data.AccountDataManager;
import Data.CustomerDataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * - This class serves as an informative page for customer to view his/her transaction history in
 *   checking account.
 * - Most UI designs are inherited from parent class "GUIHistoryWindow", and this class
 *   specifies transaction history details in checking account.
 */
public class GUICheckingHistoryWindow extends GUIHistoryWindow{

    public GUICheckingHistoryWindow(Customer customer) throws ParseException {

        super(customer);

        // title
        setTitle("Transaction History of Checking Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        hwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(hwPanel);
        hwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        hwPanel.add(backButton);

        // TODO: retrieve checking history
        CustomerDataManager cData = new CustomerDataManager();
        AccountDataManager aData = new AccountDataManager();
        Account temp = null;

        for (Account a : customer.getAccounts()) {
            // Get the checking account of the customer
            if (a.getType().equalsIgnoreCase("CHECKING")) { temp = a; }
        }

        StringBuilder text = new StringBuilder();

        for (Transaction t : temp.getHistory()) {
            text.append(t.printHistory(customer.getUserName(), "Checking")).append("\r\n\r\n");
        }

        String textStr = text.toString();

        historyArea.setText(textStr);
        scrollPane = new JScrollPane(historyArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension size = historyArea.getPreferredSize();
        scrollPane.setBounds(123,170,600,200);
        hwPanel.add(scrollPane);
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
}
