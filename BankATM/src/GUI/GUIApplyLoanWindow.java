package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * - This class serves as a parent class of "GUISavingApplyLoanWindow" and
 *   "GUICheckingApplyLoanWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 * - This class is a functional page for customer to apply a loan.
 * - It asks customer to input a collateral and loan amount in order to finish the application.
 *
 * ### 3
 */
public class GUIApplyLoanWindow extends JFrame {
    protected JPanel alPanel;
    protected JLabel headLabel;
    protected JTextField amountfield;
    protected JLabel amountLabel;
    protected JTextField collateralField;
    protected JLabel collateralLabel;
    protected JButton backButton;
    protected JButton confirmButton;

    protected Customer customer;

    public GUIApplyLoanWindow(Customer customer){
        this.customer = customer;

        alPanel = new JPanel();

        // head TODO: retrieve balance of saving account
        headLabel = new JLabel("Please enter the amount");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 77, 509, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        alPanel.add(headLabel);

        // amount input
        amountLabel = new JLabel("Loan Amount: $");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(200, 200, 200, 120);
        alPanel.add(amountLabel);
        amountfield = new JTextField(5);
        amountfield.setHorizontalAlignment(SwingConstants.CENTER);
        amountfield.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountfield.setToolTipText("");
        amountfield.setBounds(400, 240, 160, 40);
        alPanel.add(amountfield);

        // collateral
        collateralLabel = new JLabel("Collateral: ");
        collateralLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        collateralLabel.setBounds(240, 300, 200, 120);
        alPanel.add(collateralLabel);
        collateralField = new JTextField(5);
        collateralField.setHorizontalAlignment(SwingConstants.CENTER);
        collateralField.setFont(new Font("Consolas", Font.PLAIN, 20));
        collateralField.setToolTipText("");
        collateralField.setBounds(400, 340, 160, 40);
        alPanel.add(collateralField);

        // button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButton.setBounds(340, 420, 150, 60);
        confirmButton.setIcon(IconUtil.confirmIcon);
        alPanel.add(confirmButton);


    }





}
