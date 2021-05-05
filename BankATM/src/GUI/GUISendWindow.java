package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * - This class serves as a parent class of "GUISavingSendWindow" and "GUISavingSendWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 */
public class GUISendWindow extends JFrame {

    protected JPanel swPanel;
    protected JLabel headLabel;
    protected JTextField amountField;
    protected JLabel amountLabel;
    protected JTextField targetField;
    protected JLabel targetLabel;
    protected JButton backButton;
    protected JButton confirmButton;

    protected Customer customer;

    public GUISendWindow(Customer customer){
        this.customer = customer;

        swPanel = new JPanel();

        // head
        headLabel = new JLabel("Please enter the amount");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 77, 509, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        swPanel.add(headLabel);

        // target account
        targetLabel = new JLabel("Destination Account: ");
        targetLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        targetLabel.setBounds(190, 200, 250, 120);
        swPanel.add(targetLabel);
        targetField = new JTextField(5);
        targetField.setHorizontalAlignment(SwingConstants.CENTER);
        targetField.setFont(new Font("Consolas", Font.PLAIN, 20));
        targetField.setToolTipText("");
        targetField.setBounds(400, 240, 160, 40);
        swPanel.add(targetField);

        // amount
        amountLabel = new JLabel("Send Amount: $");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(240, 300, 200, 120);
        swPanel.add(amountLabel);
        amountField = new JTextField(5);
        amountField.setHorizontalAlignment(SwingConstants.CENTER);
        amountField.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountField.setToolTipText("");
        amountField.setBounds(400, 340, 160, 40);
        swPanel.add(amountField);

        // button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButton.setBounds(340, 420, 150, 60);
        confirmButton.setIcon(IconUtil.confirmIcon);
        swPanel.add(confirmButton);

    }


}
