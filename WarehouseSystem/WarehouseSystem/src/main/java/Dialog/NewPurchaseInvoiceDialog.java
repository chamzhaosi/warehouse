/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.Received;
import PsnClass.Received_PO_PI;
import PsnClass.Supplier;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author user
 */
public class NewPurchaseInvoiceDialog extends JDialog{
    private JPanel purchaseInvoiceRootPanel = null;
    private JPanel inventoryPricePanel = null;
    private JPanel addOtherReceivedDetailPanel = null;
    private JPanel inventoryPriceDetailPanel = null;
    private JPanel listPIPanel = null;
    private JTextField invoiceID_text = null;
    private JTextField supplier_text = null;
    private JDatePickerImpl invoiceDatePicker = null;
    private JComboBox receivedNumber_selectedBox = null;
    private JComboBox employee_selectedBox = null;
    
    private List<Received> dbAllReceivedInfoNotCleared = null;
    private List<Employee> dbAllEmployeeInfo = null;
    private List<Supplier> dbAllSuplierInfo = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    private List<Inventory> dbAllInventoryInfo= null;
    private List<PurchaseInvoice> dbAllPurchaseInvoiceInfo = null;
    
    private List<Received_PO_PI> received_POList = null;
    private List<PurchaseInvoice> PIList = null;
    private List<String> receivedNumberList = null;
    
    List<JTextField> unitPriceList = new ArrayList<>();
    List<JTextField> totalPriceList = new ArrayList<>();

    public NewPurchaseInvoiceDialog(JFrame frame, String title){
        super(frame, title);
        
        purchaseInvoiceRootPanel = new JPanel();
        purchaseInvoiceRootPanel.setLayout(new BorderLayout());
        purchaseInvoiceRootPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.add(purchaseInvoiceRootPanel);
        
        getDBAllReceivingInfo();
        getDBAllEmployeeInfo();
        getDBAllSupplierInfo();       
        getDBAllPurchaseOrderInfo();   
        getDBAllInventoryInfo();      
        getDBAllPurchaseInvoiceInfo();     
        setPurchaceInvoiceDetailPanel();
        setSaveButtonPanel();
    }
    
    private void getDBAllReceivingInfo(){
        dbAllReceivedInfoNotCleared = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number", 0);
    }
    
    private void getDBAllEmployeeInfo(){
       dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllSupplierInfo(){
        dbAllSuplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 1);
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllPurchaseInvoiceInfo(){
        dbAllPurchaseInvoiceInfo = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", "purchase_invoice_number");
    }
    
    private void setPurchaceInvoiceDetailPanel(){
        JPanel purchaseInvoiceDetailPanel = new JPanel();
        purchaseInvoiceDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        purchaseInvoiceRootPanel.add(purchaseInvoiceDetailPanel, BorderLayout.NORTH);
        
        JLabel invoiceIDLabel = new JLabel("Invoice ID*: ");
        invoiceID_text = new JTextField(20);
        invoiceID_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel invoicedDateLabel = new JLabel("Invoice Date*: ");
        invoiceDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel supplierLabel = new JLabel("Supplier Name*: ");
        supplier_text =  new JTextField();
        supplier_text.setEditable(false);
        
        JLabel receivedIDLabel = new JLabel("Received ID*: ");
        receivedNumber_selectedBox = new JComboBox();
        setReceivedIDComboxItem();
        
        JLabel employeeLabel = new JLabel("Employee Name*: ");
        employee_selectedBox = new JComboBox();
        setEmployeeComboxItem();
        
        JButton addNewEmployeeButton = new JButton("Add");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        purchaseInvoiceDetailPanel.add(invoiceIDLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        purchaseInvoiceDetailPanel.add(invoiceID_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        purchaseInvoiceDetailPanel.add(invoicedDateLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        purchaseInvoiceDetailPanel.add(invoiceDatePicker, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        purchaseInvoiceDetailPanel.add(supplierLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        purchaseInvoiceDetailPanel.add(supplier_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        purchaseInvoiceDetailPanel.add(receivedIDLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        purchaseInvoiceDetailPanel.add(receivedNumber_selectedBox, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        purchaseInvoiceDetailPanel.add(employeeLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        purchaseInvoiceDetailPanel.add(employee_selectedBox, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 2;
        gbc.gridy = 4;
        purchaseInvoiceDetailPanel.add(addNewEmployeeButton, gbc);
        
        addNewEmployeeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddEmployeeDialog();
            }
        });
        
        receivedNumber_selectedBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = receivedNumber_selectedBox.getSelectedItem();
                if (selectedItem != null){
                    if (inventoryPriceDetailPanel != null){
                        inventoryPriceDetailPanel.removeAll();
                        inventoryPriceDetailPanel.revalidate();
                        inventoryPriceDetailPanel.repaint();
                        unitPriceList.removeAll(unitPriceList);
                        totalPriceList.removeAll(totalPriceList);
                        setInventoryPriceDetailPanel((String)selectedItem);
                    }else {
                        setInventoryPricePanel();
                        setInventoryPriceDetailPanel((String)selectedItem);
                        setAddOtherReceivedDetailPanel();
                        inventoryPriceDetailPanel.revalidate();
                        inventoryPriceDetailPanel.repaint();
                    }
                }
            }
        });
    }

    private void setInventoryPricePanel(){
        getReceivedWithPOIDList();
        
        inventoryPricePanel = new JPanel();
        inventoryPricePanel.setLayout(new BorderLayout());
        inventoryPricePanel.setBorder(new LineBorder(Color.GRAY));
        purchaseInvoiceRootPanel.add(inventoryPricePanel, BorderLayout.CENTER);
    }
    
    private void setInventoryPriceDetailPanel(String selectedItem){
        if (inventoryPriceDetailPanel == null){
            inventoryPriceDetailPanel = new JPanel();
        }
        inventoryPriceDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inventoryPricePanel.add(inventoryPriceDetailPanel, BorderLayout.CENTER);
        
        JLabel inventoryNameLabel = new JLabel("Inventory Name*: ");
        
        JLabel quantityLabel = new JLabel("Quantity*: ");
        
        JLabel unitLabel = new JLabel("Unit*: ");
        
        JLabel unitPriceLabel = new JLabel("Unit Price*: ");
        
        JLabel totalPriceLabel = new JLabel("Total Price*: ");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inventoryPriceDetailPanel.add(inventoryNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inventoryPriceDetailPanel.add(quantityLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        inventoryPriceDetailPanel.add(unitLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        inventoryPriceDetailPanel.add(unitPriceLabel, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        inventoryPriceDetailPanel.add(totalPriceLabel, gbc);
        
        int index = 1;
        if (received_POList != null){
            for (Received_PO_PI rp : received_POList){
                if (selectedItem != null && selectedItem.equals(rp.getReceivedNumber())){ 
                    if (supplier_text.getText().equals("") || supplier_text.getText().equals(" ")){
                        supplier_text.setText(rp.getSupplierName());
                    }else if (!supplier_text.getText().equals("") && !rp.getSupplierName().equals(supplier_text.getText())){
                        JOptionPane.showMessageDialog(inventoryPricePanel, "Difference supplier name is not acceptable!", "Warning", 2);
                        break;
                    }

                    for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                        if (rp.getPurchaseOrderNumber().equals(po.getPurchase_order_number())){ 
                            JTextField inventoryName_text = new JTextField();
                            JTextField Quantity_text = new JTextField();
                            JTextField unit_text = new JTextField();
                            JTextField unitPrice_text = new JTextField(11);
                            unitPrice_text.setDocument(new LengthRestrictedDocument(11));
                            unitPriceList.add(unitPrice_text);
                            
                            JTextField totalPrice_text = new JTextField(11);
                            totalPrice_text.setDocument(new LengthRestrictedDocument(11));
                            totalPriceList.add(totalPrice_text);

                            for (Inventory i : dbAllInventoryInfo){
                                if (i.getInventory_id() == po.getInventory_id()){
                                    inventoryName_text.setText(i.getName());
                                    break;
                                }
                            }
                            inventoryName_text.setEditable(false);
                            Quantity_text.setText(String.valueOf(po.getQuantity()));
                            Quantity_text.setEditable(false);
                            unit_text.setText(po.getUnit());
                            unit_text.setEditable(false);
                            
                            gbc.gridx = 0;
                            gbc.gridy = index;
                            inventoryPriceDetailPanel.add(inventoryName_text, gbc);
                            gbc.gridx = 1;
                            gbc.gridy = index;
                            inventoryPriceDetailPanel.add(Quantity_text, gbc);
                            gbc.gridx = 2;
                            gbc.gridy = index;
                            inventoryPriceDetailPanel.add(unit_text, gbc);
                            gbc.gridx = 3;
                            gbc.gridy = index;
                            inventoryPriceDetailPanel.add(unitPrice_text, gbc);
                            gbc.gridx = 4;
                            gbc.gridy = index;
                            inventoryPriceDetailPanel.add(totalPrice_text, gbc);
                            
                            unitPrice_text.addActionListener(new ActionListener (){
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String text = unitPrice_text.getText();
                                    try{
                                        Double.parseDouble(text);
                                    }catch (Exception ex){
                                        JOptionPane.showMessageDialog(inventoryPricePanel, "Please input valid Unit Price!", "Warning", 2);
                                        unitPrice_text.setText("");
                                    }

                                    if (!unitPrice_text.getText().equals("")){
                                        String unit_price_text = unitPrice_text.getText();
                                        double unit_price_double = Double.parseDouble(unit_price_text);
                                        
                                        String quantity_text = Quantity_text.getText();
                                        double quantity_double = Double.parseDouble(quantity_text);
                                        
                                        double total_price_double = unit_price_double * quantity_double;
                                        totalPrice_text.setText(String.valueOf(total_price_double));
                                        totalPrice_text.setEditable(false);   
                                    }
                                }
                            });
                            index++;
                        }
                    }
                }
            }
        }
    }
    
    private void setAddOtherReceivedDetailPanel(){
        if (addOtherReceivedDetailPanel == null){
            addOtherReceivedDetailPanel = new JPanel();
        }
        addOtherReceivedDetailPanel.setLayout(new GridLayout(2,1));
        
        inventoryPricePanel.add(addOtherReceivedDetailPanel, BorderLayout.SOUTH);
        
        setAddSubButtonPanel();
    }

    private void setAddSubButtonPanel(){
        JPanel addSubButtonPanel = new JPanel();
        addSubButtonPanel.setLayout(new FlowLayout());
        addSubButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        addOtherReceivedDetailPanel.add(addSubButtonPanel);
        
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        removeButton.setEnabled(false);
        
        addSubButtonPanel.add(addButton);
        addSubButtonPanel.add(removeButton);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<PurchaseInvoice> piDetailList = getPIDetailList();
                if (piDetailList != null){
                    setUnenableComponents();
                    removeButton.setEnabled(true);
                    removelistPIPanelComponents();
                    setListPIPanel();
                }
            }
        });       
        
        removeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                removePIDetail();
                if (PIList == null || PIList.isEmpty()){
                    removeButton.setEnabled(false);
                }
            }
        
        });
    }
    
    private void removePIDetail(){
        List<Integer> recordRecID = new ArrayList<>();
        Object selectedReceivedItem = receivedNumber_selectedBox.getSelectedItem();
        for (Received r : dbAllReceivedInfoNotCleared){
            if (r.getReceived_number().equals(selectedReceivedItem)){
                recordRecID.add(r.getReceived_id());
            }
        }
        
        List<PurchaseInvoice> purchaseInvoices = new ArrayList<>();
        
        for (Integer i : recordRecID){
            for (PurchaseInvoice pi : PIList){
                if (i == pi.getReceived_id()){
                    purchaseInvoices.add(pi);
                }
            }
        }
        
        PIList.removeAll(purchaseInvoices);
        
        removelistPIPanelComponents();
        setListPIPanel();
    }
    
    private void setUnenableComponents(){
        invoiceID_text.setEnabled(false);
        invoiceDatePicker.getComponent(1).setEnabled(false);
        employee_selectedBox.setEnabled(false);
    }
    
    private void removelistPIPanelComponents(){
        if (inventoryPriceDetailPanel != null){
            inventoryPriceDetailPanel.removeAll();
            inventoryPriceDetailPanel.revalidate();
            inventoryPriceDetailPanel.repaint();
            unitPriceList.clear();
            totalPriceList.clear();
        }
        setInventoryPriceDetailPanel((String)receivedNumber_selectedBox.getSelectedItem());
    }
    
    private void setListPIPanel(){
        if (listPIPanel == null){
            listPIPanel = new JPanel();
        }else {
            listPIPanel.removeAll();
        }

        listPIPanel.setBorder(new LineBorder(Color.gray));
        listPIPanel.setLayout(new FlowLayout());
        
        addOtherReceivedDetailPanel.add(listPIPanel);
        
        if (receivedNumberList == null){
            receivedNumberList = new ArrayList<>();
        }else {
            receivedNumberList.removeAll(receivedNumberList);
        }
        
        String record = null;
        if (PIList != null){
            for (PurchaseInvoice pi : PIList){
                for (Received_PO_PI rp : received_POList){
                    if (pi.getReceived_id() == rp.getREC_ID() && (record == null || !record.equals(rp.getReceivedNumber()))){
                        receivedNumberList.add(rp.getReceivedNumber());
                        record = rp.getReceivedNumber();
                        break;
                    }
                }
            }
        }

        for (String s : receivedNumberList){
            JLabel RONumber = new JLabel(s);
            RONumber.setForeground(Color.red);
            listPIPanel.add(RONumber);
        }
        
        JLabel showTotalLabel = new JLabel();
        double sumPrice = 0;
        for (PurchaseInvoice pi : PIList){
            sumPrice += pi.getTotal_price();
        }
        showTotalLabel.setText("Sum total price: "+String.valueOf(sumPrice));
        listPIPanel.add(showTotalLabel);
        
        
        listPIPanel.revalidate();
        listPIPanel.repaint();
    }

    private List<PurchaseInvoice> getPIDetailList(){
        if (PIList == null){
            PIList = new ArrayList<>();
        }

        if (checkDuplicatePIID()){
            JOptionPane.showMessageDialog(inventoryPricePanel, "Duplicate Invoice ID is not acceptable!", "Warning", 0);
        }else if (checkNull_InvalidComponentsData()){
            JOptionPane.showMessageDialog(inventoryPricePanel, "* Empty value is not acceptable \n "
                    + "or unit price not valid!!", "Warning", 0);
        }else {
            List<Integer> recordRecID = new ArrayList<>();
            Object selectedReceivedItem = receivedNumber_selectedBox.getSelectedItem();
            for (Received r : dbAllReceivedInfoNotCleared){
                if (r.getReceived_number().equals(selectedReceivedItem)){
                    recordRecID.add(r.getReceived_id());
                }
            }
            
            for (int tf_index = 0; tf_index<unitPriceList.size(); tf_index++){
                PurchaseInvoice puchaseInvoice = new PurchaseInvoice();
                
                puchaseInvoice.setPurchase_invoice_number(invoiceID_text.getText());
                
                Date invoiceDate = (Date)invoiceDatePicker.getModel().getValue();
                puchaseInvoice.setPurchase_invoice_date(invoiceDate);
                
                puchaseInvoice.setReceived_id(recordRecID.get(tf_index));
                
                Object selectedEmployeeItem = employee_selectedBox.getSelectedItem();
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getName().equals(selectedEmployeeItem)){
                        puchaseInvoice.setStaff_id(e.getStaff_id());
                    }
                }
                
                puchaseInvoice.setUnit_price(Double.parseDouble(unitPriceList.get(tf_index).getText()));
                puchaseInvoice.setTotal_price(Double.parseDouble(totalPriceList.get(tf_index).getText()));
                
                PIList.add(puchaseInvoice);
                
                List<Integer> record = new ArrayList<>();
                for (int i = 0; i<PIList.size(); i++){
                    for (int j = 1+i; j<PIList.size(); j++){
                        if (PIList.get(i).getReceived_id() == PIList.get(j).getReceived_id()){
                            PIList.set(i, PIList.get(j));
                            record.add(j);
                        }
                    }
                }
                
                for (Integer i : record){
                    PIList.remove((int)i);
                }
                record.clear();
            }
            return PIList;
        }
        return null;
    }
    
    private boolean checkNull_InvalidComponentsData(){
        if (invoiceID_text.getText().equals("")){
            return true;
        }
        
        if (invoiceDatePicker.getModel().getValue() == null){
            return true;
        }
        
        if (supplier_text.getText().equals("")){
            return true;
        }
        
        if (receivedNumber_selectedBox.getSelectedIndex() == -1){
            return true;
        }
        
        if (employee_selectedBox.getSelectedIndex() == -1){
            return true;
        }
        
        for (JTextField tf : unitPriceList){
            if (tf.getText().equals("")){
                return true;
            }else if (!tf.getText().equals("")){
                try{
                Double.parseDouble(tf.getText());
                }catch (Exception ex){
                    return true;
                }
            }
        }
        
        
        for (JTextField tf : totalPriceList){
            if (tf.getText().equals("")){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkDuplicatePIID(){
        if (!invoiceID_text.getText().equals("") && dbAllPurchaseInvoiceInfo != null){
            String invoiceID = invoiceID_text.getText().toUpperCase();
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (invoiceID.equals(pi.getPurchase_invoice_number())){
                    return true;
                }
            }
        }
        return false;
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
    
    private void setReceivedIDComboxItem(){
        getDBAllReceivingInfo();
        String record = null;
        
        if (receivedNumber_selectedBox != null){
            receivedNumber_selectedBox.removeAllItems();
        }
        
        if (dbAllReceivedInfoNotCleared != null){
            for (Received r : dbAllReceivedInfoNotCleared){
                if (record == null || !record.equals(r.getReceived_number())){
                    receivedNumber_selectedBox.addItem(r.getReceived_number());
                    record = r.getReceived_number();
                }
            }
        }
    }
    
    private void setEmployeeComboxItem(){
        getDBAllEmployeeInfo();
        
        if (dbAllEmployeeInfo != null){
            for (Employee e : dbAllEmployeeInfo){
                employee_selectedBox.addItem(e.getName());
            }
        }
    }
    
    private void getReceivedWithPOIDList(){        
        if (received_POList == null){
            received_POList = new ArrayList<>();
        }else {
            received_POList.removeAll(received_POList);
        }
        
        String record = null;
        int index = 0;
        if (dbAllPurchaseOrderInfoCleared != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                if (record == null || !record.equals(po.getPurchase_order_number()) || 
                        (record.equals(po.getPurchase_order_number()) && 
                            received_POList.get(index-1).getPO_ID() > po.getPurchase_order_id())){
                    Received_PO_PI rp = new Received_PO_PI();
                    rp.setPurchaseOrderNumber(po.getPurchase_order_number());
                    rp.setPO_ID(po.getPurchase_order_id());
                    if(record != null && record.equals(po.getPurchase_order_number())){
                        received_POList.remove(index-1);
                        index--;
                    }
                    received_POList.add(rp);
                    index++;
                    record = po.getPurchase_order_number();
                }
            }
        }else {
            received_POList = null;
        }
        
        if (dbAllReceivedInfoNotCleared != null){
            for (Received r : dbAllReceivedInfoNotCleared){
                for (Received_PO_PI rp : received_POList){
                    if (r.getPurchase_order_id() == rp.getPO_ID()){
                        rp.setReceivedNumber(r.getReceived_number());
                        rp.setREC_ID(r.getReceived_id());
                        for (Supplier s : dbAllSuplierInfo){
                            if (r.getSupplier_id()==s.getSupplier_id()){
                                rp.setSupplierName(s.getCompany_name());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void setSaveButtonPanel(){
        JPanel saveButtonPanel = new JPanel();
        
        saveButtonPanel.setLayout(new FlowLayout());
        saveButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        purchaseInvoiceRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("Save");
        
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               insertPurchaseInvoiceDataToDB();
            }
        });
    }
    
    private void insertPurchaseInvoiceDataToDB(){
        if (PIList == null || PIList.isEmpty()){
            JOptionPane.showMessageDialog(purchaseInvoiceRootPanel, "Empty Data cannot be save!! \n "
                    + "Please make sure press \"+\" before saving!!", "Saving Waring", 0);
        }else {
            String record = null;
            List<String> recNumberList = new ArrayList<>();
            if (PIList != null){
                for (PurchaseInvoice pi : PIList){
                    for (Received_PO_PI rp : received_POList){
                        if (pi.getReceived_id() == rp.getREC_ID() && (record == null || !record.equals(rp.getReceivedNumber()))){
                            recNumberList.add(rp.getReceivedNumber());
                            record = rp.getReceivedNumber();
                            break;
                        }
                    }
                }
            }
            
            String sRec = null;
            for(String s : recNumberList){
                if (sRec == null){
                    sRec = s + " ";
                }else {
                    sRec += s + " ";
                }
            }
            
            int resultDialog = JOptionPane.showConfirmDialog(purchaseInvoiceRootPanel, "Do you want save with received: " + sRec, 
                    "Saving Notice", 1);
            if (resultDialog == 0){
                int insert_count = new JDBCTemplate().insertDataIntoDBPurchaseInvoice(PIList);
                int update_count = new JDBCTemplate().updateReceivedDataIntoDB(recNumberList, 1);
                if (insert_count > 0 && update_count > 0){
                    JOptionPane.showMessageDialog(purchaseInvoiceRootPanel, "Save sucessfully!!", "Notice", 1);
                }
                refreshComponents();
            }else if (resultDialog == 2){
                refreshComponents();
            }
        }
    }
    
    private void refreshComponents(){
        invoiceID_text.setText("");
        invoiceID_text.setEnabled(true);
        
        invoiceDatePicker.getComponent(1).setEnabled(true);
        
        receivedNumber_selectedBox.setSelectedIndex(-1);
        
        supplier_text.setText(" ");
        
        employee_selectedBox.setEnabled(true);
        
        if (PIList != null){
            PIList.removeAll(PIList);
            removelistPIPanelComponents();
            setListPIPanel(); 
            setReceivedIDComboxItem();
            supplier_text.setText(" ");
        }   
    }
}
