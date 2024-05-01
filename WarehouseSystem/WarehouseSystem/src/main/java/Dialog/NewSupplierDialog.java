/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.ButtonKeyAdapter;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Customer;
import PsnClass.Supplier;
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
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user
 */
public class NewSupplierDialog extends JDialog{
    private JPanel addSupplierRootPanel = null;
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

    public NewSupplierDialog (JFrame frame, String title){
        super(frame, title);

        addSupplierRootPanel = new JPanel();
        addSupplierRootPanel.setBackground(new Color(242, 128, 94));
        addSupplierRootPanel.setLayout(new BorderLayout());
        this.setContentPane(addSupplierRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Add new supplier", JLabel.CENTER);
        title.setFont(new Font ("", Font.BOLD, 30));
        
        addSupplierRootPanel.add(title, BorderLayout.NORTH);
    }
    
    private void setSaveButton(){
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setBackground(new Color(179, 193, 201));
        JButton saveButton = new JButton("Save");
        addSupplierRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
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
                 boolean checkInfoWithDB = new JDBCTemplate().CompareInfoWithDBSupplier("supplier_table", "supplier_number", id_text.getText());
                if (checkInfoWithDB==true){
                    int result = JOptionPane.showConfirmDialog(addSupplierRootPanel, "Do you want save it?", "Saving Notice", 2);
                        if (result==0){
                            Supplier newSupplier = getTextField();
                        int update_count = new JDBCTemplate().insertDataIntoDBSupplier(newSupplier);
                            if (update_count>0){
                                JOptionPane.showMessageDialog(addSupplierRootPanel, "Adding Sucessfully", "Notice", 1);
                                int resultQuery = JOptionPane.showConfirmDialog(addSupplierRootPanel, "Do you want add another supplier? ", "Query", 0);
                                if (resultQuery==0){
                                    continousAdd();
                                }else if (resultQuery == 1 || resultQuery == -1){
                                    closeFrame();
                                }
                            }
                        }
                }else {
                    JOptionPane.showMessageDialog(addSupplierRootPanel, "Duplicate ID is not acceptable.", "Warning", 0);
                }
            }else {
                JOptionPane.showMessageDialog(addSupplierRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel addSupplierPanel = new JPanel();
        addSupplierPanel.setBackground(new Color(235, 159, 136));
        addSupplierPanel.setBorder(new EmptyBorder(50,0,50,0));
        addSupplierPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addSupplierRootPanel.add(addSupplierPanel,BorderLayout.CENTER);
        
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
        addSupplierPanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addSupplierPanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addSupplierPanel.add(companyName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addSupplierPanel.add(companyName_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        addSupplierPanel.add(ssm, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addSupplierPanel.add(ssm_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        addSupplierPanel.add(email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addSupplierPanel.add(email_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        addSupplierPanel.add(companyPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addSupplierPanel.add(companyPhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        addSupplierPanel.add(mobilePhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        addSupplierPanel.add(mobilePhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        addSupplierPanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        addSupplierPanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        addSupplierPanel.add(personInChg, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        addSupplierPanel.add(personInChg_text, gbc);
    }
    
    private Supplier getTextField(){
    
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplier_number(id_text.getText());
        newSupplier.setCompany_name(companyName_text.getText());
        newSupplier.setSSM(ssm_text.getText());
        newSupplier.setEmail(email_text.getText());
        newSupplier.setCompany_phone(companyPhone_text.getText());
        newSupplier.setMobile_phone(mobilePhone_text.getText());
        newSupplier.setAddress(address_text.getText());
        newSupplier.setPerson_in_charge(personInChg_text.getText());
        
        return newSupplier;
    }
    
    private boolean checkNotNullData(){
        Supplier newSupplier = getTextField();
        boolean isEmptyValue = false;
        
        if(newSupplier.getSupplier_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(newSupplier.getCompany_name().equals("")){
            return isEmptyValue = true;
        }
        
        if(newSupplier.getSSM().equals("")){
            return isEmptyValue = true;
        }
        
        if(newSupplier.getCompany_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(newSupplier.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(newSupplier.getPerson_in_charge().equals("")){
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
