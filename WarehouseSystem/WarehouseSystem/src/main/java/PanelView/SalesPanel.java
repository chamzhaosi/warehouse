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
import Dialog.NewSalesInvoiceDialog;
import Dialog.NewSalesOrderDialog;
import Dialog.NewSalesReturnDialog;
import PsnClass.Customer;
import PsnClass.Employee;
import PsnClass.Inventory;
import PsnClass.PurchaseInvoice;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.SalesInvoice;
import PsnClass.SalesOrder;
import PsnClass.SalesReturn;
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
public class SalesPanel extends JPanel{
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

    private List<SalesOrder> dbAlSalesOrderInfoNotCleared = null;
    private List<SalesOrder> dbAllSalesOrderInfoCleared = null;
    private List<SalesInvoice> dbAllSalesInvoiceInfoNotCleared = null;
    private List<SalesInvoice> dbAllSalesInvoiceInfo = null;
    private List<SalesReturn> dbAllSalesReturnInfo = null;
    private List<Inventory> dbAllInventoryInfo = null;
    private List<Employee> dbAllEmployeeInfo = null;
    private List<Customer> dbAllCustomerInfo = null;
            
    public SalesPanel(){
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(3,1,0,10));
        
        getDBAllSalesOrderInfo();
        getDBAllSalesInvoiceInfo();
        getDBAllSalesReturnInfo();
        getDBAllInventoryInfo();
        getDBAllEmployeeInfo();
        getDBAllCustomerInfo();
        setOrderPanel();
        setInvoicePanel();
        setReturnPanel();
    }
    
    private void getDBAllSalesOrderInfo(){
        dbAlSalesOrderInfoNotCleared = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", "sales_order_number", 0);
        dbAllSalesOrderInfoCleared = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", "sales_order_number", 1);
    }
    
    private void getDBAllSalesInvoiceInfo(){
        dbAllSalesInvoiceInfoNotCleared = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", "sales_invoice_number", 0);
        dbAllSalesInvoiceInfo = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", "sales_invoice_number");
    }
    
    private void getDBAllSalesReturnInfo(){
        dbAllSalesReturnInfo = new JDBCTemplate().getDBAllSalesReturnInfo("sales_return_table", "sales_return_number");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllEmployeeInfo(){
        dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }
    
    private void getDBAllCustomerInfo(){
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
    }
    
    //Order Panel
    private void setOrderPanel(){
        orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBackground(new Color(222, 217, 118));
        orderPanel.setBorder(new LineBorder(Color.GRAY));
        this.add(orderPanel);
        
        setOrderLabel();
        setOrderTable();
        setOrderButtonPanel();
    }
    
    private void setOrderLabel(){
        JLabel orderLabel = new JLabel("<< SALES ORDER >>");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderLabel.setFont(new Font("", Font.BOLD, 20));
        
        orderPanel.add(orderLabel, BorderLayout.NORTH);
    }
    
    private void setOrderTable(){
        String columns[] = 
        {"OrderID", "Customer Name", "Inventory Name", "Quantity", "Unit", "Order Date", "Delivery Date ", "Emplyoee Name"};
        
        Object[][] orderData = getOrderData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, double.class, String.class, Date.class, Date.class, String.class};
        
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
                callingNewSalesOrderDialog();
            }
        
        });
    }
    
    private void callingNewSalesOrderDialog(){
        NewSalesOrderDialog newSalesOrderDialog = new NewSalesOrderDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Sales Order");
        newSalesOrderDialog.setBounds(300, 280, 1200, 400);
        newSalesOrderDialog.setVisible(true);
        
        newSalesOrderDialog.addWindowListener(new WindowAdapter(){
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
        if (dbAlSalesOrderInfoNotCleared != null){
            Object [][] data = new Object[dbAlSalesOrderInfoNotCleared.size()][8];
            for (int i = 0; i< dbAlSalesOrderInfoNotCleared.size(); i++){
                data[i][0] = dbAlSalesOrderInfoNotCleared.get(i).getSales_order_number();
                
                int customer_id = dbAlSalesOrderInfoNotCleared.get(i).getCustomer_id();
                for (Customer c : dbAllCustomerInfo){
                    if (c.getCustomer_id() == customer_id){
                        data[i][1] = c.getCompany_name();
                        break;
                    }
                }
                
                int inventory_id = dbAlSalesOrderInfoNotCleared.get(i).getInventory_id();
                for (Inventory inv : dbAllInventoryInfo){
                    if(inv.getInventory_id() == inventory_id){
                        data[i][2] = inv.getName();
                        break;
                    }
                }
                
                data[i][3] = dbAlSalesOrderInfoNotCleared.get(i).getQuantity();
                data[i][4] = dbAlSalesOrderInfoNotCleared.get(i).getUnit();
                data[i][5] = dbAlSalesOrderInfoNotCleared.get(i).getSales_order_date();
                data[i][6] = dbAlSalesOrderInfoNotCleared.get(i).getDelivery_date();
                int staff_id = dbAlSalesOrderInfoNotCleared.get(i).getStaff_id();
                for (Employee e : dbAllEmployeeInfo){
                    if(e.getStaff_id() == staff_id){
                        data[i][7] = e.getName();
                        break;
                    }
                }
            }

            return data;
        }
        
        return null;
    }
    
    public void updateNewOrderInfo(){
        getDBAllSalesOrderInfo();
        getDBAllEmployeeInfo();
        getDBAllCustomerInfo();
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
        invoicePanel.setLayout(new BorderLayout());
        invoicePanel.setBackground(new Color(222, 217, 118));
        invoicePanel.setBorder(new LineBorder(Color.GRAY));
        this.add(invoicePanel);
        
        setInvoiceLabel();
        setInvoiceTabel();
        setInvoiceButtonPanel();
    }
    
    private void setInvoiceLabel(){
        JLabel invoiceLabel = new JLabel("<< SALES INVOICE >>");
        invoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        invoiceLabel.setFont(new Font("", Font.BOLD, 20));
        
        invoicePanel.add(invoiceLabel, BorderLayout.NORTH);
    }
    
    private void setInvoiceTabel(){
         String columns[] = 
            {"InvoiceID", "Invoice Date", "Customer Name", "OrderID",
                "Inventory Name", "Total Amount", "Employee Name"};
         
         Object[][] invoiceData = getInvoiceData();
         
         final Class[] columnClass = new Class[]{
            String.class, Date.class, String.class, String.class, String.class, 
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
                callingNewSalesInvoiceDialog();
            }        
        });
    }
    
    private Object[][] getInvoiceData(){
        if (dbAllSalesInvoiceInfoNotCleared != null){
            Object [][] data = new Object[dbAllSalesInvoiceInfoNotCleared.size()][7];
            for (int i = 0; i< dbAllSalesInvoiceInfoNotCleared.size(); i++){
                data[i][0] = dbAllSalesInvoiceInfoNotCleared.get(i).getSales_invoice_number();
                data[i][1] = dbAllSalesInvoiceInfoNotCleared.get(i).getSales_invoice_date();
                
                int sales_order_id = dbAllSalesInvoiceInfoNotCleared.get(i).getSales_order_id();
                for (SalesOrder so : dbAllSalesOrderInfoCleared){
                    if (so.getSales_order_id() == sales_order_id){
                        data[i][3] = so.getSales_order_number();
                        
                        for (Customer c : dbAllCustomerInfo){
                            if (so.getCustomer_id() == c.getCustomer_id()){
                                data[i][2] = c.getCompany_name();
                                break;
                            }
                        }
                        
                        for (Inventory inv : dbAllInventoryInfo){
                            if (so.getInventory_id() == inv.getInventory_id()){
                                data[i][4] = inv.getName();
                                break;
                            }
                        }
                        break;
                    }
                }

                data[i][5] = dbAllSalesInvoiceInfoNotCleared.get(i).getTotal_price();
                
                int staff_id = dbAllSalesInvoiceInfoNotCleared.get(i).getStaff_id();
                for (Employee e : dbAllEmployeeInfo){
                    if(e.getStaff_id() == staff_id){
                        data[i][6] = e.getName();
                    }
                }
            }
            return data;
        }
        return null;
    }
    
    private void callingNewSalesInvoiceDialog(){
        NewSalesInvoiceDialog newSalesInvoiceDialog = new NewSalesInvoiceDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Sales Invoice");
        newSalesInvoiceDialog.setBounds(450, 220, 800, 500);
        newSalesInvoiceDialog.setVisible(true);
        
        newSalesInvoiceDialog.addWindowListener(new WindowAdapter(){
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
        updateNewOrderInfo();
        getDBAllSalesInvoiceInfo();
        getDBAllEmployeeInfo();
        
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
        returnPanel.setLayout(new BorderLayout());
        returnPanel.setBackground(new Color(222, 217, 118));
        returnPanel.setBorder(new LineBorder(Color.GRAY));
        this.add(returnPanel);
        
        setReturnLabel();
        setReturnTable();
        setReturnButtonPanel();
    }
    
    private void setReturnLabel(){
        JLabel returnLabel = new JLabel("<< SALES RETURN >>");
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
                callingNewSalesReturnDialog();
            }
        
        });
    }
    
    private Object[][] getReturnData(){
        if (dbAllSalesReturnInfo != null){
            Object [][] data = new Object[dbAllSalesReturnInfo.size()][7];
            for (int i = 0; i< dbAllSalesReturnInfo.size(); i++){
                data[i][0] = dbAllSalesReturnInfo.get(i).getSales_return_number();
                
                data[i][1] = dbAllSalesReturnInfo.get(i).getSales_return_date();
                
                int sales_invoice_id = dbAllSalesReturnInfo.get(i).getSales_invoice_id();
                for(SalesInvoice si : dbAllSalesInvoiceInfo){
                    if (si.getSales_invoice_id() == sales_invoice_id){
                        data[i][2] = si.getSales_invoice_number();
                    }
                }
                
                int inventory_id = dbAllSalesReturnInfo.get(i).getInventory_id();
                for (Inventory iv : dbAllInventoryInfo){
                    if (iv.getInventory_id() == inventory_id){
                        data[i][3] = iv.getName();
                    }
                }
                
                data[i][4] = dbAllSalesReturnInfo.get(i).getQuantity();
                data[i][5] = dbAllSalesReturnInfo.get(i).getUnit();
                data[i][6] = dbAllSalesReturnInfo.get(i).getRemark();
            }
            return data;
        }
        return null;
    }
    
    private void callingNewSalesReturnDialog(){
        NewSalesReturnDialog newSalseReturnDialog = new NewSalesReturnDialog(
                (JFrame)SwingUtilities.windowForComponent(this), "Add New Salse Return");
        newSalseReturnDialog.setBounds(450, 220, 800, 500);
        newSalseReturnDialog.setVisible(true);
        
        newSalseReturnDialog.addWindowListener(new WindowAdapter(){
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
        getDBAllSalesReturnInfo();
        
        returnTableModel.setRowCount(0);
        Object[][] updateNewReturnData = getReturnData();
        
        if (updateNewReturnData != null){
            for (int i=0; i<updateNewReturnData.length; i++){
                returnTableModel.addRow(updateNewReturnData[i]);
            }
        }
    }
}
