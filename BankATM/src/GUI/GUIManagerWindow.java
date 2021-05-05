package GUI;

import BankAttributes.Manager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as the Manager Homepage window.
 * - It provides "Check Bank Status" and "Adjust Bank Fees" options for manager.
 *   Manager could go for homepage of either checking specific details of current
 *   Bank or adjusting service fee of Bank.
 */
public class GUIManagerWindow extends JFrame {
    private JPanel mwPanel;
    private JLabel headLabel;
    private JButton checkButton;
    private JButton adjustButton;
    private JButton logoutButton;
    private Manager manager;

    public GUIManagerWindow(Manager manager){

        this.manager = manager;

        // title
        setTitle("Manager Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        mwPanel = new JPanel();
        mwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(mwPanel);
        mwPanel.setLayout(null);

        // Logout
        logoutButton = new JButton();
        logoutButton.setBounds(5,5,120,60);
        LogoutListener ll = new LogoutListener();
        logoutButton.addActionListener(ll);
        logoutButton.setIcon(IconUtil.backIcon);
        mwPanel.add(logoutButton);

        // head
        headLabel = new JLabel("Hello, Boss Christine!");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 77, 509, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setIcon(IconUtil.heartIcon);
        Border headBorder = BorderFactory.createLineBorder(Color.pink);
        headLabel.setBorder(headBorder);
        mwPanel.add(headLabel);

        // check status button
        checkButton = new JButton("Check Bank Status");
        checkButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        checkButton.setBounds(210, 240, 400, 60);
        mwPanel.add(checkButton);
        CheckListener cl = new CheckListener();
        checkButton.setIcon(IconUtil.documentIcon);
        checkButton.addActionListener(cl);

        // adjust button
        adjustButton = new JButton("Adjust Bank Fees");
        adjustButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        adjustButton.setBounds(210, 350, 400, 60);
        adjustButton.setIcon(IconUtil.settingIcon);
        mwPanel.add(adjustButton);
        AdjustListener al = new AdjustListener();
        adjustButton.addActionListener(al);

    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                int choice = JOptionPane.showConfirmDialog(mwPanel,"Continue to Logout?",
                        "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,IconUtil.warnIcon);

                if (choice == 0) {
                    setVisible(false);
                    GUIHomepage homepage = new GUIHomepage();
                    homepage.setVisible(true);
                }

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(mwPanel,"Failed to logout");
            }
        }
    }

    class CheckListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerCheckWindow managerCheckWindow = new GUIManagerCheckWindow(manager);
                managerCheckWindow.setVisible(true);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(mwPanel,"Failed to go to check page");
            }
        }
    }

    class AdjustListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerAdjustWindow managerAdjustWindow = new GUIManagerAdjustWindow(manager);
                managerAdjustWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(mwPanel,"Failed to go to adjustment page");
            }
        }
    }


}
