/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Customer;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.Received;
import PsnClass.Received_PO_PI;
import PsnClass.SalesInvoice;
import PsnClass.SalesOrder;
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
public class NewSalesInvoiceDialog extends JDialog{
    private JPanel salesInvoiceRootPanel = null;
    private JPanel inventoryPricePanel = null;
    private JPanel addOtherSODetailPanel = null;
    private JPanel inventoryPriceDetailPanel = null;
    private JPanel listSIPanel = null;
    private JTextField invoiceID_text = null;
    private JTextField customer_text = null;
    private JDatePickerImpl invoiceDatePicker = null;
    private JComboBox SONumber_selectedBox = null;
    private JComboBox employee_selectedBox = null;
    
    private List<SalesOrder> dbAllSOInfoNotCleared = null;
    private List<Employee> dbAllEmployeeInfo = null;
    private List<Customer> dbAllCustomerInfo = null;
    private List<Inventory> dbAllInventoryInfo= null;
    private List<SalesInvoice> dbAllSalesInvoiceInfo = null;
    
    private List<SalesInvoice> SIList = null;
    private List<String> SONumberList = null;
    
    List<JTextField> unitPriceList = new ArrayList<>();
    List<JTextField> totalPriceList = new ArrayList<>();
    
    int recordCustomerID = 0;

    public NewSalesInvoiceDialog(JFrame frame, String title){
        super(frame, title);
        
        salesInvoiceRootPanel = new JPanel();
        salesInvoiceRootPanel.setLayout(new BorderLayout());
        salesInvoiceRootPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.add(salesInvoiceRootPanel);
        
        getDBAllEmployeeInfo();
        getDBAllCustomerInfo();       
        getDBAllSalesOrderInfo();   
        getDBAllInventoryInfo();      
        getDBAllSalesInvoiceInfo();     
        setSalesInvoiceDetailPanel();
        setSaveButtonPanel();
    }

    private void getDBAllEmployeeInfo(){
       dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllCustomerInfo(){
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    private void getDBAllSalesOrderInfo(){
        dbAllSOInfoNotCleared = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", "sales_order_number", 0);
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllSalesInvoiceInfo(){
        dbAllSalesInvoiceInfo = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", "sales_invoice_number");
    }
    
    private void setSalesInvoiceDetailPanel(){
        JPanel salesInvoiceDetailPanel = new JPanel();
        salesInvoiceDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        salesInvoiceRootPanel.add(salesInvoiceDetailPanel, BorderLayout.NORTH);
        
        JLabel invoiceIDLabel = new JLabel("Invoice ID*: ");
        invoiceID_text = new JTextField(20);
        invoiceID_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel invoicedDateLabel = new JLabel("Invoice Date*: ");
        invoiceDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel CustomerLabel = new JLabel("Customer Name*: ");
        customer_text =  new JTextField();
        customer_text.setEditable(false);
        
        JLabel SOIDLabel = new JLabel("Sales Order ID*: ");
        SONumber_selectedBox = new JComboBox();
        seSOIDComboxItem();
        
        JLabel employeeLabel = new JLabel("Employee Name*: ");
        employee_selectedBox = new JComboBox();
        setEmployeeComboxItem();
        
        JButton addNewEmployeeButton = new JButton("Add");
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        salesInvoiceDetailPanel.add(invoiceIDLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        salesInvoiceDetailPanel.add(invoiceID_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        salesInvoiceDetailPanel.add(invoicedDateLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        salesInvoiceDetailPanel.add(invoiceDatePicker, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        salesInvoiceDetailPanel.add(CustomerLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        salesInvoiceDetailPanel.add(customer_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        salesInvoiceDetailPanel.add(SOIDLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        salesInvoiceDetailPanel.add(SONumber_selectedBox, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        salesInvoiceDetailPanel.add(employeeLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        salesInvoiceDetailPanel.add(employee_selectedBox, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 2;
        gbc.gridy = 4;
        salesInvoiceDetailPanel.add(addNewEmployeeButton, gbc);
        
        addNewEmployeeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callAddEmployeeDialog();
            }
        });
        
        SONumber_selectedBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = SONumber_selectedBox.getSelectedItem();
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
                        setAddOtherSODetailPanel();
                        inventoryPriceDetailPanel.revalidate();
                        inventoryPriceDetailPanel.repaint();
                    }
                }
            }
        });
    }

    private void setInventoryPricePanel(){
        //getReceivedWithPOIDList();
        
        inventoryPricePanel = new JPanel();
        inventoryPricePanel.setLayout(new BorderLayout());
        inventoryPricePanel.setBorder(new LineBorder(Color.GRAY));
        salesInvoiceRootPanel.add(inventoryPricePanel, BorderLayout.CENTER);
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
        if (dbAllSOInfoNotCleared != null){
            for (SalesOrder so : dbAllSOInfoNotCleared){
                if (selectedItem != null && selectedItem.equals(so.getSales_order_number())){
                    if (customer_text.getText().equals("") || customer_text.getText().equals(" ")){
                        for (Customer c : dbAllCustomerInfo){
                            if (c.getCustomer_id() == so.getCustomer_id()){
                                customer_text.setText(c.getCompany_name());
                                recordCustomerID = c.getCustomer_id();
                                break;
                            }
                        }
                    }else if (!customer_text.getText().equals(" ") && so.getCustomer_id() != recordCustomerID){
                        JOptionPane.showMessageDialog(inventoryPricePanel, "Difference supplier name is not acceptable!", "Warning", 2);
                        break;
                    }

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
                        if (i.getInventory_id() == so.getInventory_id()){
                            inventoryName_text.setText(i.getName());
                            break;
                        }
                    }
                    inventoryName_text.setEditable(false);
                    Quantity_text.setText(String.valueOf(so.getQuantity()));
                    Quantity_text.setEditable(false);
                    unit_text.setText(so.getUnit());
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
    
    private void setAddOtherSODetailPanel(){
        if (addOtherSODetailPanel == null){
            addOtherSODetailPanel = new JPanel();
        }
        addOtherSODetailPanel.setLayout(new GridLayout(2,1));
        
        inventoryPricePanel.add(addOtherSODetailPanel, BorderLayout.SOUTH);
        
        setAddSubButtonPanel();
    }

    private void setAddSubButtonPanel(){
        JPanel addSubButtonPanel = new JPanel();
        addSubButtonPanel.setLayout(new FlowLayout());
        addSubButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        addOtherSODetailPanel.add(addSubButtonPanel);
        
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        removeButton.setEnabled(false);
        
        addSubButtonPanel.add(addButton);
        addSubButtonPanel.add(removeButton);
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SalesInvoice> siDetailList = getSIDetailList();
                if (siDetailList != null){
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
                if (SIList == null || SIList.isEmpty()){
                    removeButton.setEnabled(false);
                }
            }
        
        });
    }
    
    private void removePIDetail(){
        List<Integer> recordRecID = new ArrayList<>();
        Object selectedReceivedItem = SONumber_selectedBox.getSelectedItem();
        for (SalesOrder so : dbAllSOInfoNotCleared){
            if (so.getSales_order_number().equals(selectedReceivedItem)){
                recordRecID.add(so.getSales_order_id());
            }
        }
        
        List<SalesInvoice> salesInvoices = new ArrayList<>();
        
        for (Integer i : recordRecID){
            for (SalesInvoice si : SIList){
                if (i == si.getSales_order_id()){
                    salesInvoices.add(si);
                }
            }
        }
        
        SIList.removeAll(salesInvoices);
        
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
        setInventoryPriceDetailPanel((String)SONumber_selectedBox.getSelectedItem());
    }
    
    private void setListPIPanel(){
        if (listSIPanel == null){
            listSIPanel = new JPanel();
        }else {
            listSIPanel.removeAll();
        }

        listSIPanel.setBorder(new LineBorder(Color.gray));
        listSIPanel.setLayout(new FlowLayout());
        
        addOtherSODetailPanel.add(listSIPanel);
        
        if (SONumberList == null){
            SONumberList = new ArrayList<>();
        }else {
            SONumberList.removeAll(SONumberList);
        }
        
        String record = null;
        if (SIList != null){
            for (SalesInvoice si : SIList){
                for (SalesOrder so : dbAllSOInfoNotCleared){
                    if ((si.getSales_order_id() == so.getSales_order_id()) && (record == null || !record.equals(so.getSales_order_number()))){
                        SONumberList.add(so.getSales_order_number());
                        record = so.getSales_order_number();
                        break;
                    }
                }
            }
        }

        for (String s : SONumberList){
            JLabel SONumber = new JLabel(s);
            SONumber.setForeground(Color.red);
            listSIPanel.add(SONumber);
        }
        
        JLabel showTotalLabel = new JLabel();
        double sumPrice = 0;
        for (SalesInvoice si : SIList){
            sumPrice += si.getTotal_price();
        }
        showTotalLabel.setText("Sum total price: "+String.valueOf(sumPrice));
        listSIPanel.add(showTotalLabel);

        listSIPanel.revalidate();
        listSIPanel.repaint();
    }

    private List<SalesInvoice> getSIDetailList(){
        if (SIList == null){
            SIList = new ArrayList<>();
        }

        if (checkDuplicateSIID()){
            JOptionPane.showMessageDialog(inventoryPricePanel, "Duplicate Invoice ID is not acceptable!", "Warning", 0);
        }else if (checkNull_InvalidComponentsData()){
            JOptionPane.showMessageDialog(inventoryPricePanel, "* Empty value is not acceptable \n "
                    + "or unit price invalid!!", "Warning", 0);
        }else {
            List<Integer> recordSOID = new ArrayList<>();
            Object selectedSOItem = SONumber_selectedBox.getSelectedItem();
            for (SalesOrder so : dbAllSOInfoNotCleared){
                if (so.getSales_order_number().equals(selectedSOItem)){
                    recordSOID.add(so.getSales_order_id());
                }
            }
            
            for (int tf_index = 0; tf_index<unitPriceList.size(); tf_index++){
                SalesInvoice salesInvoice = new SalesInvoice();
                
                salesInvoice.setSales_invoice_number(invoiceID_text.getText());
                
                Date invoiceDate = (Date)invoiceDatePicker.getModel().getValue();
                salesInvoice.setSales_invoice_date(invoiceDate);
                
                salesInvoice.setSales_order_id(recordSOID.get(tf_index));
                
                Object selectedEmployeeItem = employee_selectedBox.getSelectedItem();
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getName().equals(selectedEmployeeItem)){
                        salesInvoice.setStaff_id(e.getStaff_id());
                    }
                }
                
                salesInvoice.setUnit_price(Double.parseDouble(unitPriceList.get(tf_index).getText()));
                salesInvoice.setTotal_price(Double.parseDouble(totalPriceList.get(tf_index).getText()));
                
                SIList.add(salesInvoice);
                
                List<Integer> record = new ArrayList<>();
                for (int i = 0; i<SIList.size(); i++){
                    for (int j = 1+i; j<SIList.size(); j++){
                        if (SIList.get(i).getSales_order_id() == SIList.get(j).getSales_order_id()){
                            SIList.set(i, SIList.get(j));
                            record.add(j);
                        }
                    }
                }
                
                for (Integer i : record){
                    SIList.remove((int)i);
                }
                record.clear();
            }
            return SIList;
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
        
        if (customer_text.getText().equals("")){
            return true;
        }
        
        if (SONumber_selectedBox.getSelectedIndex() == -1){
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
    
    private boolean checkDuplicateSIID(){
        if (!invoiceID_text.getText().equals("") && dbAllSalesInvoiceInfo != null){
            String invoiceID = invoiceID_text.getText().toUpperCase();
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (invoiceID.equals(si.getSales_invoice_number())){
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
    
    private void seSOIDComboxItem(){
        getDBAllSalesOrderInfo();
        String record = null;
        
        if (SONumber_selectedBox != null){
            SONumber_selectedBox.removeAllItems();
        }
        
        if (dbAllSOInfoNotCleared != null){
            for (SalesOrder so : dbAllSOInfoNotCleared){
                if (record == null || !record.equals(so.getSales_order_number())){
                    SONumber_selectedBox.addItem(so.getSales_order_number());
                    record = so.getSales_order_number();
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

    private void setSaveButtonPanel(){
        JPanel saveButtonPanel = new JPanel();
        
        saveButtonPanel.setLayout(new FlowLayout());
        saveButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        salesInvoiceRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("Save");
        
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               insertSalesInvoiceDataToDB();
            }
        });
    }
    
    private void insertSalesInvoiceDataToDB(){
        if (SIList == null || SIList.isEmpty()){
            JOptionPane.showMessageDialog(salesInvoiceRootPanel, "Empty Data cannot be save!! \n "
                    + "Please make sure press \"+\" before saving!!", "Saving Waring", 0);
        }else {
            String record = null;
            List<String> soNumberList = new ArrayList<>();
            if (SIList != null){
                for (SalesInvoice si : SIList){
                    for (SalesOrder so : dbAllSOInfoNotCleared){
                        if ((si.getSales_order_id() == so.getSales_order_id()) && (record == null || !record.equals(so.getSales_order_number()))){
                            soNumberList.add(so.getSales_order_number());
                            record = so.getSales_order_number();
                            break;
                        }
                    }
                }
            }
            
            String sRec = null;
            for(String s : soNumberList){
                if (sRec == null){
                    sRec = s + " ";
                }else {
                    sRec += s + " ";
                }
            }
            
            int resultDialog = JOptionPane.showConfirmDialog(salesInvoiceRootPanel, "Do you want save with sales order: " + sRec, 
                    "Saving Notice", 1);
            if (resultDialog == 0){
                int insert_count = new JDBCTemplate().insertDataIntoDBSalesInvoice(SIList);
                int update_count = new JDBCTemplate().updateSalesOrderDataIntoDB(soNumberList, 1);
                if (insert_count > 0 && update_count > 0){
                    JOptionPane.showMessageDialog(salesInvoiceRootPanel, "Save sucessfully!!", "Notice", 1);
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

        SONumber_selectedBox.setSelectedIndex(-1);

        customer_text.setText(" ");
        
        employee_selectedBox.setEnabled(true);
        
        if (SIList != null){
            SIList.removeAll(SIList);
            removelistPIPanelComponents();
            setListPIPanel(); 
            seSOIDComboxItem();
            customer_text.setText(" ");
        }   
    }
}
