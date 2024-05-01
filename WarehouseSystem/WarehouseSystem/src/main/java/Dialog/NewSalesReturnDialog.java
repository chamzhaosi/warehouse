/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Customer;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.Received_PO_PI;
import PsnClass.SO_SI;
import PsnClass.SalesInvoice;
import PsnClass.SalesOrder;
import PsnClass.SalesReturn;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author user
 */
public class NewSalesReturnDialog extends JDialog{
    private JPanel salesReturnRootPanel = null;
    private JPanel inventoryPanel = null;
    private JPanel inventoryDetailPanel = null;
    private JPanel addOtherInvoiceDetailPanel = null;
    private JPanel listInvPanel = null;
    private JTextField returnID_text = null;
    private JTextField customer_text = null;
    private JDatePickerImpl returnDatePicker = null;
    private JComboBox invoiceNumber_selectedBox = null;

    private List<SalesReturn> dbAllSalesReturnInfo = null;
    private List<SalesOrder> dbAllSalesOrderInfoCleared = null;
    private List<Inventory> dbAllInventoryInfo= null;
    private List<Customer> dbAllCustomerInfo= null;
    private List<SalesInvoice> dbAllSalesInvoiceInfoNotCleared = null;
    
    private List<SO_SI> SO_SIList = null;
    private List<JTextField> quantityList = new ArrayList<>();
    private List<JTextField> inventoryList = new ArrayList<>();
    private List<JTextField> totalList = new ArrayList<>();
    private List<JTextArea> remarkAreaList = new ArrayList<>();
    private List<String> invoiceNumberList = null;
    private List<SalesReturn> SRList = null;
    
    public NewSalesReturnDialog (JFrame frame, String title){
        super(frame, title);
        
        salesReturnRootPanel = new JPanel();
        salesReturnRootPanel.setLayout(new BorderLayout());
        salesReturnRootPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.add(salesReturnRootPanel);
        
        getDBAllCustomerInfo();
        getDBAllInventoryInfo();
        getDBAllSalesOrderInfo();
        getDBAllSalesInvoiceInfo();
        getDBAllSalesReturnInfo();
        setSalesReturnDetailPanel();
        setSaveButtonPanel();
    }

    private void getDBAllSalesReturnInfo(){
        dbAllSalesReturnInfo = new JDBCTemplate().getDBAllSalesReturnInfo("sales_return_table", "sales_return_number");
    }

    private void getDBAllSalesOrderInfo(){
        dbAllSalesOrderInfoCleared = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", "sales_order_number", 1);
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllCustomerInfo(){
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    private void getDBAllSalesInvoiceInfo(){
        dbAllSalesInvoiceInfoNotCleared = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", "sales_invoice_number", 0);
    }
    
    private void setSalesReturnDetailPanel(){
        JPanel salesReturnDetailPanel = new JPanel();
        salesReturnDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        salesReturnRootPanel.add(salesReturnDetailPanel, BorderLayout.NORTH);
        
        JLabel returnIDLabel = new JLabel("Return ID*: ");
        returnID_text = new JTextField(20);
        returnID_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel returnDateLabel = new JLabel("Return Date*: ");
        returnDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel customerLabel = new JLabel("Customer Name*: ");
        customer_text =  new JTextField();
        customer_text.setEditable(false);
        
        JLabel invoiceIDLabel = new JLabel("Invoice ID*: ");
        invoiceNumber_selectedBox = new JComboBox();
        setInvoiceIDComboxItem();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        salesReturnDetailPanel.add(returnIDLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        salesReturnDetailPanel.add(returnID_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        salesReturnDetailPanel.add(returnDateLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        salesReturnDetailPanel.add(returnDatePicker, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        salesReturnDetailPanel.add(customerLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        salesReturnDetailPanel.add(customer_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        salesReturnDetailPanel.add(invoiceIDLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        salesReturnDetailPanel.add(invoiceNumber_selectedBox, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        
        invoiceNumber_selectedBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = invoiceNumber_selectedBox.getSelectedItem();
                if (selectedItem != null){
                    if (inventoryDetailPanel != null){
                        inventoryDetailPanel.removeAll();
                        inventoryDetailPanel.revalidate();
                        inventoryDetailPanel.repaint();
                        quantityList.clear();
                        remarkAreaList.clear();
                        inventoryList.clear();
                        setInventoryDetailPanel((String)selectedItem);
                    }else {
                        setInventoryPanel();
                        setInventoryDetailPanel((String)selectedItem);
                        setAddOtherInvoiceDetailPanel();
                        inventoryDetailPanel.revalidate();
                        inventoryDetailPanel.repaint();
                    }
                }
            }
        });
    }
    
    private void setInventoryPanel(){
        getSOID_SIID_List();
        
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBorder(new LineBorder(Color.GRAY));
        salesReturnRootPanel.add(inventoryPanel, BorderLayout.CENTER);
    }    
    
    private void setInventoryDetailPanel(String selectedItem){
         if (inventoryDetailPanel == null){
            inventoryDetailPanel = new JPanel();
        }
        inventoryDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inventoryPanel.add(inventoryDetailPanel, BorderLayout.CENTER);
        
        JLabel inventoryNameLabel = new JLabel("Inventory Name*: ");
        
        JLabel quantityLabel = new JLabel("Quantity*: ");
        
        JLabel unitLabel = new JLabel("Unit*:          ");
        
        JLabel unitPriceLabel = new JLabel("Unit Price*: ");
        
        JLabel totalPriceLabel = new JLabel("Total Price*: ");
        
        JLabel remarkLabel = new JLabel("Remark*:          ");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inventoryDetailPanel.add(inventoryNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inventoryDetailPanel.add(quantityLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        inventoryDetailPanel.add(unitLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        inventoryDetailPanel.add(unitPriceLabel, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        inventoryDetailPanel.add(totalPriceLabel, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        inventoryDetailPanel.add(remarkLabel, gbc);
        
        int index = 1;
        if (SO_SIList != null){
            for (SO_SI ss : SO_SIList){
                if (selectedItem != null && selectedItem.equals(ss.getSalesInvoiceNumber())){ 
                    if (customer_text.getText().equals("") || customer_text.getText().equals(" ")){
                        customer_text.setText(ss.getCustomerName());
                    }else if (!customer_text.getText().equals("") && !ss.getCustomerName().equals(customer_text.getText())){
                        JOptionPane.showMessageDialog(inventoryPanel, "Difference supplier name is not acceptable!", "Warning", 2);
                        break;
                    }
                    
                    List<Double> recoredUnitPriceList = new ArrayList<>();
                    for (SalesInvoice si : dbAllSalesInvoiceInfoNotCleared){
                        if (ss.getSalesInvoiceNumber().equals(si.getSales_invoice_number())){
                            for (SalesOrder so : dbAllSalesOrderInfoCleared) {
                                if (ss.getSalesOrderNumber().equals(so.getSales_order_number())
                                        && si.getSales_order_id() == so.getSales_order_id()){
                                    recoredUnitPriceList.add(si.getUnit_price());
                                }
                            }
                        }
                    }
                    

                    for (SalesOrder so : dbAllSalesOrderInfoCleared){
                        if ((ss.getSalesOrderNumber().equals(so.getSales_order_number() ) && (recoredUnitPriceList.size() != 0))){ 
                            JTextField inventoryName_text = new JTextField();
                            inventoryList.add(inventoryName_text);
                            
                            JTextField Quantity_text = new JTextField();
                            quantityList.add(Quantity_text);
                            
                            JTextField unit_text = new JTextField();
                            JTextField unitPrice_text = new JTextField();
                            JTextField totalPrice_text = new JTextField();
                            totalList.add(totalPrice_text);
                                    
                            
                            JTextArea remarkArea = new JTextArea(3,50);
                            remarkArea.setBorder(new LineBorder(Color.GRAY));
                            remarkArea.setDocument(new LengthRestrictedDocument(150));
                            remarkAreaList.add(remarkArea);

                            for (Inventory i : dbAllInventoryInfo){
                                if (i.getInventory_id() == so.getInventory_id()){
                                    inventoryName_text.setText(i.getName());
                                    break;
                                }
                            }
                            inventoryName_text.setEditable(false);
                            Quantity_text.setText(String.valueOf(so.getQuantity()));
                            unit_text.setText(so.getUnit());
                            unit_text.setEditable(false);
                            
                            if(recoredUnitPriceList != null || !recoredUnitPriceList.isEmpty()){
                                unitPrice_text.setText(String.valueOf(recoredUnitPriceList.get(0)));
                                unitPrice_text.setEditable(false);
                            
                                double totalprice = recoredUnitPriceList.get(0) * so.getQuantity();
                                totalPrice_text.setText(String.valueOf(totalprice));
                                totalPrice_text.setEditable(false);

                                recoredUnitPriceList.remove(0);
                            }
                            

                            gbc.gridx = 0;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(inventoryName_text, gbc);
                            gbc.gridx = 1;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(Quantity_text, gbc);
                            gbc.gridx = 2;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(unit_text, gbc);
                            gbc.gridx = 3;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(unitPrice_text, gbc);
                            gbc.gridx = 4;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(totalPrice_text, gbc);
                            gbc.gridx = 5;
                            gbc.gridy = index;
                            inventoryDetailPanel.add(remarkArea, gbc);

                            Quantity_text.addActionListener(new ActionListener (){
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String text = Quantity_text.getText();
                                    try{
                                        Double.parseDouble(text);
                                        if (Double.parseDouble(text) <= so.getQuantity() && Double.parseDouble(text) > -1 ){
                                            if (!Quantity_text.getText().equals("")){
                                                String unit_price_text = unitPrice_text.getText();
                                                double unit_price_double = Double.parseDouble(unit_price_text);

                                                String quantity_text = Quantity_text.getText();
                                                double quantity_double = Double.parseDouble(quantity_text);

                                                double total_price_double = unit_price_double * quantity_double;
                                                totalPrice_text.setText(String.valueOf(total_price_double));   
                                            }
                                        }else {
                                            JOptionPane.showMessageDialog(inventoryDetailPanel, "Excess or lower than purchase quantity!", "Warning", 2);
                                            Quantity_text.setText("");
                                        }
                                    }catch (Exception ex){
                                        JOptionPane.showMessageDialog(inventoryDetailPanel, "Please input valid Quantity!", "Warning", 2);
                                        Quantity_text.setText("");
                                    }
                                }
                            });
                            index++;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void setAddOtherInvoiceDetailPanel(){
        if (addOtherInvoiceDetailPanel == null){
            addOtherInvoiceDetailPanel = new JPanel();
        }
        addOtherInvoiceDetailPanel.setLayout(new GridLayout(2,1));
        
        inventoryPanel.add(addOtherInvoiceDetailPanel, BorderLayout.SOUTH);
        
        setAddSubButtonPanel();
    }
    
    private void setAddSubButtonPanel(){
        JPanel addSubButtonPanel = new JPanel();
        addSubButtonPanel.setLayout(new FlowLayout());
        addSubButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        addOtherInvoiceDetailPanel.add(addSubButtonPanel);
        
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        removeButton.setEnabled(false);
        
        addSubButtonPanel.add(addButton);
        addSubButtonPanel.add(removeButton);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SalesReturn> srDetailList = getSRDetailList();
                if (srDetailList != null){
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
                removePRDetail();
                if (SRList == null || SRList.isEmpty()){
                    removeButton.setEnabled(false);
                }
            }
        
        });
    }
    
    private void setUnenableComponents(){
        returnID_text.setEnabled(false);
        returnDatePicker.getComponent(1).setEnabled(false);
    }
    
    private void removelistPIPanelComponents(){
        if (inventoryDetailPanel != null){
            inventoryDetailPanel.removeAll();
            inventoryDetailPanel.revalidate();
            inventoryDetailPanel.repaint();
            quantityList.clear();
            remarkAreaList.clear();
        }
        setInventoryDetailPanel((String)invoiceNumber_selectedBox.getSelectedItem());
    }
    
    private void setListPIPanel(){
        if (listInvPanel == null){
            listInvPanel = new JPanel();
        }else {
            listInvPanel.removeAll();
        }

        listInvPanel.setBorder(new LineBorder(Color.gray));
        listInvPanel.setLayout(new FlowLayout());
        
        addOtherInvoiceDetailPanel.add(listInvPanel);
        
        if (invoiceNumberList == null){
            invoiceNumberList = new ArrayList<>();
        }else {
            invoiceNumberList.removeAll(invoiceNumberList);
        }
        
        String record = null;
        if (SRList != null){
            for (SalesReturn sr : SRList){
                for (SO_SI ss : SO_SIList){
                    if (sr.getSales_invoice_id() == ss.getSI_ID() && (record == null || !record.equals(ss.getSalesInvoiceNumber()))){
                        invoiceNumberList.add(ss.getSalesInvoiceNumber());
                        record = ss.getSalesInvoiceNumber();
                        break;
                    }
                }
            }
        }

        for (String s : invoiceNumberList){
            JLabel SINumber = new JLabel(s);
            SINumber.setForeground(Color.red);
            listInvPanel.add(SINumber);
        }
        
        JLabel showTotalLabel = new JLabel();
        double sumPrice = 0;
        for (SalesReturn sr : SRList){
            for (SalesInvoice si : dbAllSalesInvoiceInfoNotCleared){
                if (sr.getSales_invoice_id() == si.getSales_invoice_id()){
                    sumPrice += (sr.getQuantity()* si.getUnit_price());
                }
            }
        }
        showTotalLabel.setText("Sum total price: "+String.valueOf(sumPrice));
        listInvPanel.add(showTotalLabel);
        listInvPanel.revalidate();
        listInvPanel.repaint();
    }
    
    private void removePRDetail(){
        List<Integer> recordInvID = new ArrayList<>();
        Object selectedInvoiceItem = invoiceNumber_selectedBox.getSelectedItem();
        for (SalesInvoice si : dbAllSalesInvoiceInfoNotCleared){
            if (si.getSales_invoice_number().equals(selectedInvoiceItem)){
                recordInvID.add(si.getSales_invoice_id());
            }
        }
        
        List<SalesReturn> salesReturn = new ArrayList<>();
        
        for (Integer i : recordInvID){
            for (SalesReturn sr : SRList){
                if (i == sr.getSales_invoice_id()){
                    salesReturn.add(sr);
                }
            }
        }
        
        SRList.removeAll(salesReturn);
        
        removelistPIPanelComponents();
        setListPIPanel();
    }
    
    private List<SalesReturn> getSRDetailList(){
        if (SRList == null){
            SRList = new ArrayList<>();
        }

        if (checkDuplicateSRID()){
            JOptionPane.showMessageDialog(inventoryPanel, "Duplicate Return ID is not acceptable!", "Warning", 0);
        }else if (checkNull_InvalidComponentsData()){
            JOptionPane.showMessageDialog(inventoryPanel, "* Empty value is not acceptable \n "
                    + "or quantity not valid!!", "Warning", 0);
        }else {
            List<Integer> recordInvID = new ArrayList<>();
            Object selectedInvoiceItem = invoiceNumber_selectedBox.getSelectedItem();
            for (SalesInvoice si : dbAllSalesInvoiceInfoNotCleared){
                if (si.getSales_invoice_number().equals(selectedInvoiceItem)){
                    recordInvID.add(si.getSales_invoice_id());
                }
            }
            
            for (int tf_index = 0; tf_index<quantityList.size(); tf_index++){
                if (!quantityList.get(tf_index).getText().equals("0")){
                    SalesReturn salesReturn = new SalesReturn();
                
                    salesReturn.setSales_return_number(returnID_text.getText());

                    Date returnDate = (Date)returnDatePicker.getModel().getValue();
                    salesReturn.setSales_return_date(returnDate);

                    salesReturn.setSales_invoice_id(recordInvID.get(tf_index));


                    String inventoryName = inventoryList.get(tf_index).getText();
                    for (Inventory i : dbAllInventoryInfo){
                        if (i.getName().equals(inventoryName)){
                            salesReturn.setInventory_id(i.getInventory_id());
                            salesReturn.setUnit(i.getUnit());
                            break;
                        }
                    }

                    salesReturn.setQuantity(Double.parseDouble(quantityList.get(tf_index).getText()));
                    salesReturn.setRemark(remarkAreaList.get(tf_index).getText());

                    SRList.add(salesReturn);

                    List<Integer> record = new ArrayList<>();
                    for (int i = 0; i<SRList.size(); i++){
                        for (int j = 1+i; j<SRList.size(); j++){
                            if (SRList.get(i).getSales_invoice_id() == SRList.get(j).getSales_invoice_id()){
                                SRList.set(i, SRList.get(j));
                                record.add(j);
                            }
                        }
                    }

                    for (Integer i : record){
                        SRList.remove((int)i);
                    }
                    record.clear();
                }
            }
            
            return SRList;
        }
        return null;
    }
    
    private boolean checkDuplicateSRID(){
        if (!returnID_text.getText().equals("") && dbAllSalesReturnInfo != null){
            String returnID = returnID_text.getText().toUpperCase();
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (returnID.equals(sr.getSales_return_number())){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkNull_InvalidComponentsData(){
         if (returnID_text.getText().equals("")){
            return true;
        }
        
        if (returnDatePicker.getModel().getValue() == null){
            return true;
        }
        
        if (customer_text.getText().equals("")){
            return true;
        }
        
        if (invoiceNumber_selectedBox.getSelectedIndex() == -1){
            return true;
        }
        
        for (JTextField tf : quantityList){
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
        
        for (int i = 0; i<quantityList.size(); i++){
            if (!quantityList.get(i).getText().equals("0") && remarkAreaList.get(i).getText().equals("")){
                return true;
            }
        }

        return false;
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
    
    private void setInvoiceIDComboxItem(){
        getDBAllSalesInvoiceInfo();
        String record = null;
        
        if (invoiceNumber_selectedBox != null){
            invoiceNumber_selectedBox.removeAllItems();
        }
        
        if (dbAllSalesInvoiceInfoNotCleared != null){
            for (SalesInvoice si: dbAllSalesInvoiceInfoNotCleared){
                if (record == null || !record.equals(si.getSales_invoice_number())){
                    invoiceNumber_selectedBox.addItem(si.getSales_invoice_number());
                    record = si.getSales_invoice_number();
                }
            }
        }
    }
    
    private void getSOID_SIID_List(){
        if (SO_SIList == null){
            SO_SIList = new ArrayList<>();
        }else {
            SO_SIList.removeAll(SO_SIList);
        }
        
        String record = null;
        int index = 0;
        if (dbAllSalesOrderInfoCleared != null){
            for (SalesOrder so : dbAllSalesOrderInfoCleared){
                if (record == null || !record.equals(so.getSales_order_number()) || 
                        (record.equals(so.getSales_order_number()) && 
                            SO_SIList.get(index-1).getSO_ID() > so.getSales_order_id())){
                    SO_SI ss = new SO_SI();
                    ss.setSalesOrderNumber(so.getSales_order_number());
                    ss.setSO_ID(so.getSales_order_id());
                    if(record != null && record.equals(so.getSales_order_number())){
                        SO_SIList.remove(index-1);
                        index--;
                    }
                    SO_SIList.add(ss);
                    index++;
                    record = so.getSales_order_number();
                    for (Customer c : dbAllCustomerInfo){
                        if (so.getCustomer_id() == c.getCustomer_id()){
                            ss.setCustomerName(c.getCompany_name());
                        }
                    }
                }
            }
        }else {
            SO_SIList = null;
        }
        
        if (dbAllSalesInvoiceInfoNotCleared != null){
            for (SalesInvoice si : dbAllSalesInvoiceInfoNotCleared){
                for (SO_SI ss : SO_SIList){
                    if (si.getSales_order_id() == ss.getSO_ID()){
                        ss.setSalesInvoiceNumber(si.getSales_invoice_number());
                        ss.setSI_ID(si.getSales_invoice_id());
                        break;
                    }
                }
            }
        }
        for (SO_SI ss : SO_SIList){
            System.out.println(ss);
        }
    }
    
    private void setSaveButtonPanel(){
        JPanel saveButtonPanel = new JPanel();
        
        saveButtonPanel.setLayout(new FlowLayout());
        saveButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        salesReturnRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
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
        if (SRList == null || SRList.isEmpty()){
            JOptionPane.showMessageDialog(salesReturnRootPanel, "Empty Data cannot be save!! \n "
                    + "Please make sure press \"+\" before saving!!", "Saving Waring", 0);
        }else {
            String record = null;
            List<String> InvNumberList = new ArrayList<>();
            if (SRList != null){
                for (SalesReturn sr : SRList){
                    for (SO_SI ss : SO_SIList){
                        if (sr.getSales_invoice_id() == ss.getSI_ID()&& (record == null || !record.equals(ss.getSalesInvoiceNumber()))){
                            InvNumberList.add(ss.getSalesInvoiceNumber());
                            record = ss.getSalesInvoiceNumber();
                            break;
                        }
                    }
                }
            }
            
            String sRec = null;
            for(String s : InvNumberList){
                if (sRec == null){
                    sRec = s + " ";
                }else {
                    sRec += s + " ";
                }
            }
            
            int resultDialog = JOptionPane.showConfirmDialog(salesReturnRootPanel, "Do you want save with invoice: " + sRec, 
                    "Saving Notice", 1);
            if (resultDialog == 0){
                int insert_count = new JDBCTemplate().insertDataIntoDBSalesReturn(SRList);
                
                List<Double> recordQuantityList = new ArrayList<>();
                for (SalesReturn sr : SRList){
                    for (SO_SI ss : SO_SIList){
                        if (sr.getSales_invoice_id() == ss.getSI_ID()){
                            for (SalesOrder so : dbAllSalesOrderInfoCleared){
                                if (so.getSales_order_number().equals(ss.getSalesOrderNumber())){
                                    recordQuantityList.add(so.getQuantity());
                                }
                            }
                            break;
                        }
                    }
                    
                }
                List<Integer> recordSINumber = new ArrayList<>();
                for (SalesReturn sr : SRList){
                    for (Double d : recordQuantityList){
                        if (sr.getQuantity() == d){
                            recordSINumber.add(sr.getSales_invoice_id());
                            break;
                        }
                    }
                }
                
                int update_count = new JDBCTemplate().updateSalesInvoiceDataIntoDB(recordSINumber, 1);
                if (insert_count > 0 || update_count > 0){
                    JOptionPane.showMessageDialog(salesReturnRootPanel, "Save sucessfully!!", "Notice", 1);
                }
                refreshComponents();
            }else if (resultDialog == 2){
                refreshComponents();
            }
        }
    }
    
    private void refreshComponents(){
         setInvoiceIDComboxItem();
        
        returnID_text.setText("");
        returnID_text.setEnabled(true);
        
        returnDatePicker.getComponent(1).setEnabled(true);
        
        invoiceNumber_selectedBox.setSelectedIndex(-1);
        
        customer_text.setText(" ");
        
        if (SRList != null){
            SRList.clear();
            removelistPIPanelComponents();
            setListPIPanel();
            setInvoiceIDComboxItem();
            customer_text.setText(" ");
        }    
    }
}
