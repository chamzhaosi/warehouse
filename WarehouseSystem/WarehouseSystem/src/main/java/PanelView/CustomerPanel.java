/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import Adapter.ButtonKeyAdapter;
import Adapter.MouseAdapter;
import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import Dialog.EditCustomerDialog;
import Dialog.NewCustomerDialog;
import PsnClass.Customer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class CustomerPanel extends JPanel{
    private JPanel mainPanel = null;
    private GridBagConstraints gbc = null;
    private JList customerNameList = null;
    private JList customerIDList = null;
    private List<Customer> dbCustomerInfo = null;
    private DefaultListModel<String> customer_id = new DefaultListModel<>();
    private DefaultListModel<String> company_name = new DefaultListModel<>();
    private DefaultListModel<String> company_phone = new DefaultListModel<>();
    private DefaultListModel<String> person_in_charge = new DefaultListModel<>();

    public CustomerPanel(){
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(new Color(209, 235, 209));
        getDBAllCustomerDetial();
        setDetailPanel();
        setAddButton();
        //For show notice purpose
        checkDBHasCustomerInfo();
    }
    
    private void getDBAllCustomerDetial(){
        dbCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    private void setDetailPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(177, 222, 177));
        mainPanel.setLayout(new GridLayout(1,4,20,20));
        
        JScrollPane sp = new JScrollPane(mainPanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.add(sp, BorderLayout.CENTER);
        
        setCustomerID();
        setCustomerName();
        setCustomerPhone();
        setCustomerPrsInChg();
    }
    
    private void setCustomerID(){
        JPanel customerIDPanel = new JPanel();
        customerIDPanel.setBackground(new Color (151, 199, 151));
        customerIDPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        JLabel customerIDLabel = new JLabel("Customer ID");
        customerIDLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(customerIDPanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        customerIDPanel.add(customerIDLabel, gbc);

        setListModelCustomerID();
        
        customerIDList = new JList(customer_id);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        customerIDPanel.add(customerIDList, gbc);
        
        customerIDList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==1 && e.getClickCount()== 2){
                    if (customerIDList.getSelectedIndex()!= -1){
                        setListenerAction("customer_number", customerIDList.getSelectedValue());
                    }
                }
            }
        });
    }
    
    private void setListModelCustomerID(){
        if (dbCustomerInfo!=null){
            for (Customer c: dbCustomerInfo){
                customer_id.addElement(c.getCustomer_number());
            }
        }
    }
    
    private void setCustomerName(){
        JPanel customerNamePanel = new JPanel();
        customerNamePanel.setBackground(new Color (151, 199, 151));
        customerNamePanel.setLayout(new GridBagLayout());
        JLabel customerNameLabel = new JLabel("Customer Name");
        customerNameLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(customerNamePanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        customerNamePanel.add(customerNameLabel,gbc);
        
        setListModelCustomerName();
        
        customerNameList = new JList(company_name);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        customerNamePanel.add(customerNameList,gbc);

    }
    
    private void setListModelCustomerName(){
        if (dbCustomerInfo!=null){
            company_name.clear();
            for (Customer c: dbCustomerInfo){
                company_name.addElement(c.getCompany_name());
            }
        }
    }
    
    private void setCustomerPhone(){
        JPanel customerPhonePanel = new JPanel();
        customerPhonePanel.setBackground(new Color (151, 199, 151));
        customerPhonePanel.setLayout(new GridBagLayout());
        JLabel customerPhoneLabel = new JLabel("Company Phone");
        customerPhoneLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(customerPhonePanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        customerPhonePanel.add(customerPhoneLabel,gbc);     
        
        setListModelCustomerPhone();

        JList customerPhoneList = new JList(company_phone); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        customerPhonePanel.add(customerPhoneList,gbc);
    }
    
    private void setListModelCustomerPhone(){
        if (dbCustomerInfo!=null){
            for (Customer c: dbCustomerInfo){
                company_phone.addElement(c.getCompany_phone());
            }
        }
    }
    
    private void setCustomerPrsInChg(){
        JPanel customerPersonInChg = new JPanel();
        customerPersonInChg.setBackground(new Color (151, 199, 151));
        customerPersonInChg.setLayout(new GridBagLayout());
        JLabel customerPersonInChgLable = new JLabel("Person in charge");
        customerPersonInChgLable.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(customerPersonInChg);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        customerPersonInChg.add(customerPersonInChgLable,gbc);

        setListModelCustomerPrsInChg();
        
        JList customerPrsInChgList = new JList(person_in_charge); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        customerPersonInChg.add(customerPrsInChgList,gbc);
    }
    
    private void setListModelCustomerPrsInChg(){
        if (dbCustomerInfo!=null){
            for (Customer c: dbCustomerInfo){
                person_in_charge.addElement(c.getPerson_in_charge());
            }
        }
    }
    
    private void setAddButton(){
        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setBorder(new LineBorder(Color.GRAY));
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100,50));
        
        this.add(addButtonPanel, BorderLayout.SOUTH);
        addButtonPanel.add(addButton);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               callingAddFrame();
            }
        });
        
        addButton.addKeyListener(new ButtonKeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    callingAddFrame();
                }
            }
        
        });
    }
    
    public boolean checkDBHasCustomerInfo(){
        List<Customer> dbCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
        return dbCustomerInfo== null? false : true;
    }

    private void callingAddFrame(){
        NewCustomerDialog newCustomer = new NewCustomerDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Customer");
        newCustomer.setBounds(500, 40, 800, 800);
        newCustomer.setVisible(true);
        
        newCustomer.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewInfo();
                    }
                });
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewInfo();
                    }
                });
            }
        });
    }
    
    //Calling EditCustomerDialog
    private void setListenerAction(String columnName, Object selectedValue){
        EditCustomerDialog editCustomerFrame =  new EditCustomerDialog((JFrame)SwingUtilities.windowForComponent(this) ,"Edit Customer Detail", (String)selectedValue, columnName);
        editCustomerFrame.setBounds(500, 40, 800, 800);
        editCustomerFrame.setVisible(true);
        
        editCustomerFrame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateEditInfo();
                    }
                });
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateEditInfo();
                    }
                });
            }
        });
    }
   
    public void updateNewInfo(){
        getDBAllCustomerDetial();
        
        customer_id.clear();
        setListModelCustomerID();
        
        company_name.clear();
        setListModelCustomerName();

        company_phone.clear();
        setListModelCustomerPhone();

        person_in_charge.clear();
        setListModelCustomerPrsInChg();
    }
    
    private void updateEditInfo(){
        getDBAllCustomerDetial();
       
        company_name.clear();
        setListModelCustomerName();

        company_phone.clear();
        setListModelCustomerPhone();

        person_in_charge.clear();
        setListModelCustomerPrsInChg();
    }
}
