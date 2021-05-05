package GUI;

import BankAttributes.*;
import Data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * - This class serves as an informative page for manager to view Bank's today report.
 */
public class GUICheckReportWindow extends JFrame {
    private JPanel rwPanel;
    private JLabel headLabel;
    private JTextArea reportArea;
    private JButton backButton;
    private JScrollPane scrollPane;
    private Manager manager;

    GUICheckReportWindow(Manager manager) throws ParseException {
        this.manager = manager;

        // title
        setTitle("Today's Report of Gringotts Bank");
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

        // head TODO: retrieve date to "date"

        // Get the date of today
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
        String date = DATE_FORMAT.format(new Date());

        headLabel = new JLabel(date + " Report");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        headLabel.setBounds(153, 57, 509, 80);
        headLabel.setIcon(IconUtil.documentIcon);
        headLabel.setForeground(Color.pink);
        rwPanel.add(headLabel);

        // report
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setBounds(123,170,600,300);
        reportArea.setLineWrap(true);

        // TODO: retrieve today's report to "textStr"
        String textStr = dailyReport();

        // Check if there exists at least a single transaction today
        if (textStr.isEmpty()) { reportArea.setText("There is not a single transaction today! Have a rest~"); }
        else { reportArea.setText(textStr); }

        scrollPane = new JScrollPane(reportArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(123,200,600,240);
        rwPanel.add(scrollPane);
    }

    // Get the daily transaction histories
    public String dailyReport() throws ParseException {
        CustomerDataManager cData = new CustomerDataManager();

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
        StringBuilder res = new StringBuilder();

        for (Customer c : cData.data) {
            for (Account a : c.getAccounts()) {
                for (Transaction t : a.getHistory()) {
                    String tDate = DATE_FORMAT.format(t.getDate());
                    String today = DATE_FORMAT.format(new Date());


                    if (tDate.equals(today)) {
                        res.append(t.printHistory(c.getUserName(), a.getType())).append("\r\n\r\n");
                    }
                }
            }
        }

        return res.toString();
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
