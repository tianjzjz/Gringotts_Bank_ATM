package GUI;

import BankAttributes.Customer;

import javax.swing.*;
import java.awt.*;

/**
 * - This class serves as a parent class of "GUISavingLoanWindow" and "GUICheckingLoanWindow".
 * - Due to some same UI designs but a few different details of implementation, we put most
 *   UI designs in this class and leave action listeners to be implemented in child classes.
 * - This class is a homepage for customer to do any actions on loans.
 * - It provides two options: "Apply for a Loan" and "Repay the Loan" for customers. Customer
 *   could go for the "apply-loan" page or "repay-loan" page.
 * - Customer could open "repay-loan" page only if he/she has a loan to be repaid.
 */
public class GUILoanWindow extends JFrame {
    protected JPanel lwPanel;
    protected JLabel headLabel;
    protected JButton backButton;
    protected JButton applyButton;
    protected JButton repayButton;

    protected Customer customer;

    public GUILoanWindow(Customer customer){
        this.customer = customer;

        lwPanel = new JPanel();

        // head
        JLabel headLabel = new JLabel("Menu");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(150, 57, 509, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setIcon(IconUtil.menuIcon);
        lwPanel.add(headLabel);

        // apply for loans
        applyButton = new JButton("Apply For a Loan");
        applyButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        applyButton.setBounds(210, 200, 400, 60);
        applyButton.setIcon(IconUtil.moneyIcon);
        lwPanel.add(applyButton);

        // repay the loans
        repayButton = new JButton("Repay Your Loan");
        repayButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        repayButton.setBounds(210, 280, 400, 60);
        repayButton.setIcon(IconUtil.moneyIcon);
        lwPanel.add(repayButton);





    }

}
