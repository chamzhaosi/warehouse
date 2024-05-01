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
public class NewEmployeeDialog extends JDialog{
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

    public NewEmployeeDialog (JFrame frame, String title){
        super(frame, title);

        addEmployeeRootPanel = new JPanel();
        addEmployeeRootPanel.setBackground(new Color(89, 194, 240));
        addEmployeeRootPanel.setLayout(new BorderLayout());
        this.setContentPane(addEmployeeRootPanel);
        
        setLabel();
        setSaveButton();
        setLabelTextField();
    }
    
    private void setLabel(){
        JLabel title = new JLabel("Add new employee", JLabel.CENTER);
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
                 boolean checkInfoWithDB = new JDBCTemplate().CompareInfoWithDBCustomer("staff_table", "staff_number", id_text.getText());
                if (checkInfoWithDB==true){
                    int result = JOptionPane.showConfirmDialog(addEmployeeRootPanel, "Do you want save it?", "Saving Notice", 2);
                        if (result==0){
                            Employee newEmployee = getTextField();
                        int update_count = new JDBCTemplate().insertDataIntoDBEmployee(newEmployee);
                            if (update_count>0){
                                JOptionPane.showMessageDialog(addEmployeeRootPanel, "Adding Sucessfully", "Notice", 1);
                                int resultQuery = JOptionPane.showConfirmDialog(addEmployeeRootPanel, "Do you want add another employee? ", "Query", 0);
                                if (resultQuery==0){
                                    continousAdd();
                                }else if (resultQuery == 1 || resultQuery == -1){
                                    closeFrame();
                                }
                            }
                        }
                }else {
                    JOptionPane.showMessageDialog(addEmployeeRootPanel, "Duplicate ID is not acceptable.", "Warning", 0);
                }
            }else {
                JOptionPane.showMessageDialog(addEmployeeRootPanel, "* Empty value is not acceptable.", "Warning", 0);
            }
    }
    
    private void setLabelTextField(){
        JPanel addEmployeePanel = new JPanel();
        addEmployeePanel.setBackground(new Color(119, 205, 242));
        addEmployeePanel.setBorder(new EmptyBorder(50,0,50,0));
        addEmployeePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addEmployeeRootPanel.add(addEmployeePanel,BorderLayout.CENTER);
        
        id = new JLabel("ID *: ");
        id_text = new JTextField(20);
        id_text.setDocument(new LengthRestrictedDocument(20));
        
        employee_name = new JLabel("Employee Name *: ");
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
        addEmployeePanel.add(id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addEmployeePanel.add(id_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addEmployeePanel.add(employee_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addEmployeePanel.add(employee_name_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        addEmployeePanel.add(identity_number, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addEmployeePanel.add(identity_number_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        addEmployeePanel.add(mobile_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addEmployeePanel.add(mobile_phone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        addEmployeePanel.add(emergency_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        addEmployeePanel.add(emergency_phone_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        addEmployeePanel.add(address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        addEmployeePanel.add(address_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        addEmployeePanel.add(employee_position, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        addEmployeePanel.add(employee_position_text, gbc);
    }
    
    private Employee getTextField(){
    
        Employee newEmployee = new Employee();
        newEmployee.setStaff_number(id_text.getText());
        newEmployee.setName(employee_name_text.getText());
        newEmployee.setIdentity_number(identity_number_text.getText());
        newEmployee.setMobile_phone(mobile_phone_text.getText());
        newEmployee.setEmergency_phone(emergency_phone_text.getText());
        newEmployee.setAddress(address_text.getText());
        newEmployee.setPosition(employee_position_text.getText());
        
        return newEmployee;
    }
    
    private boolean checkNotNullData(){
        Employee newEmployee = getTextField();
        boolean isEmptyValue = false;
        
        if(newEmployee.getStaff_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getName().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getIdentity_number().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getMobile_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getEmergency_phone().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getAddress().equals("")){
            return isEmptyValue = true;
        }
        
        if(newEmployee.getPosition().equals("")){
            return isEmptyValue = true;
        }
        
        return isEmptyValue;
        
    }
    
    private void continousAdd(){
        id_text.setText("");
        employee_name_text.setText("");
        identity_number_text.setText("");
        mobile_phone_text.setText("");
        emergency_phone_text.setText("");
        address_text.setText("");
        employee_position_text.setText("");
    }
    
    private void closeFrame(){
        this.setVisible(false);
        dispose();
    }
   
}
