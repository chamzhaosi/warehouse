/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.ButtonKeyAdapter;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Customer;
import PsnClass.Employee;
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
public class EditEmployeeDialog extends JDialog{
    private JPanel addEmployeeRootPanel = null;
    private JLabel id= null;
    private JTextField id_text = null;
    private JLabel employee_name = null;
    private JTextField employee_name_text = null;
    private JLabel identity_number = null;
    private JTextField identity_number_text = null;
    private JLabel mobile_phone = null;
    private JTextField mobile_phone_text = null;
    private JLabel emergency_phone = null;
    private JTextField emergency_phone_text = null;
    private JLabel address = null;
    private JTextArea address_text = null;
    private JLabel employee_position = null;
    private JTextField employee_position_text = null;
    
    private String selectedValue = null;
    private String columnname = null;

    public EditEmployeeDialog (JFrame frame, String title, String selectedValue, String columnName){
        super(frame ,title);
        this.selectedValue = selectedValue;
        this.columnname = columnName;
        
        addEmployeeRootPanel = new JPanel();
        addEmployeeRootPanel.setBackground(new Color(89, 194, 240,150));
        addEmployeeRootPanel.setLayout(new BorderLayout());
        this.add(addEmployeeRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
        setTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Edit employee", JLabel.CENTER);
        title.setFont(new Font ("", Font.BOLD, 30));
        
        addEmployeeRootPanel.add(title, BorderLayout.NORTH);
    }
    
    private void setSaveButton(){
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setBackground(new Color(179, 193, 201));
        JButton saveButton = new JButton("Save");
        addEmployeeRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
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
                int result = JOptionPane.showConfirmDialog(addEmployeeRootPanel, "Do you want save it?", "Saving Notice", 2);
                    if (result==0){
                        Employee editEmployee = getTextField();
                    int update_count = new JDBCTemplate().updateEmployeeDataIntoDB(editEmployee);
                        if (update_count > 0){
                            JOptionPane.showMessageDialog(addEmployeeRootPanel, "Edit Sucessfully", "Notice", 1);
                            closeFrame();
                        }
                    }
            }else {
                JOptionPane.showMessageDialog(addEmployeeRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel editEmployeePanel = new JPanel();
        editEmployeePanel.setBackground(new Color(119, 205, 242));
        editEmployeePanel.setBorder(new EmptyBorder(50,0,50,0));
        editEmployeePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addEmployeeRootPanel.add(editEmployeePanel,BorderLayout.CENTER);
        
        id = new JLabel("ID *: ");
        id_text = new JTextField(20);
        id_text.setDocument(new LengthRestrictedDocument(20));
        id_text.setEnabled(false);
        
        employee_name = new JLabel("Company Name *: ");
        employee_name_text = new JTextField(32);
        employee_name_text.setDocument(new LengthRestrictedDocument(32));
        
        identity_number = new JLabel("Identity Number *: ");
        identity_number_text = new JTextField(15);
        identity_number_text.setDocument(new LengthRestrictedDocument(15));
        
        mobile_phone = new JLabel("Mobile Phone: ");
        mobile_phone_text = new JTextField(20);
        mobile_phone_text.setDocument(new LengthRestrictedDocument(20));
        
        emergency_phone = new JLabel("Emergency Phone *: ");
        emergency_phone_text = new JTextField(20);
        emergency_phone_text.setDocument(new LengthRestrictedDocument(20));
        
        address = new JLabel("Address *: ");
        address_text = new JTextArea(5,10);
        address_text.setDocument(new LengthRestrictedDocument(500));
        address_text.setBorder(new LineBorder(Color.GRAY));
        
        
        employee_position = new JLabel("Position *: ");
        employee_position_text = new JTextField(50);
        employee_position_text.setDocument(new LengthRestrictedDocument(50));
        
        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        editEmployeePanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        editEmployeePanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        editEmployeePanel.add(employee_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        editEmployeePanel.add(employee_name_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        editEmployeePanel.add(identity_number, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        editEmployeePanel.add(identity_number_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        editEmployeePanel.add(mobile_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        editEmployeePanel.add(mobile_phone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        editEmployeePanel.add(emergency_phone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        editEmployeePanel.add(emergency_phone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        editEmployeePanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        editEmployeePanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        editEmployeePanel.add(employee_position, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        editEmployeePanel.add(employee_position_text, gbc);
    }
    
    private void setTextField(){
        Employee dbEmployeeInfo = new JDBCTemplate().getDBEmployeeInfo(columnname, (String)selectedValue);

        id_text.setText(dbEmployeeInfo.getStaff_number());
        employee_name_text.setText(dbEmployeeInfo.getName());
        identity_number_text.setText((dbEmployeeInfo.getIdentity_number()));
        mobile_phone_text.setText(dbEmployeeInfo.getMobile_phone());
        emergency_phone_text.setText(dbEmployeeInfo.getEmergency_phone());
        address_text.setText(dbEmployeeInfo.getAddress());
        employee_position_text.setText(dbEmployeeInfo.getPosition());
    }
    
    private Employee getTextField(){
        Employee editEmployee = new Employee();
        editEmployee.setStaff_number(id_text.getText());
        editEmployee.setName(employee_name_text.getText());
        editEmployee.setIdentity_number(identity_number_text.getText());
        editEmployee.setMobile_phone(mobile_phone_text.getText());
        editEmployee.setEmergency_phone(emergency_phone_text.getText());
        editEmployee.setAddress(address_text.getText());
        editEmployee.setPosition(employee_position_text.getText());
        
        return editEmployee;
    }

    private boolean checkNotNullData(){
        Employee editEmployee = getTextField();
        boolean isEmptyValue = false;
        
        if(editEmployee.getStaff_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getName().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getIdentity_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getMobile_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getEmergency_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(editEmployee.getPosition().equals("")){
            return isEmptyValue = true;
        }
        
        return isEmptyValue;
        
    }
    
    private void closeFrame(){
        this.dispose();
        this.setVisible(false);
    }
   
}
