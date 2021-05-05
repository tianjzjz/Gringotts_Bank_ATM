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
import java.util.ArrayList;

/**
 * - This class serves as an informative page for customer to view his/her transaction history
 *   including stock buy/sell details(if has one) in saving account.
 * - Most UI designs are inherited from parent class "GUIHistoryWindow", and this class
 *   specifies transaction history details in saving account.
 */
public class GUISavingHistoryWindow extends GUIHistoryWindow{

    public GUISavingHistoryWindow(Customer customer) throws ParseException {
        super(customer);

        // title
        setTitle("Transaction History of Saving Account");
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

        // TODO: retrieve saving history
        CustomerDataManager cData = new CustomerDataManager();
        AccountDataManager aData = new AccountDataManager();

        ArrayList<Account> securities = new ArrayList<>();
        Account savings = null;

        for (Account a : customer.getAccounts()) {
            // Get the savings and securities (if has) account of the customer
            if (a.getType().equalsIgnoreCase("SAVINGS")) { savings = a; }
            if (a.getType().equalsIgnoreCase("SECURITIES")) { securities.add(a); }
        }

        StringBuilder text = new StringBuilder();

        for (Transaction t : savings.getHistory()) {
            // Fot all transactions of the savings account
            text.append(t.printHistory(customer.getUserName(), "Savings")).append("\r\n\r\n");
        }

        for (Account a : securities) {
            // For all securities account of the customer
            for (Transaction t : a.getHistory()) {
                // Fot all transactions of the securities account
                text.append(t.printHistory(customer.getUserName(), "Securities")).append("\r\n\r\n");
            }
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
                GUISavingWindow savingWindow = new GUISavingWindow(customer);
                savingWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to go back");
            }
        }
    }
}
