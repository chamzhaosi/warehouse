/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import PsnClass.Type;
import PsnClass.Categories;
import DBConnector.JDBCTemplate;
import PsnClass.AvailableInventory;
import PsnClass.InitialInventory;
import PsnClass.Inventory;
import PsnClass.InventoryLocation;
import PsnClass.PurchaseOrder;
import PsnClass.PurchaseReturn;
import PsnClass.Received;
import PsnClass.SalesOrder;
import Table.JTableBg;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class HomePanel extends JPanel{
    private JPanel safetyPanel = null;
    private JPanel expiredPanel = null;
    private JPanel receivePanel = null;
    private JPanel salesPanel = null;
    
    private DefaultTableModel tableModel = null;
    private JTable invetoryTable = null;
    private DefaultTableModel safetyTableModel = null;
    private JTable safetyTable = null;
    private DefaultTableModel expiredTableModel = null;
    private JTable expiredTable = null;
    private DefaultTableModel receiveTableModel = null;
    private JTable receiveTable = null;
    private DefaultTableModel salesTableModel = null;
    private JTable salesTable = null;

    private List<Inventory> dbAllInventoryInfo = null; 
    private List<Received> dbAllReceivedInfoCleared = null;
    private List<Received> dbAllReceivedInfo = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoCleared = null;
    private List<PurchaseOrder> dbAllPurchaseOrderInfoNotCleared = null;
    private List<PurchaseReturn> dbAllPurchaseReturnInfo = null;
    private List<SalesOrder> dbAllSalesOrderInfo = null;
    private List<SalesOrder> dbAllSalesOrderInfoNotCleared = null;
    private List<InitialInventory> dbAllInitialInventorysInfo = null;
    private List<Categories> dbAllCategoriesInfo = null;
    private List<Type> dbAllTypeInfo = null;
    private List<InventoryLocation> dbAllLocationInfo = null;
    
    private List<AvailableInventory> aiList = null;
                     
    
    public HomePanel(){
        this.setLayout(new GridLayout(2,1,10,10));
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(Color.WHITE);
        
        getAvailableInventory();
        setAvailableInventoryPanel();
        setRemindNoticeMessagePanel();
        //becasue this is the first panel to show, so other panel can be skip.
        setSafetyPanelTabel();
    }
    
    public void updateAllDBInfo(){
        getDBAllInventoryInfo();
        getDBAllSalseOrderInfo();
        getDBAllInitialInventoryInfo();
        getDBAllReceivedInfo();
        getDBAllPurchaseReturnInfo();
        getDBAllPurchaseOrderInfo();
        getDBAllTypeInfo();
        getDBAllCategoriesInfo();
        getDBAllLocationInfo();
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("Inventory_table", "Inventory_id");
    }
    
    private void getDBAllSalseOrderInfo(){
        dbAllSalesOrderInfo = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", 
                "sales_order_number");
        
        dbAllSalesOrderInfoNotCleared = new JDBCTemplate().getDBAllSalesOrdersInfo("sales_order_table", 
                "sales_order_number", 0);
    }
    
    private void getDBAllInitialInventoryInfo(){
        dbAllInitialInventorysInfo = new JDBCTemplate().getDBAllInitialInventoryInfo("initial_inventory_table", 
                "initial_inventory_id");
    }
     
    private void getDBAllReceivedInfo(){
        dbAllReceivedInfoCleared = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number", 1);
        dbAllReceivedInfo = new JDBCTemplate().getDBAllReceivedInfo("received_table", "received_number");
    }
      
    private void getDBAllPurchaseReturnInfo(){
        dbAllPurchaseReturnInfo = new JDBCTemplate().getDBAllPurchaseReturnInfo("purchase_return_table", 
                "purchase_return_number");
    }
     
    private void getDBAllPurchaseOrderInfo(){
        dbAllPurchaseOrderInfoCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", 
                "purchase_order_number", 1);
        
        dbAllPurchaseOrderInfoNotCleared = new JDBCTemplate().getDBAllPurchaseOrdersInfo("purchase_order_table", 
                "purchase_order_number", 0);
    }
    
    private void getDBAllLocationInfo(){
        dbAllLocationInfo = new JDBCTemplate().getDBAllLocationInfo("inventory_location_table", "name");
    }
    
    private void getDBAllTypeInfo(){
        dbAllTypeInfo = new JDBCTemplate().getDBALLTypeInfo("type_table", "name");
    }
    
    private void getDBAllCategoriesInfo(){
        dbAllCategoriesInfo = new JDBCTemplate().getDBALLCategoriesInfo("categories_table", "name");
    }
    
    private void setAvailableInventoryPanel(){
        JPanel availableInventoryPanel = new JPanel();
        availableInventoryPanel.setBackground(new Color(119, 199, 103));
        availableInventoryPanel.setLayout(new BorderLayout());
        
        JLabel availableInventoryLabel = new JLabel("<< Current Available Inventory List >>", SwingUtilities.CENTER);
        availableInventoryLabel.setFont(new Font("", Font.BOLD, 40));
        availableInventoryPanel.add(availableInventoryLabel, BorderLayout.NORTH);
        
        String columns[] = 
        {"InventoryID", "Inventory Name", "Category", "Type", "Quantity", "Safety Level", "Unit"};

        Object [][] data = getInventoryData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class};
        
        tableModel = new DefaultTableModel(data, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        invetoryTable = new JTableBg(tableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                   comp.setBackground(c);
                   c = null;
                } 
                
                Object price = getModel().getValueAt(row, column);
                if (price instanceof Double && !getModel().getColumnName(column).equals("Safety Level")){
                    Double price_value = (Double)price;
                    if (price_value == 0.0 ){
                        comp.setBackground(new Color(230, 123, 96));
                    }else {
                        comp.setBackground(new Color(142, 237, 168));
                    }
                }
                return comp;
            }
            
        };
        invetoryTable.setCellSelectionEnabled(true);
        invetoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = invetoryTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(invetoryTable);
        sp.setBackground(new Color(102, 176, 103));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        availableInventoryPanel.add(sp, BorderLayout.CENTER);
        
        JPanel viewLocationDetialButtonPanel = new JPanel();
        viewLocationDetialButtonPanel.setLayout(new FlowLayout());
        
        JButton viewLocationDetialButton = new JButton("View Location Detail");
        viewLocationDetialButtonPanel.add(viewLocationDetialButton);
        
        availableInventoryPanel.add(viewLocationDetialButtonPanel, BorderLayout.SOUTH);
        this.add(availableInventoryPanel);
        
        viewLocationDetialButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = invetoryTable.getSelectedRow();
                if (index != -1){
                    callViewLocationDetialDialog(index);
                }
            }
        
        });
    }
    
    private void callViewLocationDetialDialog(int index){
        if (aiList.get(index).getQuantity() == 0){
            JOptionPane.showMessageDialog(invetoryTable, "The item is not any available!!", "Notice", 1);
        }else {
            AvailableInventory iniAvaInventory = new AvailableInventory();
            int inventory_id = aiList.get(index).getInventory_id();
            for (InitialInventory il : dbAllInitialInventorysInfo){
                if (il.getInventory_id() == inventory_id){
                    iniAvaInventory.setQuantity(il.getQuantity());
                    
                    for (InventoryLocation invloc : dbAllLocationInfo){
                        if (il.getInventory_location_id() == invloc.getInventory_location_id()){
                            iniAvaInventory.setInventory_location_name(invloc.getName());
                        }
                    }
                    break;
                }
            }
            //System.out.println(iniAvaInventory.getQuantity());
            
            double salesQuantity = 0.0;
            for (SalesOrder so : dbAllSalesOrderInfo){
                if (so.getInventory_id() == inventory_id){
                    salesQuantity += so.getQuantity();
                }
            }
            //System.out.println(salesQuantity);
            
            AvailableInventory recAvaInventory = new AvailableInventory();
            for (Received r : dbAllReceivedInfoCleared){
                for (PurchaseOrder po : dbAllPurchaseOrderInfoCleared){
                    if ((po.getInventory_id() == inventory_id) && (r.getPurchase_order_id() == po.getPurchase_order_id())){
                        recAvaInventory.setQuantity(recAvaInventory.getQuantity()+ po.getQuantity());
                        
                        for (InventoryLocation invloc : dbAllLocationInfo){
                            if (r.getInventory_location_id() == invloc.getInventory_location_id()){
                                recAvaInventory.setInventory_location_name(invloc.getName());
                                break;
                            }
                        }
                    }
                }
            }
            
            //System.out.println(recAvaInventory.getQuantity());
            
            double returnQuantity = 0.0;
            for (PurchaseReturn pr : dbAllPurchaseReturnInfo){
                if (pr.getInventory_id() == inventory_id){
                    returnQuantity += pr.getQuantity();
                }
            }
            
            //System.out.println(returnQuantity);
            
            String inventory_name = aiList.get(index).getInventory_name();
            double iniBalQuantity = iniAvaInventory.getQuantity() - salesQuantity;
            double recBalQuantity = recAvaInventory.getQuantity() - returnQuantity;
            if (iniBalQuantity > 0 && recBalQuantity > 0){
                JOptionPane.showMessageDialog(invetoryTable, "The item "+ inventory_name + " is located at: \n"
                        + iniAvaInventory.getInventory_location_name() + " -> " + String.valueOf(iniBalQuantity) +
                        "\n "+ recAvaInventory.getInventory_location_name() + " -> " + recBalQuantity, "Notice", 1);
            }else if (iniBalQuantity == 0 && recBalQuantity > 0){
                JOptionPane.showMessageDialog(invetoryTable, "The item "+ inventory_name + " is located at: \n"
                        + recAvaInventory.getInventory_location_name() + " -> " + recBalQuantity, "Notice", 1);
            }else if (iniBalQuantity < 0 && recBalQuantity > 0){
                recBalQuantity += iniBalQuantity;  
                JOptionPane.showMessageDialog(invetoryTable, "The item "+ inventory_name + " is located at: \n"
                        + recAvaInventory.getInventory_location_name() + " -> " + recBalQuantity, "Notice", 1);
            }else if (iniBalQuantity > 0 && recBalQuantity == 0){
                JOptionPane.showMessageDialog(invetoryTable, "The item "+ inventory_name + " is located at: \n"
                        + iniAvaInventory.getInventory_location_name() + " -> " + String.valueOf(iniBalQuantity), "Notice", 1);
            }
        }
    }
    
    public void updateInventory(){
        getAvailableInventory();
        
        tableModel.setRowCount(0);
        
        Object[][] updataNewRows = getInventoryData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                tableModel.addRow(updataNewRows[i]);
            }
        }
    }
    
    private Object[][] getInventoryData(){
        if (aiList != null){
            Object [][] data = new Object[aiList.size()][7];
            for (int i = 0; i< aiList.size(); i++){
                data[i][0] = aiList.get(i).getInventory_number();
                data[i][1] = aiList.get(i).getInventory_name();
                data[i][2] = aiList.get(i).getCategory();
                data[i][3] = aiList.get(i).getType();
                data[i][4] = aiList.get(i).getQuantity();
                data[i][5] = aiList.get(i).getSafety_quantity();
                data[i][6] = aiList.get(i).getUnit();
            }
            return data;
        }
        return null;
    }
    
    private void getAvailableInventory(){
        updateAllDBInfo();
        
        if (aiList == null){
            aiList = new ArrayList<>();
        }else {
            aiList.clear();
        }
        
        
        if (dbAllInventoryInfo != null){
            for (Inventory i : dbAllInventoryInfo){
            AvailableInventory ai = new AvailableInventory();
            ai.setInventory_number(i.getInventory_number());
            ai.setInventory_id(i.getInventory_id());
            ai.setInventory_name(i.getName());
            ai.setUnit(i.getUnit());
            ai.setSafety_quantity(i.getSafety_level());

            for (Categories c : dbAllCategoriesInfo){
                if (i.getCategories_id() == c.getCategories_id()){
                    ai.setCategory(c.getName());
                    break;
                }
            }

            for (Type t : dbAllTypeInfo){
                if (i.getType_id() == t.getType_id()){
                    ai.setType(t.getName());
                    break;
                }
            }
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
    
    private void setRemindNoticeMessagePanel(){
        JPanel remindNoticeMessagePanel = new JPanel();
        remindNoticeMessagePanel.setBackground(new Color(87, 166, 212));
        remindNoticeMessagePanel.setLayout(new BorderLayout());
        
        JLabel reminderLabel = new JLabel(" <<Reminder Notice>> " , SwingUtilities.CENTER);
        reminderLabel.setFont(new Font("", Font.BOLD, 40));
        remindNoticeMessagePanel.add(reminderLabel, BorderLayout.NORTH);

        JPanel differentButtonPanel = new JPanel();
        differentButtonPanel.setBackground(new Color(87, 166, 212));
        differentButtonPanel.setLayout(new GridLayout(4,1,5,5));
        remindNoticeMessagePanel.add(differentButtonPanel, BorderLayout.EAST);
        
        JButton safetyButton = new JButton("Safety");
        JButton expiredButton = new JButton("Expired");
        JButton receiveButton = new JButton("Receive");
        JButton salesButton = new JButton("Delivery");
            
        differentButtonPanel.add(safetyButton);
        differentButtonPanel.add(expiredButton);
        differentButtonPanel.add(receiveButton);
        differentButtonPanel.add(salesButton);
        
        safetyPanel = new JPanel();
        expiredPanel = new JPanel();
        receivePanel = new JPanel();
        salesPanel = new JPanel();

        JPanel carLayoutPanel = new JPanel();
        carLayoutPanel.setBorder(new LineBorder(Color.GRAY));
        carLayoutPanel.setLayout(new CardLayout());
        carLayoutPanel.add(safetyPanel, "card1");
        carLayoutPanel.add(expiredPanel, "card2");
        carLayoutPanel.add(receivePanel, "card3");
        carLayoutPanel.add(salesPanel, "card4");
        
        safetyButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (safetyTableModel != null){
                    updateSafety();
                }else {
                    setSafetyPanelTabel();
                }
                
                CardLayout c1 = (CardLayout)carLayoutPanel.getLayout();
                c1.show(carLayoutPanel, "card1");
            }
        
        });
        
        expiredButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expiredTableModel != null){
                    updateExpired();
                }else {
                    setExpiredPanelTabel();
                }
                CardLayout c1 = (CardLayout)carLayoutPanel.getLayout();
                c1.show(carLayoutPanel, "card2");
            }
        
        });
        
        receiveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (receiveTableModel != null){
                    updateReceive();
                }else {
                    setReceivePanelTabel();
                }
                CardLayout c1 = (CardLayout)carLayoutPanel.getLayout();
                c1.show(carLayoutPanel, "card3");
            }
        
        });
        
        salesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (salesTableModel != null){
                    updateSales();
                }else {
                    setSalesPanelTabel();
                }
                CardLayout c1 = (CardLayout)carLayoutPanel.getLayout();
                c1.show(carLayoutPanel, "card4");
            }
        
        });
        
        remindNoticeMessagePanel.add(carLayoutPanel, BorderLayout.CENTER);
        
        this.add(remindNoticeMessagePanel);
    }
    
    private void setSafetyPanelTabel(){
        safetyPanel.setLayout(new BorderLayout());
        
        String columns[] = 
        {"InventoryID", "Inventory Name", "Category", "Type", "Quantity", "Safety Level", "Unit"};

        Object [][] safetyData = getSafetyData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class};
        
        safetyTableModel = new DefaultTableModel(safetyData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        safetyTable = new JTableBg(safetyTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                   comp.setBackground(c);
                   c = null;
                } 
                
                Object quantity = getModel().getValueAt(row, column);
                if (quantity instanceof Double && !getModel().getColumnName(column).equals("Safety Level")){
                    Double safety_value = (Double)getModel().getValueAt(row, 5);
                    Double quantity_value = (Double)quantity;
                    if (quantity_value >= safety_value ){
                        comp.setBackground(new Color(222, 109, 109));
                    }else if (quantity_value < safety_value){
                        comp.setBackground(new Color(161, 21, 21));
                    }
                }
                return comp;
            }
            
        };
        
        safetyTable.setCellSelectionEnabled(true);
        safetyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = safetyTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(safetyTable);
        sp.setBackground(new Color(90, 143, 230));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        safetyPanel.add(sp, BorderLayout.CENTER);
    }
    
    private Object[][] getSafetyData(){
        int index = 0, count = 0;
        
        if (aiList != null){
            for (AvailableInventory ai : aiList){
                if (ai.getSafety_quantity() > 0.0){
                    double safety_quantity = ai.getSafety_quantity();
                    if (ai.getQuantity() <= safety_quantity*2){
                        count++;
                    }
                }
            }
        }
        
        Object [][] safetyData = new Object[count][7];
        
        if (aiList != null){
            for (AvailableInventory ai : aiList){
                if (ai.getSafety_quantity() > 0.0){
                    double safety_quantity = ai.getSafety_quantity();
                    if (ai.getQuantity() <= safety_quantity*2){
                        safetyData[index][0] = ai.getInventory_number();
                        safetyData[index][1] = ai.getInventory_name();
                        safetyData[index][2] = ai.getCategory();
                        safetyData[index][3] = ai.getType();
                        safetyData[index][4] = ai.getQuantity();
                        safetyData[index][5] = ai.getSafety_quantity();
                        safetyData[index][6] = ai.getUnit();
                        index++;
                    }
                }
            }
            return safetyData;
        }
        return null;
    }
    
    public void updateSafety(){
        safetyTableModel.setRowCount(0);
        
        Object[][] updataNewRows = getSafetyData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                safetyTableModel.addRow(updataNewRows[i]);
            }
        }
    }

    private void setExpiredPanelTabel(){
        expiredPanel.setLayout(new BorderLayout());
        
        String columns[] = 
        {"ReceivedID", "InventoryID", "Inventory Name", "Category", "Type", "Quantity", "Unit", "Expired Date"};

        Object [][] expiredData = getExpiredData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class, LocalDate.class};
        
        expiredTableModel = new DefaultTableModel(expiredData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        expiredTable = new JTableBg(expiredTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                   comp.setBackground(c);
                   c = null;
                } 
                
                if (getModel().getColumnName(column).equals("Expired Date")){
                    comp.setBackground(new Color(222, 109, 109));
                }
                return comp;
            }
        };
        expiredTable.setCellSelectionEnabled(true);
        expiredTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = expiredTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(expiredTable);
        sp.setBackground(new Color(70, 128, 224));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        expiredPanel.add(sp, BorderLayout.CENTER);
    }
    
    private Object[][] getExpiredData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate currentDate = LocalDate.parse(dateFormat.format(date), dtf);
        LocalDate currentPlus1Months = currentDate.plusMonths(1);

        List<List<Object>> expiredDataList = new ArrayList<>();
        
        List<AvailableInventory> copyAIList = new ArrayList<>(aiList);
        
        if (dbAllReceivedInfo != null){
            for (Received r : dbAllReceivedInfo){
                LocalDate expired_date = LocalDate.parse(dateFormat.format(r.getExpired_date()), dtf);
                if (expired_date.compareTo(currentPlus1Months) == 0 || expired_date.compareTo(currentPlus1Months) < 0){
                    for (int po = dbAllPurchaseOrderInfoCleared.size()-1; po>=0; po--){
                        if (r.getPurchase_order_id() == dbAllPurchaseOrderInfoCleared.get(po).getPurchase_order_id()){
                            for (AvailableInventory ai: copyAIList){
                                if (dbAllPurchaseOrderInfoCleared.get(po).getInventory_id() == ai.getInventory_id()){
                                    List<Object> itemList = new ArrayList<>();
                                    String received_number = r.getReceived_number();
                                    String inventory_number = ai.getInventory_number();
                                    String inventory_name = ai.getInventory_name();
                                    String category = ai.getCategory();
                                    String type = ai.getType();
                                    double quantity = 0.0;
                                    if (ai.getQuantity() < dbAllPurchaseOrderInfoCleared.get(po).getQuantity()){
                                        quantity = ai.getQuantity();
                                    }else if (ai.getQuantity() == dbAllPurchaseOrderInfoCleared.get(po).getQuantity()){
                                        quantity = ai.getQuantity();
                                    }else if (ai.getQuantity() > dbAllPurchaseOrderInfoCleared.get(po).getQuantity()){
                                        quantity = dbAllPurchaseOrderInfoCleared.get(po).getQuantity();
                                        double balcance = ai.getQuantity() - quantity;
                                        ai.setQuantity(balcance);
                                    }
                                    String unit = ai.getUnit();
                                    LocalDate expiredDate = expired_date;
                                    
                                    itemList.add(received_number);
                                    itemList.add(inventory_number);
                                    itemList.add(inventory_name);
                                    itemList.add(category);
                                    itemList.add(type);
                                    itemList.add(quantity);
                                    itemList.add(unit);
                                    itemList.add(expiredDate);
                                    
                                    expiredDataList.add(itemList);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            Object [][] expiredData = new Object[expiredDataList.size()][8];
            for (int i = 0; i<expiredDataList.size(); i++){
                List<Object> list = expiredDataList.get(i);
                expiredData[i] = list.toArray();
            }
            return expiredData;
        }
        return null;
    }
    
    public void updateExpired(){
        expiredTableModel.setRowCount(0);
        
        Object[][] updataNewRows = getExpiredData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                expiredTableModel.addRow(updataNewRows[i]);
            }
        }
    }

    private void setReceivePanelTabel(){        
        receivePanel.setLayout(new BorderLayout());
        
        String columns[] = 
        {"PuchaseOrderID", "InventoryID", "Inventory Name", "Category", "Type", "Quantity", "Unit", "Estimated Date"};

        Object [][] receiveData = getReceiveData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class, LocalDate.class};
        
        receiveTableModel = new DefaultTableModel(receiveData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        receiveTable = new JTableBg(receiveTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                   comp.setBackground(c);
                   c = null;
                } 
                
                if (getModel().getColumnName(column).equals("Estimated Date")){
                    comp.setBackground(new Color(222, 109, 109));
                }
                return comp;
            }
        };
        receiveTable.setCellSelectionEnabled(true);
        receiveTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = receiveTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(receiveTable);
        sp.setBackground(new Color(53, 115, 219));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        receivePanel.add(sp, BorderLayout.CENTER);
    }
    
    private Object[][] getReceiveData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate currentDate = LocalDate.parse(dateFormat.format(date), dtf);
        LocalDate currentPlus7Days = currentDate.plusDays(7);

        List<List<Object>> receiveDataList = new ArrayList<>();
        
        if (dbAllPurchaseOrderInfoNotCleared != null){
            for (PurchaseOrder po : dbAllPurchaseOrderInfoNotCleared){
                LocalDate expected_date = LocalDate.parse(dateFormat.format(po.getExpect_received_date()), dtf);
                if (expected_date.compareTo(currentPlus7Days) == 0 || expected_date.compareTo(currentPlus7Days) < 0){
                    for (AvailableInventory ai: aiList){
                        if (po.getInventory_id() == ai.getInventory_id()){
                            List<Object> itemList = new ArrayList<>();
                            String purchase_order_number = po.getPurchase_order_number();
                            String inventory_number = ai.getInventory_number();
                            String inventory_name = ai.getInventory_name();
                            String category = ai.getCategory();
                            String type = ai.getType();
                            double quantity = po.getQuantity();
                            String unit = ai.getUnit();
                            LocalDate expectedDate = expected_date;

                            itemList.add(purchase_order_number);
                            itemList.add(inventory_number);
                            itemList.add(inventory_name);
                            itemList.add(category);
                            itemList.add(type);
                            itemList.add(quantity);
                            itemList.add(unit);
                            itemList.add(expectedDate);

                            receiveDataList.add(itemList);
                            break;
                        }
                    }
                }
            }

            
            Object [][] expectedData = new Object[receiveDataList.size()][8];
            for (int i = 0; i<receiveDataList.size(); i++){
                List<Object> list = receiveDataList.get(i);
                expectedData[i] = list.toArray();
            }
            return expectedData;
        }
        return null;
    }
    
    public void updateReceive(){
        receiveTableModel.setRowCount(0);
        
        Object[][] updataNewRows = getReceiveData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                receiveTableModel.addRow(updataNewRows[i]);
            }
        }
    }

    private void setSalesPanelTabel(){
        salesPanel.setLayout(new BorderLayout());
        
        String columns[] = 
        {"SalesOrderID", "InventoryID", "Inventory Name", "Category", "Type", "Quantity", "Unit", "Delivery Date"};

        Object [][] salesData = getSalesData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class, LocalDate.class};
        
        salesTableModel = new DefaultTableModel(salesData, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        salesTable = new JTableBg(salesTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(200, 201, 210);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                   comp.setBackground(c);
                   c = null;
                } 
                
                if (getModel().getColumnName(column).equals("Delivery Date")){
                    comp.setBackground(new Color(222, 109, 109));
                }
                return comp;
            }
        };
        salesTable.setCellSelectionEnabled(true);
        salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = salesTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(salesTable);
        sp.setBackground(new Color(35, 104, 219));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        salesPanel.add(sp, BorderLayout.CENTER);
    }
    
    private Object[][] getSalesData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate currentDate = LocalDate.parse(dateFormat.format(date), dtf);
        LocalDate currentPlus7Days = currentDate.plusDays(7);

        List<List<Object>> salesDataList = new ArrayList<>();
        
        if (dbAllSalesOrderInfoNotCleared != null){
            for (SalesOrder so : dbAllSalesOrderInfoNotCleared){
                LocalDate delivery_date = LocalDate.parse(dateFormat.format(so.getDelivery_date()), dtf);
                if (delivery_date.compareTo(currentPlus7Days) == 0 || delivery_date.compareTo(currentPlus7Days) < 0){
                    for (AvailableInventory ai: aiList){
                        if (so.getInventory_id() == ai.getInventory_id()){
                            List<Object> itemList = new ArrayList<>();
                            String purchase_order_number = so.getSales_order_number();
                            String inventory_number = ai.getInventory_number();
                            String inventory_name = ai.getInventory_name();
                            String category = ai.getCategory();
                            String type = ai.getType();
                            double quantity = so.getQuantity();
                            String unit = ai.getUnit();
                            LocalDate expectedDate = delivery_date;

                            itemList.add(purchase_order_number);
                            itemList.add(inventory_number);
                            itemList.add(inventory_name);
                            itemList.add(category);
                            itemList.add(type);
                            itemList.add(quantity);
                            itemList.add(unit);
                            itemList.add(expectedDate);

                            salesDataList.add(itemList);
                            break;
                        }
                    }
                }
            }

            
            Object [][] expectedData = new Object[salesDataList.size()][8];
            for (int i = 0; i<salesDataList.size(); i++){
                List<Object> list = salesDataList.get(i);
                expectedData[i] = list.toArray();
            }
            return expectedData;
        }
        return null;
    }
    
    public void updateSales(){
        salesTableModel.setRowCount(0);
        
        Object[][] updataNewRows = getSalesData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                salesTableModel.addRow(updataNewRows[i]);
            }
        }
    }
}
