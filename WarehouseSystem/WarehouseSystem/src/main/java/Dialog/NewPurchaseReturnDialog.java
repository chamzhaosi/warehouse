/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dialog;

import DBConnector.JDBCTemplate;
import DateFormat.CustomizingDateFormat;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
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
public class NewPurchaseReturnDialog extends JDialog{
    private JPanel purchaseReturnRootPanel = null;
    private JPanel inventoryPanel = null;
    private JPanel inventoryDetailPanel = null;
    private JPanel addOtherInvoiceDetailPanel = null;
    private JPanel listInvPanel = null;
    private JTextField returnID_text = null;
    private JTextField supplier_text = null;
    private JDatePickerImpl returnDatePicker = null;
    private JComboBox invoiceNumber_selectedBox = null;
    
    
    private List<Received> dbAllReceivedInfoCleared = null;
    private List<PurchaseReturn> dbAllPurchaseReturnInfo = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    private List<Inventory> dbAllInventoryInfo= null;
    private List<Supplier> dbAllSupplierInfo= null;
    private List<PurchaseInvoice> dbAllPurchaseInvoiceInfoNotCleared = null;
    
    private List<Received_PO_PI> received_PO_PIList = null;
    private List<JTextField> quantityList = new ArrayList<>();
    private List<JTextField> inventoryList = new ArrayList<>();
    private List<JTextField> totalList = new ArrayList<>();
    private List<JTextArea> remarkAreaList = new ArrayList<>();
    private List<String> invoiceNumberList = null;
    
    private List<PurchaseReturn> PRList = null;
    
    public NewPurchaseReturnDialog (JFrame frame, String title){
        super(frame, title);
        
        purchaseReturnRootPanel = new JPanel();
        purchaseReturnRootPanel.setLayout(new BorderLayout());
        purchaseReturnRootPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.add(purchaseReturnRootPanel);
        
        getDBAllSupplierInfo();
        getDBAllInventoryInfo();
        getDBAllReceivingInfo();
        getDBAllPurchaseOrderInfo();
        getDBAllPurchaseInvoiceInfo();
        getDBAllPurchaseReturnInfo();
        setPurchaceReturnDetailPanel();
        setSaveButtonPanel();
    }

    private void getDBAllReceivingInfo(){
        dbAllReceivedInfoCleared = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number", 1);
    }
    
    private void getDBAllPurchaseReturnInfo(){
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", "purchase_return_number");
    }

    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 1);
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllSupplierInfo(){
        dbAllSupplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    private void getDBAllPurchaseInvoiceInfo(){
        dbAllPurchaseInvoiceInfoNotCleared = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", "purchase_invoice_number", 0);
    }
    
    private void setPurchaceReturnDetailPanel(){
        JPanel purchaseReturnDetailPanel = new JPanel();
        purchaseReturnDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        purchaseReturnRootPanel.add(purchaseReturnDetailPanel, BorderLayout.NORTH);
        
        JLabel returnIDLabel = new JLabel("Return ID*: ");
        returnID_text = new JTextField(20);
        returnID_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel returnDateLabel = new JLabel("Return Date*: ");
        returnDatePicker = new JDatePickerImpl(setDatePickerPanel(), new CustomizingDateFormat());
        
        JLabel supplierLabel = new JLabel("Supplier Name*: ");
        supplier_text =  new JTextField();
        supplier_text.setEditable(false);
        
        JLabel invoiceIDLabel = new JLabel("Invoice ID*: ");
        invoiceNumber_selectedBox = new JComboBox();
        setInvoiceIDComboxItem();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        purchaseReturnDetailPanel.add(returnIDLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        purchaseReturnDetailPanel.add(returnID_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        purchaseReturnDetailPanel.add(returnDateLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        purchaseReturnDetailPanel.add(returnDatePicker, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        purchaseReturnDetailPanel.add(supplierLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        purchaseReturnDetailPanel.add(supplier_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        purchaseReturnDetailPanel.add(invoiceIDLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        purchaseReturnDetailPanel.add(invoiceNumber_selectedBox, gbc);
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
        getReceived_POID_PIID_List();
        
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBorder(new LineBorder(Color.GRAY));
        purchaseReturnRootPanel.add(inventoryPanel, BorderLayout.CENTER);
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
        if (received_PO_PIList != null){
            for (Received_PO_PI rp : received_PO_PIList){
                if (selectedItem != null && selectedItem.equals(rp.getPurchaseInvoiceNumber())){ 
                    if (supplier_text.getText().equals("") || supplier_text.getText().equals(" ")){
                        supplier_text.setText(rp.getSupplierName());
                    }else if (!supplier_text.getText().equals("") && !rp.getSupplierName().equals(supplier_text.getText())){
                        JOptionPane.showMessageDialog(inventoryPanel, "Difference supplier name is not acceptable!", "Warning", 2);
                        break;
                    }
                    System.out.println("123");
                    List<Double> recoredUnitPriceList = new ArrayList<>();
                    for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfoNotCleared){
                        if (rp.getPurchaseInvoiceNumber().equals(pi.getPurchase_invoice_number())){
                            for (Received r : dbAllReceivedInfoCleared){
                                if (pi.getReceived_id() == rp.getREC_ID()){
                                    recoredUnitPriceList.add(pi.getUnit_price());
                                    break;
                                }
                            }
                        }
                    }
                    

                    for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                        System.out.println("456");
                        if ((rp.getPurchaseOrderNumber().equals(po.getPurchase_order_number()) && (recoredUnitPriceList.size() != 0))){ 
                            System.out.println("789");
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
                                if (i.getInventory_id() == po.getInventory_id()){
                                    inventoryName_text.setText(i.getName());
                                    break;
                                }
                            }
                            inventoryName_text.setEditable(false);
                            Quantity_text.setText(String.valueOf(po.getQuantity()));
                            unit_text.setText(po.getUnit());
                            unit_text.setEditable(false);
                            
                            if(recoredUnitPriceList != null || !recoredUnitPriceList.isEmpty()){
                                unitPrice_text.setText(String.valueOf(recoredUnitPriceList.get(0)));
                                unitPrice_text.setEditable(false);
                            
                                double totalprice = recoredUnitPriceList.get(0) * po.getQuantity();
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
                                        if (Double.parseDouble(text) <= po.getQuantity() && Double.parseDouble(text) > -1 ){
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
                List<PurchaseReturn> prDetailList = getPRDetailList();
                if (prDetailList != null){
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
                if (PRList == null || PRList.isEmpty()){
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
        if (PRList != null){
            for (PurchaseReturn pr : PRList){
                for (Received_PO_PI rp : received_PO_PIList){
                    if (pr.getPurchase_invoice_id() == rp.getPI_ID() && (record == null || !record.equals(rp.getPurchaseInvoiceNumber()))){
                        invoiceNumberList.add(rp.getPurchaseInvoiceNumber());
                        record = rp.getPurchaseInvoiceNumber();
                        break;
                    }
                }
            }
        }

        for (String s : invoiceNumberList){
            JLabel PINumber = new JLabel(s);
            PINumber.setForeground(Color.red);
            listInvPanel.add(PINumber);
        }
        
        JLabel showTotalLabel = new JLabel();
        double sumPrice = 0;
        for (PurchaseReturn pr : PRList){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfoNotCleared){
                if (pr.getPurchase_invoice_id() == pi.getPurchase_invoice_id()){
                    sumPrice += (pr.getQuantity()* pi.getUnit_price());
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
        for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfoNotCleared){
            if (pi.getPurchase_invoice_number().equals(selectedInvoiceItem)){
                recordInvID.add(pi.getPurchase_invoice_id());
            }
        }
        
        List<PurchaseReturn> purchaseReturn = new ArrayList<>();
        
        for (Integer i : recordInvID){
            for (PurchaseReturn pr : PRList){
                if (i == pr.getPurchase_invoice_id()){
                    purchaseReturn.add(pr);
                }
            }
        }
        
        PRList.removeAll(purchaseReturn);
        
        removelistPIPanelComponents();
        setListPIPanel();
    }
    
    private List<PurchaseReturn> getPRDetailList(){
        if (PRList == null){
            PRList = new ArrayList<>();
        }

        if (checkDuplicatePRID()){
            JOptionPane.showMessageDialog(inventoryPanel, "Duplicate Return ID is not acceptable!", "Warning", 0);
        }else if (checkNull_InvalidComponentsData()){
            JOptionPane.showMessageDialog(inventoryPanel, "* Empty value is not acceptable \n "
                    + "or quantity not valid!!", "Warning", 0);
        }else {
            List<Integer> recordInvID = new ArrayList<>();
            Object selectedInvoiceItem = invoiceNumber_selectedBox.getSelectedItem();
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfoNotCleared){
                if (pi.getPurchase_invoice_number().equals(selectedInvoiceItem)){
                    recordInvID.add(pi.getPurchase_invoice_id());
                }
            }
            
            for (int tf_index = 0; tf_index<quantityList.size(); tf_index++){
                if (!quantityList.get(tf_index).getText().equals("0")){
                    PurchaseReturn purchaseReturn = new PurchaseReturn();
                
                    purchaseReturn.setPurchase_return_number(returnID_text.getText());

                    Date returnDate = (Date)returnDatePicker.getModel().getValue();
                    purchaseReturn.setPurchase_return_date(returnDate);

                    purchaseReturn.setPurchase_invoice_id(recordInvID.get(tf_index));


                    String inventoryName = inventoryList.get(tf_index).getText();
                    for (Inventory i : dbAllInventoryInfo){
                        if (i.getName().equals(inventoryName)){
                            purchaseReturn.setInventory_id(i.getInventory_id());
                            purchaseReturn.setUnit(i.getUnit());
                            break;
                        }
                    }

                    purchaseReturn.setQuantity(Double.parseDouble(quantityList.get(tf_index).getText()));
                    purchaseReturn.setRemark(remarkAreaList.get(tf_index).getText());

                    PRList.add(purchaseReturn);

                    List<Integer> record = new ArrayList<>();
                    for (int i = 0; i<PRList.size(); i++){
                        for (int j = 1+i; j<PRList.size(); j++){
                            if (PRList.get(i).getPurchase_invoice_id() == PRList.get(j).getPurchase_invoice_id()){
                                PRList.set(i, PRList.get(j));
                                record.add(j);
                            }
                        }
                    }

                    for (Integer i : record){
                        PRList.remove((int)i);
                    }
                    record.clear();
                }
            }
            
            return PRList;
        }
        return null;
    }
    
    private boolean checkDuplicatePRID(){
        if (!returnID_text.getText().equals("") && dbAllPurchaseReturnInfo != null){
            String returnID = returnID_text.getText().toUpperCase();
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (returnID.equals(pr.getPurchase_return_number())){
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
        
        if (supplier_text.getText().equals("")){
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
        getDBAllPurchaseInvoiceInfo();
        String record = null;
        
        if (invoiceNumber_selectedBox != null){
            invoiceNumber_selectedBox.removeAllItems();
        }
        
        if (dbAllPurchaseInvoiceInfoNotCleared != null){
            for (PurchaseInvoice pi: dbAllPurchaseInvoiceInfoNotCleared){
                if (record == null || !record.equals(pi.getPurchase_invoice_number())){
                    invoiceNumber_selectedBox.addItem(pi.getPurchase_invoice_number());
                    record = pi.getPurchase_invoice_number();
                }
            }
        }
    }
    
    private void getReceived_POID_PIID_List(){
        if (received_PO_PIList == null){
            received_PO_PIList = new ArrayList<>();
        }else {
            received_PO_PIList.removeAll(received_PO_PIList);
        }
        
        String record = null;
        int index = 0;
        if (dbAllPurchaseOrderInfoCleared != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                if (record == null || !record.equals(po.getPurchase_order_number()) || 
                        (record.equals(po.getPurchase_order_number()) && 
                            received_PO_PIList.get(index-1).getPO_ID() > po.getPurchase_order_id())){
                    Received_PO_PI rp = new Received_PO_PI();
                    rp.setPurchaseOrderNumber(po.getPurchase_order_number());
                    rp.setPO_ID(po.getPurchase_order_id());
                    if(record != null && record.equals(po.getPurchase_order_number())){
                        received_PO_PIList.remove(index-1);
                        index--;
                    }
                    received_PO_PIList.add(rp);
                    index++;
                    record = po.getPurchase_order_number();
                }
            }
        }else {
            received_PO_PIList = null;
        }
        
        if (dbAllReceivedInfoCleared != null){
            for (Received r : dbAllReceivedInfoCleared){
                for (Received_PO_PI rp : received_PO_PIList){
                    if (r.getPurchase_order_id() == rp.getPO_ID()){
                        rp.setReceivedNumber(r.getReceived_number());
                        rp.setREC_ID(r.getReceived_id());
                        for (Supplier s : dbAllSupplierInfo){
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
        
        if (dbAllPurchaseInvoiceInfoNotCleared != null){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfoNotCleared){
                for (Received_PO_PI rp : received_PO_PIList){
                    if (pi.getReceived_id() == rp.getREC_ID()){
                        rp.setPI_ID(pi.getPurchase_invoice_id());
                        rp.setPurchaseInvoiceNumber(pi.getPurchase_invoice_number());
                    }
                }
            }
        }
    }
    
    private void setSaveButtonPanel(){
        JPanel saveButtonPanel = new JPanel();
        
        saveButtonPanel.setLayout(new FlowLayout());
        saveButtonPanel.setBorder(new LineBorder(Color.GRAY));
        
        purchaseReturnRootPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("Save");
        
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               insertPurchaseInvoiceDataToDB();
               refreshComponents();
            }
        });
    }
    
    private void insertPurchaseInvoiceDataToDB(){
        if (PRList == null || PRList.isEmpty()){
            JOptionPane.showMessageDialog(purchaseReturnRootPanel, "Empty Data cannot be save!! \n "
                    + "Please make sure press \"+\" before saving!!", "Saving Waring", 0);
        }else {
            String record = null;
            List<String> InvNumberList = new ArrayList<>();
            if (PRList != null){
                for (PurchaseReturn pr : PRList){
                    for (Received_PO_PI rp : received_PO_PIList){
                        if (pr.getPurchase_invoice_id() == rp.getPI_ID()&& (record == null || !record.equals(rp.getPurchaseInvoiceNumber()))){
                            InvNumberList.add(rp.getPurchaseInvoiceNumber());
                            record = rp.getPurchaseInvoiceNumber();
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
            
            int resultDialog = JOptionPane.showConfirmDialog(purchaseReturnRootPanel, "Do you want save with invoice: " + sRec, 
                    "Saving Notice", 1);
            if (resultDialog == 0){
                int insert_count = new JDBCTemplate().insertDataIntoDBPurchaseReturn(PRList);
                
                List<Double> recordQuantityList = new ArrayList<>();
                for (PurchaseReturn pr : PRList){
                    for (Received_PO_PI rp : received_PO_PIList){
                        if (pr.getPurchase_invoice_id() == rp.getPI_ID()){
                            for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                                if (po.getPurchase_order_number().equals(rp.getPurchaseOrderNumber())){
                                    recordQuantityList.add(po.getQuantity());
                                }
                            }
                            break;
                        }
                    }
                    
                }
                List<Integer> recordPINumber = new ArrayList<>();
                for (PurchaseReturn pr : PRList){
                    for (Double d : recordQuantityList){
                        if (pr.getQuantity() == d){
                            recordPINumber.add(pr.getPurchase_invoice_id());
                            break;
                        }
                    }
                }
                
                int update_count = new JDBCTemplate().updatePurchaseInvoiceDataIntoDB(recordPINumber, 1);
                if (insert_count > 0 || update_count > 0){
                    JOptionPane.showMessageDialog(purchaseReturnRootPanel, "Save sucessfully!!", "Notice", 1);
                }
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
        
        supplier_text.setText(" ");
        
        PRList.clear();
        
        removelistPIPanelComponents();
        setListPIPanel();    
    }
}
