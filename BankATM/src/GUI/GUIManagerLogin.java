package GUI;

import BankAttributes.Manager;
import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * - This class serves as the Login window for Manager.
 * - It provides "login" and "back" options for user, either login to manager
 *   window or back to homepage.
 */
public class GUIManagerLogin extends JFrame {

    private JPanel mlPanel;
    private JButton backButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel keyLabel;
    private JPasswordField keyField;
    private JButton loginButton;

    public GUIManagerLogin(){

        // title
        setTitle("Manager Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        mlPanel = new JPanel();
        mlPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(mlPanel);
        mlPanel.setLayout(null);

        // Back
//        Icon backIcon = new ImageIcon("back.png");
        backButton = new JButton();
        backButton.setIcon(IconUtil.backIcon);
//        backButton.setIcon(backIcon)
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        mlPanel.add(backButton);

        // head
        JLabel headLabel = new JLabel("<html>\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "Hi, Boss!<br>\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your default username is Christine<br>\r\n" +
                "Your default password is OO611<br>\r\n</html>");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        headLabel.setBounds(153, 77, 509, 120);
        headLabel.setForeground(Color.pink);
        mlPanel.add(headLabel);

        // username
        nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        nameLabel.setBounds(150, 250, 200, 60);
        nameLabel.setIcon(IconUtil.managerIcon);
        mlPanel.add(nameLabel);
        nameField = new JTextField(10);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 20));
        nameField.setToolTipText("");
        nameField.setBounds(340, 256, 290, 40);
        mlPanel.add(nameField);

        // password
        keyLabel = new JLabel("Password");
        keyLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        keyLabel.setBounds(150, 322, 160, 60);
        keyLabel.setIcon(IconUtil.passwordIcon);
        mlPanel.add(keyLabel);
        keyField = new JPasswordField(10);
        keyField.setHorizontalAlignment(SwingConstants.CENTER);
        keyField.setFont(new Font("Consolas", Font.PLAIN, 20));
        keyField.setToolTipText("");
        keyField.setBounds(340, 338, 290, 40);
        mlPanel.add(keyField);

        // login button

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        loginButton.setBounds(280, 413, 270, 60);
        loginButton.setIcon(IconUtil.confirmIcon);
        mlPanel.add(loginButton);
        LoginListener ll = new LoginListener();
        loginButton.addActionListener(ll);
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

    // TODO
    class LoginListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String name = nameField.getText().trim();
                String key = String.valueOf(keyField.getPassword());

                if (name.equals("") || key.equals("")) {
                    JOptionPane.showMessageDialog(mlPanel,"Username or password cannot be empty",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                if (!name.equals("Christine")) {
                    JOptionPane.showMessageDialog(mlPanel,"Boss, your username is: Christine",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                if (!key.equals("OO611")) {
                    JOptionPane.showMessageDialog(mlPanel,"Boss, your password is: OO611",
                            "Error",JOptionPane.ERROR_MESSAGE,IconUtil.closeIcon);
                    return;
                }

                // TODO: manager login
                Manager manager = new Manager();

                setVisible(false);
                GUIManagerWindow managerWindow = new GUIManagerWindow(manager);
                managerWindow.setVisible(true);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(mlPanel,"Failed to login");
            }
        }
    }

//    public static void main(String[] args){
//
//        System.out.println("Hi");
//
//        GUIManagerLogin test = new GUIManagerLogin();
//        test.setVisible(true);
//
//
//    }

}
