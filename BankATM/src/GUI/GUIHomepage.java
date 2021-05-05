package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * - This class serves as the entrance of the entire project.
 * - This class would initiate a homepage window for user to choose either "Customer
 *   Login" or "Manager Login."
 */
public class GUIHomepage extends JFrame{

    private JPanel homepagePanel;


    public GUIHomepage(){
        setTitle("Gringotts Bank ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        homepagePanel = new JPanel();
        homepagePanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(homepagePanel);
        homepagePanel.setLayout(null);

        // message
        JLabel infoLabel = new JLabel("Welcome to Gringotts!");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Comic Sans MS", Font.PLAIN,40));
        infoLabel.setForeground(Color.pink);
        infoLabel.setBounds(100,81,582,60);
        homepagePanel.add(infoLabel);


        // manager Login button
        JButton managerButton = new JButton("Manager Login");
        MlListenner ml = new MlListenner();
        managerButton.addActionListener(ml);
        managerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,20));
        managerButton.setBounds(100,230,270,60);
        managerButton.setIcon(IconUtil.managerIcon);
        homepagePanel.add(managerButton);

        // customer Login button
        JButton customerButton = new JButton("Customer Login");
        ClListener cl = new ClListener();
        customerButton.addActionListener(cl);
        customerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,20));
        customerButton.setBounds(430,230,270,60);
        customerButton.setIcon(IconUtil.userIcon);
        homepagePanel.add(customerButton);

        // Exit button
        JButton exitButton = new JButton("Exit");
        ExitListener el = new ExitListener();
        exitButton.addActionListener(el);
        exitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        exitButton.setBounds(265, 372, 270, 60);
        exitButton.setIcon(IconUtil.exitIcon);
        homepagePanel.add(exitButton);
    }

    class ExitListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    class ClListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            try{
                GUICustomerLogin customerLogin = new GUICustomerLogin();
                customerLogin.setVisible(true);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open manager login window");
            }
        }
    }

    class MlListenner implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            try{
                GUIManagerLogin managerLogin = new GUIManagerLogin();
                managerLogin.setVisible(true);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open manager login window");
            }
        }
    }
}
