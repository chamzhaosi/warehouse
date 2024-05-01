/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import Adapter.ButtonKeyAdapter;
import Adapter.MouseAdapter;
import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import Dialog.EditEmployeeDialog;
import Dialog.NewCustomerDialog;
import Dialog.NewEmployeeDialog;
import PsnClass.Customer;
import PsnClass.Employee;
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
public class EmployeePanel extends JPanel{
    private JPanel mainPanel = null;
    private GridBagConstraints gbc = null;
    private JList employeeNameList = null;
    private JList employeeIDList = null;
    private List<Employee> dbEmployeeInfo = null;
    private DefaultListModel<String> employee_id = new DefaultListModel<>();
    private DefaultListModel<String> employee_name = new DefaultListModel<>();
    private DefaultListModel<String> employee_phone = new DefaultListModel<>();
    private DefaultListModel<String> employee_position = new DefaultListModel<>();

    public EmployeePanel(){
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(new Color(165, 216, 240));
        getDBAllEmployeeDetial();
        setDetailPanel();
        setAddButton();
        //For show notice purpose
        checkDBHasEmployeeInfo();
    }
    
    private void getDBAllEmployeeDetial(){
        dbEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void setDetailPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(140, 212, 245));
        mainPanel.setLayout(new GridLayout(1,4,20,20));
        
        JScrollPane sp = new JScrollPane(mainPanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.add(sp, BorderLayout.CENTER);
        
        setEmployeeID();
        setEmployeeName();
        setEmployeePhone();
        setEmployeePosition();
    }
    
    private void setEmployeeID(){
        JPanel employeeIDPanel = new JPanel();
        employeeIDPanel.setBackground(new Color(109, 196, 237));
        employeeIDPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        JLabel EmployeeIDLabel = new JLabel("Employee ID");
        EmployeeIDLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(employeeIDPanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        employeeIDPanel.add(EmployeeIDLabel, gbc);

        setListModelEmployeeID();
        
        employeeIDList = new JList(employee_id);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        employeeIDPanel.add(employeeIDList, gbc);
        
        employeeIDList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==1 && e.getClickCount()== 2){
                    if (employeeIDList.getSelectedIndex()!=-1){
                        setListenerAction("staff_number", employeeIDList.getSelectedValue());
                    }
                }
            }
        });
    }
    
    private void setListModelEmployeeID(){
        if (dbEmployeeInfo!=null){
            for (Employee e: dbEmployeeInfo){
                employee_id.addElement(e.getStaff_number());
            }
        }
    }
    
    private void setEmployeeName(){
        JPanel employeeNamePanel = new JPanel();
        employeeNamePanel.setBackground(new Color(109, 196, 237));
        employeeNamePanel.setLayout(new GridBagLayout());
        JLabel employeeNameLabel = new JLabel("Employee Name");
        employeeNameLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(employeeNamePanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        employeeNamePanel.add(employeeNameLabel,gbc);
        
        setListModelEmployeeName();
        
        employeeNameList = new JList(employee_name);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        employeeNamePanel.add(employeeNameList,gbc);

    }
    
    private void setListModelEmployeeName(){
        if (dbEmployeeInfo!=null){
            employee_name.clear();
            for (Employee e: dbEmployeeInfo){
                employee_name.addElement(e.getName());
            }
        }
    }
    
    private void setEmployeePhone(){
        JPanel employeePhonePanel = new JPanel();
        employeePhonePanel.setBackground(new Color(109, 196, 237));
        employeePhonePanel.setLayout(new GridBagLayout());
        JLabel employeePhoneLabel = new JLabel("Employee Phone");
        employeePhoneLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(employeePhonePanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        employeePhonePanel.add(employeePhoneLabel,gbc);     
        
        setListModelEmployeePhone();

        JList employeePhoneList = new JList(employee_phone); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        employeePhonePanel.add(employeePhoneList,gbc);
    }
    
    private void setListModelEmployeePhone(){
        if (dbEmployeeInfo!=null){
            for (Employee e: dbEmployeeInfo){
                employee_phone.addElement(e.getMobile_phone());
            }
        }
    }
    
    private void setEmployeePosition(){
        JPanel employeePosition = new JPanel();
        employeePosition.setBackground(new Color(109, 196, 237));
        employeePosition.setLayout(new GridBagLayout());
        JLabel employeePositionLable = new JLabel("Position");
        employeePositionLable.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(employeePosition);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        employeePosition.add(employeePositionLable,gbc);

        setListModelEmployeePosition();
        
        JList employeePositionList = new JList(employee_position); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        employeePosition.add(employeePositionList,gbc);
    }
    
    private void setListModelEmployeePosition(){
        if (dbEmployeeInfo!=null){
            for (Employee e: dbEmployeeInfo){
                employee_position.addElement(e.getPosition());
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
    
    public boolean checkDBHasEmployeeInfo(){
        List<Employee> dbEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
        return dbEmployeeInfo== null? false : true;
    }

    private void callingAddFrame(){
        NewEmployeeDialog newEmployee = new NewEmployeeDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Employee");
        newEmployee.setBounds(500, 40, 800, 800);
        newEmployee.setVisible(true);
        
        newEmployee.addWindowListener(new WindowAdapter(){

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
    
    //Calling EditEmployeeDialog
    private void setListenerAction(String columnName, Object selectedValue){
        EditEmployeeDialog editEmployeeFrame =  new EditEmployeeDialog((JFrame)SwingUtilities.windowForComponent(this) ,"Edit Employee Detail", (String)selectedValue, columnName);
        editEmployeeFrame.setBounds(500, 40, 800, 800);
        editEmployeeFrame.setVisible(true);
        
        editEmployeeFrame.addWindowListener(new WindowAdapter(){

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
        getDBAllEmployeeDetial();
        
        employee_id.clear();
        setListModelEmployeeID();
        
        employee_name.clear();
        setListModelEmployeeName();

        employee_phone.clear();
        setListModelEmployeePhone();

        employee_position.clear();
        setListModelEmployeePosition();
    }
    
    private void updateEditInfo(){
        getDBAllEmployeeDetial();
       
        employee_name.clear();
        setListModelEmployeeName();

        employee_phone.clear();
        setListModelEmployeePhone();

        employee_position.clear();
        setListModelEmployeePosition();
    }
}
