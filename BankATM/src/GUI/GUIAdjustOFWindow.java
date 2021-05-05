package GUI;

import BankAttributes.Manager;
import Data.BossDataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * This class serves as a functional page for manager to adjust open-account fee
 */
public class GUIAdjustOFWindow extends JFrame {
    private JPanel owPanel;
    private JLabel headLabel;
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton backButton;
    private JButton confirmButton;
    private Manager manager;

    public GUIAdjustOFWindow(Manager manager) throws ParseException {
        this.manager = manager;

        // title
        setTitle("Reset Open-Account Fees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        owPanel = new JPanel();
        owPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(owPanel);
        owPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        owPanel.add(backButton);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);

        // head
        // TODO: retrieve current open-account fee to "currFee"
        double currFee = 5.0;

        BossDataManager bData = new BossDataManager();

        currFee = bData.data.get(bData.data.size() - 1).getOpenAccountFee();

        headLabel = new JLabel("Current Open-Account Fee: $ " + currFee);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 120);
        headLabel.setForeground(Color.pink);
        owPanel.add(headLabel);

        // input
        amountLabel = new JLabel("Set New Fee:  $");
        amountLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountLabel.setBounds(230, 200, 200, 120);
        owPanel.add(amountLabel);
        amountField = new JTextField(5);
        amountField.setHorizontalAlignment(SwingConstants.CENTER);
        amountField.setFont(new Font("Consolas", Font.PLAIN, 20));
        amountField.setToolTipText("");
        amountField.setBounds(380, 240, 160, 40);
        owPanel.add(amountField);

        // confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmButton.setBounds(340, 343, 150, 60);
        confirmButton.setIcon(IconUtil.confirmIcon);
        owPanel.add(confirmButton);
        ConfirmListener cl = new ConfirmListener();
        confirmButton.addActionListener(cl);

    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerAdjustWindow managerAdjustWindow = new GUIManagerAdjustWindow(manager);
                managerAdjustWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(owPanel,"Failed to go back");
            }
        }
    }

    // TODO: reset open-account fee

    /**
     * manager resets the open-account fee
     */
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Double amount = null;

            try {
                amount = Double.valueOf(amountField.getText());

                if (amount <= 0.0) {
                    JOptionPane.showMessageDialog(owPanel,"You Should Input a Positive Number!",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // double-check the change
                int choice = JOptionPane.showConfirmDialog(owPanel,"Confirm the reset Open-Account fee " +
                        "to $" + amount,"Warning", JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    // TODO: reset the open-account fee
                    BossDataManager bData = new BossDataManager();

                    bData.adjustOpenAccountFee(amount);

                    JOptionPane.showMessageDialog(owPanel,"Successfully reset the Open-Account Fee");

                    // Update the label to display the latest open-account fee
                    setVisible(false);
                    GUIAdjustOFWindow adjustOFWindow = new GUIAdjustOFWindow(manager);
                    adjustOFWindow.setVisible(true);
                }

            } catch (Exception ex) {
                if(amount == null){
                    JOptionPane.showMessageDialog(owPanel,"You should input a number",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(owPanel,"Failed to adjust the fee");
            }
        }
    }

}
