/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import PsnClass.Categories;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author user
 */
public class ExportPanel extends JPanel{
    private JTextField companyName_text = null;
    private JTextField companySSM_text = null;
    private JTextArea companyAddress_text = null;
    private JTextField companyEmail_text = null;
    private JTextField companyPhone_text = null;
    private JTextField companyFax_text = null;
    
    private JRadioButton excelReceivedButton = null;
    private JRadioButton PDF_ReceivedButton = null;
    private JRadioButton excelPOButton = null;
    private JRadioButton PDF_POButton = null;
    private JRadioButton excelPIButton = null;
    private JRadioButton excelPRButton = null;
    private JRadioButton excelSOButton = null;
    private JRadioButton PDF_SOButton = null;
    private JRadioButton excelSIButton = null;
    private JRadioButton PDF_SIButton = null;
    private JRadioButton excelSRButton = null;
    private JRadioButton PDF_SRButton = null;
    private JRadioButton excelInitialButton = null;
    private JRadioButton excelCustomerButton = null;
    private JRadioButton excelSupplierButton = null;
    private JRadioButton excelEmployeeButton = null;
    private JRadioButton excelInventoryButton = null;
    
    private JComboBox startReceivedBox = null;
    private JComboBox endReceivedBox = null;
    private JComboBox startPOBox = null;
    private JComboBox endPOBox = null;
    private JComboBox startPIBox = null;
    private JComboBox endPIBox = null;
    private JComboBox startPRBox = null;
    private JComboBox endPRBox = null;
    private JComboBox startSOBox = null;
    private JComboBox endSOBox = null;
    private JComboBox startSIBox = null;
    private JComboBox endSIBox = null;
    private JComboBox startSRBox = null;
    private JComboBox endSRBox = null;
    
    List<String> companyInfoList = new ArrayList<>();

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
    
    public ExportPanel (){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(Color.WHITE);
        
        updateAllDBInfo();
        setEnterCompanyInfoPanel();
        setPreviousCompanyInfo();
        setBilingExportPanel();
    }
    
    public void updateAllDBInfo(){
        dbAllInitialInventoryInfo = new JDBCTemplate().getDBAllInitialInventoryInfo("initial_inventory_table", "initial_inventory_id");
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("Inventory_table", "Inventory_id");
        dbAllTypeInfo = new JDBCTemplate().getDBALLTypeInfo("type_table", "name");
        dbAllCategoriesInfo = new JDBCTemplate().getDBALLCategoriesInfo("categories_table", "name");
        dbAllPurchaseOrderInfo = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", 
                "purchase_order_id");
        dbAllPurchaseInvoiceInfo = new JDBCTemplate().getDBAllPurchaseInvoiceInfo("purchase_invoice_table", 
                "purchase_invoice_id");
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", 
                "purchase_return_id");
        dbAllSalesOrderInfo = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", 
                "sales_order_id");
        dbAllSalesInvoiceInfo = new JDBCTemplate().getDBAllSalesInvoiceInfo("sales_invoice_table", 
                "sales_invoice_id");
        dbAllSalesReturnInfo = new JDBCTemplate().getDBAllSalesReturnInfo("sales_return_table", 
                "sales_return_id");
        dbAllReceivedInfo = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_id");
        dbAllInventoryLocationInfo = new JDBCTemplate().getDBAllLocationInfo("inventory_location_table", "name");
        dbAllCustomerInfo = new JDBCTemplate().getDBAllCustomerInfo("customer_table", "customer_number");
        dbAllSupplierInfo = new JDBCTemplate().getDBAllSupplierInfo("supplier_table", "supplier_number");
        dbAllEmployeeInfo = new JDBCTemplate().getDBAllEmployeeInfo("staff_table", "staff_number");
    }

    private void setEnterCompanyInfoPanel(){
        JPanel companyinfoPanel = new JPanel();
        companyinfoPanel.setLayout(new BorderLayout());
        companyinfoPanel.setBorder(new LineBorder(Color.GRAY));
        
        JLabel companyInfoLabel = new JLabel("<< Company Infomation >>");
        companyInfoLabel.setFont(new Font("",Font.BOLD, 35));
        
        companyinfoPanel.add(companyInfoLabel, BorderLayout.NORTH);
        
        JPanel enterCompanyInfoPanel = new JPanel();
        enterCompanyInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        companyinfoPanel.add(enterCompanyInfoPanel, BorderLayout.CENTER);
        
        this.add(companyinfoPanel, BorderLayout.NORTH);
        
        JLabel companyNameLabel = new JLabel("Company Name*: ");
        companyName_text = new JTextField(50);
        companyName_text.setDocument(new LengthRestrictedDocument(50));
        
        JLabel companySSMLabel = new JLabel("Company SSM*: ");
        companySSM_text = new JTextField(15);
        companySSM_text.setDocument(new LengthRestrictedDocument(15));
        
        JLabel companyAddressLabel = new JLabel("Company Address*: ");
        companyAddress_text = new JTextArea();
        companyAddress_text.setBorder(new LineBorder(Color.GRAY));
        companyAddress_text.setDocument(new LengthRestrictedDocument(150));
        
        JLabel companyEmailLabel = new JLabel("Company Email*: ");
        companyEmail_text = new JTextField(20);
        companyEmail_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel companyPhoneLabel = new JLabel("Company Phone*: ");
        companyPhone_text = new JTextField(15);
        companyPhone_text.setDocument(new LengthRestrictedDocument(15));
        
        JLabel companyFaxLabel = new JLabel("Company Fax*: ");
        companyFax_text = new JTextField(15);
        companyFax_text.setDocument(new LengthRestrictedDocument(15));
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        enterCompanyInfoPanel.add(companyNameLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        enterCompanyInfoPanel.add(companyName_text, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        enterCompanyInfoPanel.add(companySSMLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        enterCompanyInfoPanel.add(companySSM_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        enterCompanyInfoPanel.add(companyAddressLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        enterCompanyInfoPanel.add(companyAddress_text, gbc);
        gbc.insets = new Insets(5,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        enterCompanyInfoPanel.add(companyEmailLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        enterCompanyInfoPanel.add(companyEmail_text, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 2;
        gbc.gridy = 1;
        enterCompanyInfoPanel.add(companyPhoneLabel, gbc);
        gbc.insets = new Insets(5,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 1;
        enterCompanyInfoPanel.add(companyPhone_text, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        enterCompanyInfoPanel.add(companyFaxLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        enterCompanyInfoPanel.add(companyFax_text, gbc);
        
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setLayout(new FlowLayout());
        
        companyinfoPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        JButton saveButton = new JButton("Save");
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveCompayDataToProperties();
            }
        });
    }
    
    private void retrieveCompayDataToProperties(){
        if (checkNullData()){
           JOptionPane.showMessageDialog(this, "* Empty value is not acceptabel!");
        }else {
            int result = JOptionPane.showConfirmDialog(this, "Do you want save the company data?", "Saving Notice", 1);
            switch (result){
                case 0: 
                    saveComponentsDataIntoProperties(); 
                    JOptionPane.showMessageDialog(this, "Successfully Saving!");
                    break;
                case 2: refreshComponents(); break;
            }
            
        }
    }
    
    private boolean checkNullData(){
        if (companyName_text.getText().equals("")){
            return true;
        }
        
        if (companySSM_text.getText().equals("")){
            return true;
        }
        
        if (companyAddress_text.getText().equals("")){
            return true;
        }
        
        if (companyEmail_text.getText().equals("")){
            return true;
        }
        
        if (companyPhone_text.getText().equals("")){
            return true;
        }
        
        
        if (companyFax_text.getText().equals("")){
            return true;
        }
            
        return false;
    }
    
    private void saveComponentsDataIntoProperties(){
        String companyName = companyName_text.getText().toUpperCase();
        String companySSM = companySSM_text.getText().toUpperCase();
        String companyAddress = companyAddress_text.getText().toUpperCase();
        String companyEmail = companyEmail_text.getText();
        String companyPhone = companyPhone_text.getText();
        String companyFax = companyFax_text.getText();
        
        if (!companyInfoList.isEmpty()){
             companyInfoList.clear();
        }
        
        companyInfoList.add(companyName);
        companyInfoList.add(companySSM);
        companyInfoList.add(companyAddress);
        companyInfoList.add(companyEmail);
        companyInfoList.add(companyPhone);
        companyInfoList.add(companyFax);
        
        File fileURL = new File("..\\WarehouseSystem\\src\\main\\resources\\companyInfo.properties");
        FileOutputStream out = null;
        
        if (!fileURL.exists()){
            try {
                fileURL.createNewFile();
                
                Properties prop = new Properties();
                out = new FileOutputStream(fileURL);
                
                prop.setProperty("companyName", companyName);
                prop.setProperty("companySSM", companySSM);
                prop.setProperty("companyAddress", companyAddress);
                prop.setProperty("companyEmail", companyEmail);
                prop.setProperty("companyPhone", companyPhone);
                prop.setProperty("companyFax", companyFax);
                prop.store(out, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }else {
            try {
                Properties prop = new Properties();
                out = new FileOutputStream(fileURL);
                
                prop.load(ExportPanel.class.getClassLoader().getResourceAsStream("companyInfo.properties"));

                prop.setProperty("companyName", companyName);
                prop.setProperty("companySSM", companySSM);
                prop.setProperty("companyAddress", companyAddress);
                prop.setProperty("companyEmail", companyEmail);
                prop.setProperty("companyPhone", companyPhone);
                prop.setProperty("companyFax", companyFax);
                prop.store(out, null);
            
            } catch (Exception ex) {
                Logger.getLogger(ExportPanel.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void refreshComponents(){
        companyName_text.setText("");
        companySSM_text.setText("");
        companyAddress_text.setText("");
        companyEmail_text.setText("");
        companyPhone_text.setText("");
        companyFax_text.setText("");
    }
    
    private void setPreviousCompanyInfo(){
        File fileURL = new File("..\\WarehouseSystem\\src\\main\\resources\\companyInfo.properties");
        FileOutputStream out = null;
        
        if (fileURL.exists()){
            try {
                out = new FileOutputStream(fileURL);
                Properties prop = new Properties();
   
                prop.load(ExportPanel.class.getClassLoader().getResourceAsStream("companyInfo.properties"));
                
                String companyName = (String)prop.get("companyName");
                String companySSM = (String)prop.get("companySSM");
                String companyAddress = (String)prop.get("companyAddress");
                String companyEmail = (String)prop.get("companyEmail");
                String companyPhone = (String)prop.get("companyPhone");
                String companyFax = (String)prop.get("companyFax");
                
                companyName_text.setText(companyName);
                companySSM_text.setText(companySSM);
                companyAddress_text.setText(companyAddress);
                companyEmail_text.setText(companyEmail);
                companyPhone_text.setText(companyPhone);
                companyFax_text.setText(companyFax);
                
                if (!companyInfoList.isEmpty()){
                    companyInfoList.clear();
               }
                
                companyInfoList.add(companyName);
                companyInfoList.add(companySSM);
                companyInfoList.add(companyAddress);
                companyInfoList.add(companyEmail);
                companyInfoList.add(companyPhone);
                companyInfoList.add(companyFax);
                
                prop.store(out, companySSM);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally{
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void setBilingExportPanel(){
        JPanel billingExportPanel = new JPanel();
        billingExportPanel.setLayout(new BorderLayout());
        billingExportPanel.setBorder(new LineBorder(Color.GRAY));
        
        JLabel exportBillingLabel = new JLabel("<< Export Billing/Report >>");
        exportBillingLabel.setFont(new Font("",Font.BOLD, 35));
        
        billingExportPanel.add(exportBillingLabel, BorderLayout.NORTH);
        
        JPanel tickSelectPanel = new JPanel();
        tickSelectPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        billingExportPanel.add(tickSelectPanel, BorderLayout.CENTER);
        
        this.add(billingExportPanel, BorderLayout.CENTER);
        
        JLabel excelLabel = new JLabel ("Excel");
        JLabel PDFLabel = new JLabel ("PDF");
        JLabel billingReportLabel = new JLabel ("Billing/Report");
        JLabel startLabel = new JLabel ("Start");
        JLabel endLabel = new JLabel ("End");
        JLabel receivedLabel = new JLabel ("Received");
        JLabel purchaseOrderLabel = new JLabel ("Purchase Order");
        JLabel purchaseInvoiceLabel = new JLabel ("Purchase Invoice");
        JLabel purchaseReturnLabel = new JLabel ("Purchase Return");
        JLabel salesOrderLabel = new JLabel ("Sales Order");
        JLabel salesInvoiceLabel = new JLabel ("Sales Invoice");
        JLabel salesReturnLabel = new JLabel ("Sales Return");
        JLabel initialInventoryLabel = new JLabel ("Initial Inventory");
        JLabel customerLabel = new JLabel ("Customer");
        JLabel supplierLabel = new JLabel ("Supplier");
        JLabel employeeLabel = new JLabel ("Employee");
        JLabel inventoryLabel = new JLabel ("Inventory");
        
        excelReceivedButton = new JRadioButton();
        PDF_ReceivedButton = new JRadioButton();
        excelPOButton = new JRadioButton();
        PDF_POButton = new JRadioButton();
        excelPIButton = new JRadioButton();
        excelPRButton = new JRadioButton();
        excelSOButton = new JRadioButton();
        PDF_SOButton = new JRadioButton();
        excelSIButton = new JRadioButton();
        PDF_SIButton = new JRadioButton();
        excelSRButton = new JRadioButton();
        PDF_SRButton = new JRadioButton();
        excelInitialButton = new JRadioButton();
        excelCustomerButton = new JRadioButton();
        excelSupplierButton = new JRadioButton();
        excelEmployeeButton = new JRadioButton();
        excelInventoryButton = new JRadioButton();
        
        ButtonGroup bg = new ButtonGroup();
        
        bg.add(excelReceivedButton);
        bg.add(PDF_ReceivedButton);
        bg.add(excelPOButton);
        bg.add(PDF_POButton);
        bg.add(excelPIButton);
        bg.add(excelPRButton);
        bg.add(excelSOButton);
        bg.add(PDF_SOButton);
        bg.add(excelSIButton);
        bg.add(PDF_SIButton);
        bg.add(excelSRButton);
        bg.add(PDF_SRButton);
        bg.add(excelInitialButton);
        bg.add(excelCustomerButton);
        bg.add(excelSupplierButton);
        bg.add(excelEmployeeButton);
        bg.add(excelInventoryButton);
        
        startReceivedBox = new JComboBox();
        endReceivedBox = new JComboBox();
        endReceivedBox.setEnabled(false);
        setReceivedComBoxItem();
        
        startPOBox = new JComboBox();
        endPOBox = new JComboBox();
        endPOBox.setEnabled(false);
        setPOComBoxItem();
        
        startPIBox = new JComboBox();
        endPIBox = new JComboBox();
        endPIBox.setEnabled(false);
        setPIComBoxItem();
        
        startPRBox = new JComboBox();
        endPRBox = new JComboBox();
        endPRBox.setEnabled(false);
        setPRComBoxItem();
        
        startSOBox = new JComboBox();
        endSOBox = new JComboBox();
        endSOBox.setEnabled(false);
        setSOComBoxItem();
        
        startSIBox = new JComboBox();
        endSIBox = new JComboBox();
        endSIBox.setEnabled(false);
        setSIComBoxItem();
        
        startSRBox = new JComboBox();
        endSRBox = new JComboBox();
        endSRBox.setEnabled(false);
        setSRComBoxItem();

        excelReceivedButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(true);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        PDF_ReceivedButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        excelPOButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(true);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        PDF_POButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        excelPIButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(true);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });

        excelPRButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(true);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });

        excelSOButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(true);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        PDF_SOButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        excelSIButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(true);
                endSRBox.setEnabled(false);
            }
        });
        
        PDF_SIButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        excelSRButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(true);
            }
        });
        
        PDF_SRButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                endReceivedBox.setEnabled(false);
                endPOBox.setEnabled(false);
                endPIBox.setEnabled(false);
                endPRBox.setEnabled(false);
                endSOBox.setEnabled(false);
                endSIBox.setEnabled(false);
                endSRBox.setEnabled(false);
            }
        });
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tickSelectPanel.add(excelLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        tickSelectPanel.add(PDFLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        tickSelectPanel.add(billingReportLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        tickSelectPanel.add(startLabel, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        tickSelectPanel.add(endLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        tickSelectPanel.add(excelPOButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tickSelectPanel.add(PDF_POButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        tickSelectPanel.add(purchaseOrderLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 1;
        tickSelectPanel.add(startPOBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 1;
        tickSelectPanel.add(endPOBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        tickSelectPanel.add(excelReceivedButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        tickSelectPanel.add(PDF_ReceivedButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        tickSelectPanel.add(receivedLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 2;
        tickSelectPanel.add(startReceivedBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 2;
        tickSelectPanel.add(endReceivedBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        tickSelectPanel.add(excelPIButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        tickSelectPanel.add(purchaseInvoiceLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 3;
        tickSelectPanel.add(startPIBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 3;
        tickSelectPanel.add(endPIBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        tickSelectPanel.add(excelPRButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        tickSelectPanel.add(purchaseReturnLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 4;
        tickSelectPanel.add(startPRBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 4;
        tickSelectPanel.add(endPRBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        tickSelectPanel.add(excelSOButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        tickSelectPanel.add(PDF_SOButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        tickSelectPanel.add(salesOrderLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 5;
        tickSelectPanel.add(startSOBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 5;
        tickSelectPanel.add(endSOBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        tickSelectPanel.add(excelSIButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        tickSelectPanel.add(PDF_SIButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        tickSelectPanel.add(salesInvoiceLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 6;
        tickSelectPanel.add(startSIBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 6;
        tickSelectPanel.add(endSIBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        tickSelectPanel.add(excelSRButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        tickSelectPanel.add(PDF_SRButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 7;
        tickSelectPanel.add(salesReturnLabel, gbc);
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 3;
        gbc.gridy = 7;
        tickSelectPanel.add(startSRBox, gbc);
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 4;
        gbc.gridy = 7;
        tickSelectPanel.add(endSRBox, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 8;
        tickSelectPanel.add(excelInitialButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 8;
        tickSelectPanel.add(initialInventoryLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        tickSelectPanel.add(excelCustomerButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 9;
        tickSelectPanel.add(customerLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        tickSelectPanel.add(excelSupplierButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 10;
        tickSelectPanel.add(supplierLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        tickSelectPanel.add(excelEmployeeButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 11;
        tickSelectPanel.add(employeeLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 12;
        tickSelectPanel.add(excelInventoryButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 12;
        tickSelectPanel.add(inventoryLabel, gbc);

        JPanel exportButtonPanel = new JPanel();
        exportButtonPanel.setLayout(new FlowLayout());
        billingExportPanel.add(exportButtonPanel, BorderLayout.SOUTH);
        
        JButton exportButton = new JButton("Export");
        exportButtonPanel.add(exportButton);
        
        exportButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PDF_POButton.isSelected() || PDF_ReceivedButton.isSelected() || PDF_SIButton.isSelected()
                        || PDF_SOButton.isSelected() || PDF_SRButton.isSelected()){
                    generateBillingToPDF();
                }
                
                if (excelCustomerButton.isSelected() || excelEmployeeButton.isSelected() || excelInitialButton.isSelected()
                        || excelPIButton.isSelected() || excelPOButton.isSelected() || excelPRButton.isSelected() ||
                        excelReceivedButton.isSelected() || excelSIButton.isSelected() || excelSOButton.isSelected() ||
                        excelSRButton.isSelected() || excelSupplierButton.isSelected() || excelInventoryButton.isSelected()){
                    generateReportToExcel();
                }
            }
        });
    }
    
    public void setAllComBoxItem(){
        setPOComBoxItem();
        setPIComBoxItem();
        setPRComBoxItem();
        setSOComBoxItem();
        setSIComBoxItem();
        setSRComBoxItem();
        setReceivedComBoxItem();
    }
    
    private void setPOComBoxItem(){
        String record = null;
        
        startPOBox.removeAllItems();
        endPOBox.removeAllItems();
        
        if (dbAllPurchaseOrderInfo != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (record == null || !record.equals(po.getPurchase_order_number())){
                    startPOBox.addItem(po.getPurchase_order_number());
                    endPOBox.addItem(po.getPurchase_order_number());
                    record = po.getPurchase_order_number();
                }
            }
        }
    }
    
    private void setPIComBoxItem(){
        
        startPIBox.removeAllItems();
        endPIBox.removeAllItems();
        
        String record = null;
        if (dbAllPurchaseInvoiceInfo != null){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (record == null || !record.equals(pi.getPurchase_invoice_number())){
                    startPIBox.addItem(pi.getPurchase_invoice_number());
                    endPIBox.addItem(pi.getPurchase_invoice_number());
                    record = pi.getPurchase_invoice_number();
                }
            }
        }
    }
    
    private void setPRComBoxItem(){
        
        startPRBox.removeAllItems();
        endPRBox.removeAllItems();
        
        String record = null;
        if (dbAllPurchaseReturnInfo != null){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (record == null || !record.equals(pr.getPurchase_return_number())){
                    startPRBox.addItem(pr.getPurchase_return_number());
                    endPRBox.addItem(pr.getPurchase_return_number());
                    record = pr.getPurchase_return_number();
                }
            }
        }
    }
    
    private void setSOComBoxItem(){
        
        startSOBox.removeAllItems();
        endSOBox.removeAllItems();
        
        String record = null;
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (record == null || !record.equals(so.getSales_order_number())){
                    startSOBox.addItem(so.getSales_order_number());
                    endSOBox.addItem(so.getSales_order_number());
                    record = so.getSales_order_number();
                }
            }
        }
    }
    
    private void setSIComBoxItem(){
        String record = null;
        
        startSIBox.removeAllItems();
        endSIBox.removeAllItems();
        
        if (dbAllSalesInvoiceInfo != null){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (record == null || !record.equals(si.getSales_invoice_number())){
                    startSIBox.addItem(si.getSales_invoice_number());
                    endSIBox.addItem(si.getSales_invoice_number());
                    record = si.getSales_invoice_number();
                }
            }
        }
    }
    
    private void setSRComBoxItem(){
        
        startSRBox.removeAllItems();
        endSRBox.removeAllItems();
        
        String record = null;
        if (dbAllSalesReturnInfo != null){
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (record == null || !record.equals(sr.getSales_return_number())){
                    startSRBox.addItem(sr.getSales_return_number());
                    endSRBox.addItem(sr.getSales_return_number());
                    record = sr.getSales_return_number();
                }
            }
        }
    }
    
    private void setReceivedComBoxItem(){
        
        startReceivedBox.removeAllItems();
        endReceivedBox.removeAllItems();
        
        String record = null;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (record == null || !record.equals(r.getReceived_number())){
                    startReceivedBox.addItem(r.getReceived_number());
                    endReceivedBox.addItem(r.getReceived_number());
                    record = r.getReceived_number();
                }
            }
        }
    }

    private void generateBillingToPDF(){
        com.itextpdf.text.Font catFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD);
        
        String selectedFile = null;

        JFileChooser fc = new JFileChooser();
        int showSaveDialog = fc.showSaveDialog(null);
        if(showSaveDialog==fc.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile().toString() + ".pdf";
        }
        
        Document document = null;
        try {
            document = new Document();
            if (selectedFile!= null){
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
            }
            document.open();

            Paragraph preface = new Paragraph();
            if (companyInfoList != null){
                preface.add(new Paragraph(companyInfoList.get(0) + "    ("+companyInfoList.get(1) + ")", catFont));
                preface.add(new Paragraph(companyInfoList.get(2),catFont));
                preface.add(new Paragraph("Email: " +companyInfoList.get(3),catFont));
                preface.add(new Paragraph("Phone: " +companyInfoList.get(4) + "Fax: " + companyInfoList.get(5),catFont));
            }

            addEmptyLine(preface, 3);

            setEachOfBillingInfo(preface);
            
            document.add(preface);

            if (selectedFile != null){
                JOptionPane.showMessageDialog(this, "File export successfully!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            document.close();
        }
    }
    
    private void setEachOfBillingInfo(Paragraph preface){
        com.itextpdf.text.Font redFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.NORMAL, BaseColor.RED);

        if (PDF_POButton.isSelected()){
            List<List<Object>> allBillInfoList = new ArrayList<>();
            String selectedPOItem = (String)startPOBox.getSelectedItem();
            if (dbAllPurchaseOrderInfo != null){
                for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                    if (po.getPurchase_order_number().equals(selectedPOItem)){
                        List<Object> poList = new ArrayList<>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        poList.add(dateFormat.format(po.getOrder_date()));

                        poList.add(dateFormat.format(po.getExpect_received_date()));

                        for (Inventory i : dbAllInventoryInfo){
                            if (i.getInventory_id() == po.getInventory_id()){
                                poList.add(i.getName());
                                break;
                            }
                        }

                        poList.add(String.valueOf(po.getQuantity()));
                        poList.add(po.getUnit());

                        for (Employee e : dbAllEmployeeInfo){
                            if (e.getStaff_id() == po.getStaff_id()){
                                poList.add(e.getName());
                            }
                        }

                        allBillInfoList.add(poList);
                    }
                }
            }
            
            preface.add(new Paragraph("Purchase Order Number: " + (String)startPOBox.getSelectedItem(), redFont));
            preface.add(new Paragraph("Purchase Order Date: " + allBillInfoList.get(0).get(0)));
            preface.add(new Paragraph("Expected Receiving Date: " + allBillInfoList.get(0).get(1)));

            addEmptyLine(preface, 3);
            
            List<String> columnName = new ArrayList<>();
            columnName.add("No");
            columnName.add("Inventory");
            columnName.add("Quantity");
            columnName.add("Unit");
            
            createBillingTabel(preface, allBillInfoList, 4, columnName);
        } 
       
        if (PDF_ReceivedButton.isSelected()){
            List<List<Object>> allBillInfoList = new ArrayList<>();
            String selectedRecItem = (String)startReceivedBox.getSelectedItem();
            if (dbAllReceivedInfo != null){
                for (Received r : dbAllReceivedInfo){
                    if (r.getReceived_number().equals(selectedRecItem)){
                        List<Object> poList = new ArrayList<>();

                        for (Supplier s : dbAllSupplierInfo){
                            if (s.getSupplier_id()== r.getSupplier_id()){
                                poList.add(s.getCompany_name());
                                break;
                            }
                        }

                        for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                            if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                                poList.add(po.getPurchase_order_number());

                                for (Inventory i : dbAllInventoryInfo){
                                    if (i.getInventory_id() == po.getInventory_id()){
                                        poList.add(i.getName());
                                        break;
                                    }
                                }
                                poList.add(String.valueOf(po.getQuantity()));
                                poList.add(po.getUnit());
                                break;
                            }
                        }

                        for (Employee e : dbAllEmployeeInfo){
                            if (e.getStaff_id() == r.getStaff_id()){
                                poList.add(e.getName());
                                break;
                            }
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        LocalDate expiredDate = LocalDate.parse(dateFormat.format(r.getExpired_date()));
                        LocalDate nowMinus5Years = LocalDate.now().minusYears(5);


                        if (nowMinus5Years.compareTo(expiredDate) > 0){
                            poList.add(dateFormat.format(0000-00-00));
                        }else {
                            poList.add(dateFormat.format(r.getExpired_date()));
                        }

                        poList.add(dateFormat.format(r.getReceived_date()));

                        allBillInfoList.add(poList);
                    }
                }
            }

            preface.add(new Paragraph("Received Number: " + (String)startReceivedBox.getSelectedItem(), redFont));
            preface.add(new Paragraph("Received Date: " + allBillInfoList.get(0).get(7)));
            addEmptyLine(preface, 2);
            preface.add(new Paragraph("Supplier: " + allBillInfoList.get(0).get(0)));
            addEmptyLine(preface, 3);
            
            List<String> columnName = new ArrayList<>();
            columnName.add("No");
            columnName.add("PO Number");
            columnName.add("Inventory");
            columnName.add("Quantity");
            columnName.add("Unit");
            columnName.add("Expired Date");
            
            createBillingTabel(preface, allBillInfoList, 6, columnName);
        } 
        
        if (PDF_SOButton.isSelected()){
            List<List<Object>> allBillInfoList = new ArrayList<>();
            String selectedSOItem = (String)startSOBox.getSelectedItem();
            if (dbAllSalesOrderInfo != null){
                for (SalesOrder so : dbAllSalesOrderInfo){
                    if (so.getSales_order_number().equals(selectedSOItem)){
                        List<Object> poList = new ArrayList<>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        poList.add(dateFormat.format(so.getSales_order_date()));

                        poList.add(dateFormat.format(so.getDelivery_date()));

                        for (Inventory i : dbAllInventoryInfo){
                            if (i.getInventory_id() == so.getInventory_id()){
                                poList.add(i.getName());
                                break;
                            }
                        }

                        poList.add(String.valueOf(so.getQuantity()));
                        poList.add(so.getUnit());

                        for (Employee e : dbAllEmployeeInfo){
                            if (e.getStaff_id() == so.getStaff_id()){
                                poList.add(e.getName());
                                break;
                            }
                        }

                        for (Customer c : dbAllCustomerInfo){
                            if (c.getCustomer_id() == so.getCustomer_id()){
                                poList.add(c.getCompany_name());
                                break;
                            }
                        }

                        allBillInfoList.add(poList);
                    }
                }
            }
            
            
            preface.add(new Paragraph("Sales Order Number: " + (String)startSOBox.getSelectedItem(), redFont));
            preface.add(new Paragraph("Sales Order Date: " + allBillInfoList.get(0).get(0)));
            preface.add(new Paragraph("Delivery Date: " + allBillInfoList.get(0).get(1)));
            addEmptyLine(preface, 2);
            preface.add(new Paragraph("Customer: " + allBillInfoList.get(0).get(6)));

            addEmptyLine(preface, 3);
            
            List<String> columnName = new ArrayList<>();
            columnName.add("No");
            columnName.add("Inventory");
            columnName.add("Quantity");
            columnName.add("Unit");
            
            createBillingTabel(preface, allBillInfoList, 4, columnName);
        } 
        
        if (PDF_SIButton.isSelected()){
            List<List<Object>> allBillInfoList = new ArrayList<>();
            String selectedSIItem = (String)startSIBox.getSelectedItem();
            Double sum_total_price = 0.0;
            if (dbAllSalesInvoiceInfo != null){
                for (SalesInvoice si : dbAllSalesInvoiceInfo){
                    if (si.getSales_invoice_number().equals(selectedSIItem)){
                        List<Object> poList = new ArrayList<>();

                        for(SalesOrder so : dbAllSalesOrderInfo){
                            if (si.getSales_order_id() == so.getSales_order_id()){
                                poList.add(so.getSales_order_number());

                                for (Customer c : dbAllCustomerInfo){
                                    if (c.getCustomer_id() == so.getCustomer_id()){
                                        poList.add(c.getCompany_name());
                                        poList.add(c.getAddress());
                                        poList.add(c.getEmail());
                                        poList.add(c.getCompany_phone());

                                        for (Employee e : dbAllEmployeeInfo){
                                            if (e.getStaff_id() == si.getStaff_id()){
                                                poList.add(e.getName());
                                                break;
                                            }
                                        }

                                        poList.add(c.getMobile_phone());
                                        poList.add(c.getPerson_in_charge());
                                        break;
                                    }
                                }

                                for (Inventory i : dbAllInventoryInfo){
                                    if (i.getInventory_id() == so.getInventory_id()){
                                        poList.add(i.getName());
                                        break;
                                    }
                                }
                                poList.add(String.valueOf(so.getQuantity()));
                                poList.add(so.getUnit());

                                break;
                            }
                        }

                        poList.add(String.valueOf(si.getUnit_price()));
                        poList.add(String.valueOf(si.getTotal_price()));

                        sum_total_price += si.getTotal_price();

                        poList.add(String.valueOf(sum_total_price));

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        poList.add(dateFormat.format(si.getSales_invoice_date()));

                        allBillInfoList.add(poList);
                    }
                }
            }

            preface.add(new Paragraph("Sales Invoice Number: " + (String)startSIBox.getSelectedItem(), redFont));
            preface.add(new Paragraph("Sales Invoice Date: " + allBillInfoList.get(0).get(14)));
            addEmptyLine(preface, 2);
            preface.add(new Paragraph("Customer: "));
            preface.add(new Paragraph((String)allBillInfoList.get(0).get(1)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(2)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(3)));
            preface.add(new Paragraph((String)allBillInfoList.get(0).get(4)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(6)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(7)));
            addEmptyLine(preface, 3);
            
            List<String> columnName = new ArrayList<>();
            columnName.add("No");
            columnName.add("SO Number");
            columnName.add("Inventory");
            columnName.add("Quantity");
            columnName.add("Unit");
            columnName.add("Unit Price");
            columnName.add("Total Price");
            columnName.add("Acc. Price");

            createBillingTabel(preface, allBillInfoList, 8, columnName);
        }
        
        if (PDF_SRButton.isSelected()){
            List<List<Object>> allBillInfoList = new ArrayList<>();
            String selectedSRItem = (String)startSRBox.getSelectedItem();
            Double sum_total_price = 0.0;
            if (dbAllSalesReturnInfo != null){
                for (SalesReturn sr : dbAllSalesReturnInfo){
                    if (sr.getSales_return_number().equals(selectedSRItem)){
                        List<Object> poList = new ArrayList<>();

                        for (SalesInvoice si : dbAllSalesInvoiceInfo){
                            if (si.getSales_invoice_id() == sr.getSales_invoice_id()){
                                poList.add(si.getSales_invoice_number());

                                for(SalesOrder so : dbAllSalesOrderInfo){
                                    if (si.getSales_order_id() == so.getSales_order_id()){
                                        //poList.add(so.getSales_order_number());

                                        for (Customer c : dbAllCustomerInfo){
                                            if (c.getCustomer_id() == so.getCustomer_id()){
                                                poList.add(c.getCompany_name());
                                                poList.add(c.getAddress());
                                                poList.add(c.getEmail());
                                                poList.add(c.getCompany_phone());

                                                for (Employee e : dbAllEmployeeInfo){
                                                    if (e.getStaff_id() == si.getStaff_id()){
                                                        poList.add(e.getName());
                                                        break;
                                                    }
                                                }

                                                poList.add(c.getMobile_phone());
                                                poList.add(c.getPerson_in_charge());
                                                break;
                                            }
                                        }

                                        for (Inventory i : dbAllInventoryInfo){
                                            if (i.getInventory_id() == sr.getInventory_id()){
                                                poList.add(i.getName());
                                                break;
                                            }
                                        }
                                        poList.add(String.valueOf(sr.getQuantity()));
                                        double total_price = sr.getQuantity() * si.getUnit_price();
                                        poList.add(String.valueOf(total_price));

                                        sum_total_price += total_price;
                                        poList.add(String.valueOf(sum_total_price));

                                        poList.add(so.getUnit());

                                        break;
                                    }
                                }
                                break;
                            }
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        poList.add(dateFormat.format(sr.getSales_return_date()));

                        allBillInfoList.add(poList);
                    }
                }
            }

            preface.add(new Paragraph("Sales Return Number: " + (String)startSRBox.getSelectedItem(), redFont));
            preface.add(new Paragraph("Sales Invoice Date: " + allBillInfoList.get(0).get(13)));
            addEmptyLine(preface, 2);
            preface.add(new Paragraph("Customer: "));
            preface.add(new Paragraph((String)allBillInfoList.get(0).get(1)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(2)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(3)));
            preface.add(new Paragraph((String)allBillInfoList.get(0).get(4)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(6)));
            preface.add(new Paragraph((String) allBillInfoList.get(0).get(7)));
            addEmptyLine(preface, 3);
            
            List<String> columnName = new ArrayList<>();
            columnName.add("No");
            columnName.add("SI Number");
            columnName.add("Inventory");
            columnName.add("Quantity");
            columnName.add("Unit");
            columnName.add("Total Price");
            columnName.add("Acc. Price");

            createBillingTabel(preface, allBillInfoList, 7, columnName);
        }
        
    }
    
    private void createBillingTabel(Paragraph preface, List<List<Object>> allBillInfoList, int column, List<String> columnName){
        
        PdfPTable table = new PdfPTable(column);
        PdfPCell c1 = null;
        
        for (String s : columnName){
            c1 = new PdfPCell(new Phrase(s));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        
        if (PDF_POButton.isSelected() || PDF_SOButton.isSelected()){
            int index = 0;
            for (List<Object> list : allBillInfoList){
                index++;
                table.addCell(String.valueOf(index));
                table.addCell((String)list.get(2));
                table.addCell((String)list.get(3));
                table.addCell((String)list.get(4));
            }
        }
        
        if (PDF_ReceivedButton.isSelected()){
            int index = 0;
            for (List<Object> list : allBillInfoList){
                index++;
                table.addCell(String.valueOf(index));
                table.addCell((String)list.get(1));
                table.addCell((String)list.get(2));
                table.addCell((String)list.get(3));
                table.addCell((String)list.get(4));
                table.addCell((String)list.get(6));
            }
        }
        
        if (PDF_SIButton.isSelected()){
            int index = 0;
            for (List<Object> list : allBillInfoList){
                index++;
                table.addCell(String.valueOf(index));
                table.addCell((String)list.get(0));
                table.addCell((String)list.get(8));
                table.addCell((String)list.get(9));
                table.addCell((String)list.get(10));
                table.addCell((String)list.get(11));
                table.addCell((String)list.get(12));
                table.addCell((String)list.get(13));
            }
        }
        
        if (PDF_SRButton.isSelected()){
            int index = 0;
            for (List<Object> list : allBillInfoList){
                index++;
                table.addCell(String.valueOf(index));
                table.addCell((String)list.get(0));
                table.addCell((String)list.get(8));
                table.addCell((String)list.get(9));
                table.addCell((String)list.get(12));
                table.addCell((String)list.get(10));
                table.addCell((String)list.get(11));
            }
        }

        preface.add(table);
        
        addEmptyLine(preface, 3);
            
        preface.add(new Paragraph("Prepared by,"));

        addEmptyLine(preface, 3);

        preface.add(new Paragraph("_____________"));
        preface.add(new Paragraph("Name: "+ (String)allBillInfoList.get(0).get(5)));
    }
    
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    private void generateReportToExcel(){
        String selectedFile = null;

        JFileChooser fc = new JFileChooser();
        int showSaveDialog = fc.showSaveDialog(null);
        if(showSaveDialog==fc.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile().toString() + ".xlsx";
        }
        
        try {
            HashMap<Integer, Object[]> infoData = null;
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = null;

            if (excelInventoryButton.isSelected()){
                spreadsheet = workbook.createSheet("inventory");
                infoData = getInventoryDataInfo();
            }
            
            if (excelInitialButton.isSelected()){
                spreadsheet = workbook.createSheet("initial");
                infoData = getInitialInventoryInfo();
            }
            
            if (excelCustomerButton.isSelected()){
                spreadsheet = workbook.createSheet("customer");
                infoData = getCustomerInfo();
            }
            
            if (excelSupplierButton.isSelected()){
                spreadsheet = workbook.createSheet("supplier");
                infoData = getSupplierInfo();
            }
            
            if (excelEmployeeButton.isSelected()){
                spreadsheet = workbook.createSheet("employee");
                infoData = getEmployeeInfo();
            }
            
            if (excelPOButton.isSelected()){
                spreadsheet = workbook.createSheet("Purchase Order");
                infoData = getPurchaseOrderInfo();
            }
            
            if (excelPIButton.isSelected()){
                spreadsheet = workbook.createSheet("Purchase Invoice");
                infoData = getPurchaseInvoiceInfo();
            }
            
            if (excelPRButton.isSelected()){
                spreadsheet = workbook.createSheet("Purchase Return");
                infoData = getPurchaseReturnInfo();
            }
            
            if (excelReceivedButton.isSelected()){
                spreadsheet = workbook.createSheet("Received");
                infoData = getReceivedInfo();
            }
            
            if (excelSOButton.isSelected()){
                spreadsheet = workbook.createSheet("Sales Order");
                infoData = getSalesOrderInfo();
            }
            
            if (excelSIButton.isSelected()){
                spreadsheet = workbook.createSheet("Sales invoice");
                infoData = getSalesInvoiceInfo();
            }
            
            if (excelSRButton.isSelected()){
                spreadsheet = workbook.createSheet("Sales Return");
                infoData = getSalesReturnInfo();
            }

            if (infoData != null){
                XSSFRow row;

                int rowid = 0;
                for (Integer key : infoData.keySet()){
                   row = spreadsheet.createRow(rowid++);
                    Object[] data = infoData.get(key);
                    int cellid = 0;

                    for (Object obj : data){
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue((String)obj);
                    }
                }

                if (selectedFile != null){
                    workbook.write(new FileOutputStream(selectedFile));
                }

                if (selectedFile != null){
                    JOptionPane.showMessageDialog(this, "File export successfully!!");
                }
            }else {
                JOptionPane.showMessageDialog(this, "Empty data cannot be export");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private HashMap<Integer, Object[]> getInventoryDataInfo (){
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Inventory_number","Name","Categories", "Type" ," Quantity" ,"Unit", 
            "Sub_Quantity" ,"Sub_Unit", "Remark ", "Safety_Level"} );
        
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
                index ++;
                String inventory_number = i.getInventory_number();
                String name = i.getName();
                String categories = null;
                for (Categories c : dbAllCategoriesInfo){
                    if (c.getCategories_id() == i.getCategories_id()){
                        categories = c.getName();
                        break;
                    }
                }

                String type = null;
                for (Type t : dbAllTypeInfo){
                    if (t.getType_id() == i.getType_id()){
                        type = t.getName();
                        break;
                    }
                }
                String quantity = String.valueOf(i.getQuantity());
                String unit = i.getUnit();
                String quantity_per_unit = String.valueOf(i.getQuantity_per_unit());
                String quantity_unit = i.getQuantity_unit();
                String remark = i.getRemark();
                String safety_level = String.valueOf(i.getSafety_level());

                infoData.put(index, new Object[]{inventory_number,name,categories, type,quantity,unit,quantity_per_unit
                ,quantity_unit,remark,safety_level} );  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getInitialInventoryInfo (){
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Inventory_number","Name","Categories", "Type" ," Quantity" ,"Unit", 
            "Sub_Quantity" ,"Sub_Unit", "Remark ", "Location"} );
        
        if (dbAllInitialInventoryInfo != null){
            for (InitialInventory il : dbAllInitialInventoryInfo){
                index ++;
                String inventory_number = null;
                String name = null;
                String categories = null;
                String type = null;
                for (Inventory i : dbAllInventoryInfo){
                    if (i.getInventory_id() == il.getInventory_id()){
                        inventory_number = i.getInventory_number();
                        name = i.getName();

                        for (Categories c : dbAllCategoriesInfo){
                            if (c.getCategories_id() == i.getCategories_id()){
                                categories = c.getName();
                                break;
                            }
                        }

                        for (Type t : dbAllTypeInfo){
                            if (t.getType_id() == i.getType_id()){
                                type = t.getName();
                                break;
                            }
                        }

                        break;
                    }
                }
                String quantity = String.valueOf(il.getQuantity());
                String unit = il.getUnit();
                String sub_quantity = String.valueOf(il.getQuantity_per_unit());
                String quantity_unit = il.getQuantity_unit();
                String remark = il.getRemark();

                String location = null;
                for (InventoryLocation invloc : dbAllInventoryLocationInfo){
                    if(invloc.getInventory_location_id() == il.getInventory_location_id()){
                        location = invloc.getName();
                    }
                }

                infoData.put(index, new Object[]{inventory_number, name, categories, type, quantity, unit, sub_quantity
                , quantity_unit,remark, location} );  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getCustomerInfo (){
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Customer Number","Name","SSM", "Email" ," Company Phone" ,"Mobile Phone", 
            "Address" ,"Person in charge"} );
        
        if (dbAllCustomerInfo != null){
            for (Customer c : dbAllCustomerInfo){
                index ++;
                String customer_number = c.getCustomer_number();
                String company_name = c.getCompany_name();
                String ssm = c.getSSM();
                String email = c.getEmail();
                String company_phone = c.getCompany_phone();
                String mobile_phone = c.getMobile_phone();
                String address = c.getAddress();
                String person_in_charge = c.getPerson_in_charge();

                infoData.put(index, new Object[]{customer_number, company_name, ssm, email, company_phone,
                mobile_phone, address, person_in_charge});  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getSupplierInfo (){
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Supplier Number","Name","SSM", "Email" ," Company Phone" ,"Mobile Phone", 
            "Address" ,"Person in charge"} );
        
        if (dbAllSupplierInfo != null){
            for (Supplier s : dbAllSupplierInfo){
                index ++;
                String supplier_number = s.getSupplier_number();
                String company_name = s.getCompany_name();
                String ssm = s.getSSM();
                String email = s.getEmail();
                String company_phone = s.getCompany_phone();
                String mobile_phone = s.getMobile_phone();
                String address = s.getAddress();
                String person_in_charge = s.getPerson_in_charge();

                infoData.put(index, new Object[]{supplier_number, company_name, ssm, email, company_phone,
                mobile_phone, address, person_in_charge});  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getEmployeeInfo (){
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Employee Number","Name","Identity Card Number" ,"Mobile Phone", 
            "Emergency Phone","Address" ,"Position"} );
        
        if (dbAllEmployeeInfo != null){
            for (Employee e : dbAllEmployeeInfo){
                index ++;
                String staff_number = e.getStaff_number();
                String name = e.getName();
                String identity_number = e.getIdentity_number();
                String mobile_phone = e.getMobile_phone();
                String emergency_phone = e.getEmergency_phone();
                String address = e.getAddress();
                String position = e.getPosition();

                infoData.put(index, new Object[]{staff_number, name, identity_number, mobile_phone, emergency_phone,
                address, position});  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getPurchaseOrderInfo (){
        String selectedStartItem = (String)startPOBox.getSelectedItem();
        String selectedEndItem = (String)endPOBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllPurchaseOrderInfo != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (po.getPurchase_order_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (po.getPurchase_order_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }

        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"PO Number","Order Date", "Invetory Number", "Inventory", "Categories",
            "Type", "Quantity", "Unit","Expected Receiving Date", "Employee"} );
        
        if (dbAllPurchaseOrderInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String purchase_order_number = dbAllPurchaseOrderInfo.get(i).getPurchase_order_number();
                String inventory = null;
                String inventory_number = null;
                String categories = null;
                String type = null;
                for (Inventory inv : dbAllInventoryInfo){
                    if(inv.getInventory_id() == dbAllPurchaseOrderInfo.get(i).getInventory_id()){
                        inventory = inv.getName();
                        inventory_number = inv.getInventory_number();
                        
                        for (Categories c : dbAllCategoriesInfo){
                            if (c.getCategories_id() == inv.getCategories_id()){
                                categories = c.getName();
                                break;
                            }
                        }

                        for (Type t : dbAllTypeInfo){
                            if (t.getType_id() == inv.getType_id()){
                                type = t.getName();
                                break;
                            }
                        }
                        
                        break;
                    } 
                }
                String quantity = String.valueOf(dbAllPurchaseOrderInfo.get(i).getQuantity());
                String unit = dbAllPurchaseOrderInfo.get(i).getUnit();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                String order_date = dateFormat.format(dbAllPurchaseOrderInfo.get(i).getOrder_date());
                String received_date = dateFormat.format(dbAllPurchaseOrderInfo.get(i).getExpect_received_date());
                String employee = null;
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getStaff_id() == dbAllPurchaseOrderInfo.get(i).getStaff_id()){
                        employee = e.getName();
                        break;
                    }
                }
                infoData.put(index, new Object[]{purchase_order_number, order_date, inventory_number, inventory,
                categories, type, quantity, unit, received_date, employee});  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getPurchaseInvoiceInfo (){
        String selectedStartItem = (String)startPIBox.getSelectedItem();
        String selectedEndItem = (String)endPIBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllPurchaseInvoiceInfo != null){
            for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (pi.getPurchase_invoice_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (pi.getPurchase_invoice_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"PI Number","Invoice Date","Received Number", "Supplier", "Invetory Number", 
            "Inventory", "Categories", "Type", "Quantity", "Unit","Unit Price", "Total Price", "Employee"} );
        
        if (dbAllPurchaseInvoiceInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String purchase_invoice_number = dbAllPurchaseInvoiceInfo.get(i).getPurchase_invoice_number();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String invoice_date = dateFormat.format(dbAllPurchaseInvoiceInfo.get(i).getPurchase_invoice_date());
                String received_number = null;
                String supplier = null;
                String inventory_number = null;
                String inventory_name = null;
                String categories = null;
                String type = null;
                String quantity = null;
                String unit = null;
                for (Received r : dbAllReceivedInfo){
                    if (r.getReceived_id() == dbAllPurchaseInvoiceInfo.get(i).getReceived_id()){
                        received_number = r.getReceived_number();
                        
                        for (Supplier s : dbAllSupplierInfo){
                            if (s.getSupplier_id() == r.getSupplier_id()){
                                supplier = s.getCompany_name();
                                break;
                            }
                        }
                        
                        for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                            if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                                for (Inventory inv : dbAllInventoryInfo){
                                    if (inv.getInventory_id() == po.getInventory_id()){
                                        inventory_number = inv.getInventory_number();
                                        inventory_name = inv.getName();
                                        unit = inv.getUnit();
                                        quantity = String.valueOf(po.getQuantity());
                                        
                                        for (Categories c : dbAllCategoriesInfo){
                                            if (c.getCategories_id() == inv.getCategories_id()){
                                                categories = c.getName();
                                                break;
                                            }
                                        }

                                        for (Type t : dbAllTypeInfo){
                                            if (t.getType_id() == inv.getType_id()){
                                                type = t.getName();
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                
                String unit_price = String.valueOf(dbAllPurchaseInvoiceInfo.get(i).getUnit_price());
                String total_price = String.valueOf(dbAllPurchaseInvoiceInfo.get(i).getTotal_price());
                String employee = null;
                
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getStaff_id() == dbAllPurchaseInvoiceInfo.get(i).getStaff_id()){
                        employee = e.getName();
                    }
                }
                
                infoData.put(index, new Object[]{purchase_invoice_number, invoice_date, received_number,
                supplier, inventory_number, inventory_name, categories, type, quantity, unit, unit_price,
                total_price, employee});  
            }
        }
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getPurchaseReturnInfo (){
        String selectedStartItem = (String)startPRBox.getSelectedItem();
        String selectedEndItem = (String)endPRBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllPurchaseReturnInfo != null){
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (pr.getPurchase_return_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (pr.getPurchase_return_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"PR Number","Return Date", "Supplier", "Invetory Number", 
            "Inventory", "Categories", "Type", "Quantity", "Unit","Unit Price", "Total Price", "Remark"} );
        
        if (dbAllPurchaseReturnInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String purchase_return_number = dbAllPurchaseReturnInfo.get(i).getPurchase_return_number();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String return_date = dateFormat.format(dbAllPurchaseReturnInfo.get(i).getPurchase_return_date());
                String supplier = null;
                String inventory_number = null;
                String inventory_name = null;
                String categories = null;
                String type = null;
                Double quantityToCountTotalPrice = 0.0;
                String quantity = null;
                String unit = null;
                for (PurchaseInvoice pi: dbAllPurchaseInvoiceInfo){
                    if (pi.getPurchase_invoice_id() == dbAllPurchaseReturnInfo.get(i).getPurchase_invoice_id()){
                        for (Received r : dbAllReceivedInfo){
                            if (r.getReceived_id()== pi.getReceived_id()){
                                for (Supplier s : dbAllSupplierInfo){
                                    if (s.getSupplier_id() == r.getSupplier_id()){
                                        supplier = s.getCompany_name();
                                        break;
                                    }
                                }

                                for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                                    if (po.getPurchase_order_id() == r.getPurchase_order_id()){
                                        for (Inventory inv : dbAllInventoryInfo){
                                            if (inv.getInventory_id() == po.getInventory_id()){
                                                inventory_number = inv.getInventory_number();
                                                inventory_name = inv.getName();
                                                unit = inv.getUnit();
                                                quantity = String.valueOf(dbAllPurchaseReturnInfo.get(i).getQuantity());
                                                quantityToCountTotalPrice = dbAllPurchaseReturnInfo.get(i).getQuantity();

                                                for (Categories c : dbAllCategoriesInfo){
                                                    if (c.getCategories_id() == inv.getCategories_id()){
                                                        categories = c.getName();
                                                        break;
                                                    }
                                                }

                                                for (Type t : dbAllTypeInfo){
                                                    if (t.getType_id() == inv.getType_id()){
                                                        type = t.getName();
                                                        break;
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                
                String unit_price = null;
                Double unitPrice = 0.0;
                for (PurchaseInvoice pi : dbAllPurchaseInvoiceInfo){
                    if (pi.getPurchase_invoice_id() == dbAllPurchaseReturnInfo.get(i).getPurchase_invoice_id()){
                        unit_price = String.valueOf(pi.getUnit_price());
                        unitPrice = pi.getUnit_price();
                        break;
                    }
                    
                }

                String total_price = String.valueOf(quantityToCountTotalPrice*unitPrice);
                String remark = dbAllPurchaseReturnInfo.get(i).getRemark();
                
                
                
                infoData.put(index, new Object[]{purchase_return_number, return_date, supplier,
                inventory_number, inventory_name, categories, type, quantity, unit, unit_price,
                total_price, remark});  
            }
        }
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getReceivedInfo (){
        String selectedStartItem = (String)startReceivedBox.getSelectedItem();
        String selectedEndItem = (String)endReceivedBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (r.getReceived_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (r.getReceived_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"Received Number","Received Date", "Supplier", "Invetory Number", 
            "Inventory", "Categories", "Type", "Quantity", "Unit", "Expired Date", "Location"} );
        
        if (dbAllReceivedInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String received_number = dbAllReceivedInfo.get(i).getReceived_number();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String received_date = dateFormat.format(dbAllReceivedInfo.get(i).getReceived_date());
                String expired_date = dateFormat.format(dbAllReceivedInfo.get(i).getExpired_date());
                String supplier = null;
                String inventory_number = null;
                String inventory_name = null;
                String categories = null;
                String type = null;
                String quantity = null;
                String unit = null;
                String location = null;

                for (Supplier s : dbAllSupplierInfo){
                    if (s.getSupplier_id() == dbAllReceivedInfo.get(i).getSupplier_id()){
                        supplier = s.getCompany_name();
                        break;
                    }
                }

                for (PurchaseOrder po : dbAllPurchaseOrderInfo){
                    if (po.getPurchase_order_id() == dbAllReceivedInfo.get(i).getPurchase_order_id()){
                        for (Inventory inv : dbAllInventoryInfo){
                            if (inv.getInventory_id() == po.getInventory_id()){
                                inventory_number = inv.getInventory_number();
                                inventory_name = inv.getName();
                                unit = inv.getUnit();
                                quantity = String.valueOf(dbAllPurchaseOrderInfo.get(i).getQuantity());

                                for (Categories c : dbAllCategoriesInfo){
                                    if (c.getCategories_id() == inv.getCategories_id()){
                                        categories = c.getName();
                                        break;
                                    }
                                }

                                for (Type t : dbAllTypeInfo){
                                    if (t.getType_id() == inv.getType_id()){
                                        type = t.getName();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }


                for (InventoryLocation il : dbAllInventoryLocationInfo){
                    if (il.getInventory_location_id() == dbAllReceivedInfo.get(i).getInventory_location_id()){
                        location = il.getName();
                    }
                }

                infoData.put(index, new Object[]{received_number, received_date, supplier,
                inventory_number, inventory_name, categories, type, quantity, unit, expired_date,
                location});  
            }
        }
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getSalesOrderInfo (){
        String selectedStartItem = (String)startSOBox.getSelectedItem();
        String selectedEndItem = (String)endSOBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllSalesOrderInfo != null){
            for (SalesOrder po : dbAllSalesOrderInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (po.getSales_order_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (po.getSales_order_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }

        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"SO Number","Order Date", "Customer", "Invetory Number", "Inventory", "Categories",
            "Type", "Quantity", "Unit","Delivery Date", "Employee"} );
        
        if (dbAllSalesOrderInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String sales_order_number = dbAllSalesOrderInfo.get(i).getSales_order_number();
                String inventory = null;
                String inventory_number = null;
                String categories = null;
                String type = null;
                String customer = null;
                
                for (Customer c: dbAllCustomerInfo){
                    if (c.getCustomer_id() == dbAllSalesOrderInfo.get(i).getCustomer_id()){
                        customer = c.getCompany_name();
                    }
                }
                
                for (Inventory inv : dbAllInventoryInfo){
                    if(inv.getInventory_id() == dbAllSalesOrderInfo.get(i).getInventory_id()){
                        inventory = inv.getName();
                        inventory_number = inv.getInventory_number();
                        
                        for (Categories c : dbAllCategoriesInfo){
                            if (c.getCategories_id() == inv.getCategories_id()){
                                categories = c.getName();
                                break;
                            }
                        }

                        for (Type t : dbAllTypeInfo){
                            if (t.getType_id() == inv.getType_id()){
                                type = t.getName();
                                break;
                            }
                        }
                        
                        break;
                    } 
                }
                String quantity = String.valueOf(dbAllSalesOrderInfo.get(i).getQuantity());
                String unit = dbAllSalesOrderInfo.get(i).getUnit();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                String order_date = dateFormat.format(dbAllSalesOrderInfo.get(i).getSales_order_date());
                String delivery_date = dateFormat.format(dbAllSalesOrderInfo.get(i).getDelivery_date());
                String employee = null;
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getStaff_id() == dbAllSalesOrderInfo.get(i).getStaff_id()){
                        employee = e.getName();
                        break;
                    }
                }
                infoData.put(index, new Object[]{sales_order_number, order_date, customer, inventory_number, inventory,
                categories, type, quantity, unit, delivery_date, employee});  
            }
        }
        
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getSalesInvoiceInfo (){
        String selectedStartItem = (String)startSIBox.getSelectedItem();
        String selectedEndItem = (String)endSIBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllSalesInvoiceInfo != null){
            for (SalesInvoice si : dbAllSalesInvoiceInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (si.getSales_invoice_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (si.getSales_invoice_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"SI Number","Invoice Date","SO Number", "Customer", "Invetory Number", 
            "Inventory", "Categories", "Type", "Quantity", "Unit","Unit Price", "Total Price", "Employee"} );
        
        if (dbAllSalesInvoiceInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String sales_invoice_number = dbAllSalesInvoiceInfo.get(i).getSales_invoice_number();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String invoice_date = dateFormat.format(dbAllSalesInvoiceInfo.get(i).getSales_invoice_date());
                String SO_number = null;
                String customer = null;
                String inventory_number = null;
                String inventory_name = null;
                String categories = null;
                String type = null;
                String quantity = null;
                String unit = null;
                for (SalesOrder so : dbAllSalesOrderInfo){
                    if (so.getSales_order_id() == dbAllSalesInvoiceInfo.get(i).getSales_order_id()){
                        SO_number = so.getSales_order_number();
                        quantity = String.valueOf(so.getQuantity());
                        
                        for (Customer c : dbAllCustomerInfo){
                            if (so.getCustomer_id() == c.getCustomer_id()){
                                customer = c.getCompany_name();
                                break;
                            }
                        }
                        
                        for (Inventory inv : dbAllInventoryInfo){
                            if (inv.getInventory_id() == so.getInventory_id()){
                                inventory_number = inv.getInventory_number();
                                inventory_name = inv.getName();
                                unit = inv.getUnit();
                                
                                for (Categories c : dbAllCategoriesInfo){
                                    if (c.getCategories_id() == inv.getCategories_id()){
                                        categories = c.getName();
                                        break;
                                    }
                                }

                                for (Type t : dbAllTypeInfo){
                                    if (t.getType_id() == inv.getType_id()){
                                        type = t.getName();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                
                String unit_price = String.valueOf(dbAllSalesInvoiceInfo.get(i).getUnit_price());
                String total_price = String.valueOf(dbAllSalesInvoiceInfo.get(i).getTotal_price());
                String employee = null;
                for (Employee e : dbAllEmployeeInfo){
                    if (e.getStaff_id() == dbAllSalesInvoiceInfo.get(i).getStaff_id()){
                        employee = e.getName();
                    }
                }
                
                infoData.put(index, new Object[]{sales_invoice_number, invoice_date, SO_number, customer,
                inventory_number, inventory_name, categories, type, quantity, unit, unit_price, total_price,
                employee});  
            }
        }
        return infoData;
    }
    
    private HashMap<Integer, Object[]> getSalesReturnInfo (){
        String selectedStartItem = (String)startSRBox.getSelectedItem();
        String selectedEndItem = (String)endSRBox.getSelectedItem();

        int selectedStartIndex = 0;
        int selectedEndIndex = 0; 
        boolean check = true, checkFirst = true;
        int indexDB = 0;
        if (dbAllSalesReturnInfo != null){
            for (SalesReturn sr : dbAllSalesReturnInfo){
                if (selectedEndIndex != 0 && check){
                    selectedEndIndex++;
                    checkFirst = false;
                }
                
                if (sr.getSales_return_number().equals(selectedStartItem) && checkFirst){
                    if (selectedEndIndex == 0){
                        selectedEndIndex++;
                        selectedStartIndex = indexDB;
                    }
                }

                if (sr.getSales_return_number().equals(selectedEndItem)){
                    check = false;
                    selectedEndIndex++;
                }
                indexDB ++;
            }
            selectedEndIndex--;
        }
        HashMap<Integer, Object[]> infoData = new HashMap<>();
        int index = 0;
        
        infoData.put(index, new Object[]{"SR Number","Return Date", "Customer", "Invetory Number", 
            "Inventory", "Categories", "Type", "Quantity", "Unit","Unit Price", "Total Price", "Remark"} );
        
        if (dbAllSalesReturnInfo != null){
            for (int i = selectedStartIndex; i<(selectedStartIndex + selectedEndIndex); i++){
                index++;
                String sales_return_number = dbAllSalesReturnInfo.get(i).getSales_return_number();
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String return_date = dateFormat.format(dbAllSalesReturnInfo.get(i).getSales_return_date());
                String customer = null;
                String inventory_number = null;
                String inventory_name = null;
                String categories = null;
                String type = null;
                Double quantityToCountTotalPrice = 0.0;
                String quantity = null;
                String unit = null;
                String unit_price = null;
                Double unitPrice = 0.0;
                for (SalesInvoice si: dbAllSalesInvoiceInfo){
                    if (si.getSales_invoice_id() == dbAllSalesReturnInfo.get(i).getSales_invoice_id()){
                        for (SalesOrder so : dbAllSalesOrderInfo){
                            if (so.getSales_order_id()== si.getSales_order_id()){
                                
                                for (Customer c : dbAllCustomerInfo){
                                    if (c.getCustomer_id() == so.getCustomer_id()){
                                        customer = c.getCompany_name();
                                        break;
                                    }
                                }
                                
                                for (Inventory inv : dbAllInventoryInfo){
                                    if (inv.getInventory_id() == so.getInventory_id()){
                                        inventory_number = inv.getInventory_number();
                                        inventory_name = inv.getName();
                                        unit = inv.getUnit();
                                        
                                        for (Categories c : dbAllCategoriesInfo){
                                            if (c.getCategories_id() == inv.getCategories_id()){
                                                categories = c.getName();
                                                break;
                                            }
                                        }

                                        for (Type t : dbAllTypeInfo){
                                            if (t.getType_id() == inv.getType_id()){
                                                type = t.getName();
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        unit_price = String.valueOf(si.getUnit_price());
                        unitPrice = si.getUnit_price();
                        break;
                    }
                }
                
                quantity = String.valueOf(dbAllSalesReturnInfo.get(i).getQuantity());
                quantityToCountTotalPrice = dbAllSalesReturnInfo.get(i).getQuantity();
                
                String total_price = String.valueOf(quantityToCountTotalPrice*unitPrice);
                String remark = dbAllSalesReturnInfo.get(i).getRemark();

                infoData.put(index, new Object[]{sales_return_number, return_date, customer,
                inventory_number, inventory_name, categories, type, quantity, unit, unit_price,
                total_price, remark});  
            }
        }
        return infoData;
    }
}
