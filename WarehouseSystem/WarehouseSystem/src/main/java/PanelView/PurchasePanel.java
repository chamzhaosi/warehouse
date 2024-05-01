/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import Adapter.WindowAdapter;
import DBConnector.JDBCTemplate;
import Dialog.NewPurchaseInvoiceDialog;
import Dialog.NewPurchaseOrderDialog;
import Dialog.NewPurchaseReturnDialog;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.Supplier;
import Table.JTableBg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class PurchasePanel extends JPanel{
    private JPanel orderPanel = null;
    private JPanel invoicePanel = null;
    private JPanel returnPanel = null;
    private JPanel orderButtonPanel = null;
    private JPanel invoiceButtonPanel = null;
    private JPanel returnButtonPanel = null;
    private DefaultTableModel orderTableModel = null;
    private DefaultTableModel invoiceTableModel = null;
    private DefaultTableModel returnTableModel = null;
    private JTable orderTable = null;
    private JTable invoiceTable = null;
    private JTable returnTable = null;
    private JButton orderAddButton = null;    
    private JButton invoiceAddButton = null;
    private JButton returnAddButton = null;

    private List<PurchaseOrder> dbAllPurchaseOrderInfoNotCleared = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    private List<PurchaseInvoice> dbAllPurchaseInvoiceInfoNotCleared = null;
    private List<PurchaseInvoice> dbAllPurchaseInvoiceInfo = null;
    private List<PurchaseReturn> dbAllPurchaseReturnInfo = null;
    private List<Inventory> dbAllInventoryInfo = null;
    private List<Employee> dbAllEmployeeInfo = null;
    private List<Received> dbAllReceivedInfo = null;
    private List<Supplier> dbAllSupplierInfo = null;
            
    public PurchasePanel(){
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(3,1,0,10));
        getDBAllPurchaseOrderInfo();
        getDBAllPurchaseInvoiceInfo();
        getDBAllPurchaseReturnInfo();
        getDBAllInventoryInfo();
        getDBAllEmployeeInfo();
        getDBAllReceivedInfo();
        getDBAllSupplierInfo();
        setOrderPanel();
        setInvoicePanel();
        setReturnPanel();
    }
    
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoNotCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 0);
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", "purchase_order_number", 1);
    }
    
    private void getDBAllPurchaseInvoiceInfo(){
        dbAllPurchaseInvoiceInfoNotCleared = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", "purchase_invoice_number", 0);
        dbAllPurchaseInvoiceInfo = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", "purchase_invoice_number");
    }
    
    private void getDBAllPurchaseReturnInfo(){
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", "purchase_return_number");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllEmployeeInfo(){
        dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllReceivedInfo(){
        dbAllReceivedInfo = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number");
    }
    
    private void getDBAllSupplierInfo(){
        dbAllSupplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
    }
    
    //Order Panel
    private void setOrderPanel(){
        orderPanel = new JPanel();
        orderPanel.setBackground(new Color(196, 132, 116));
        orderPanel.setBorder(new LineBorder(Color.GRAY));
        orderPanel.setLayout(new BorderLayout());
        this.add(orderPanel);
        
        setOrderLabel();
        setOrderTable();
        setOrderButtonPanel();
    }
    
    private void setOrderLabel(){
        JLabel orderLabel = new JLabel("<< PURCHASES ORDER >>");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderLabel.setFont(new Font("", Font.BOLD, 20));
        
        orderPanel.add(orderLabel, BorderLayout.NORTH);
    }
    
    private void setOrderTable(){
        String columns[] = 
        {"OrderID", "Inventory Name", "Quantity", "Unit", "Order Date", "Expected Receive Date ", "Emplyoee Name"};
        
        Object[][] orderData = getOrderData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, double.class, String.class, Date.class, Date.class, String.class};
        
        orderTableModel = new DefaultTableModel(orderData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        orderTable = new JTableBg(orderTableModel);
        orderTable.setCellSelectionEnabled(true);
        orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = orderTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(orderTable);
        sp.setBorder(new EmptyBorder(10,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        orderPanel.add(sp, BorderLayout.CENTER);
    }
    
    private void setOrderButtonPanel(){
        orderButtonPanel = new JPanel();
        orderButtonPanel.setLayout(new FlowLayout());
        orderPanel.add(orderButtonPanel, BorderLayout.SOUTH);

        setOrderAddButton();
    }

    private void setOrderAddButton(){
        orderAddButton = new JButton("Add");
        orderButtonPanel.add(orderAddButton);
        
        orderAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callingNewPurchaseOrderDialog();
            }
        
        });
    }
    
    private void callingNewPurchaseOrderDialog(){
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
                        updateNewOrderInfo();
                    }
                });
            }

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewOrderInfo();
                    }
                });
            }
        });
    }
    
    private Object[][] getOrderData(){
        if (dbAllPurchaseOrderInfoNotCleared != null){
            Object [][] data = new Object[dbAllPurchaseOrderInfoNotCleared.size()][7];
            for (int i = 0; i< dbAllPurchaseOrderInfoNotCleared.size(); i++){
                data[i][0] = dbAllPurchaseOrderInfoNotCleared.get(i).getPurchase_order_number();
                
                int inventory_id = dbAllPurchaseOrderInfoNotCleared.get(i).getInventory_id();
                for (Inventory inv : dbAllInventoryInfo){
                    if(inv.getInventory_id() == inventory_id){
                        data[i][1] = inv.getName();
                        break;
                    }
                }
                
                data[i][2] = dbAllPurchaseOrderInfoNotCleared.get(i).getQuantity();
                data[i][3] = dbAllPurchaseOrderInfoNotCleared.get(i).getUnit();
                data[i][4] = dbAllPurchaseOrderInfoNotCleared.get(i).getOrder_date();
                data[i][5] = dbAllPurchaseOrderInfoNotCleared.get(i).getExpect_received_date();
                int staff_id = dbAllPurchaseOrderInfoNotCleared.get(i).getStaff_id();
                for (Employee e : dbAllEmployeeInfo){
                    if(e.getStaff_id() == staff_id){
                        data[i][6] = e.getName();
                        break;
                    }
                }
            }

            return data;
        }
        
        return null;
    }
    
    public void updateNewOrderInfo(){
        getDBAllPurchaseOrderInfo();
        getDBAllEmployeeInfo();
        getDBAllInventoryInfo();
        
        orderTableModel.setRowCount(0);
        Object[][] updateNewOrderData = getOrderData();
        
        if (updateNewOrderData != null){
            for (int i=0; i<updateNewOrderData.length; i++){
                orderTableModel.addRow(updateNewOrderData[i]);
            }
        }
    }
    
    //Invoice Panel
    private void setInvoicePanel(){
        invoicePanel = new JPanel();
        invoicePanel.setBackground(new Color(196, 132, 116));
        invoicePanel.setBorder(new LineBorder(Color.GRAY));
        invoicePanel.setLayout(new BorderLayout());
        this.add(invoicePanel);
        
        setInvoiceLabel();
        setInvoiceTabel();
        setInvoiceButtonPanel();
    }
    
    private void setInvoiceLabel(){
        JLabel invoiceLabel = new JLabel("<< PURCHASES INVOICE >>");
        invoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invoiceLabel.setFont(new Font("", Font.BOLD, 20));
        
        invoicePanel.add(invoiceLabel, BorderLayout.NORTH);
    }
    
    private void setInvoiceTabel(){
         String columns[] = 
            {"InvoiceID", "Invoice Date", "Supplier Name", "Received Number", "OrderID",
                "Inventory Name", "Total Amount", "Employee Name"};
         
         Object[][] invoiceData = getInvoiceData();
         
         final Class[] columnClass = new Class[]{
            String.class, Date.class, String.class, String.class, String.class, String.class, 
             Double.class, String.class};
        
        invoiceTableModel = new DefaultTableModel(invoiceData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        invoiceTable = new JTableBg(invoiceTableModel);
        invoiceTable.setCellSelectionEnabled(true);
        invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        invoiceTable.setGridColor(Color.YELLOW);
        ListSelectionModel selectionModel = invoiceTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(invoiceTable);
        sp.setBorder(new EmptyBorder(10,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        invoicePanel.add(sp, BorderLayout.CENTER);
    }
    
    private void setInvoiceButtonPanel(){
        invoiceButtonPanel = new JPanel();
        invoiceButtonPanel.setLayout(new FlowLayout());
        invoicePanel.add(invoiceButtonPanel, BorderLayout.SOUTH);
        
        setInvoiceAddButton();
    }
    
    private void setInvoiceAddButton(){
        invoiceAddButton = new JButton("Add");
        invoiceButtonPanel.add(invoiceAddButton);
        
        invoiceAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callingNewPurchaseInvoiceDialog();
            }        
        });
    }
    
    private Object[][] getInvoiceData(){
        if (dbAllPurchaseInvoiceInfoNotCleared != null){
            Object [][] data = new Object[dbAllPurchaseInvoiceInfoNotCleared.size()][8];
            for (int i = 0; i< dbAllPurchaseInvoiceInfoNotCleared.size(); i++){
                data[i][0] = dbAllPurchaseInvoiceInfoNotCleared.get(i).getPurchase_invoice_number();
                data[i][1] = dbAllPurchaseInvoiceInfoNotCleared.get(i).getPurchase_invoice_date();
                
                int received_id = dbAllPurchaseInvoiceInfoNotCleared.get(i).getReceived_id();
                for (Received r : dbAllReceivedInfo){
                    if (r.getReceived_id() == received_id){
                        data[i][3] = r.getReceived_number();
                        
                        for (Supplier s : dbAllSupplierInfo){
                            if (s.getSupplier_id() == r.getSupplier_id()){
                                data[i][2] = s.getCompany_name();
                                break;
                            }
                        }
                        for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                            if (r.getPurchase_order_id() == po.getPurchase_order_id()){
                                data[i][4] = po.getPurchase_order_number();
                                
                                for (Inventory inv : dbAllInventoryInfo){
                                    if (inv.getInventory_id() == po.getInventory_id()){
                                        data[i][5] = inv.getName();
                                        break;
                                    }
                                }
                                break;
                            }
                            
                        }
                    }
                }

                data[i][6] = dbAllPurchaseInvoiceInfoNotCleared.get(i).getTotal_price();
                
                int staff_id = dbAllPurchaseInvoiceInfoNotCleared.get(i).getStaff_id();
                for (Employee e : dbAllEmployeeInfo){
                    if(e.getStaff_id() == staff_id){
                        data[i][7] = e.getName();
                    }
                }
            }
            return data;
        }
        return null;
    }
    
    private void callingNewPurchaseInvoiceDialog(){
        NewPurchaseInvoiceDialog newPurchaseInvoiceDialog = new NewPurchaseInvoiceDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Purchase Invoice");
        newPurchaseInvoiceDialog.setBounds(450, 220, 800, 500);
        newPurchaseInvoiceDialog.setVisible(true);
        
        newPurchaseInvoiceDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewInvoiceInfo();
                    }
                });
            }

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewInvoiceInfo();
                    }
                });
            }
        });
    }
    
    public void updateNewInvoiceInfo(){
        getDBAllPurchaseInvoiceInfo();
        getDBAllReceivedInfo();
        getDBAllEmployeeInfo();
        getDBAllSupplierInfo();
        
        invoiceTableModel.setRowCount(0);
        Object[][] updateNewInvoiceData = getInvoiceData();
        
        if (updateNewInvoiceData != null){
            for (int i=0; i<updateNewInvoiceData.length; i++){
                invoiceTableModel.addRow(updateNewInvoiceData[i]);
            }
        }
    }
    
    //Return Panel
    private void setReturnPanel(){
        returnPanel = new JPanel();
        returnPanel.setBackground(new Color(196, 132, 116));
        returnPanel.setBorder(new LineBorder(Color.GRAY));
        returnPanel.setLayout(new BorderLayout());
        this.add(returnPanel);
        
        setReturnLabel();
        setReturnTable();
        setReturnButtonPanel();
    }
    
    private void setReturnLabel(){
        JLabel returnLabel = new JLabel("<< PURCHASES RETURN >>");
        returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        returnLabel.setFont(new Font("", Font.BOLD, 20));
        
        returnPanel.add(returnLabel, BorderLayout.NORTH);
    }
    
    private void setReturnTable(){
        String columns[] = 
            {"RetureID","Reture Date", "InvoiceID", "Inventory Name", "Quantity", "Unit", "Remark"};
        
        Object[][] returnData = getReturnData();
        
        final Class[] columnClass = new Class[]{
            String.class, Date.class,  String.class, String.class, Double.class, String.class, String.class};
        
        returnTableModel = new DefaultTableModel(returnData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        returnTable = new JTableBg(returnTableModel);
        returnTable.setCellSelectionEnabled(true);
        returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = returnTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(returnTable);
        sp.setBorder(new EmptyBorder(10,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        returnPanel.add(sp, BorderLayout.CENTER);
    }
    
    private void setReturnButtonPanel(){
        returnButtonPanel = new JPanel();
        returnButtonPanel.setLayout(new FlowLayout());
        returnPanel.add(returnButtonPanel, BorderLayout.SOUTH);
        
        setReturnAddPanel();
    }
    
    private void setReturnAddPanel(){
        returnAddButton = new JButton("Add");
        returnButtonPanel.add(returnAddButton);
        
        returnAddButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                callingNewPurchaseReturnDialog();
            }
        
        });
    }
    
    private Object[][] getReturnData(){
        if (dbAllPurchaseReturnInfo != null){
            Object [][] data = new Object[dbAllPurchaseReturnInfo.size()][7];
            for (int i = 0; i< dbAllPurchaseReturnInfo.size(); i++){
                data[i][0] = dbAllPurchaseReturnInfo.get(i).getPurchase_return_number();
                
                data[i][1] = dbAllPurchaseReturnInfo.get(i).getPurchase_return_date();
                
                int purchase_invoice_id = dbAllPurchaseReturnInfo.get(i).getPurchase_invoice_id();
                for(PurchaseInvoice pv : dbAllPurchaseInvoiceInfo){
                    if (pv.getPurchase_invoice_id() == purchase_invoice_id){
                        data[i][2] = pv.getPurchase_invoice_number();
                    }
                }
                
                int inventory_id = dbAllPurchaseReturnInfo.get(i).getInventory_id();
                for (Inventory iv : dbAllInventoryInfo){
                    if (iv.getInventory_id() == inventory_id){
                        data[i][3] = iv.getName();
                    }
                }
                
                data[i][4] = dbAllPurchaseReturnInfo.get(i).getQuantity();
                data[i][5] = dbAllPurchaseReturnInfo.get(i).getUnit();
                data[i][6] = dbAllPurchaseReturnInfo.get(i).getRemark();
            }
            return data;
        }
        return null;
    }
    
    private void callingNewPurchaseReturnDialog(){
        NewPurchaseReturnDialog newPurchaseReturnDialog = new NewPurchaseReturnDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Purchase Return");
        newPurchaseReturnDialog.setBounds(450, 220, 800, 500);
        newPurchaseReturnDialog.setVisible(true);
        
        newPurchaseReturnDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewReturnInfo();
                    }
                });
            }

            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        updateNewReturnInfo();
                    }
                });
            }
        });
    }
    
    public void updateNewReturnInfo(){
        updateNewInvoiceInfo();
        getDBAllPurchaseReturnInfo();
        
        returnTableModel.setRowCount(0);
        Object[][] updateNewReturnData = getReturnData();
        
        if (updateNewReturnData != null){
            for (int i=0; i<updateNewReturnData.length; i++){
                returnTableModel.addRow(updateNewReturnData[i]);
            }
        }
    }
}
