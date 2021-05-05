package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * - This class serves as a parent class of "GUISavingRepayWindow" and "GUICheckingRepayWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 * - This class is a functional page for customer to repay the loan that customer has.
 * - It asks customer to input a repay-amount to repay his/her loan. Customer could repay his/her
 *   loan with any amount but not exceeding total repay amount.
 */
public class GUIRepayLoanWindow extends JFrame {

    protected JPanel rwPanel;
    protected JLabel headLabel;
    protected JLabel hintLabel;
    protected JLabel amountLabel;
    protected JTextField amountField;
    protected JButton confirmButton;
    protected JButton backButton;

    protected Customer customer;

    public GUIRepayLoanWindow(Customer customer){
        this.customer = customer;

        rwPanel = new JPanel();

        // head
        headLabel = new JLabel("Please Enter the Amount You Want to Repay");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(83, 57, 660, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        rwPanel.add(headLabel);


        // input
        amountLabel = new JLabel("Repay Amount: $");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(200, 240, 200, 120);
        rwPanel.add(amountLabel);
        amountField = new JTextField(5);
        amountField.setHorizontalAlignment(SwingConstants.CENTER);
        amountField.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountField.setToolTipText("");
        amountField.setBounds(400, 280, 160, 40);
        rwPanel.add(amountField);

        // button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButton.setBounds(340, 400, 150, 60);
        confirmButton.setIcon(IconUtil.confirmIcon);
        rwPanel.add(confirmButton);

    }


}
