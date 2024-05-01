/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import LengthRestricted.LengthRestrictedDocument;
import PanelView.InventoryPanel;
import PsnClass.AvailableInventory;
import PsnClass.Customer;
import PsnClass.Employee;
import PsnClass.InitialInventory;
import PsnClass.Inventory;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.SalesOrder;
import Table.JTableBg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author user
 */
public class NewSalesOrderDialog extends JDialog{
    private JPanel newSalseOrderPanel = null;
    private JPanel inventory_panel = null;
    private JTextField orderID_text = null;
    private JTextField orderQuantity_text = null;
    private JTextField orderUnit_text = null;
    private JDatePickerImpl orderDatePicker = null;
    private JDatePickerImpl ordeDateDeliveryPicker = null;
    private JComboBox orderEmployee_select = null;
    private JComboBox orderCustomer_select = null;
    private JComboBox orderInventory_select = null;
    private DefaultTableModel tableModel = null;
    private JTableBg invetoryTable = null;
    private JButton minusInventoryButton = null;
    
    List<Employee> dbAllEmployeeInfo = null;
    List<Customer> dbAllCustomerInfo = null;
    List<Inventory> dbAllInventoryInfo = null;
    List<SalesOrder> dbAllSalesOrderInfo = null;
    List<Inventory> inventorysInfo = new ArrayList<>();
    
    List<InitialInventory> dbAllInitialInventorysInfo = null;
    List<Received> dbAllReceivedInfoCleared = null;
    List<PurchaseReturn> dbAllPurchaseReturnInfo = null;
    List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    List<AvailableInventory> aiList = null;

    // if index !=-1 and press the "+" then will do whole process
    // if index !=-1 and press the "-" then will not process get data from the compenents
    int index = -1;
   
    public NewSalesOrderDialog (JFrame frame, String title){
        super(frame, title);
        
        newSalseOrderPanel = new JPanel();
        newSalseOrderPanel.setBorder(new EmptyBorder(10,10,10,10));
        newSalseOrderPanel.setLayout(new BorderLayout());
        this.setContentPane(newSalseOrderPanel);
        
        getDBAllSalseOrderInfo();
        setOrderDetailPanel();
        setOrderInventoryPanel();
        setOrderSaveButtonPanel();
    }
    
    private void getDBAllEmployeeInfo(){
        dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllCustomerInfo(){
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("Inventory_table", "Inventory_id");
    }
    
    private void getDBAllSalseOrderInfo(){
        dbAllSalesOrderInfo = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", 
                "sales_order_number");
    }
    
    private void getDBAllInitialInventoryInfo(){
        dbAllInitialInventorysInfo = new JDBCTemplate().getDBAllInitialInventoryInfo("initial_inventory_table", 
                "initial_inventory_id");
    }
     
    private void getDBAllReceivedInfo(){
        dbAllReceivedInfoCleared = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number", 1);
    }
      
    private void getDBAllPurchaseReturnInfo(){
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", 
                "purchase_return_number");
    }
     
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", 
                "purchase_order_number", 1);
    }
    
    private void setOrderDetailPanel(){
        JPanel detailPanel = new JPanel();
        detailPanel.setBorder(new LineBorder(Color.GRAY));
        detailPanel.setLayout(new GridBagLayout());
        newSalseOrderPanel.add(detailPanel, BorderLayout.WEST);
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel orderID = new JLabel(" Order ID*: ");
        orderID_text = new JTextField(20);
        orderID_text.setDocument(new LengthRestrictedDocument(20));

        JLabel orderDate = new JLabel(" Order Date*: ");
        orderDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel orderCustomerLable = new JLabel(" Customer Name*: ");
        orderCustomer_select = new JComboBox();
        setCustomerComboxItem();
        JButton addCustomerButton = new JButton("Add");
        
        JLabel orderDeliveryDateRecived = new JLabel(" Delivery Date*: ");
        ordeDateDeliveryPicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel orderEmployeeLable = new JLabel(" Order Employee*: ");
        orderEmployee_select = new JComboBox();
        setEmployeeComboxItem();
        JButton addEmployeeButton = new JButton("Add");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailPanel.add(orderID, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailPanel.add(orderID_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailPanel.add(orderDate, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        detailPanel.add(orderDatePicker, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        detailPanel.add(orderCustomerLable, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        detailPanel.add(orderCustomer_select, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        detailPanel.add(addCustomerButton, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        detailPanel.add(orderDeliveryDateRecived, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        detailPanel.add(ordeDateDeliveryPicker, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 8;
        detailPanel.add(orderEmployeeLable, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        detailPanel.add(orderEmployee_select, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        detailPanel.add(addEmployeeButton, gbc);
        
        addEmployeeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddEmployeeDialog();
            }
        });
        
        addCustomerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               callAddCustomerDialog();
            }
        });
    }
    
    private void callAddEmployeeDialog(){
        NewEmployeeDialog newEmployee = new NewEmployeeDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Employee");
        newEmployee.setBounds(500, 40, 800, 800);
        newEmployee.setVisible(true);
        
        newEmployee.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                setEmployeeComboxItem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                setEmployeeComboxItem();
            }
            
        });
    }
    
    private void callAddCustomerDialog(){
        NewCustomerDialog newCustomer = new NewCustomerDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Customer");
        newCustomer.setBounds(500, 40, 800, 800);
        newCustomer.setVisible(true);
        
        newCustomer.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                setCustomerComboxItem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                setCustomerComboxItem();
            }
            
        });
    }
    
    private JDatePanelImpl setDatePickerPanel(){
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String format = dateformat.format(date);
        String[] split = format.split("-");
        int day = Integer.parseInt(split[0]);
        int mth = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(year, mth-1, day);
        return new JDatePanelImpl(dateModel);
    }
    
    private void setEmployeeComboxItem(){
        getDBAllEmployeeInfo();
        
        orderEmployee_select.removeAllItems();
        if (dbAllEmployeeInfo != null){
            for (Employee e : dbAllEmployeeInfo){
                orderEmployee_select.addItem(e.getName());
            }
        }
    }
    
    private void setCustomerComboxItem(){
        getDBAllCustomerInfo();
        
        orderCustomer_select.removeAllItems();
        if (dbAllCustomerInfo != null){
            for (Customer c: dbAllCustomerInfo){
                orderCustomer_select.addItem(c.getCompany_name());
            }
        }
    }
    
    private void setOrderInventoryPanel(){
        inventory_panel = new JPanel();
        inventory_panel.setBorder(new LineBorder(Color.GRAY));
        inventory_panel.setLayout(new BorderLayout());
        newSalseOrderPanel.add(inventory_panel, BorderLayout.CENTER);
        
        setSelectOrderInventoryPanel();
        setListOrderInventoryPanel();
        setEditButtonPanel();
    }
    
    private void setSelectOrderInventoryPanel(){
        JPanel selectOrderInventoryPanel = new JPanel();
        selectOrderInventoryPanel.setBorder(new LineBorder(Color.GRAY));
        selectOrderInventoryPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inventory_panel.add(selectOrderInventoryPanel, BorderLayout.NORTH);
        
        JLabel orderInventoryLabel = new JLabel(" Inventory*: ");
        orderInventory_select = new JComboBox();
        setInventoryComboxItem();
        
        JButton addInventoryButton = new JButton("Add");
        
        JLabel orderQuantityLabel = new JLabel("Quantity*: ");
        orderQuantity_text = new JTextField(7);
        orderQuantity_text.setDocument(new LengthRestrictedDocument(7));
        
        JLabel orderUnitLabel = new JLabel("Unit*: ");
        orderUnit_text = new JTextField(10);
        orderUnit_text.setDocument(new LengthRestrictedDocument(10));
        
        JButton plusInventoryButton = new JButton("+");
        minusInventoryButton = new JButton("-");
        minusInventoryButton.setEnabled(false);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderInventoryLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderInventory_select, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(addInventoryButton, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderQuantityLabel, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderQuantity_text, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 5;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderUnitLabel, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(orderUnit_text, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 7;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(plusInventoryButton,gbc);
        gbc.insets = new Insets(0,2,0,0);
        gbc.gridx = 8;
        gbc.gridy = 0;
        selectOrderInventoryPanel.add(minusInventoryButton, gbc);
        
        if(orderInventory_select.getSelectedIndex()!= -1){
            int selectedIndex = orderInventory_select.getSelectedIndex();
            setInventoryUnit(selectedIndex);
        }

        orderInventory_select.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orderInventory_select.getSelectedIndex()!= -1){
                    int selectedIndex = orderInventory_select.getSelectedIndex();
                    setInventoryUnit(selectedIndex);
                }
            }
        });
        
        addInventoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newInventoryFrame = new JFrame("New Inventory Frame");
                newInventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newInventoryFrame.setBounds(300, 40, 1200, 800);
                newInventoryFrame.setVisible(true);
                
                InventoryPanel ip = new InventoryPanel();
                newInventoryFrame.add(ip);
                
                newInventoryFrame.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setInventoryComboxItem();
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                        setInventoryComboxItem();
                    }
                
                });
                
              }
        });
        
        plusInventoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!orderQuantity_text.getText().equals("") && !orderUnit_text.getText().equals("")){
                    if (checkValidInputData()){
                        if (checkAvailableInventoryQuantity()){
                            if (checkInventoryMatchUnit()){
                                //Because the removeInventoryFromList() function, this if justify must put at here, 
                                //I base on the index != -1, then the getComponentsData() will not process
                                //when index == -1, then the getComponentsData() will process
                                 if (index != -1){
                                    inventorysInfo.remove(index);
                                    index = -1;
                                 }
                                updateNewInventoryRow();
                                minusInventoryButton.setEnabled(false);
                            }else {
                                JOptionPane.showMessageDialog(inventory_panel, "The unit is not match with the inventory!", "Notice", 1);
                            }

                        }else {
                            double availableQuantity = 0;
                            for (AvailableInventory ai : aiList){
                                if (ai.getInventory_name().equals(orderInventory_select.getSelectedItem())){
                                    availableQuantity = ai.getQuantity();
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(inventory_panel, "No that many available inventory! \n "
                                    + "Current available: " + availableQuantity, "Notice", 1);
                        }
                    }else {
                        JOptionPane.showMessageDialog(inventory_panel, "Data type of quantity is invalid!", "Notice", 1);
                    }
                }else {
                    JOptionPane.showMessageDialog(inventory_panel, "* Empty value is not acceptable!", "Notice", 1);
                }
            }
            
        });
        
        minusInventoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                removeInventoryFromList();
                minusInventoryButton.setEnabled(false);
            }
        });
    }
    
    private boolean checkInventoryMatchUnit(){
        int selectedIndex = orderInventory_select.getSelectedIndex();
        String unit = dbAllInventoryInfo.get(selectedIndex).getUnit();
        if(unit.equals(orderUnit_text.getText())){
            return true;
        }
        return false;
    }
    
    private void setInventoryUnit(int selectedIndex){
        String unit = dbAllInventoryInfo.get(selectedIndex).getUnit();
        
        orderUnit_text.setText(unit);
    }
    
    private void setInventoryComboxItem(){
        getAvailableInventory();
        
        orderInventory_select.removeAllItems();
        if (aiList != null){
            for (AvailableInventory ai : aiList){
                if (ai.getQuantity() > 0){
                    orderInventory_select.addItem(ai.getInventory_name());
                }
            }
        }
        
//        orderInventory_select.removeAllItems();
//        if (dbAllInventoryInfo != null){
//            for (Inventory i : dbAllInventoryInfo){
//                orderInventory_select.addItem(i.getName());
//            }
//        }
    }
    
    private void setListOrderInventoryPanel(){
       String columns[] = {"No", "Inventory Name", "Quantity", "Unit"};
       
        Object[][] inventoryinfo = getInventoryinfo();
        
        final Class[] columnClass = new Class[]{
            int.class, String.class, Double.class, String.class};
        
        tableModel = new DefaultTableModel(inventoryinfo, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        invetoryTable = new JTableBg(tableModel);
        invetoryTable.setCellSelectionEnabled(true);
        invetoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = invetoryTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(invetoryTable);
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        inventory_panel.add(sp, BorderLayout.CENTER);
    }
    
    private Object[][] getInventoryinfo(){
        insertInventoryInfoToList();
        
        if (inventorysInfo != null){
            Object[][] data = new Object[inventorysInfo.size()][4];
            for (int i=0; i<inventorysInfo.size(); i++){
                data[i][0] = i+1;
                data[i][1] = inventorysInfo.get(i).getName();
                data[i][2] = inventorysInfo.get(i).getQuantity();
                data[i][3] = inventorysInfo.get(i).getUnit();
            }
            return data;
        }
        return null;
    }
    
    private void insertInventoryInfoToList(){
        Inventory componentsData = getComponentsData();
        if (componentsData != null){
            inventorysInfo.add(componentsData);
        }
    }
    
    private Inventory getComponentsData(){
        Inventory i = new Inventory();
        if (index == -1){
            if (!orderQuantity_text.getText().equals("")){
                i.setName((String)orderInventory_select.getSelectedItem());
                i.setQuantity(Double.parseDouble(orderQuantity_text.getText()));
                i.setUnit(orderUnit_text.getText());
                
                int recordCount = 0;
                if (!inventorysInfo.isEmpty()){
                    for (Inventory inv : inventorysInfo){
                        if (inv.getName().equals(i.getName())){
                            JOptionPane.showMessageDialog(inventory_panel, "This item has been added at the bottom list!", "Notice", 1);
                            return null;
                        }else {
                            recordCount++;
                        }
                        
                        if (recordCount == inventorysInfo.size()){
                            return i;
                        }
                    }
                }else {
                    return i;
                }
            }
        }
        return null;
    }

    private void updateNewInventoryRow(){
        tableModel.setRowCount(0);
        Object[][] newInventoryInfoRow = getInventoryinfo();
        
        for (int i=0; i<newInventoryInfoRow.length; i++){
            tableModel.addRow(newInventoryInfoRow[i]);
        }
    }
    
    private void setEditButtonPanel(){
        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setLayout(new FlowLayout());
        inventory_panel.add(editButtonPanel, BorderLayout.SOUTH);
        
        JButton editButton = new JButton("Edit");
        editButtonPanel.add(editButton);
        
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = invetoryTable.getSelectedRow();
                if (index != -1){
                    minusInventoryButton.setEnabled(true);
                    setEditTextField(index);
                }
            }
        });
    }
    
    private void setEditTextField(int index){
        this.index = index;
        
        orderInventory_select.setSelectedItem(inventorysInfo.get(index).getName());
        orderQuantity_text.setText(String.valueOf(inventorysInfo.get(index).getQuantity()));
        orderUnit_text.setText(inventorysInfo.get(index).getUnit());
    }
    
    private void removeInventoryFromList(){
        if (index != -1){
            inventorysInfo.remove(index);
        }
        
        updateNewInventoryRow();
        JOptionPane.showMessageDialog(inventory_panel, "Remove successfully!!", "Notice", 1);
        index = -1;
    }
    
    private void setOrderSaveButtonPanel(){
        JPanel orderSaveButtonpanel = new JPanel();
        orderSaveButtonpanel.setLayout(new FlowLayout());
        newSalseOrderPanel.add(orderSaveButtonpanel, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("SAVE");
        orderSaveButtonpanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNullComponents()){
                    JOptionPane.showMessageDialog(newSalseOrderPanel, "* Empty value is not acceptable", "Notice", 2);
                }else {
                    if (checkDuplicatePONumber()){
                        JOptionPane.showMessageDialog(newSalseOrderPanel, "* Duplicate order ID is not acceptable", "Notice", 2);
                    }else {
                        int result = JOptionPane.showConfirmDialog(newSalseOrderPanel, "Do you want save it?", "Saving Notice", 0);
                        if(result == 0){
                            List<SalesOrder> orderComponentData = getOrderComponentData();
                            int update_count = new JDBCTemplate().insertDataIntoDBSalesOrder(orderComponentData);
                            if (update_count > 0){
                                JOptionPane.showMessageDialog(newSalseOrderPanel, "Save successfully!!", "Notice", 1);
                            }
                            refreshComponent();
                        }
                    }
                    
                }
            }
        });
    }
    
    private boolean checkNullComponents(){
        if (orderID_text.getText().equals("")){
            return true;
        }
        
        if (orderDatePicker.getModel().getValue()== null){
            return true;
        }
        
        if (orderCustomer_select.getSelectedIndex() == -1){
            return true;
        }
        
        if (ordeDateDeliveryPicker.getModel().getValue() == null){
            return true;
        }
        
        if (orderEmployee_select.getSelectedIndex() == -1){
            return true;
        }
        
        if (inventorysInfo.isEmpty()){
            return true;
        }
        
        return false;
    }
    
    private boolean checkDuplicatePONumber(){
        String orderNumber = orderID_text.getText().toUpperCase();
        
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getSales_order_number().equals(orderNumber)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private List<SalesOrder> getOrderComponentData(){
        List<SalesOrder> soList = new ArrayList<>();
        
        for (Inventory i : inventorysInfo){
            SalesOrder so = new SalesOrder();
        
            so.setSales_order_number(orderID_text.getText());

            Date orderDate = (Date)orderDatePicker.getModel().getValue();
            so.setSales_order_date(orderDate);
            
            Object selectedCustomerItem = orderCustomer_select.getSelectedItem();
            for (Customer c : dbAllCustomerInfo){
                if (c.getCompany_name().equals(selectedCustomerItem)){
                    so.setCustomer_id(c.getCustomer_id());
                }
            }

            Date deliveryDate = (Date)ordeDateDeliveryPicker.getModel().getValue();
            so.setDelivery_date(deliveryDate);

            Object selectedEmployeeItem = orderEmployee_select.getSelectedItem();
            for (Employee e :dbAllEmployeeInfo){
                if (e.getName().equals(selectedEmployeeItem)){
                    so.setStaff_id(e.getStaff_id());
                }
            }
            
            for (Inventory dbi : dbAllInventoryInfo){
                if (i.getName().equals(dbi.getName())){
                    so.setInventory_id(dbi.getInventory_id());
                }
            }
            
            so.setQuantity(i.getQuantity());
            so.setUnit(i.getUnit());
            so.setBe_cleared(0);
            
            soList.add(so);
        }

        return soList;   
    }
    
    private void refreshComponent(){
        setInventoryComboxItem();
        
        orderID_text.setText("");
        inventorysInfo.removeAll(inventorysInfo);
        index = -2;
        updateNewInventoryRow();
        index = -1;
        
        orderQuantity_text.setText("");
    }
    
    private void getAvailableInventory(){
        getDBAllInitialInventoryInfo();
        getDBAllReceivedInfo();
        getDBAllPurchaseReturnInfo();
        getDBAllPurchaseOrderInfo();
        getDBAllInventoryInfo();
        getDBAllSalseOrderInfo();
        
        if (aiList == null){
            aiList = new ArrayList<>();
        }else {
            aiList.clear();
        }
        
        
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                AvailableInventory ai = new AvailableInventory();
                ai.setInventory_id(i.getInventory_id());
                ai.setInventory_name(i.getName());
                ai.setUnit(i.getUnit());
                ai.setSafety_quantity(i.getSafety_level());
                aiList.add(ai);
             }
        }
        
        if (dbAllInitialInventorysInfo != null){
            for (InitialInventory il : dbAllInitialInventorysInfo){
                for (AvailableInventory ai : aiList){
                    if (il.getInventory_id() == ai.getInventory_id()){
                        ai.setQuantity(il.getQuantity());
                        break;
                    }
                }
            }
        }
        
        if (dbAllReceivedInfoCleared != null){
            for (Received r : dbAllReceivedInfoCleared){
                for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                    if (r.getPurchase_order_id() == po.getPurchase_order_id()){
                        for (AvailableInventory ai : aiList){
                            if (ai.getInventory_id() == po.getInventory_id()){
                                double quantity = ai.getQuantity();
                                ai.setQuantity(quantity + po.getQuantity());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        
        if (dbAllPurchaseReturnInfo != null){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                for (AvailableInventory ai : aiList){
                    if (pr.getInventory_id() == ai.getInventory_id()){
                        double quantity = ai.getQuantity();
                        ai.setQuantity(quantity - pr.getQuantity());
                        break;
                    }
                }
            }
        }
        
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                for (AvailableInventory ai : aiList){
                   if (so.getInventory_id() == ai.getInventory_id()){
                        double quantity = ai.getQuantity();
                        ai.setQuantity(quantity - so.getQuantity());
                        break;
                    } 
                }
            }
        }
    }
    
    private boolean checkAvailableInventoryQuantity(){
        if (aiList != null){
            for (AvailableInventory ai : aiList){
                if (ai.getInventory_name().equals(orderInventory_select.getSelectedItem())){
                    if (Double.parseDouble(orderQuantity_text.getText()) > ai.getQuantity()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean checkValidInputData(){
        try{
            Double.parseDouble(orderQuantity_text.getText());
        }catch (Exception ex){
            return false;
        }
        
        return true;
    }
}
