package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * - This class serves as a parent class of "GUISavingHistoryWindow" and "GUICheckingHistoryWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 *
 */
public class GUIHistoryWindow extends JFrame {

    protected JPanel hwPanel;
    protected JLabel headLabel;
    protected JTextArea historyArea;
    protected JButton backButton;
    protected JScrollPane scrollPane;

    protected Customer customer;

    public GUIHistoryWindow(Customer customer){
        this.customer = customer;

        hwPanel = new JPanel();

        // head
        headLabel = new JLabel("Transaction History");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(173, 57, 509, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setIcon(IconUtil.documentIcon);
        hwPanel.add(headLabel);

        // history
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBounds(123,170,600,350);
        historyArea.setLineWrap(true);

    }

}
