package GUI;

import BankAttributes.*;
import Data.BossDataManager;
import Data.CustomerDataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * - This class serves as an informative page for manager to view the revenue gained
 *   from all active accounts.
 */
public class GUICheckRevenueWindow extends JFrame {
    private JPanel rwPanel;
    private JLabel headLabel;
    private JTextArea revenueArea;
    private JButton backButton;
    private JScrollPane scrollPane;
    private Manager manager;
    private JLabel sumLabel;

    private double revenueSum;

    public GUICheckRevenueWindow(Manager manager) throws ParseException {
        this.manager = manager;
        this.revenueSum = 0.0;

        // title
        setTitle("Bank Revenue Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,800,600);
        rwPanel = new JPanel();
        rwPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(rwPanel);
        rwPanel.setLayout(null);

        // back
        backButton = new JButton();
        backButton.setBounds(5,5,120,60);
        backButton.setIcon(IconUtil.backIcon);
        rwPanel.add(backButton);
        BackListener bl = new BackListener();
        backButton.addActionListener(bl);

        // head
        headLabel = new JLabel("Revenue Details of Active Accounts");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 550, 70);
        headLabel.setIcon(IconUtil.revenueIcon);
        headLabel.setForeground(Color.pink);
        rwPanel.add(headLabel);

        // revenue details
        revenueArea = new JTextArea();
        revenueArea.setEditable(false);
        revenueArea.setBounds(123,170,600,300);
        revenueArea.setLineWrap(true);

        // TODO: retrieve revenue details to "textStr"
        revenueArea.setText(getRevenueHistory());
        scrollPane = new JScrollPane(revenueArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension size = revenueArea.getPreferredSize();
        scrollPane.setBounds(123,170,600,270);
        rwPanel.add(scrollPane);

        // summary
        // TODO retrieve total revenue gained so far to "sum"
        DecimalFormat df = new DecimalFormat("#.00");
        sumLabel = new JLabel("Total Revenue: $ "+ df.format(getTotalRevenueAmount()));
        sumLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        sumLabel.setBounds(420, 400, 509, 120);
        rwPanel.add(sumLabel);
    }


    /**
     * @return revenue history
     * @throws ParseException
     */
    public String getRevenueHistory() throws ParseException {
        CustomerDataManager cData = new CustomerDataManager();
        BossDataManager bData = new BossDataManager();

        ArrayList<AccountFees> fees = bData.data;
        StringBuilder res = new StringBuilder();

        for (Customer c : cData.data) {
            for (Account a : c.getAccounts()) {
                double accOpenFee = 0.0;

                if (fees.size() == 1) {
                    // If there is only 1 fee currently (i.e. the default open account fee)
                    accOpenFee = fees.get(0).getOpenAccountFee();
                } else {
                    for (int i = 0; i < fees.size() - 1; ++i) {
                        Date accOpenDate = a.getOpenDate();
                        Date preOpenFeeTime = fees.get(i).getFeeSetTime();
                        Date postOpenFeeTime = fees.get(i + 1).getFeeSetTime();

                        if (accOpenDate.after(preOpenFeeTime) && accOpenDate.before(postOpenFeeTime)) {
                            // If the account was opened between preOpenFeeTime and postOpenFeeTime,
                            // the account open fee should be the fee set at preOpenFeeTime
                            accOpenFee = fees.get(i).getOpenAccountFee();
                            break;
                        }

                        if (accOpenDate.after(postOpenFeeTime) && (i == fees.size() - 2)) {
                            // If postOpenFeeTime is already the latest time and the account was opened after that,
                            // the account open fee should be the fee set at postOpenFeeTime
                            accOpenFee = fees.get(i + 1).getOpenAccountFee();
                            break;
                        }
                    }
                }

                for (Transaction t : a.getHistory()) {
                    res.append(t.printRevenue(c.getUserName(), accOpenFee, a.getType()));
                }
            }
        }

        return res.toString();
    }

    /**
     *   Get the current total revenue.
     */
    public double getTotalRevenueAmount() throws ParseException {
        CustomerDataManager cData = new CustomerDataManager();
        BossDataManager bData = new BossDataManager();

        ArrayList<AccountFees> fees = bData.data;
        StringBuilder res = new StringBuilder();

        for (Customer c : cData.data) {
            for (Account a : c.getAccounts()) {
                double accOpenFee = 0.0;

                if (fees.size() == 1) {
                    // If there is only 1 fee currently (i.e. the default open account fee)
                    accOpenFee = fees.get(0).getOpenAccountFee();
                } else {
                    for (int i = 0; i < fees.size() - 1; ++i) {
                        Date accOpenDate = a.getOpenDate();
                        Date preOpenFeeTime = fees.get(i).getFeeSetTime();
                        Date postOpenFeeTime = fees.get(i + 1).getFeeSetTime();

                        if (accOpenDate.after(preOpenFeeTime) && accOpenDate.before(postOpenFeeTime)) {
                            // If the account was opened between preOpenFeeTime and postOpenFeeTime,
                            // the account open fee should be the fee set at preOpenFeeTime
                            accOpenFee = fees.get(i).getOpenAccountFee();
                        }

                        if (accOpenDate.after(postOpenFeeTime) && (i == fees.size() - 2)) {
                            // If postOpenFeeTime is already the latest time and the account was opened after that,
                            // the account open fee should be the fee set at postOpenFeeTime
                            accOpenFee = fees.get(i + 1).getOpenAccountFee();
                        }
                    }
                }

                for (Transaction t : a.getHistory()) {
                    revenueSum += t.revenueAmount(accOpenFee);
                }
            }
        }

        return revenueSum;
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try{
                setVisible(false);
                GUIManagerCheckWindow managerCheckWindow = new GUIManagerCheckWindow(manager);
                managerCheckWindow.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(rwPanel,"Failed to go back");
            }
        }
    }
}
