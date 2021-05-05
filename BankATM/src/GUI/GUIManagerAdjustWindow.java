package GUI;

import BankAttributes.Manager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * - This class serves as the Adjust-Fee Homepage window for manager.
 * - Manager could choose "adjust Open-Account Fee" to go to the specific page of
 *   "Adjust Open-Account Fee".
 */
public class GUIManagerAdjustWindow extends JFrame {

    private JPanel awPanel;
    private JLabel headLabel;
    private JButton openFeeButton;
    private JButton closeFeeButton;
    private JButton backButton;
    private Manager manager;

    public GUIManagerAdjustWindow(Manager manager){
        this.manager = manager;

        // title
        setTitle("Adjust Bank Fees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        awPanel = new JPanel();
        awPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(awPanel);
        awPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        awPanel.add(backButton);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);

        // head
        headLabel = new JLabel("Menu");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 90);
        headLabel.setForeground(Color.pink);
        Border headBorder = BorderFactory.createLineBorder(Color.pink);
        headLabel.setBorder(headBorder);
        headLabel.setIcon(IconUtil.menuIcon);
        awPanel.add(headLabel);

        // adjust open-account button
        openFeeButton = new JButton("Open-Account Fee");
        openFeeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        openFeeButton.setBounds(210, 200, 400, 60);
        openFeeButton.setIcon(IconUtil.moneyIcon);
        awPanel.add(openFeeButton);
        OpenFeeListener ol = new OpenFeeListener();
        openFeeButton.addActionListener(ol);


//        // adjust button
//        closeFeeButton = new JButton("Close-Account Fee");
//        closeFeeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
//        closeFeeButton.setBounds(210, 320, 400, 60);
//        closeFeeButton.
//        awPanel.add(closeFeeButton);
//        CloseFeeListener cl = new CloseFeeListener();
//        closeFeeButton.addActionListener(cl);



    }
    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerWindow managerWindow = new GUIManagerWindow(manager);
                managerWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(awPanel,"Failed to go back");
            }
        }
    }

    class OpenFeeListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIAdjustOFWindow adjustOFWindow = new GUIAdjustOFWindow(manager);
                adjustOFWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(awPanel,"Failed to go to adjust open-account fee page");
            }
        }
    }





}
