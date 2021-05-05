package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as a parent class of "GUICheckingWithdrawWindow" and
 *   "GUISavingWithdrawWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 */
public class GUIWithdrawWindow extends JFrame{

    protected JPanel wwPanel;
    protected JLabel headLabel;
    protected JLabel amountLabel;
    protected JTextField amountfield;
    protected JButton confirmButton;
    protected JButton backButton;

    protected Customer customer;

    public GUIWithdrawWindow(Customer customer){
        this.customer = customer;

        wwPanel = new JPanel();


        // head TODO: retrieve balance of saving account
        headLabel = new JLabel("Please enter the amount");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        wwPanel.add(headLabel);

        // input
        amountLabel = new JLabel("Withdraw Amount: $");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(200, 200, 200, 120);
        wwPanel.add(amountLabel);
        amountfield = new JTextField(5);
        amountfield.setHorizontalAlignment(SwingConstants.CENTER);
        amountfield.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountfield.setToolTipText("");
        amountfield.setBounds(400, 240, 160, 40);
        wwPanel.add(amountfield);

        // button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButton.setBounds(340, 323, 150, 60);
        confirmButton.setIcon(IconUtil.confirmIcon);
        wwPanel.add(confirmButton);

    }




}
