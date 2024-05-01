/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import DBConnector.JDBCTemplate;
import PsnClass.Categories;
import PsnClass.Customer;
import PsnClass.Employee;
import PsnClass.InitialInventory;
import PsnClass.Inventory;
import PsnClass.InventoryLocation;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.SalesInvoice;
import PsnClass.SalesOrder;
import PsnClass.SalesReturn;
import PsnClass.Supplier;
import PsnClass.Type;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author user
 */
public class DeletePanel extends JPanel{
    
    private JRadioButton POButton;
    private JRadioButton PIButton;
    private JRadioButton PRButton;
    private JRadioButton receivedButton;
    private JRadioButton SOButton;
    private JRadioButton SIButton;
    private JRadioButton SRButton;
    private JRadioButton initialButton;
    private JRadioButton inventoryButton;
    private JRadioButton customerButton;
    private JRadioButton supplierButton;
    private JRadioButton employeeButton;
    private JRadioButton categoriesButton;
    private JRadioButton typeButton;
    private JRadioButton locationButton;
    
    private JComboBox POBox;
    private JComboBox PIBox;
    private JComboBox PRBox;
    private JComboBox receivedBox;
    private JComboBox SOBox;
    private JComboBox SIBox;
    private JComboBox SRBox;
    private JComboBox initialBox;
    private JComboBox inventoryBox;
    private JComboBox customerBox;
    private JComboBox supplierBox;
    private JComboBox employeeBox;
    private JComboBox categoriesBox;
    private JComboBox typeBox;
    private JComboBox locationBox;

    List<InitialInventory> dbAllInitialInventoryInfo = null;
    List<Inventory> dbAllInventoryInfo = null;
    List<Categories> dbAllCategoriesInfo = null;
    List<Type> dbAllTypeInfo = null;
    List<InventoryLocation> dbAllInventoryLocationInfo = null;
    List<PurchaseOrder> dbAllPurchaseOrderInfo = null;
    List<PurchaseInvoice> dbAllPurchaseInvoiceInfo = null;
    List<PurchaseReturn> dbAllPurchaseReturnInfo = null;
    List<SalesOrder>  dbAllSalesOrderInfo = null;
    List<SalesInvoice> dbAllSalesInvoiceInfo = null;
    List<SalesReturn> dbAllSalesReturnInfo = null;
    List<Received> dbAllReceivedInfo = null;
    List<Customer> dbAllCustomerInfo = null;
    List<Supplier> dbAllSupplierInfo = null;
    List<Employee> dbAllEmployeeInfo = null;
    
    public DeletePanel (){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.GRAY));
        updateAllDBInfo();
        setDeletePanel();
    }

    public void updateAllDBInfo(){
        getDBAllTypeInfo();
        getDBAllCategoriesInfo();
        getDBAllInventoryInfo();
        getDBAllLocationInfo();
        getDBAllSupplierInfo();
        getDBAllCustomerInfo();
        getDBAllEmployeeInfo();
        getDBAllInitialInventoryInfo();
        getDBAllPurchaseOrderInfo();
        getDBAllReceivedInfo();
        getDBAllPurchaseInoviceInfo();
        getDBAllPurchaseReturnInfo();
        getDBAllSalesOrderInfo();
        getDBAllSalesInvoiceInfo();
        getDBAllSalesReturnInfo();   
    }
    
    private void getDBAllTypeInfo(){
        dbAllTypeInfo = new JDBCTemplate().getDBALLTypeInfo("type_table", "name");
    }
    
    private void getDBAllCategoriesInfo(){
    dbAllCategoriesInfo = new JDBCTemplate().getDBALLCategoriesInfo("categories_table", "name");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("Inventory_table", "Inventory_id");
    }
    
    private void getDBAllLocationInfo(){
        dbAllInventoryLocationInfo = new JDBCTemplate().getDBAllLocationInfo("inventory_location_table", "name");
    }
    
    private void getDBAllSupplierInfo(){
        dbAllSupplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    private void getDBAllCustomerInfo(){
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    private void getDBAllEmployeeInfo(){
        dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllInitialInventoryInfo(){
        dbAllInitialInventoryInfo = new JDBCTemplate().getDBAllInitialInventoryInfo("initial_inventory_table", "initial_inventory_id");
    }
    
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfo = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", 
                "purchase_order_id");
    }
    
    private void getDBAllReceivedInfo(){
        dbAllReceivedInfo = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_id");
    }
    
    private void getDBAllPurchaseInoviceInfo(){
        dbAllPurchaseInvoiceInfo = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", 
                "purchase_invoice_id");
    }
    
    private void getDBAllPurchaseReturnInfo(){
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", 
                "purchase_return_id");
    }
    
    private void getDBAllSalesOrderInfo(){
        dbAllSalesOrderInfo = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", 
                "sales_order_id");
    }
    
    private void getDBAllSalesInvoiceInfo(){
        dbAllSalesInvoiceInfo = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", 
                "sales_invoice_id");
    }
    
    private void getDBAllSalesReturnInfo(){
        dbAllSalesReturnInfo = new JDBCTemplate().getDBAllSalesReturnInfo("sales_return_table", 
                "sales_return_id"); 
    }

    private void setDeletePanel(){
        JPanel deleteLabelPanel = new JPanel();
        this.add(deleteLabelPanel, BorderLayout.NORTH);
        
        JLabel deleteLabel = new JLabel("<< Delete >>", SwingUtilities.CENTER);
        deleteLabel.setFont(new Font("", Font.BOLD, 35));
        deleteLabelPanel.add(deleteLabel);
        
        JPanel deleteRootPanel = new JPanel();
        deleteRootPanel.setLayout(new GridLayout(1,2,0,5));
        this.add(deleteRootPanel, BorderLayout.CENTER);

        JPanel deletePart1Panel = new JPanel();
        deletePart1Panel.setLayout(new GridBagLayout());
        GridBagConstraints gbcPart1 = new GridBagConstraints();
        
        JPanel deletePart2Panel = new JPanel();
        deletePart2Panel.setLayout(new GridBagLayout());
        GridBagConstraints gbcPart2 = new GridBagConstraints();
        
        deleteRootPanel.add(deletePart1Panel);
        deleteRootPanel.add(deletePart2Panel);
        
        JLabel POLabel = new JLabel ("Purchase Order");
        JLabel PILabel = new JLabel ("Purchase invoice");
        JLabel PRLabel = new JLabel ("Purchase Return");
        JLabel ReceivedLabel = new JLabel ("Received");
        JLabel SOLabel = new JLabel ("Sales Order");
        JLabel SILabel = new JLabel ("Sales Invoice");
        JLabel SRLabel = new JLabel ("Sales Return");
        JLabel InitialLabel = new JLabel ("Initial Inventory");
        JLabel inventoryLabel = new JLabel ("Inventory");
        JLabel customerLabel = new JLabel ("Customer");
        JLabel supplierLabel = new JLabel ("Supllier");
        JLabel employeeLabel = new JLabel ("Employee");
        JLabel categoriesLabel = new JLabel ("Categories");
        JLabel typeLabel = new JLabel ("Type");
        JLabel locationLabel = new JLabel ("Location"); 
        
        POBox = new JComboBox();
        PIBox = new JComboBox();
        PRBox = new JComboBox();
        receivedBox = new JComboBox();
        SOBox = new JComboBox();
        SIBox = new JComboBox();
        SRBox = new JComboBox();
        initialBox = new JComboBox();
        inventoryBox = new JComboBox();
        customerBox = new JComboBox();
        supplierBox = new JComboBox();
        employeeBox = new JComboBox();
        categoriesBox = new JComboBox();
        typeBox = new JComboBox();
        locationBox = new JComboBox();
        
        setAllComBoxItem();

        POButton = new JRadioButton();
        PIButton = new JRadioButton();
        PRButton = new JRadioButton();
        receivedButton = new JRadioButton();
        SOButton = new JRadioButton();
        SIButton = new JRadioButton();
        SRButton = new JRadioButton();
        initialButton = new JRadioButton();
        inventoryButton = new JRadioButton();
        customerButton = new JRadioButton();
        supplierButton = new JRadioButton();
        employeeButton = new JRadioButton();
        categoriesButton = new JRadioButton();
        typeButton = new JRadioButton();
        locationButton = new JRadioButton();
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(POButton);
        group.add(PIButton);
        group.add(PRButton);
        group.add(receivedButton);
        group.add(SOButton);
        group.add(SIButton);
        group.add(SRButton);
        group.add(initialButton);
        group.add(inventoryButton);
        group.add(customerButton);
        group.add(supplierButton);
        group.add(employeeButton);
        group.add(categoriesButton);
        group.add(typeButton);
        group.add(locationButton);

        gbcPart1.fill = GridBagConstraints.HORIZONTAL;
        gbcPart1.ipady = 10;
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 0;
        deletePart1Panel.add(POLabel, gbcPart1);
        gbcPart1.insets = new Insets(0,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 0;
        deletePart1Panel.add(POBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 0;
        deletePart1Panel.add(POButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 1;
        deletePart1Panel.add(PILabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 1;
        deletePart1Panel.add(PIBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 1;
        deletePart1Panel.add(PIButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 2;
        deletePart1Panel.add(PRLabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 2;
        deletePart1Panel.add(PRBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 2;
        deletePart1Panel.add(PRButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 3;
        deletePart1Panel.add(ReceivedLabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 3;
        deletePart1Panel.add(receivedBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 3;
        deletePart1Panel.add(receivedButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 4;
        deletePart1Panel.add(SOLabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 4;
        deletePart1Panel.add(SOBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 4;
        deletePart1Panel.add(SOButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 5;
        deletePart1Panel.add(SILabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 5;
        deletePart1Panel.add(SIBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 5;
        deletePart1Panel.add(SIButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 6;
        deletePart1Panel.add(SRLabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 6;
        deletePart1Panel.add(SRBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 6;
        deletePart1Panel.add(SRButton, gbcPart1);
        
        gbcPart1.insets = new Insets(10,0,0,0);
        gbcPart1.gridx = 0;
        gbcPart1.gridy = 7;
        deletePart1Panel.add(InitialLabel, gbcPart1);
        gbcPart1.insets = new Insets(10,5,0,0);
        gbcPart1.gridx = 1;
        gbcPart1.gridy = 7;
        deletePart1Panel.add(initialBox, gbcPart1);
        gbcPart1.gridx = 2;
        gbcPart1.gridy = 7;
        deletePart1Panel.add(initialButton, gbcPart1);
        
        gbcPart2.fill = GridBagConstraints.HORIZONTAL;
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 0;
        deletePart2Panel.add(supplierLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 0;
        deletePart2Panel.add(supplierBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 0;
        deletePart2Panel.add(supplierButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 1;
        deletePart2Panel.add(customerLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 1;
        deletePart2Panel.add(customerBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 1;
        deletePart2Panel.add(customerButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 2;
        deletePart2Panel.add(employeeLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 2;
        deletePart2Panel.add(employeeBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 2;
        deletePart2Panel.add(employeeButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 3;
        deletePart2Panel.add(inventoryLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 3;
        deletePart2Panel.add(inventoryBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 3;
        deletePart2Panel.add(inventoryButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 4;
        deletePart2Panel.add(categoriesLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 4;
        deletePart2Panel.add(categoriesBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 4;
        deletePart2Panel.add(categoriesButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 5;
        deletePart2Panel.add(typeLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 5;
        deletePart2Panel.add(typeBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 5;
        deletePart2Panel.add(typeButton, gbcPart2);
        
        gbcPart2.insets = new Insets(10,0,0,0);
        gbcPart2.gridx = 0;
        gbcPart2.gridy = 6;
        deletePart2Panel.add(locationLabel, gbcPart2);
        gbcPart2.insets = new Insets(10,5,0,0);
        gbcPart2.gridx = 1;
        gbcPart2.gridy = 6;
        deletePart2Panel.add(locationBox, gbcPart2);
        gbcPart2.gridx = 2;
        gbcPart2.gridy = 6;
        deletePart2Panel.add(locationButton, gbcPart2);
        
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.setLayout(new FlowLayout());
        deleteButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        this.add(deleteButtonPanel, BorderLayout.SOUTH);
        JButton deleteButton = new JButton("Delete");
        
        deleteButtonPanel.add(deleteButton);
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getDeletedItem();
            }
        });
    }
    
    private void getDeletedItem(){
        if (POButton.isSelected()){
            purchaseOrderDeleteOperation();
            getDBAllPurchaseOrderInfo();
            setPOComBoxItem();
        }
        
        if (PIButton.isSelected()){
            purchaseInvoiceDeleteOperation();
            getDBAllPurchaseInoviceInfo();
            getDBAllReceivedInfo();
            setPIComBoxItem();
        }
        
        if (PRButton.isSelected()){
            purchaseReturnDeleteOperation();
            getDBAllPurchaseReturnInfo();
            getDBAllPurchaseInoviceInfo();
            setPRComBoxItem();
        }
        
        if (SOButton.isSelected()){
            salesOrderDeleteOperation();
            getDBAllSalesOrderInfo();
            setSOComBoxItem();
        }
        
        if (SIButton.isSelected()){
            salesInvoiceDeleteOperation();
            getDBAllSalesInvoiceInfo();
            getDBAllSalesOrderInfo();
            setSIComBoxItem();
        }
        
        if (SRButton.isSelected()){
            salesReturnDeleteOperation();
            getDBAllSalesReturnInfo();
            getDBAllSalesInvoiceInfo();
            setSRComBoxItem();
        }
        
        if (receivedButton.isSelected()){
            receivedDeleteOperation();
            getDBAllReceivedInfo();
            getDBAllPurchaseOrderInfo();
            setReceivedComBoxItem();
        }
        
        if (initialButton.isSelected()){
            initialDeleteOperation();
            getDBAllInitialInventoryInfo();
            setInitialInventoryComBoxItem();
        }
        
        if (inventoryButton.isSelected()){
            inventoryDeleteOperation();
            getDBAllInventoryInfo();
            setInventoryComBoxItem();
        }
        
        if (customerButton.isSelected()){
            customerDeleteOperation();
            getDBAllCustomerInfo();
            setCustomerComBoxItem();
        }
        
        if (supplierButton.isSelected()){
            supplierDeleteOperation();
            getDBAllSupplierInfo();
            setSupplierComBoxItem();
        }
        
        if (employeeButton.isSelected()){
            employeeDeleteOperation();
            getDBAllEmployeeInfo();
            setEmployeeComBoxItem();
        }
        
        if (locationButton.isSelected()){
            locationDeleteOperation();
            getDBAllLocationInfo();
            setLocationComBoxItem();
        }
        
        if (categoriesButton.isSelected()){
            categoriesDeleteOperation();
            getDBAllCategoriesInfo();
            setCategoriesComBoxItem();
        }
        
        if (typeButton.isSelected()){
            typeDeleteOperation();
            getDBAllTypeInfo();
            setTypeComBoxItem();
        }
    }
    
    private void typeDeleteOperation(){
        String selectedTypeItem = (String)typeBox.getSelectedItem();
        int type_id = -1;
        
        if (dbAllTypeInfo != null){
            for (Type t : dbAllTypeInfo){
                if (t.getName().equals(selectedTypeItem)){
                    type_id = t.getType_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                if (i.getType_id() == type_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (isFree && type_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedTypeItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("type_table", "type_id", String.valueOf(type_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedTypeItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedTypeItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void categoriesDeleteOperation(){
        String selectedCategoriesItem = (String)categoriesBox.getSelectedItem();
        int categories_id = -1;
        
        if (dbAllCategoriesInfo != null){
            for (Categories c : dbAllCategoriesInfo){
                if (c.getName().equals(selectedCategoriesItem)){
                    categories_id = c.getCategories_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                if (i.getCategories_id() == categories_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (isFree && categories_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedCategoriesItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("categories_table", "categories_id", String.valueOf(categories_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedCategoriesItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedCategoriesItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void inventoryDeleteOperation(){
        String selectedInventoryItem = (String)inventoryBox.getSelectedItem();
        int inventory_id = -1;
        
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                if (i.getInventory_number().equals(selectedInventoryItem)){
                    inventory_id = i.getInventory_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllInitialInventoryInfo != null){
            for (InitialInventory il : dbAllInitialInventoryInfo){
                if (il.getInventory_id() == inventory_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllPurchaseOrderInfo != null && isFree){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (po.getInventory_id() == inventory_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllSalesOrderInfo != null && isFree){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getInventory_id() == inventory_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (isFree && inventory_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedInventoryItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("inventory_table", "inventory_id", String.valueOf(inventory_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedInventoryItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedInventoryItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void locationDeleteOperation(){
        String selectedLocationItem = (String)locationBox.getSelectedItem();
        int location_id = -1;
        
        if (dbAllInventoryLocationInfo != null){
            for (InventoryLocation il : dbAllInventoryLocationInfo){
                if (il.getName().equals(selectedLocationItem)){
                    location_id = il.getInventory_location_id();
                    break;
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (r.getInventory_location_id() == location_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllInitialInventoryInfo != null && isFree){
            for (InitialInventory il: dbAllInitialInventoryInfo){
                if (il.getInventory_location_id() == location_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (isFree && location_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedLocationItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("inventory_location_table", "inventory_location_id", String.valueOf(location_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedLocationItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedLocationItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void supplierDeleteOperation(){
        String selectedSupplierItem = (String)supplierBox.getSelectedItem();
        int supplier_id = -1;
        
        if (dbAllSupplierInfo != null){
            for (Supplier s : dbAllSupplierInfo){
                if (s.getSupplier_number().equals(selectedSupplierItem)){
                    supplier_id = s.getSupplier_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (r.getSupplier_id() == supplier_id){
                    isFree = false;
                    break;
                }
            }
        }

        if (isFree && supplier_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedSupplierItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("supplier_table", "supplier_id", String.valueOf(supplier_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedSupplierItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedSupplierItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void customerDeleteOperation(){
        String selectedCustomerItem = (String)customerBox.getSelectedItem();
        int customer_id = -1;
        
        if (dbAllCustomerInfo != null){
            for (Customer c: dbAllCustomerInfo){
                if (c.getCustomer_number().equals(selectedCustomerItem)){
                    customer_id = c.getCustomer_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getCustomer_id() == customer_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (isFree && customer_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedCustomerItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("customer_table", "customer_id", String.valueOf(customer_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedCustomerItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedCustomerItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void employeeDeleteOperation(){
        String selectedEmployeeItem = (String)employeeBox.getSelectedItem();
        int employee_id = -1;
        
        if (dbAllEmployeeInfo != null){
            for (Employee e: dbAllEmployeeInfo){
                if (e.getStaff_number().equals(selectedEmployeeItem)){
                    employee_id = e.getStaff_id();
                }
            }
        }
        
        boolean isFree = true;
        if (dbAllPurchaseOrderInfo != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (po.getStaff_id() == employee_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllReceivedInfo != null && isFree){
            for (Received r : dbAllReceivedInfo){
                if (r.getStaff_id() == employee_id){
                    isFree = false;
                    break;
                }
            }
        }
            
        if (dbAllPurchaseInvoiceInfo != null && isFree){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (pi.getStaff_id() == employee_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllSalesOrderInfo != null && isFree){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getStaff_id() == employee_id){
                    isFree = false;
                    break;
                }
            }
        }
        
        if (dbAllSalesInvoiceInfo != null && isFree){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (si.getStaff_id() == employee_id){
                    isFree = false;
                    break;
                }
            }
        }

        if (isFree && employee_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedEmployeeItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("staff_table", "staff_id", String.valueOf(employee_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedEmployeeItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedEmployeeItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void initialDeleteOperation(){
        String selectedInitialItem = (String)initialBox.getSelectedItem();
        int initial_inventory_id = -1;
        
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                if (i.getInventory_number().equals(selectedInitialItem)){
                    if (dbAllInitialInventoryInfo != null){
                        for (InitialInventory il : dbAllInitialInventoryInfo){
                            if (il.getInventory_id() == i.getInventory_id()){
                                initial_inventory_id = il.getInitial_inventory_id();
                                break;
                            }
                        }
                    }
                } 
            }
        }

        if (initial_inventory_id != -1){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedInitialItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("initial_inventory_table", "initial_inventory_id", String.valueOf(initial_inventory_id));
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedInitialItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedInitialItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void purchaseOrderDeleteOperation(){
        String selectedPOItem = (String)POBox.getSelectedItem();

        boolean isFree = true;
        if (dbAllPurchaseOrderInfo != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (po.getPurchase_order_number().equals(selectedPOItem)){
                    if (po.getBe_cleared() == 1){
                        isFree = false;
                        break;
                    }
                }
            }
        }
        
        if (dbAllReceivedInfo != null && isFree){
            for (Received r : dbAllReceivedInfo){
                for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                    if (po.getPurchase_order_number().equals(selectedPOItem)){
                        if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                            isFree = false;
                            break;
                        }
                    }
                }
            }
        }
      

        if (isFree && selectedPOItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedPOItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int result = new JDBCTemplate().deleteItemFromDB("purchase_order_table", "purchase_order_number", selectedPOItem);
                if (result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedPOItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedPOItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void receivedDeleteOperation(){
        String selectedReceivedItem = (String)receivedBox.getSelectedItem();

        boolean isFree = true;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (r.getReceived_number().equals(selectedReceivedItem)){
                    if (r.getBe_cleared() == 1){
                        isFree = false;
                        break;
                    }
                }
            }
        }
        
        if (dbAllPurchaseInvoiceInfo != null && isFree){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (dbAllReceivedInfo != null){
                    for (Received r : dbAllReceivedInfo){
                        if (r.getReceived_number().equals(selectedReceivedItem)){
                            if (pi.getReceived_id() == r.getReceived_id()){
                                isFree = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        List<String> purhcaseOrderNumberList = new ArrayList<>();
        String purhcase_order_number = null;
        if (dbAllPurchaseOrderInfo != null && isFree){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (dbAllReceivedInfo != null){
                    for (Received r : dbAllReceivedInfo){
                        if (r.getReceived_number().equals(selectedReceivedItem)){
                            if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                                purhcase_order_number = po.getPurchase_order_number();
                                purhcaseOrderNumberList.add(purhcase_order_number);
                            }
                        }
                    }
                }
            }
        }
      

        if (isFree && selectedReceivedItem != null && purhcase_order_number != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedReceivedItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("received_table", "received_number", selectedReceivedItem);
                int update_result = new JDBCTemplate().updatePurchaseOrderDataIntoDB(purhcaseOrderNumberList, 0);
                if (delete_result > 0 && update_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedReceivedItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedReceivedItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void purchaseInvoiceDeleteOperation(){
        String selectedPIItem = (String)PIBox.getSelectedItem();

        boolean isFree = true;
        if (dbAllPurchaseInvoiceInfo != null){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (pi.getPurchase_invoice_number().equals(selectedPIItem)){
                    if (pi.getBe_cleared() == 1){
                        isFree = false;
                        break;
                    }
                }
            }
        }
        
        if (dbAllPurchaseReturnInfo != null && isFree){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                    if (pi.getPurchase_invoice_number().equals(selectedPIItem)){
                        if (pi.getPurchase_invoice_id() == pr.getPurchase_invoice_id()){
                            isFree = false;
                            break;
                        }
                    }
                }
            }
        }
        
        String received_number = null;
        if (dbAllReceivedInfo != null && isFree){
            for (Received r : dbAllReceivedInfo){
                if (dbAllPurchaseInvoiceInfo != null){
                    for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                        if (pi.getPurchase_invoice_number().equals(selectedPIItem)){
                            if (pi.getReceived_id() == r.getReceived_id()){
                                received_number = r.getReceived_number();
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        if (isFree && selectedPIItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedPIItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("purchase_invoice_table", "purchase_invoice_number", selectedPIItem);
                List<String> receivedNumberList = new ArrayList<>();
                receivedNumberList.add(received_number);
                int update_result = new JDBCTemplate().updateReceivedDataIntoDB(receivedNumberList, 0);
                if (delete_result > 0 && update_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedPIItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedPIItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void purchaseReturnDeleteOperation(){
        String selectedPRItem = (String)PRBox.getSelectedItem();

        List<Integer> purchaseInvoiceIdList = new ArrayList<>();
        
        if (dbAllPurchaseReturnInfo != null){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (pr.getPurchase_return_number().equals(selectedPRItem)){
                    int purchase_inovice_id = pr.getPurchase_invoice_id();
                    purchaseInvoiceIdList.add(purchase_inovice_id);
                }
            }
        }
        
        if (selectedPRItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedPRItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("purchase_return_table", "purchase_return_number", selectedPRItem);
                int update_result = new JDBCTemplate().updatePurchaseInvoiceDataIntoDB(purchaseInvoiceIdList, 0);
                if (delete_result > 0 && update_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedPRItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedPRItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void salesOrderDeleteOperation(){
        String selectedSOItem = (String)SOBox.getSelectedItem();

        boolean isFree = true;
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getSales_order_number().equals(selectedSOItem)){
                   if (so.getBe_cleared() == 1){
                       isFree = false;
                       break;
                   }
                }
            }
        }
        
        if (dbAllSalesInvoiceInfo != null && isFree){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (dbAllSalesOrderInfo != null){
                    for (SalesOrder so : dbAllSalesOrderInfo){
                        if (so.getSales_order_number().equals(selectedSOItem)){
                            if (so.getSales_order_id() == si.getSales_order_id()){
                                isFree = false;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (isFree && selectedSOItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedSOItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("sales_order_table", "sales_order_number", selectedSOItem);
                if (delete_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedSOItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedSOItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void salesInvoiceDeleteOperation(){
        String selectedSIItem = (String)SIBox.getSelectedItem();

        boolean isFree = true;
        if (dbAllSalesInvoiceInfo != null){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (si.getSales_invoice_number().equals(selectedSIItem)){
                   if (si.getBe_cleared() == 1){
                       isFree = false;
                       break;
                   }
                }
            }
        }
        
        if (dbAllSalesReturnInfo != null && isFree){
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (dbAllSalesInvoiceInfo != null){
                    for (SalesInvoice si : dbAllSalesInvoiceInfo){
                        if (sr.getSales_invoice_id() == si.getSales_invoice_id()){
                            isFree = false;
                            break;
                        }
                    }
                }
            }
        }
        
        List<String> salesOrderNumberList = new ArrayList<>();
        String sales_order_number = null;
        if(dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if(dbAllSalesInvoiceInfo != null){
                    for (SalesInvoice si : dbAllSalesInvoiceInfo){
                        if (si.getSales_invoice_number().equals(selectedSIItem)){
                            if (si.getSales_order_id() == so.getSales_order_id()){
                                sales_order_number = so.getSales_order_number();
                                salesOrderNumberList.add(sales_order_number);
                            }
                        }
                    }
                }
            }
        }
        
        

        if (isFree && selectedSIItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedSIItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("sales_invoice_table", "sales_invoice_number", selectedSIItem);
                int update_result = new JDBCTemplate().updateSalesOrderDataIntoDB(salesOrderNumberList, 0);
                if (delete_result > 0 && update_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedSIItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedSIItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    private void salesReturnDeleteOperation(){
        String selectedSRItem = (String)SRBox.getSelectedItem();

        List<Integer> salesInvoiceIdList = new ArrayList<>();
        
        if (dbAllSalesReturnInfo != null){
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (sr.getSales_return_number().equals(selectedSRItem)){
                    int sales_inovice_id = sr.getSales_invoice_id();
                    salesInvoiceIdList.add(sales_inovice_id);
                }
            }
        }

        if (selectedSRItem != null){
            int result_dialog = JOptionPane.showConfirmDialog(this, "Do you want delete item :" + selectedSRItem + "?", "Delete Notice", 0);
            if (result_dialog == 0){
                int delete_result = new JDBCTemplate().deleteItemFromDB("sales_return_table", "sales_return_number", selectedSRItem);
                int update_result = new JDBCTemplate().updateSalesInvoiceDataIntoDB(salesInvoiceIdList, 0);
                if (delete_result > 0 && update_result > 0){
                    JOptionPane.showMessageDialog(this, "Delete Successfully!!");
                }
            }
        }else if (selectedSRItem != null){
           JOptionPane.showMessageDialog(this, "Itme: " + selectedSRItem + " is using, cannot be deleted!"); 
        }else {
            JOptionPane.showMessageDialog(this, "Nothing can be deleted!"); 
        }
    }
    
    public void setAllComBoxItem(){
        setPOComBoxItem();
        setPIComBoxItem();
        setPRComBoxItem();
        setSOComBoxItem();
        setSIComBoxItem();
        setSRComBoxItem();
        setReceivedComBoxItem();
        setInitialInventoryComBoxItem();
        setInventoryComBoxItem();
        setCustomerComBoxItem();
        setSupplierComBoxItem();
        setEmployeeComBoxItem();
        setCategoriesComBoxItem();
        setTypeComBoxItem();
        setLocationComBoxItem();
    }
    
    private void setPOComBoxItem(){
        String record = null;
        
        POBox.removeAllItems();

        if (dbAllPurchaseOrderInfo != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (record == null || !record.equals(po.getPurchase_order_number())){
                    POBox.addItem(po.getPurchase_order_number());
                    record = po.getPurchase_order_number();
                }
            }
        }
    }
    
    private void setPIComBoxItem(){
        
        PIBox.removeAllItems();
        
        String record = null;
        if (dbAllPurchaseInvoiceInfo != null){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (record == null || !record.equals(pi.getPurchase_invoice_number())){
                    PIBox.addItem(pi.getPurchase_invoice_number());
                    record = pi.getPurchase_invoice_number();
                }
            }
        }
    }
    
    private void setPRComBoxItem(){
        
        PRBox.removeAllItems();
        
        String record = null;
        if (dbAllPurchaseReturnInfo != null){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (record == null || !record.equals(pr.getPurchase_return_number())){
                    PRBox.addItem(pr.getPurchase_return_number());
                    record = pr.getPurchase_return_number();
                }
            }
        }
    }
    
    private void setSOComBoxItem(){
        
        SOBox.removeAllItems();
        
        String record = null;
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (record == null || !record.equals(so.getSales_order_number())){
                    SOBox.addItem(so.getSales_order_number());
                    record = so.getSales_order_number();
                }
            }
        }
    }
    
    private void setSIComBoxItem(){
        String record = null;
        
        SIBox.removeAllItems();
        
        if (dbAllSalesInvoiceInfo != null){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (record == null || !record.equals(si.getSales_invoice_number())){
                    SIBox.addItem(si.getSales_invoice_number());
                    record = si.getSales_invoice_number();
                }
            }
        }
    }
    
    private void setSRComBoxItem(){
        
        SRBox.removeAllItems();
        
        String record = null;
        if (dbAllSalesReturnInfo != null){
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (record == null || !record.equals(sr.getSales_return_number())){
                    SRBox.addItem(sr.getSales_return_number());
                    record = sr.getSales_return_number();
                }
            }
        }
    }
    
    private void setReceivedComBoxItem(){
        
        receivedBox.removeAllItems();
        
        String record = null;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (record == null || !record.equals(r.getReceived_number())){
                    receivedBox.addItem(r.getReceived_number());
                    record = r.getReceived_number();
                }
            }
        }
    }
    
    private void setInitialInventoryComBoxItem(){
        
        initialBox.removeAllItems();

        if (dbAllInitialInventoryInfo != null){
            for (InitialInventory il : dbAllInitialInventoryInfo){
                for (Inventory i : dbAllInventoryInfo){
                    if (i.getInventory_id() == il.getInventory_id()){
                        initialBox.addItem(i.getInventory_number());
                    }
                }
            }
        }
    }
    
    private void setInventoryComBoxItem(){
        
        inventoryBox.removeAllItems();

        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                inventoryBox.addItem(i.getInventory_number());
            }
        }
    }
    
    private void setCustomerComBoxItem(){
        
        customerBox.removeAllItems();
        
        String record = null;
        if (dbAllCustomerInfo != null){
            for (Customer c : dbAllCustomerInfo){
                customerBox.addItem(c.getCustomer_number());
            }
        }
    }
    
    private void setSupplierComBoxItem(){
        
        supplierBox.removeAllItems();
        
        String record = null;
        if (dbAllSupplierInfo != null){
            for (Supplier s : dbAllSupplierInfo){
                supplierBox.addItem(s.getSupplier_number());
            }
        }
    }
    
    private void setEmployeeComBoxItem(){
        
        employeeBox.removeAllItems();
        
        String record = null;
        if (dbAllEmployeeInfo != null){
            for (Employee e : dbAllEmployeeInfo){
                employeeBox.addItem(e.getStaff_number());
            }
        }
    }
    
    private void setCategoriesComBoxItem(){
        
        categoriesBox.removeAllItems();
        
        String record = null;
        if (dbAllCategoriesInfo != null){
            for (Categories c : dbAllCategoriesInfo){
                categoriesBox.addItem(c.getName());
            }
        }
    }
    
    private void setTypeComBoxItem(){
        
        typeBox.removeAllItems();
        
        String record = null;
        if (dbAllTypeInfo != null){
            for (Type t : dbAllTypeInfo){
                typeBox.addItem(t.getName());
            }
        }
    }
    
    private void setLocationComBoxItem(){
        
        locationBox.removeAllItems();
        
        String record = null;
        if (dbAllInventoryLocationInfo != null){
            for (InventoryLocation il : dbAllInventoryLocationInfo){
                locationBox.addItem(il.getName());
            }
        }
    }
}
