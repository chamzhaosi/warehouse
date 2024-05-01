/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.ButtonKeyAdapter;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Customer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class NewCustomerDialog extends JDialog{
    private JPanel addCustomerRootPanel = null;
    private JLabel id= null;
    private JTextField id_text = null;
    private JLabel companyName = null;
    private JTextField companyName_text = null;
    private JLabel ssm = null;
    private JTextField ssm_text = null;
    private JLabel email = null;
    private JTextField email_text = null;
    private JLabel companyPhone = null;
    private JTextField companyPhone_text = null;
    private JLabel mobilePhone = null;
    private JTextField mobilePhone_text = null;
    private JLabel address = null;
    private JTextArea address_text = null;
    private JLabel personInChg = null;
    private JTextField personInChg_text = null;

    public NewCustomerDialog (JFrame frame, String title){
        super(frame, title);

        addCustomerRootPanel = new JPanel();
        addCustomerRootPanel.setBackground(new Color(73, 158, 11,150));
        addCustomerRootPanel.setLayout(new BorderLayout());
        this.setContentPane(addCustomerRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Add new customer", JLabel.CENTER);
        title.setFont(new Font ("", Font.BOLD, 30));
        
        addCustomerRootPanel.add(title, BorderLayout.NORTH);
    }
    
    private void setSaveButton(){
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setBackground(new Color(179, 193, 201));
        JButton saveButton = new JButton("Save");
        addCustomerRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        saveButton.setPreferredSize(new Dimension(100, 50));
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setListenerAction();
            }
        });
        
        saveButton.addKeyListener(new ButtonKeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    setListenerAction();
                }
            }
        
        });
    }
    
    private void setListenerAction(){
            if (checkNotNullData() == false){
                 boolean checkInfoWithDB = new JDBCTemplate().CompareInfoWithDBCustomer("customer_table", "customer_number", id_text.getText());
                if (checkInfoWithDB==true){
                    int result = JOptionPane.showConfirmDialog(addCustomerRootPanel, "Do you want save it?", "Saving Notice", 2);
                        if (result==0){
                            Customer newCustomer = getTextField();
                        int update_count = new JDBCTemplate().insertDataIntoDBCustomer(newCustomer);
                            if (update_count>0){
                                JOptionPane.showMessageDialog(addCustomerRootPanel, "Adding Sucessfully", "Notice", 1);
                                int resultQuery = JOptionPane.showConfirmDialog(addCustomerRootPanel, "Do you want add another customer? ", "Query", 0);
                                if (resultQuery==0){
                                    continousAdd();
                                }else if (resultQuery == 1 || resultQuery == -1){
                                    closeFrame();
                                }
                            }
                        }
                }else {
                    JOptionPane.showMessageDialog(addCustomerRootPanel, "Duplicate ID is not acceptable.", "Warning", 0);
                }
            }else {
                JOptionPane.showMessageDialog(addCustomerRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel addCustomerPanel = new JPanel();
        addCustomerPanel.setBackground(new Color(184, 242, 141));
        addCustomerPanel.setBorder(new EmptyBorder(50,0,50,0));
        addCustomerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addCustomerRootPanel.add(addCustomerPanel,BorderLayout.CENTER);
        
        id = new JLabel("ID *: ");
        id_text = new JTextField(20);
        id_text.setDocument(new LengthRestrictedDocument(20));
        
        companyName = new JLabel("Company Name *: ");
        companyName_text = new JTextField(50);
        companyName_text.setDocument(new LengthRestrictedDocument(50));
        
        ssm = new JLabel("SSM *: ");
        ssm_text = new JTextField(20);
        ssm_text.setDocument(new LengthRestrictedDocument(20));
        
        email = new JLabel("Email: ");
        email_text = new JTextField(50);
        email_text.setDocument(new LengthRestrictedDocument(50));
        
        companyPhone = new JLabel("Company Phone *: ");
        companyPhone_text = new JTextField(20);
        companyPhone_text.setDocument(new LengthRestrictedDocument(20));
        
        mobilePhone = new JLabel("Mobile Phone: ");
        mobilePhone_text = new JTextField(20);
        mobilePhone_text.setDocument(new LengthRestrictedDocument(20));
        
        address = new JLabel("Address *: ");
        address_text = new JTextArea(5,10);
        address_text.setDocument(new LengthRestrictedDocument(500));
        address_text.setBorder(new LineBorder(Color.GRAY));
        
        
        personInChg = new JLabel("Person In Charge *: ");
        personInChg_text = new JTextField(32);
        personInChg_text.setDocument(new LengthRestrictedDocument(32));
        
        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addCustomerPanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addCustomerPanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addCustomerPanel.add(companyName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addCustomerPanel.add(companyName_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        addCustomerPanel.add(ssm, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addCustomerPanel.add(ssm_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        addCustomerPanel.add(email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addCustomerPanel.add(email_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        addCustomerPanel.add(companyPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addCustomerPanel.add(companyPhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        addCustomerPanel.add(mobilePhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        addCustomerPanel.add(mobilePhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        addCustomerPanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        addCustomerPanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        addCustomerPanel.add(personInChg, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        addCustomerPanel.add(personInChg_text, gbc);
    }
    
    private Customer getTextField(){
    
        Customer newCustomer = new Customer();
        newCustomer.setCustomer_number(id_text.getText());
        newCustomer.setCompany_name(companyName_text.getText());
        newCustomer.setSSM(ssm_text.getText());
        newCustomer.setEmail(email_text.getText());
        newCustomer.setCompany_phone(companyPhone_text.getText());
        newCustomer.setMobile_phone(mobilePhone_text.getText());
        newCustomer.setAddress(address_text.getText());
        newCustomer.setPerson_in_charge(personInChg_text.getText());
        
        return newCustomer;
    }
    
    private boolean checkNotNullData(){
        Customer newCustomer = getTextField();
        boolean isEmptyValue = false;
        
        if(newCustomer.getCustomer_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(newCustomer.getCompany_name().equals("")){
            return isEmptyValue = true;
        }
        
        if(newCustomer.getSSM().equals("")){
            return isEmptyValue = true;
        }
        
        if(newCustomer.getCompany_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(newCustomer.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(newCustomer.getPerson_in_charge().equals("")){
            return isEmptyValue = true;
        }
        
        return isEmptyValue;
        
    }
    
    private void continousAdd(){
        id_text.setText("");
        companyName_text.setText("");
        ssm_text.setText("");
        email_text.setText("");
        companyPhone_text.setText("");
        mobilePhone_text.setText("");
        address_text.setText("");
        personInChg_text.setText("");
    }
    
    private void closeFrame(){
        this.setVisible(false);
        dispose();
    }
   
}
