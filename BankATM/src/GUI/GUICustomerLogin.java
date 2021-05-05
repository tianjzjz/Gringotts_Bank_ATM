package GUI;

import BankAttributes.*;
import Data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * - This class serves as the Login window for customers.
 * - If user doesn't have an account yet, user could choose "Customer Signin" option
 *   then go to the Sign-in Window.
 * - If user already has an account, then he/she could login to the homepage for customer.
 */
public class GUICustomerLogin extends JFrame{

    private JPanel clPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel keyLabel;
    private JPasswordField keyField;
    private JButton backButton;
    private JButton loginButton;
    private JButton signinButton;

    public GUICustomerLogin(){
        // title
        setTitle("Customer Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        clPanel = new JPanel();
        clPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(clPanel);
        clPanel.setLayout(null);

        // Back
        backButton = new JButton();
        backButton.setBounds(5,5,100,60);
        backButton.setIcon(IconUtil.backIcon);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        clPanel.add(backButton);

        // head
        JLabel headLabel = new JLabel("Customer Login");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 90);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        headLabel.setIcon(IconUtil.heartIcon);
        clPanel.add(headLabel);

        // username
        nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        nameLabel.setBounds(150, 200, 210, 52);
        nameLabel.setIcon(IconUtil.nameIcon);
        clPanel.add(nameLabel);
        nameField = new JTextField(7);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 20));
        nameField.setToolTipText("");
        nameField.setBounds(340, 196, 290, 40);
        clPanel.add(nameField);

        // password
        keyLabel = new JLabel("  Password");
        keyLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        keyLabel.setBounds(160, 260, 160, 52);
        keyLabel.setIcon(IconUtil.passwordIcon);
        clPanel.add(keyLabel);
        keyField = new JPasswordField(7);
        keyField.setHorizontalAlignment(SwingConstants.CENTER);
        keyField.setFont(new Font("Consolas", Font.PLAIN, 20));
        keyField.setToolTipText("");
        keyField.setBounds(340, 262, 290, 40);
        clPanel.add(keyField);

        // login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        loginButton.setBounds(190, 393, 150, 60);
        loginButton.setIcon(IconUtil.confirmIcon);
        clPanel.add(loginButton);
        LoginListener ll = new LoginListener();
        loginButton.addActionListener(ll);

        // signin button
        signinButton = new JButton("Sign-in");
        signinButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        signinButton.setBounds(445, 393, 150, 60);
        signinButton.setIcon(IconUtil.signinIcon);
        clPanel.add(signinButton);
        SigninListener sl = new SigninListener();
        signinButton.addActionListener(sl);

    }


    class BackListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIHomepage homepage = new GUIHomepage();
                homepage.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open homepage");
            }

        }
    }

    // TODO: test username and password

    /**
     * customer login page
     * test his/her username and password
     */
    class LoginListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String username = nameField.getText().trim();
                String key = String.valueOf(keyField.getPassword());

                if (username.equals("") || key.equals("")) {
                    JOptionPane.showMessageDialog(clPanel,"You cannot leave blank",
                            "Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    // TODO: match username and key
                    CustomerDataManager cData = new CustomerDataManager();

                    boolean hasMatchedCustomer = false;

                    for (Customer c : cData.data) {
                        if (c.getUserName().equals(username) && c.getPassword().equals(key)) {
                            // If there is a matched customer, go to the next page
                            setVisible(false);

                            GUICustomerWindow customerWindow = new GUICustomerWindow(c);

                            customerWindow.setVisible(true);
                            hasMatchedCustomer = true;

                            break;
                        }
                }

                    if (!hasMatchedCustomer) {
                        // If there is no matched customer, pop up error message
                        JOptionPane.showMessageDialog(clPanel, "There is no matched customer!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to open customer window");
            }

        }
    }

    class SigninListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICustomerSignin customerSignin = new GUICustomerSignin();
                customerSignin.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open customer signin page");
            }

        }
    }
}
