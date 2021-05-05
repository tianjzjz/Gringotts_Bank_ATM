package GUI;

import BankAttributes.*;
import Data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * - This class serves as an informative page for manager oto view all users details
 *   with their transaction history
 * - Manager could search a specific username for his/her personal transaction history
 */
public class GUICheckCustomersWindow extends JFrame {
    private JPanel cwPanel;
    private JLabel headLabel;
    private JTextArea userArea;
    private JButton backButton;
    private JScrollPane scrollPane;
    private Manager manager;

    private JLabel checkLabel;
    private JTextField nameField;
    private JButton checkButton;

    public GUICheckCustomersWindow(Manager manager) throws ParseException {
        this.manager = manager;

        // title
        setTitle("Users of Gringotts Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        cwPanel = new JPanel();
        cwPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(cwPanel);
        cwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5, 5, 120, 60);
        backButton.setIcon(IconUtil.backIcon);
        cwPanel.add(backButton);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);

        // head
        headLabel = new JLabel("Users Details");
        headLabel.setIcon(IconUtil.usersIcon);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 80);
        headLabel.setForeground(Color.pink);
        cwPanel.add(headLabel);

        // users details
        userArea = new JTextArea();
        userArea.setEditable(false);
        userArea.setBounds(123, 170, 600, 300);
        userArea.setLineWrap(true);

        // TODO: retrieve all user transaction details to "textStr"
        userArea.setText(allHistory());
        scrollPane = new JScrollPane(userArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension size = userArea.getPreferredSize();
        scrollPane.setBounds(123, 170, 600, 240);
        cwPanel.add(scrollPane);

        // check place
        checkLabel = new JLabel("Enter the specific username you want to check: ");
        checkLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        checkLabel.setBounds(140, 400, 400, 120);
        cwPanel.add(checkLabel);
        nameField = new JTextField(5);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 20));
        nameField.setToolTipText("");
        nameField.setBounds(540, 442, 160, 40);
        cwPanel.add(nameField);

        // check button
        checkButton = new JButton("Search");
        checkButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        checkButton.setBounds(340, 485, 150, 50);
        checkButton.setIcon(IconUtil.searchIcon);
        checkButton.setIcon(IconUtil.confirmIcon);
        cwPanel.add(checkButton);
        CheckListener cl = new CheckListener();
        checkButton.addActionListener(cl);
    }

    // Retrieve all user transaction details
    public String allHistory() throws ParseException {
        CustomerDataManager cData = new CustomerDataManager();
        StringBuilder res = new StringBuilder();

        for (Customer c : cData.data) {
            for (Account a : c.getAccounts()) {
                for (Transaction t : a.getHistory()) {
                    res.append(t.printHistory(c.getUserName(), a.getType())).append("\r\n\r\n");
                }
            }
        }

        return res.toString();
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                setVisible(false);
                GUIManagerCheckWindow managerCheckWindow = new GUIManagerCheckWindow(manager);
                managerCheckWindow.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(cwPanel, "Failed to go back");
            }
        }
    }

    // TODO: check a specific customer

    /**
     * manager view all customers in this bank
     * and manager is able to check one specific user by entering his/her username
     */
    class CheckListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                String username = nameField.getText().trim();

                if (username.equals("")) {
                    JOptionPane.showMessageDialog(cwPanel, "Username cannot be empty!",
                            "Warning", JOptionPane.ERROR_MESSAGE, IconUtil.closeIcon);
                    return;
                }

                // TODO: check whether it's a valid username;
                //  if yes then retrieve the history transaction of this user to "historyStr"

                boolean hasFoundUser = false;
                CustomerDataManager cData = new CustomerDataManager();

                for (Customer c : cData.data) {
                    // check whether it's a valid username
                    if (c.getUserName().equals(username)) {
                        hasFoundUser = true;
                        break;
                    }
                }

                if (!hasFoundUser) {
                    // If the input username is invalid
                    JOptionPane.showMessageDialog(cwPanel,"Username not found!",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

//                String historyStr = "Transaction 1\r\nTransaction 2\r\nTransaction 3\r\n";
                JOptionPane.showMessageDialog(cwPanel, customerTransactions(username), "Transaction History of User " +
                        username, JOptionPane.PLAIN_MESSAGE, IconUtil.documentIcon);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(cwPanel, "Failed to check this user");
            }
        }

        // Retrieve all history transactions of this customer
        public String customerTransactions(String username) throws ParseException {
            CustomerDataManager cData = new CustomerDataManager();
            StringBuilder res = new StringBuilder();

            for (Customer c : cData.data) {
                for (Account a : c.getAccounts()) {
                    for (Transaction t : a.getHistory()) {
                        if (c.getUserName().equals(username)) {
                            // Put all transaction histories of this customer to [res]
                            res.append(t.printHistory(c.getUserName(), a.getType())).append("\r\n\r\n");
                        }
                    }
                }
            }

            return res.toString();
        }
    }


}
