package GUI;

import BankAttributes.Customer;
import Data.CustomerDataManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * - This class serves as the Sign-in window for customers.
 */
public class GUICustomerSignin extends JFrame{

    private JPanel csPanel;
    private JButton backButton;
    private JLabel realnameLabel;
    private JTextField realnameField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel keyLabel;
    private JPasswordField keyField;
    private JLabel confirmKeyLabel;
    private JPasswordField confirmKeyField;
    private JButton createButton;

    public GUICustomerSignin(){
        // title
        setTitle("Create New Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        csPanel = new JPanel();
        csPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(csPanel);
        csPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        csPanel.add(backButton);

        // head
        JLabel headLabel = new JLabel("Create a new Account in Gringotts Bank!");
        headLabel.setIcon(IconUtil.heartIcon);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(63, 77, 700, 120);
        headLabel.setForeground(Color.pink);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        csPanel.add(headLabel);



        // username
        nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        nameLabel.setBounds(150, 210, 210, 60);
        nameLabel.setIcon(IconUtil.nameIcon);
        csPanel.add(nameLabel);
        nameField = new JTextField(8);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 20));
        nameField.setToolTipText("");
        nameField.setBounds(350, 212, 250, 40);
        csPanel.add(nameField);

        // password
        keyLabel = new JLabel("Password");
        keyLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        keyLabel.setBounds(150, 270, 160, 60);
        keyLabel.setIcon(IconUtil.passwordIcon);
        csPanel.add(keyLabel);
        keyField = new JPasswordField(8);
        keyField.setHorizontalAlignment(SwingConstants.CENTER);
        keyField.setFont(new Font("Consolas", Font.PLAIN, 20));
        keyField.setToolTipText("");
        keyField.setBounds(350, 278, 250, 40);
        csPanel.add(keyField);

        // confirmed password
        confirmKeyLabel = new JLabel("Confirm password");
        confirmKeyLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        confirmKeyLabel.setBounds(100, 340, 250, 60);
        confirmKeyLabel.setIcon(IconUtil.passwordIcon);
        csPanel.add(confirmKeyLabel);
        confirmKeyField = new JPasswordField(8);
        confirmKeyField.setHorizontalAlignment(SwingConstants.CENTER);
        confirmKeyField.setFont(new Font("Consolas", Font.PLAIN, 20));
        confirmKeyField.setToolTipText("");
        confirmKeyField.setBounds(350, 344, 250, 40);
        csPanel.add(confirmKeyField);

        // create button
        createButton = new JButton("Create");
        createButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        createButton.setBounds(265, 410, 270, 60);
        createButton.setIcon(IconUtil.confirmIcon);
        csPanel.add(createButton);
        CreateListener cl = new CreateListener();
        createButton.addActionListener(cl);

    }
    class BackListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUICustomerLogin customerLogin = new GUICustomerLogin();
                customerLogin.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open homepage");
            }

        }
    }

    // TODO: register action
    /**
     * customer input a username and type the passwords twice
     * test validity of username and password
     */
    class CreateListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String username = nameField.getText().trim();
                String key = String.valueOf(keyField.getPassword());
                String confirmKey = String.valueOf(confirmKeyField.getPassword());

                if (username.equals("") || key.equals("") || confirmKey.equals("")){
                    JOptionPane.showMessageDialog(csPanel,"You cannot leave blank","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                } else if (!key.equals(confirmKey)){
                    JOptionPane.showMessageDialog(csPanel,"Two passwords don't match","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                } else if(username.equalsIgnoreCase("Christine")){
                    JOptionPane.showMessageDialog(csPanel,"This is manager's username","Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                }else {
                    // TODO: test whether the username has been registered ; if not then register
                    CustomerDataManager customer = new CustomerDataManager();

                    boolean hasBeenRegistered = false;


                    for (Customer c : customer.data) {
                        if (c.getUserName().equals(username)) {
                            hasBeenRegistered = true;

                            JOptionPane.showMessageDialog(csPanel, "The customer has already been " +
                                    "registered!", "Error", JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);

                            break;
                        }
                    }

                    if (!hasBeenRegistered) {
                        // Register this customer to the database (i.e. Customers.csv)
                        Customer temp = new Customer(username, key);
                        customer.data.add(temp);
                        customer.writeData();

                        JOptionPane.showMessageDialog(csPanel,"Successfully create the customer!","Success"
                                ,JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    }
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(csPanel, "Failed to create the customer!");
            }

        }
    }


}
