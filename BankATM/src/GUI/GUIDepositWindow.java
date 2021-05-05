package GUI;
import BankAttributes.Account;
import BankAttributes.Currency;
import BankAttributes.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as a parent class of "GUICheckingDepositWindow" and
 *   "GUISavingDepositWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 */
public class GUIDepositWindow extends JFrame{
    protected JPanel dwPanel;
    protected JLabel headLabel;
    protected JLabel amountLabel;
    protected JTextField amountfield;
    protected JComboBox currencyTypeBox;
    protected JButton confirmButtion;
    protected JButton backButton;

    protected Customer customer;

    public GUIDepositWindow(Customer customer){
        this.customer = customer;

        dwPanel = new JPanel();

        // head TODO: account balance -> No! Should appear in GUICheckingWindow or GUISavingWindow ^
        headLabel = new JLabel("<html>Deposit over $1000 one time<br>&nbsp;&nbsp;&nbsp;&nbsp;Gain 5% Interest rate!<html>");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setIcon(IconUtil.notificationIcon);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
        headLabel.setBounds(153, 57, 509, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        dwPanel.add(headLabel);

        // input
        amountLabel = new JLabel("Deposit Amount:");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(170, 200, 200, 120);
        dwPanel.add(amountLabel);
        amountfield = new JTextField(5);
        amountfield.setHorizontalAlignment(SwingConstants.CENTER);
        amountfield.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountfield.setToolTipText("");
        amountfield.setBounds(350, 240, 160, 40);
        dwPanel.add(amountfield);
        currencyTypeBox = new JComboBox();
        currencyTypeBox.addItem("USD");
        currencyTypeBox.addItem("HKD");
        currencyTypeBox.addItem("CNY");
        currencyTypeBox.setFont(new Font("Consolas", Font.PLAIN, 20));
        currencyTypeBox.setBounds(530, 230, 110, 50);
        dwPanel.add(currencyTypeBox);

        // confirm button
        confirmButtion = new JButton("Confirm");
        confirmButtion.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButtion.setBounds(340, 323, 150, 60);
        confirmButtion.setIcon(IconUtil.confirmIcon);
        dwPanel.add(confirmButtion);


    }
}
