package GUI;

import BankAttributes.Manager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as the Check-Status Homepage window for manager.
 * - Manager could choose which part she/he is interested in, such as "Daily Report",
 *   "Users Details", or "Bank Revenue".
 */
public class GUIManagerCheckWindow extends JFrame {

    private JPanel cwPanel;
    private JLabel headLabel;
    private JButton userButton;
    private JButton revenueButton;
    private JButton reportButton;
    private JButton backButton;
    private Manager manager;

    public GUIManagerCheckWindow(Manager manager){
        this.manager = manager;

        // title
        setTitle("View Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        cwPanel = new JPanel();
        cwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(cwPanel);
        cwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        cwPanel.add(backButton);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);

        // head
        headLabel = new JLabel("Menu");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setIcon(IconUtil.menuIcon);
        Border headBorder = BorderFactory.createLineBorder(Color.pink);
        headLabel.setBorder(headBorder);
        cwPanel.add(headLabel);

        // view user button
        userButton = new JButton("Check Bank Users");
        userButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        userButton.setBounds(210, 200, 400, 60);
        userButton.setIcon(IconUtil.usersIcon);
        cwPanel.add(userButton);
        UserListener ul = new UserListener();
        userButton.addActionListener(ul);

        // view revenue button
        revenueButton = new JButton("View Bank Revenue");
        revenueButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        revenueButton.setBounds(210, 280, 400, 60);
        revenueButton.setIcon(IconUtil.revenueIcon);
        cwPanel.add(revenueButton);
        RevenueListener rl = new RevenueListener();
        revenueButton.addActionListener(rl);

        // view daily-report button
        reportButton = new JButton("Bank Daily Report");
        reportButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        reportButton.setBounds(210, 360, 400, 60);
        reportButton.setIcon(IconUtil.documentIcon);
        cwPanel.add(reportButton);
        ReportListener dl = new ReportListener();
        reportButton.addActionListener(dl);
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerWindow managerWindow = new GUIManagerWindow(manager);
                managerWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to go back");
            }
        }
    }

    class UserListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckCustomersWindow checkCustomersWindow = new GUICheckCustomersWindow(manager);
                checkCustomersWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to go back");
            }
        }
    }

    class ReportListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckReportWindow checkReportWindow = new GUICheckReportWindow(manager);
                checkReportWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to go back");
            }
        }
    }

    class RevenueListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICheckRevenueWindow revenueWindow = new GUICheckRevenueWindow(manager);
                revenueWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(cwPanel,"Failed to go back");
            }
        }
    }

}
