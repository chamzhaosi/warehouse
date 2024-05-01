/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import Dialog.NewEmployeeDialog;
import Dialog.NewPurchaseOrderDialog;
import Dialog.NewSupplierDialog;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.InventoryLocation;
import PsnClass.PurchaseOrder;
import PsnClass.Received;
import PsnClass.Supplier;
import Table.JTableBg;
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
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ReceivedPanel extends JPanel{
    private JPanel receivedRootPanel = null;
    private JPanel showReceivingTabelPanel = null;
    private JPanel newReceivedDetailPanel = null;
    private JPanel selectedPODetailPanel = null;
    private JPanel addOtherDetailPanel = null;
    private JPanel listPOPanel = null;
    private JPanel saveButtonPanel = null;
    private JTextField receivedID_text = null;
    private JTextField receivedSupplier_text = null;
    private JTextField receivedInventory_text = null;
    private JTextField receivedQuantity_text = null;
    private JComboBox receivedSupplier_selecetBox = null;
    private JComboBox receivedPO_selecetBox = null;
    private JComboBox receivedEmployee_selecetBox = null;
    private DefaultTableModel tableModel = null;
    private JTable invetoryTable = null;
    private JDatePickerImpl receivedDatePicker = null;
    
    private List<Received> dbAllReceivedInfo = null;
    private List<Received> dbAllReceivedInfoNotCleared = null;
    private List<Employee> dbAllEmployeeInfo = null;
    private List<Supplier> dbAllSuplierInfo = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoNotCleared = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfo = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    private List<Inventory> dbAllInventoryInfo = null;
    private List<InventoryLocation> dbAllLocationInfo = null;

    private List<String> PONumberList = null;
    private List<Received> receivedList = null;
    private List<Received> receivedLsit_new = null;
    private List<Integer> POIDList = null;
    
    private List<JTextField> expiredDateList = new ArrayList<>();
    private List<JComboBox> locationList = new ArrayList<>();
    
    
    //this index to identify whether first create the selectedPODetailPanel
    //if 0 = first
    //if > 0 != first
    int index = 0;
    
    public ReceivedPanel (){
        receivedRootPanel = new JPanel();
        receivedRootPanel.setBorder(new EmptyBorder(5,5,0,5));
        receivedRootPanel.setLayout(new GridLayout(1,2));
        receivedRootPanel.setBackground(Color.WHITE);
        
        this.setLayout(new BorderLayout());
        this.add(receivedRootPanel, BorderLayout.CENTER);
        
        getDBAllReceivingInfo();
        getDBAllEmployeeInfo();
        getDBAllSupplierInfo();
        getDBAllPurchaseOrderInfo();
        getDBAllInventoryInfo();
        setShowReceivedTablePanel();
        setNewReceivedPanel();
        setSelectedPODetailPanel(index);
        setAddOtherDetailPanel();
        setSaveButtonPanel();
    }
    
    private void getDBAllReceivingInfo(){
        dbAllReceivedInfo = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number");
        dbAllReceivedInfoNotCleared = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number", 0);
    }
    
    private void getDBAllEmployeeInfo(){
       dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllSupplierInfo(){
        dbAllSuplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoNotCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 0);
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 1);
        dbAllPurchaseOrderInfo = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllLocationInfo(){
        dbAllLocationInfo = new JDBCTemplate().getDBAllLocationInfo("inventory_location_table", "name");
    }
    
    private void setShowReceivedTablePanel(){
        showReceivingTabelPanel = new JPanel();
        showReceivingTabelPanel.setLayout(new BorderLayout());
        showReceivingTabelPanel.setBorder(new LineBorder(Color.GRAY));
        receivedRootPanel.add(showReceivingTabelPanel);
        
        setReceivingTable();
    }
    
    private void setReceivingTable(){
        JLabel receivingLabel = new JLabel("<< Receiving >>", SwingUtilities.CENTER);
        receivingLabel.setFont(new Font("", Font.BOLD, 30));
        showReceivingTabelPanel.setBackground(new Color(235, 199, 120));
        showReceivingTabelPanel.add(receivingLabel, BorderLayout.NORTH);
        
        String columns[] = {"Received ID", "Received Date","Supplier Name", "Purchase Order ID", "Staff"};
        
        Object[][] receivingData = getReceivingData();
        
        final Class[] columnClass = new Class[]{String.class, Date.class, String.class, String.class, String.class};
        
        tableModel = new DefaultTableModel(receivingData, columns){
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
        sp.setBackground(new Color(240, 217, 168));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        showReceivingTabelPanel.add(sp, BorderLayout.CENTER);
    }
    
    private HashMap<String, String> getHashMapofReceivedPO(){
        HashMap<String, String> receivedPOMap = new HashMap<>();
        
        for (Received r : dbAllReceivedInfoNotCleared){
            for(PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                if (r.getPurchase_order_id() == po.getPurchase_order_id()){
                    receivedPOMap.put(po.getPurchase_order_number(), r.getReceived_number());
                }
            }
        }
        
        return receivedPOMap;
    }
    
    private int countRowReceivedData(){
        HashMap<String, String> countRow = new HashMap<>();
        
        for (Received r : dbAllReceivedInfoNotCleared){
            for(PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                if (r.getPurchase_order_id() == po.getPurchase_order_id()){
                    countRow.put(r.getReceived_number(), po.getPurchase_order_number());
                }
            }
        }
        return countRow.size();
    }
    
    private Object[][] getReceivingData(){
        if (dbAllReceivedInfoNotCleared != null){
            HashMap<String, String> hashMapofReceivedPO = getHashMapofReceivedPO();

            String receivedID = null;
            int index = 0;
            Object[][] data = new Object[countRowReceivedData()][5];
            for (Received r : dbAllReceivedInfoNotCleared){
                if(receivedID == null || !receivedID.equals(r.getReceived_number())){
                    data[index][0] = r.getReceived_number();
                    receivedID = r.getReceived_number();
                    
                    data[index][1] = r.getReceived_date();

                    if (dbAllSuplierInfo != null){
                        for (Supplier s : dbAllSuplierInfo){
                            if (s.getSupplier_id() == r.getSupplier_id()){
                                data[index][2] = s.getCompany_name();
                            }
                        }
                    }

                    if (dbAllPurchaseOrderInfo != null){
                        for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                            if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                                data[index][3] = "";
                                for (String s: hashMapofReceivedPO.keySet()){
                                    if (hashMapofReceivedPO.get(s).equals(r.getReceived_number())){
                                        data[index][3] += s + " ";
                                    }
                                }
                            }
                        }
                    }

                    if (dbAllEmployeeInfo != null){
                        for (Employee e : dbAllEmployeeInfo){
                            if (e.getStaff_id() == r.getStaff_id()){
                                data[index][4] = e.getName();
                            }
                        }
                    }
                    index++;
                }
            }
            return data;
        }
        return null;
    }
    
    private void updateReceived(){
        getDBAllReceivingInfo();
        
        tableModel.setRowCount(0);
        
        Object[][] updataNewRows = getReceivingData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                tableModel.addRow(updataNewRows[i]);
            }
        }
    }
    
    private void setNewReceivedPanel(){
        newReceivedDetailPanel = new JPanel();
        newReceivedDetailPanel.setLayout(new BorderLayout());
        
        JPanel newReceivedPanel = new JPanel();
        newReceivedPanel.setLayout(new GridBagLayout());
        newReceivedPanel.setBorder(new LineBorder(Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        newReceivedDetailPanel.add(newReceivedPanel, BorderLayout.NORTH);
        
        receivedRootPanel.add(newReceivedDetailPanel);
        
        JLabel receivedIDLable = new JLabel("Receiving ID* :");
        receivedID_text = new JTextField(20);
        receivedID_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel receivedDate = new JLabel("Receiving Date*: ");
        receivedDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel receivedSupplierLable = new JLabel("Supplier Name* :");
        receivedSupplier_selecetBox = new JComboBox();
        setSupplierComboxItem();
        
        JButton receivedSupplierAddButton = new JButton("Add");
        
        JLabel receivedPOLable = new JLabel("Purchase Order* :");
        receivedPO_selecetBox = new JComboBox();
        setPOComboxItem();
        
        JButton receivedPOAddButton = new JButton("Add");
        
        JLabel receivedEmployeeLable = new JLabel("Employee Name* :");
        receivedEmployee_selecetBox = new JComboBox();
        setEmployeeComboxItem();
        
        JButton receivedEmployeeAddButton = new JButton("Add");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        newReceivedPanel.add(receivedIDLable, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        newReceivedPanel.add(receivedID_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        newReceivedPanel.add(receivedDate, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        newReceivedPanel.add(receivedDatePicker, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        newReceivedPanel.add(receivedSupplierLable, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        newReceivedPanel.add(receivedSupplier_selecetBox, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        newReceivedPanel.add(receivedSupplierAddButton,gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        newReceivedPanel.add(receivedPOLable,gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        newReceivedPanel.add(receivedPO_selecetBox,gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        newReceivedPanel.add(receivedPOAddButton,gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        newReceivedPanel.add(receivedEmployeeLable,gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        newReceivedPanel.add(receivedEmployee_selecetBox,gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        newReceivedPanel.add(receivedEmployeeAddButton,gbc);
        
        receivedSupplierAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddNewSupplierDialog();
            }
        }); 
        
        receivedEmployeeAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddNewEmployeeDialog();
            }
        
        });
        
        receivedPOAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddNewPODialog();
            }
        
        });

        receivedPO_selecetBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPODetailPanel.removeAll();
                selectedPODetailPanel.revalidate();
                selectedPODetailPanel.repaint();
                locationList.removeAll(locationList);
                expiredDateList.removeAll(expiredDateList);
                
                index = 1;
                setSelectedPODetailPanel(index);
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
    
    private void callAddNewSupplierDialog(){
        NewSupplierDialog newSupplier = new NewSupplierDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Supplier");
        newSupplier.setBounds(500, 40, 800, 800);
        newSupplier.setVisible(true);
        
        newSupplier.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setSupplierComboxItem();
                    }
                });
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setSupplierComboxItem();
                    }
                });
            }
        });
    }
    
    private void callAddNewEmployeeDialog(){
        NewEmployeeDialog newEmployee = new NewEmployeeDialog((JFrame) SwingUtilities.windowForComponent(this),"Add New Employee");
        newEmployee.setBounds(500, 40, 800, 800);
        newEmployee.setVisible(true);
        
        newEmployee.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setEmployeeComboxItem();
                    }
                });
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setEmployeeComboxItem();
                    }
                });
            }
        });
    }
    
    private void callAddNewPODialog(){
        NewPurchaseOrderDialog newPurchaseOrderDialog = new NewPurchaseOrderDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Purchase Order");
        newPurchaseOrderDialog.setBounds(300, 280, 1200, 350);
        newPurchaseOrderDialog.setVisible(true);
        
        newPurchaseOrderDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setPOComboxItem();
                    }
                });
            }

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        setPOComboxItem();
                    }
                });
            }
        });
    }
    
    private void setSupplierComboxItem(){
        getDBAllSupplierInfo();
        
        if (receivedSupplier_selecetBox != null ){
            receivedSupplier_selecetBox.removeAllItems();
        }
        
        if (dbAllSuplierInfo != null){
            for (Supplier s : dbAllSuplierInfo){
                receivedSupplier_selecetBox.addItem(s.getCompany_name());
            }
        }
    }
    
    public void setPOComboxItem(){
        getDBAllPurchaseOrderInfo();
        
        String PO_ID = null;
        
        if (receivedPO_selecetBox != null){
            receivedPO_selecetBox.removeAllItems();
        }
        
        if (dbAllPurchaseOrderInfoNotCleared != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
                if (PO_ID == null || !PO_ID.equals(po.getPurchase_order_number())){
                    receivedPO_selecetBox.addItem(po.getPurchase_order_number());
                    PO_ID = po.getPurchase_order_number();
                }
            }
        }
    }
    
    private void setEmployeeComboxItem(){
        getDBAllEmployeeInfo();
        
        if (receivedEmployee_selecetBox != null){
            receivedEmployee_selecetBox.removeAllItems();
        }
        
        if(dbAllEmployeeInfo != null){
            for (Employee e : dbAllEmployeeInfo){
                receivedEmployee_selecetBox.addItem(e.getName());
            }
        }
    }
    
    private void setSelectedPODetailPanel(int index){
        if (index == 0){
            selectedPODetailPanel = new JPanel();
        }
        selectedPODetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        newReceivedDetailPanel.add(selectedPODetailPanel, BorderLayout.CENTER);
        
        JLabel InventoryLabel = new JLabel("Inventory* ");

        JLabel QuantityLabel = new JLabel("Quantity* ");
        
        JLabel UnitLabel = new JLabel(" Unit* ");
        
        JLabel ExpiredDateLable = new JLabel("Exipired Date (dd-MM-yyyy)");
        
        JLabel LocationLable = new JLabel("Location");
        
        JButton addOtherDetialButton = new JButton("+");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        selectedPODetailPanel.add(InventoryLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        selectedPODetailPanel.add(QuantityLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        selectedPODetailPanel.add(UnitLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        selectedPODetailPanel.add(ExpiredDateLable, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        selectedPODetailPanel.add(LocationLable, gbc);

        Object selectedItem = receivedPO_selecetBox.getSelectedItem();
        
        List<PurchaseOrder> poList = new ArrayList<>();
        
        if (dbAllPurchaseOrderInfoNotCleared != null){
                for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
                if (selectedItem != null && selectedItem.equals(po.getPurchase_order_number())){
                    poList.add(po);
                }
            }
        }
        
        int i;
        for (i = 0; i<poList.size(); i++){
            JTextField inventory_text = new JTextField();
            JTextField quantity_text = new JTextField();
            JTextField unit_text = new JTextField();
            JTextField expiredDate_text = new JTextField();
            expiredDate_text.setText("00-00-0000");
            expiredDateList.add(expiredDate_text);
            
            JComboBox receivedLocation_selecetBox = new JComboBox();
            locationList.add(receivedLocation_selecetBox);
            setLocationComboxItem();
            JButton addLocationButton = new JButton("Add");

            if (dbAllInventoryInfo != null){
                for (Inventory inv : dbAllInventoryInfo){
                    if(poList.get(i).getInventory_id() == inv.getInventory_id()){
                        inventory_text.setText(inv.getName());
                        inventory_text.setEditable(false);
                    }
                }
            }

            quantity_text.setText(String.valueOf(poList.get(i).getQuantity()));
            quantity_text.setEditable(false);
            unit_text.setText(poList.get(i).getUnit());
            unit_text.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(inventory_text, gbc);
            gbc.gridx = 1;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(quantity_text, gbc);
            gbc.gridx = 2;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(unit_text, gbc);
            gbc.gridx = 3;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(expiredDate_text, gbc);
            gbc.gridx = 4;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(receivedLocation_selecetBox, gbc);
            gbc.gridx = 5;
            gbc.gridy = i+1;
            selectedPODetailPanel.add(addLocationButton, gbc);
            
            addLocationButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setAddLocationDialog();
                    setLocationComboxItem();
                }
            });
        }
    }
    
    private void setAddLocationDialog(){
        String showInputDialog = JOptionPane.showInputDialog(this, "Please enter a new location", "Enter a new location", 1);
        if (showInputDialog != null){
            showInputDialog = showInputDialog.toUpperCase();
        }
        
        boolean check = false;
        if (dbAllLocationInfo != null){
            for (InventoryLocation il : dbAllLocationInfo){
                if (showInputDialog != null){
                    if (showInputDialog.equals(il.getName())){
                        JOptionPane.showMessageDialog(this, "Item already exist", "Notice", 1);
                        check= true;
                        break;
                    }
                }else{
                    check= true;
                }
            }
        }
        
        if (!check){
            int update_count = new JDBCTemplate().insertLocationDataIntoDB(showInputDialog);
        
            if (update_count > 0){
                JOptionPane.showMessageDialog(this, "Update sucessfully", "Notice", 1);
            }   
        }
    }
    
    private void setLocationComboxItem(){
        getDBAllLocationInfo();

        for (JComboBox cb : locationList){
            if (cb != null){
                cb.removeAllItems();
            }

            if (dbAllLocationInfo != null){
                if (dbAllLocationInfo != null){
                    for (InventoryLocation il : dbAllLocationInfo){
                        cb.addItem(il.getName());
                    }
                }
            }
        }
    }
    
    private void setAddOtherDetailPanel (){
        addOtherDetailPanel = new JPanel();
        addOtherDetailPanel.setLayout(new GridLayout(2,1));
        
        newReceivedDetailPanel.add(addOtherDetailPanel, BorderLayout.SOUTH);
        setAddSubButtonPanel();
    }
    
    private void setAddSubButtonPanel(){
        JPanel addSubButtonPanel = new JPanel();
        addSubButtonPanel.setBorder(new LineBorder(Color.gray));
        addSubButtonPanel.setLayout(new FlowLayout());
        
        addOtherDetailPanel.add(addSubButtonPanel);
        
        JButton addButton = new JButton ("+");
        addSubButtonPanel.add(addButton);
        
        JButton removeButton = new JButton ("-");
        addSubButtonPanel.add(removeButton);
        removeButton.setEnabled(false);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Received> poDetailDataList = getPODetailData();
                if (poDetailDataList != null){
                    setUnenableComponents();
                    removeButton.setEnabled(true);
                    removelistPOPanelComponents();
                    setListPOPanel(poDetailDataList);
                } 
            }
        });
        
        removeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                removePODetail();
                if (receivedList == null || receivedList.isEmpty()){
                    removeButton.setEnabled(false);
                }
            }
        
        });
    }
    
    private void setUnenableComponents(){
        receivedID_text.setEnabled(false);
        receivedDatePicker.getComponent(1).setEnabled(false);
        receivedSupplier_selecetBox.setEnabled(false);
        receivedEmployee_selecetBox.setEnabled(false);
    }

    private List<Received> getPODetailData(){
        if (receivedList == null){
            receivedList = new ArrayList<>();
        }
        if (checkDuplicateReceivedID()){
            JOptionPane.showMessageDialog(selectedPODetailPanel, "Duplicate Received ID is not acceptable!", "Warning", 0);
        }else if (checkNull_InvalidComponentsData()){
            JOptionPane.showMessageDialog(selectedPODetailPanel, "* Empty value is not acceptable \n "
                    + "or date format not valid!!", "Warning", 0);
            }else {
                List<Integer> recordPOID = new ArrayList<>();
                Object selectedPONumber = receivedPO_selecetBox.getSelectedItem();
                for (PurchaseOrder po: dbAllPurchaseOrderInfoNotCleared){
                    if (selectedPONumber.equals(po.getPurchase_order_number())){
                       recordPOID.add(po.getPurchase_order_id());
                    }  
                }

                for (JTextField tf : expiredDateList){
                    if (tf.getText().equals("")){
                        tf.setText("00-00-0000");
                    }
                }

                for (int i = 0; i<locationList.size(); i++){
                    Received recivedDetailData = new Received();
                    recivedDetailData.setReceived_number(receivedID_text.getText());

                    Date receivedDate = (Date)receivedDatePicker.getModel().getValue();
                    recivedDetailData.setReceived_date(receivedDate);

                    Object selectedSupplierName = receivedSupplier_selecetBox.getSelectedItem();
                    for (Supplier s : dbAllSuplierInfo){
                        if (selectedSupplierName.equals(s.getCompany_name())){
                            recivedDetailData.setSupplier_id(s.getSupplier_id());
                        }
                    }

                    recivedDetailData.setPurchase_order_id(recordPOID.get(i));

                    Object selectedEmployeeName = receivedEmployee_selecetBox.getSelectedItem();
                    for (Employee e : dbAllEmployeeInfo){
                        if (selectedEmployeeName.equals(e.getName())){
                            recivedDetailData.setStaff_id(e.getStaff_id());
                        }
                    }

                    String expiredDate = expiredDateList.get(i).getText();
                    if (!expiredDate.equals("00-00-0000")){
                        try {
                            recivedDetailData.setExpired_date(new SimpleDateFormat("dd-MM-yyyy").parse(expiredDate));
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(selectedPODetailPanel, ex.getMessage(),"Warning",0);
                        }
                    }else {
                            recivedDetailData.setExpired_date(new Date(00,00,000));
                    }

                    Object selectedLocationName = locationList.get(i).getSelectedItem();
                    for (InventoryLocation il: dbAllLocationInfo){
                        if (il.getName().equals(selectedLocationName)){
                            recivedDetailData.setInventory_location_id(il.getInventory_location_id());
                        }
                    }
                    recivedDetailData.setBe_cleared(0);
                            
                      receivedList.add(recivedDetailData);
                }

                int check = 0;
                if (receivedLsit_new == null){
                    receivedLsit_new = new ArrayList<>();
                }
                for (int i = 0; i<receivedList.size(); i++){
                    check = -1;
                    for (int j = i+1; j<receivedList.size(); j++){
                        if (receivedList.get(i).getPurchase_order_id() == receivedList.get(j).getPurchase_order_id()){
                            receivedLsit_new.add(receivedList.get(j));
                            receivedList.remove(receivedList.get(j));
                            check = 1; 
                            break;
                        }
                    }
                    if (check == -1){
                        receivedLsit_new.add(receivedList.get(i));
                    }
                }
                receivedList.removeAll(receivedList);
                receivedList.addAll(receivedLsit_new);
                receivedLsit_new.removeAll(receivedLsit_new);

                return receivedList;
            }
        return null;
    }
    
    private boolean checkNull_InvalidComponentsData(){
        if (receivedID_text.getText().equals("")){
            return true;
        }
        
        if (receivedDatePicker.getModel().getValue() == null){
            return true;
        }
        
        if (receivedSupplier_selecetBox.getSelectedIndex() == -1){
            return true;
        }
        
        if (receivedPO_selecetBox.getSelectedIndex() == -1){
            return true;
        }
        
        if (receivedEmployee_selecetBox.getSelectedIndex() == -1){
            return true;
        }
        
       for (JComboBox cb: locationList){
           if (cb.getSelectedIndex() == -1){
               return true;
           }
       }
       
       for(JTextField tf : expiredDateList){
           if (!tf.getText().equals("") && !tf.getText().equals("00-00-0000")){
               String textDate = tf.getText();
               String[] split_date = textDate.split("-");
               
               if (split_date.length == 3){
                   try {
                        int day = Integer.parseInt(split_date[0]);
                        int mth = Integer.parseInt(split_date[1]);
                        int year = Integer.parseInt(split_date[2]);
                        
                        if (day < 0 || day > 32 || mth < 0 || mth > 12){
                            return true;
                        }else if (year % 4 == 0 && mth == 2 && day > 29){
                            return true;
                        }else if (year % 4 != 0 && mth == 2 && day > 28){
                            return true;
                        }else if (year > 2100 || year < 2000){
                            return true;
                        }
                        
                   }catch (NumberFormatException ex){
                       return false;
                   }
                   
                       
               }else{
                   return true;
               }
           }else {
               return false;
           }
       }
       
       return false;
    }
    
    private boolean checkDuplicateReceivedID(){
        if (!receivedID_text.getText().equals("")){
            if (dbAllReceivedInfo != null){
                String receivedID = receivedID_text.getText().toUpperCase();
                for (Received r : dbAllReceivedInfo){
                    if (receivedID.equals(r.getReceived_number())){
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private void setListPOPanel(List<Received> receivedList){
        if (listPOPanel == null){
            listPOPanel = new JPanel();
        }
        listPOPanel.setBorder(new LineBorder(Color.gray));
        listPOPanel.setLayout(new FlowLayout());
        
        addOtherDetailPanel.add(listPOPanel);
        
        if (POIDList == null){
            POIDList = new ArrayList<>();
        }else {
            POIDList.removeAll(POIDList);
        }
        
        if (receivedList != null){
            for (Received r : receivedList){
                POIDList.add(r.getPurchase_order_id());
            }
        }
        setListPOLable(POIDList);
    }
    
    private void setListPOLable(List<Integer> POIDList){
        if (PONumberList == null){
            PONumberList = new ArrayList<>();
        }else {
            PONumberList.removeAll(PONumberList);
        }
        String recordPONumber = null;
        
        if (dbAllPurchaseOrderInfoNotCleared != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
                for (Integer i : POIDList){
                    if ((recordPONumber== null || !recordPONumber.equals(po.getPurchase_order_number())) && i == po.getPurchase_order_id()){
                        PONumberList.add(po.getPurchase_order_number());
                        recordPONumber = po.getPurchase_order_number();
                        break;
                    }
                }
            }
        }
        
        for (String s : PONumberList){
            JLabel PONumberLabel = new JLabel(s);
            PONumberLabel.setForeground(Color.red);
            listPOPanel.add(PONumberLabel);
        }
        
            listPOPanel.revalidate();
            listPOPanel.repaint();
    }
    
    private void removelistPOPanelComponents(){
        if (listPOPanel != null){
            listPOPanel.removeAll();
            listPOPanel.revalidate();
            listPOPanel.repaint();
        }
    }
    
    private void removePODetail(){
        Object selectedPONumber = receivedPO_selecetBox.getSelectedItem();
        List<Integer> po_id = new ArrayList<>();
        for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
            if (selectedPONumber.equals(po.getPurchase_order_number())){
                po_id.add(po.getPurchase_order_id());
            }
        }
        
        List<Received> rev = new ArrayList<>();
        
        for (Integer i : po_id){
            for (Received r : receivedList){
                if (r.getPurchase_order_id() == i){
                    rev.add(r);
                }
            }
        }
        receivedList.removeAll(rev);
        
        removelistPOPanelComponents();
        setListPOPanel(receivedList);
    }
    
    private void setSaveButtonPanel(){
        saveButtonPanel = new JPanel();
        saveButtonPanel.setLayout(new GridLayout(1,2));
        saveButtonPanel.setBorder(new LineBorder(Color.GRAY));
        this.add(saveButtonPanel, BorderLayout.SOUTH);
        
        setSavePanel();
    }
    
    private void setSavePanel(){
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout());
        
        saveButtonPanel.add(savePanel);
        
        JButton saveButton = new JButton("Save");
        
        savePanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                insertReceivedDataToDB();
                refreshComponents();
                updateReceived();
            }
        });
    }
    
    private void insertReceivedDataToDB(){
        if (receivedList == null || receivedList.isEmpty()){
            JOptionPane.showMessageDialog(selectedPODetailPanel, "Empty Data cannot be save!! \n "
                    + "Please make sure press \"+\" before saving!!", "Saving Waring", 0);
        }else{
            String sRecord = null;
            List<String> list = new ArrayList<>();
            for (Received r : receivedList){
                for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
                    if (r.getPurchase_order_id() == po.getPurchase_order_id()){
                        if (sRecord == null || !sRecord.equals(po.getPurchase_order_number())){
                            list.add(po.getPurchase_order_number());
                            sRecord = po.getPurchase_order_number();
                        }
                    }
                }
            }
            String sPO = null;
            for(String s : list){
                if (sPO == null){
                    sPO = s + " ";
                }else {
                    sPO += s + " ";
                }
            }

            int resultDialog = JOptionPane.showConfirmDialog(selectedPODetailPanel, "Do you want save with PO: " + sPO, 
                    "Saving Notice", 1);
            if (resultDialog == 0){
                int insert_count = new JDBCTemplate().insertDataIntoDBReceived(receivedList);
                int update_count = new JDBCTemplate().updatePurchaseOrderDataIntoDB(list, 1);
                if (insert_count > 0 && update_count > 0){
                    JOptionPane.showMessageDialog(selectedPODetailPanel, "Save sucessfully!!", "Notice", 1);
                }
            }else if (resultDialog == 2){
                refreshComponents();
            }
        }
    }
    
    private void refreshComponents(){
        setPOComboxItem();
        
        receivedID_text.setEnabled(true);
        receivedID_text.setText("");
        
        receivedDatePicker.getComponent(1).setEnabled(true);
        
        receivedSupplier_selecetBox.setEnabled(true);
        
        receivedEmployee_selecetBox.setEnabled(true);
        
        receivedList.removeAll(receivedList);
        removelistPOPanelComponents();
        setListPOPanel(receivedList);
    }
    
}
