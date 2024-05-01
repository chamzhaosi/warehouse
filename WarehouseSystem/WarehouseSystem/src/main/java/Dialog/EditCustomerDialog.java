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
public class EditCustomerDialog extends JDialog{
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
    
    private String selectedValue = null;
    private String columnname = null;

    public EditCustomerDialog (JFrame frame, String title, String selectedValue, String columnName){
        super(frame ,title);
        this.selectedValue = selectedValue;
        this.columnname = columnName;
        
        addCustomerRootPanel = new JPanel();
        addCustomerRootPanel.setBackground(new Color(73, 158, 11,150));
        addCustomerRootPanel.setLayout(new BorderLayout());
        this.add(addCustomerRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
        setTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Edit customer", JLabel.CENTER);
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
                int result = JOptionPane.showConfirmDialog(addCustomerRootPanel, "Do you want save it?", "Saving Notice", 2);
                    if (result==0){
                        Customer editCustomer = getTextField();
                    int update_count = new JDBCTemplate().updateCustomerDataIntoDB(editCustomer);
                        if (update_count > 0){
                            JOptionPane.showMessageDialog(addCustomerRootPanel, "Edit Sucessfully", "Notice", 1);
                            closeFrame();
                        }
                    }
            }else {
                JOptionPane.showMessageDialog(addCustomerRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel editCustomerPanel = new JPanel();
        editCustomerPanel.setBackground(new Color(184, 242, 141));
        editCustomerPanel.setBorder(new EmptyBorder(50,0,50,0));
        editCustomerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addCustomerRootPanel.add(editCustomerPanel,BorderLayout.CENTER);
        
        id = new JLabel("ID *: ");
        id_text = new JTextField(20);
        id_text.setDocument(new LengthRestrictedDocument(20));
        id_text.setEnabled(false);
        
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
        editCustomerPanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        editCustomerPanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        editCustomerPanel.add(companyName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        editCustomerPanel.add(companyName_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        editCustomerPanel.add(ssm, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        editCustomerPanel.add(ssm_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        editCustomerPanel.add(email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        editCustomerPanel.add(email_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        editCustomerPanel.add(companyPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        editCustomerPanel.add(companyPhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        editCustomerPanel.add(mobilePhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        editCustomerPanel.add(mobilePhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        editCustomerPanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        editCustomerPanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        editCustomerPanel.add(personInChg, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        editCustomerPanel.add(personInChg_text, gbc);
    }
    
    private void setTextField(){
        Customer dbCustomerInfo = new JDBCTemplate().getDBCustomerInfo(columnname, (String)selectedValue);

        id_text.setText(dbCustomerInfo.getCustomer_number());
        companyName_text.setText(dbCustomerInfo.getCompany_name());
        ssm_text.setText((dbCustomerInfo.getSSM()));
        email_text.setText(dbCustomerInfo.getEmail());
        companyPhone_text.setText(dbCustomerInfo.getCompany_phone());
        mobilePhone_text.setText(dbCustomerInfo.getMobile_phone());
        address_text.setText(dbCustomerInfo.getAddress());
        personInChg_text.setText(dbCustomerInfo.getPerson_in_charge());
    }
    
    private Customer getTextField(){
        Customer editCustomer = new Customer();
        editCustomer.setCustomer_number(id_text.getText());
        editCustomer.setCompany_name(companyName_text.getText());
        editCustomer.setSSM(ssm_text.getText());
        editCustomer.setEmail(email_text.getText());
        editCustomer.setCompany_phone(companyPhone_text.getText());
        editCustomer.setMobile_phone(mobilePhone_text.getText());
        editCustomer.setAddress(address_text.getText());
        editCustomer.setPerson_in_charge(personInChg_text.getText());
        
        return editCustomer;
    }

    private boolean checkNotNullData(){
        Customer editCustomer = getTextField();
        boolean isEmptyValue = false;
        
        if(editCustomer.getCustomer_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(editCustomer.getCompany_name().equals("")){
            return isEmptyValue = true;
        }
        
        if(editCustomer.getSSM().equals("")){
            return isEmptyValue = true;
        }
        
        if(editCustomer.getCompany_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(editCustomer.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(editCustomer.getPerson_in_charge().equals("")){
            return isEmptyValue = true;
        }
        
        return isEmptyValue;
        
    }
    
    private void closeFrame(){
        this.dispose();
        this.setVisible(false);
    }
   
}
