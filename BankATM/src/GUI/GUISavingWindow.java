package GUI;

import BankAttributes.*;
import Data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * - This class serves as a homepage for saving account of customers.
 * - Beside the functions inherited from parent class "GUICustomerAccountWindow", it also
 *   provides "Stock" function for customers who have balance over $5000 in saving account.
 *   When the customer is qualified and willing to enter the stock market, system will
 *   automatically help she/he buy $1000 shares, and then immediately ask the customer to
 *   do a series of decisions before selling all shares he/she holds.
 */
public class GUISavingWindow extends GUICustomerAccountWindow {

    private JButton stockButton;
    private double balance;

    public GUISavingWindow(Customer customer){
        super(customer);

        // title
        setTitle("Your Saving Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        swPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(swPanel);
        swPanel.setLayout(null);

        // balance
        balance = getBalance();
        DecimalFormat df = new DecimalFormat("#.00");
        JLabel balanceLabel = new JLabel("Your Balance: $" + df.format(balance));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        balanceLabel.setBounds(150, 77, 509, 90);
        swPanel.add(balanceLabel);


        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);
        backButton.setIcon(IconUtil.backIcon);
        swPanel.add(backButton);

        // deposit
        DepositListener dl = new DepositListener();
        depositButton.addActionListener(dl);

        // withdraw
        WithdrawListener wl = new WithdrawListener();
        withdrawButton.addActionListener(wl);

        // send
        SendListener sl = new SendListener();
        sendButton.addActionListener(sl);

        // loan
        LoanListener ll = new LoanListener();
        loanButton.addActionListener(ll);

        // history
        HistoryListener hl = new HistoryListener();
        historyButton.addActionListener(hl);

        // close account
        CloseAccountListener cl = new CloseAccountListener();
        closeAccountButton.addActionListener(cl);
        closeAccountButton.setBounds(205,500,400,60);

        // stock
        stockButton = new JButton("Stock");
        stockButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stockButton.setBounds(205, 420, 400, 60);
        stockButton.setIcon(IconUtil.stockIcon);
        swPanel.add(stockButton);
        StockListener tl = new StockListener();
        stockButton.addActionListener(tl);
    }

    class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingDepositWindow savingDepositWindow = new GUISavingDepositWindow(customer);
                savingDepositWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open deposit window");
            }
        }
    }

    class WithdrawListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingWithdrawWindow savingWithdrawWindow = new GUISavingWithdrawWindow(customer);
                savingWithdrawWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open withdraw window");
            }
        }
    }

    class SendListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingSendWindow savingSendWindow = new GUISavingSendWindow(customer);
                savingSendWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open send window");
            }
        }
    }

    class LoanListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingLoanWindow savingLoanWindow = new GUISavingLoanWindow(customer);
                savingLoanWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open loan window");
            }
        }
    }

    class HistoryListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUISavingHistoryWindow savingHistoryWindow = new GUISavingHistoryWindow(customer);
                savingHistoryWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Failed to open withdraw window");
            }
        }
    }

    // TODO: close account (savings)
    class CloseAccountListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                int choice = JOptionPane.showConfirmDialog(swPanel,"Continue to close your saving account?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);

                if(choice == JOptionPane.YES_OPTION){
                    // TODO: close account action
                    CustomerDataManager cData = new CustomerDataManager();
                    AccountDataManager aData = new AccountDataManager();

                    Account target = null;

                    for (Account a : customer.getAccounts()) {
                        // Find the savings account of the customer
                        if (a.getType().equalsIgnoreCase("SAVINGS")) { target = a; }
                    }

                    // Close this account in all places
                    cData.closeAccount(customer, target, new Date());
                    aData.removeAccount(target);

                    JOptionPane.showMessageDialog(swPanel,"Successfully Close Saving Account!","Success",
                            JOptionPane.PLAIN_MESSAGE,IconUtil.confirmIcon);
                    setVisible(false);
                    GUICustomerWindow customerWindow = new GUICustomerWindow(customer);
                    customerWindow.setVisible(true);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(swPanel,"Failed to close account");
            }
        }
    }

    /**
     * provides "Stock" function for customers who have balance over $5000 in saving account.
     *   When the customer is qualified and willing to enter the stock market, system will
     *   automatically help she/he buy $1000 shares, and then immediately ask the customer to
     *   do a series of decisions before selling all shares he/she holds.
     */
    class StockListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

            try {
                balance = getBalance();

                if (balance <= 5000) {
                    JOptionPane.showMessageDialog(swPanel,"You need at least $5000 in Saving Account to support you to enter Stock Market",
                            "Warning",JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(swPanel,"" +
                                "Do you want to buy $1000 shares of \"Hogwarts Fund\"?", "Warning",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, IconUtil.warnIcon);

                if (choice != 0) { return; }

                // TODO: Add the newly created securities account
                CustomerDataManager cData = new CustomerDataManager();
                AccountDataManager aData = new AccountDataManager();

                // Create a new securities account
                Account newAcc = new Securities("Securities", IDCenter.randomID(),
                        new Currency(1000.0, "$"), new Date());

                // Add the new checking account to both csv files and the customer's account list
                cData.openAccount(customer, newAcc, new Date());
                aData.addAccount(newAcc);

                Double currBalance = 1000.0;
                Double unrealizedProfit = 0.0;
                Double changeRate = 0.0;
                Double fee = 0.0;
                Double totalAmount = 0.0;
                Random random = new Random();
                DecimalFormat df = new DecimalFormat("#");

                while (true) {
                    Object[] options = {"Sell", "Update"};
                    String balancePart = "Current Balance = $" + df.format(currBalance);
                    String profitPart = unrealizedProfit >= 0 ? "Unrealized Profit = +$"+df.format(unrealizedProfit) :
                            "Unrealized Profit = -$" + df.format(unrealizedProfit * -1);
                    String ratePart = changeRate >= 0 ? "Current Change Rate = +"+df.format(changeRate*100) + "%":
                            "Current Change Rate = -" + df.format(changeRate*-100) + "%";

                    choice = JOptionPane.showOptionDialog(swPanel,balancePart+"\r\n"+profitPart+"\r\n"+ratePart,
                            "Latest Update",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,IconUtil.stockIcon,
                            options,options[0]);

                    // user closes this window
                    if(choice == -1){
                        JOptionPane.showMessageDialog(swPanel,"You cannot close stock window before selling all your shares!",
                                "Warning",JOptionPane.WARNING_MESSAGE,IconUtil.warnIcon);
                    } else if (choice ==1) {
                        if (currBalance<=10) {
                            JOptionPane.showMessageDialog(swPanel,"Your balance is close to a warning bound!\r\nWe will automatically sell your shares!",
                                    "Warning",JOptionPane.ERROR_MESSAGE,IconUtil.warnIcon);
                            break;
                        }

                        double rate = random.nextDouble();
                        changeRate = random.nextBoolean() ? rate * 0.4 : -1 * rate * 0.4;
                        currBalance = currBalance * (1 + changeRate);
                        unrealizedProfit = currBalance - 1000;

                    } else if (choice == 0) {
                        fee = currBalance*0.2;
                        totalAmount = currBalance-fee;

                        int option = JOptionPane.showConfirmDialog(swPanel,"You will be charged $" +
                                        df.format(fee)+" service fee.\r\n" +
                                        "You can obtain an amount of $ " +
                                        df.format(totalAmount)+" in total\r\nDo you want to continue?",
                                "Warning", JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,IconUtil.warnIcon);

                        // User chooses to sell the stock
                        if(option == 0) { break; }
                    }
                }

                double addToSaving = totalAmount;
                System.out.println("Successfully sell the stock and add the balance to savings");

                // TODO: sell addToSaving to saving
                aData.readData();
                cData.readData();
                Account savings = null;

                for (Account a : customer.getAccounts()) {
                    // Get the savings account of the customer
                    if (a.getType().equalsIgnoreCase("SAVINGS")) { savings = a; }
                }

                if (aData.sellStock(newAcc, savings, new Date(), addToSaving) == 0.0) {
                    cData.updateBalance(customer, savings, (balance - 1000 + addToSaving));
                    cData.readData();

                    for (Customer cus : cData.data) {
                        // Update up-to-date customer info in the database to [customer]
                        if (cus.getUserName().equals(customer.getUserName())) { customer = cus; }
                    }
                }


                setVisible(false);
                GUISavingWindow savingWindow = new GUISavingWindow(customer);
                savingWindow.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to open stock window");
            }
        }
    }

    public double getBalance() {
        // obtain latest saving balance
        double balance = 0.0;

        for (Account a : customer.getAccounts()) {
            // Get the balance of the customer's checking account
            if (a.getType().equalsIgnoreCase("SAVINGS")) {
                balance = a.getBalance().getAmount();
            }
        }

        return balance;
    }
}
