/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.ButtonKeyAdapter;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
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
import java.util.Map;
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
public class EditSupplierDialog extends JDialog{
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
    
    private String selectedValue = null;
    private String columnname = null;

    public EditSupplierDialog (JFrame frame, String title, String selectedValue, String columnName){
        super(frame ,title);
        this.selectedValue = selectedValue;
        this.columnname = columnName;
        
        addSupplierRootPanel = new JPanel();
        addSupplierRootPanel.setBackground(new Color(242, 128, 94));
        addSupplierRootPanel.setLayout(new BorderLayout());
        this.add(addSupplierRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
        setTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Edit supplier", JLabel.CENTER);
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
                int result = JOptionPane.showConfirmDialog(addSupplierRootPanel, "Do you want save it?", "Saving Notice", 2);
                    if (result==0){
                        Supplier editSupplier = getTextField();
                    int update_count = new JDBCTemplate().updateSupplierDataIntoDB(editSupplier);
                        if (update_count > 0){
                            JOptionPane.showMessageDialog(addSupplierRootPanel, "Edit Sucessfully", "Notice", 1);
                            closeFrame();
                        }
                    }
            }else {
                JOptionPane.showMessageDialog(addSupplierRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel editSupplierPanel = new JPanel();
        editSupplierPanel.setBackground(new Color(235, 159, 136));
        editSupplierPanel.setBorder(new EmptyBorder(50,0,50,0));
        editSupplierPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addSupplierRootPanel.add(editSupplierPanel,BorderLayout.CENTER);
        
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
        editSupplierPanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        editSupplierPanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        editSupplierPanel.add(companyName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        editSupplierPanel.add(companyName_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        editSupplierPanel.add(ssm, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        editSupplierPanel.add(ssm_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        editSupplierPanel.add(email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        editSupplierPanel.add(email_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        editSupplierPanel.add(companyPhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        editSupplierPanel.add(companyPhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        editSupplierPanel.add(mobilePhone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        editSupplierPanel.add(mobilePhone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        editSupplierPanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        editSupplierPanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        editSupplierPanel.add(personInChg, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        editSupplierPanel.add(personInChg_text, gbc);
    }
    
    private void setTextField(){
        Supplier dbSupplierInfo = new JDBCTemplate().getDBSupplierInfo(columnname, (String)selectedValue);

        id_text.setText(dbSupplierInfo.getSupplier_number());
        companyName_text.setText(dbSupplierInfo.getCompany_name());
        ssm_text.setText((dbSupplierInfo.getSSM()));
        email_text.setText(dbSupplierInfo.getEmail());
        companyPhone_text.setText(dbSupplierInfo.getCompany_phone());
        mobilePhone_text.setText(dbSupplierInfo.getMobile_phone());
        address_text.setText(dbSupplierInfo.getAddress());
        personInChg_text.setText(dbSupplierInfo.getPerson_in_charge());
    }
    
    private Supplier getTextField(){
        Supplier editSupplier = new Supplier();
        editSupplier.setSupplier_number(id_text.getText());
        editSupplier.setCompany_name(companyName_text.getText());
        editSupplier.setSSM(ssm_text.getText());
        editSupplier.setEmail(email_text.getText());
        editSupplier.setCompany_phone(companyPhone_text.getText());
        editSupplier.setMobile_phone(mobilePhone_text.getText());
        editSupplier.setAddress(address_text.getText());
        editSupplier.setPerson_in_charge(personInChg_text.getText());
        
        return editSupplier;
    }

    private boolean checkNotNullData(){
        Supplier editSupplier = getTextField();
        boolean isEmptyValue = false;
        
        if(editSupplier.getSupplier_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(editSupplier.getCompany_name().equals("")){
            return isEmptyValue = true;
        }
        
        if(editSupplier.getSSM().equals("")){
            return isEmptyValue = true;
        }
        
        if(editSupplier.getCompany_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(editSupplier.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(editSupplier.getPerson_in_charge().equals("")){
            return isEmptyValue = true;
        }
        
        return isEmptyValue;
        
    }
    
    private void closeFrame(){
        this.dispose();
        this.setVisible(false);
    }
   
}
