/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import Adapter.ButtonKeyAdapter;
import Adapter.MouseAdapter;
import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import Dialog.EditSupplierDialog;
import Dialog.NewSupplierDialog;
import PsnClass.Supplier;
import UtilsTools.DruidUtils_User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user
 */
public class SupplierPanel extends JPanel{
    private JPanel mainPanel = null;
    private GridBagConstraints gbc = null;
    private JList supplierNameList = null;
    private JList supplierIDList = null;
    private List<Supplier> dbSuplierInfo = null;
    private DefaultListModel<String> supplier_id = new DefaultListModel<>();
    private DefaultListModel<String> company_name = new DefaultListModel<>();
    private DefaultListModel<String> company_phone = new DefaultListModel<>();
    private DefaultListModel<String> person_in_charge = new DefaultListModel<>();

    public SupplierPanel(){
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(new Color(217, 206, 210));
        getDBAllSuplierDetail();
        setDetailPanel();
        setAddButton();
        //For show notice purpose
        checkDBHasSupplierInfo();
    }
    
    private void getDBAllSuplierDetail(){
        dbSuplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    private void setDetailPanel(){
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(204, 182, 191));
        mainPanel.setLayout(new GridLayout(1,4,20,20));
        
        JScrollPane sp = new JScrollPane(mainPanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.add(sp, BorderLayout.CENTER);
        
        setSupplierID();
        setSupplierName();
        setSupplierPhone();
        setSupplierPrsInChg();
    }
    
    private void setSupplierID(){
        JPanel supplierIDPanel = new JPanel();
        supplierIDPanel.setBackground(new Color(194, 172, 180));
        supplierIDPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        JLabel supplierIDLabel = new JLabel("Supplier ID");
        supplierIDLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(supplierIDPanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        supplierIDPanel.add(supplierIDLabel, gbc);

        setListModelSupplierID();
        supplierIDList = new JList(supplier_id);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        supplierIDPanel.add(supplierIDList, gbc);
        
        supplierIDList.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==1 && e.getClickCount()== 2){
                    if (supplierIDList.getSelectedIndex() != -1){
                        setListenerAction("supplier_number", supplierIDList.getSelectedValue());
                    }
                }
            }
        });
    }
    
    private void setListModelSupplierID(){
        if (dbSuplierInfo!=null){
            for (Supplier s: dbSuplierInfo){
                supplier_id.addElement(s.getSupplier_number());
            }
        }
    }
    
    private void setSupplierName(){
        JPanel supplierNamePanel = new JPanel();
        supplierNamePanel.setBackground(new Color(194, 172, 180));
        supplierNamePanel.setLayout(new GridBagLayout());
        JLabel supplierNameLabel = new JLabel("Supplier Name");
        supplierNameLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(supplierNamePanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        supplierNamePanel.add(supplierNameLabel,gbc);
        
        setListModelSupplierName();
        
        supplierNameList = new JList(company_name);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        supplierNamePanel.add(supplierNameList,gbc);

    }
    
    private void setListModelSupplierName(){
        if (dbSuplierInfo!=null){
            company_name.clear();
            for (Supplier s: dbSuplierInfo){
                company_name.addElement(s.getCompany_name());
            }
        }
    }
    
    private void setSupplierPhone(){
        JPanel supplierPhonePanel = new JPanel();
        supplierPhonePanel.setBackground(new Color(194, 172, 180));
        supplierPhonePanel.setLayout(new GridBagLayout());
        JLabel supplierPhoneLabel = new JLabel("Company Phone");
        supplierPhoneLabel.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(supplierPhonePanel);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        supplierPhonePanel.add(supplierPhoneLabel,gbc);     
        
        setListModelSupplierPhone();

        JList supplierPhoneList = new JList(company_phone); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        supplierPhonePanel.add(supplierPhoneList,gbc);
    }
    
    private void setListModelSupplierPhone(){
        if (dbSuplierInfo!=null){
            for (Supplier s: dbSuplierInfo){
                company_phone.addElement(s.getCompany_phone());
            }
        }
    }
    
    private void setSupplierPrsInChg(){
        JPanel supplierPersonInChg = new JPanel();
        supplierPersonInChg.setBackground(new Color(194, 172, 180));
        supplierPersonInChg.setLayout(new GridBagLayout());
        JLabel supplierPersonInChgLable = new JLabel("Person in charge");
        supplierPersonInChgLable.setFont(new Font("", Font.BOLD, 20));
        
        mainPanel.add(supplierPersonInChg);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0,0,0,0);
        supplierPersonInChg.add(supplierPersonInChgLable,gbc);

        setListModelSupplierPrsInChg();
        
        JList supplierPrsInChgList = new JList(person_in_charge); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10,0,0,0);
        supplierPersonInChg.add(supplierPrsInChgList,gbc);
    }
    
    private void setListModelSupplierPrsInChg(){
        if (dbSuplierInfo!=null){
            for (Supplier s: dbSuplierInfo){
                person_in_charge.addElement(s.getPerson_in_charge());
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
    
    public boolean checkDBHasSupplierInfo(){
        List<Supplier> dbSuplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
        return dbSuplierInfo== null? false : true;
    }

    private void callingAddFrame(){
        NewSupplierDialog newSupplier = new NewSupplierDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Supplier");
        //newSupplier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newSupplier.setBounds(500, 40, 800, 800);
        newSupplier.setVisible(true);
        
        newSupplier.addWindowListener(new WindowAdapter(){

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
    
    //Calling EditSupplierDialog
    private void setListenerAction(String columnName, Object selectedValue){
        EditSupplierDialog editSupplierFrame =  new EditSupplierDialog((JFrame)SwingUtilities.windowForComponent(this) ,"Edit Supplier Detail", (String)selectedValue, columnName);
        //editSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editSupplierFrame.setBounds(500, 40, 800, 800);
        editSupplierFrame.setVisible(true);
        
        editSupplierFrame.addWindowListener(new WindowAdapter(){

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
        getDBAllSuplierDetail();
        
        supplier_id.clear();
        setListModelSupplierID();
        
        company_name.clear();
        setListModelSupplierName();

        company_phone.clear();
        setListModelSupplierPhone();

        person_in_charge.clear();
        setListModelSupplierPrsInChg();
    }
    
    private void updateEditInfo(){
        getDBAllSuplierDetail();
       
        company_name.clear();
        setListModelSupplierName();

        company_phone.clear();
        setListModelSupplierPhone();

        person_in_charge.clear();
        setListModelSupplierPrsInChg();
    }
}
