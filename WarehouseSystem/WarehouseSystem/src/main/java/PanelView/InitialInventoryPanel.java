/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import PsnClass.Type;
import PsnClass.Categories;
import DBConnector.JDBCTemplate;
import PsnClass.InitialInventory;
import PsnClass.Inventory;
import PsnClass.InventoryLocation;
import Table.JTableBg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class InitialInventoryPanel extends JPanel{
    private DefaultTableModel tableModel = null;
    private JTable inventoryTable = null;
    private JButton saveButton = null;

    private List<Type> dballTypeInfo = null;
    private List<Categories> dballCategoriesInfo = null;
    private List<Inventory> dbAllInventoryInfo = null;
    private List<InventoryLocation> dbAllLocationInfo = null;
    private List<InitialInventory> dbAllInitialInventoryInfo = null;
    private List<String> recordLocationName = null;

    public InitialInventoryPanel(){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(Color.WHITE);
        
        getDBAllInventoryInfo();
        getDBAllTypeInfo();
        getDBAllCategoriesInfo();
        getDBAllLocationInfo();
        getDBAllInitialInventoryInfo();
        setSaveButtonPanel();
        setInventoryDetailTable();
    } 
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void getDBAllTypeInfo(){
        dballTypeInfo = new JDBCTemplate().getDBALLTypeInfo("type_table", "name");
    }
    
    private void getDBAllCategoriesInfo(){
        dballCategoriesInfo = new JDBCTemplate().getDBALLCategoriesInfo("categories_table", "name");
    }
    
    private void getDBAllLocationInfo(){
        dbAllLocationInfo = new JDBCTemplate().getDBAllLocationInfo("inventory_location_table", "name");
    }
    
    private void getDBAllInitialInventoryInfo(){
        dbAllInitialInventoryInfo = new JDBCTemplate().getDBAllInitialInventoryInfo("initial_inventory_table", "initial_inventory_id");
    }
    
    private void setInventoryDetailTable(){
        JPanel inventoryDetailTablePanel = new JPanel();
        inventoryDetailTablePanel.setLayout(new BorderLayout());
        inventoryDetailTablePanel.setBackground(new Color(104, 199, 237));
        
        JLabel initialLabel = new JLabel("<< Initial Inventory >> ", SwingUtilities.CENTER);
        initialLabel.setFont(new Font("",Font.BOLD, 30));
        inventoryDetailTablePanel.add(initialLabel, BorderLayout.NORTH);
        
        String columns[] = 
        {"Iventory ID", "Inventory Name", "Category", "Type", "Bal Quantity*", "Unit", "Sub Quantity", "Sub unit", "Remak", "Location*"};

        Object [][] data = getInventoryData();
        
        if (data == null || Arrays.asList(data).isEmpty()){
            saveButton.setEnabled(false);
        }else {
            saveButton.setEnabled(true);
        }

        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, 
                String.class, String.class};
        
        tableModel = new DefaultTableModel(data, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4 || column == 6 || column == 8 || column == 9){
                    return true;
                }
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        
        inventoryTable = new JTableBg(tableModel);
        inventoryTable.setCellSelectionEnabled(true);
        inventoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = inventoryTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(inventoryTable);
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setBackground(new Color(135, 208, 237));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        inventoryDetailTablePanel.add(sp, BorderLayout.CENTER);
        this.add(inventoryDetailTablePanel, BorderLayout.CENTER);
    }
    
    private Object[][] getInventoryData(){
        int countRow=0; 
        
        if (dbAllInventoryInfo != null && dbAllInitialInventoryInfo != null){
            countRow = dbAllInventoryInfo.size() - dbAllInitialInventoryInfo.size();
        } else {
            countRow = 0;
        }

        Object [][] data = new Object[countRow][10];
        int index =0;
        
        if (dbAllInventoryInfo != null){
            for (Inventory inv : dbAllInventoryInfo){
                if (dbAllInitialInventoryInfo != null){
                    for (int k = 0; k<dbAllInitialInventoryInfo.size(); k++){
                        if (dbAllInitialInventoryInfo.get(k).getInventory_id() == inv.getInventory_id()){
                            break;
                        }
                        if (k == dbAllInitialInventoryInfo.size()-1){
                            data[index][0] = inv.getInventory_number();
                            data[index][1] = inv.getName();

                            int categories_id = inv.getCategories_id();
                            for (Categories c : dballCategoriesInfo){
                                if(categories_id == c.getCategories_id()){
                                    data[index][2] = c.getName();
                                    break;
                                }
                            }

                            int type_id = inv.getType_id();
                            for (Type t : dballTypeInfo){
                                if(type_id == t.getType_id()){
                                    data[index][3] = t.getName();
                                    break;
                                }
                            }

                            data[index][4] = "";
                            data[index][5] = inv.getUnit();
                            data[index][6] = "0.0";
                            data[index][7] = inv.getQuantity_unit();
                            data[index][8] = inv.getRemark();
                            data[index][9] = "";

                            index++;
                        }
                    }
                }else {
                    data[index][0] = inv.getInventory_number();
                    data[index][1] = inv.getName();

                    int categories_id = inv.getCategories_id();
                    for (Categories c : dballCategoriesInfo){
                        if(categories_id == c.getCategories_id()){
                            data[index][2] = c.getName();
                            break;
                        }
                    }

                    int type_id = inv.getType_id();
                    for (Type t : dballTypeInfo){
                        if(type_id == t.getType_id()){
                            data[index][3] = t.getName();
                            break;
                        }
                    }

                    data[index][4] = "";
                    data[index][5] = inv.getUnit();
                    data[index][6] = "0.0";
                    data[index][7] = inv.getQuantity_unit();
                    data[index][8] = inv.getRemark();
                    data[index][9] = "";

                    index++;
                }
            }
            return data;
        }
        return null;
    }

    private void setSaveButtonPanel(){
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setLayout(new FlowLayout());
        
        this.add(saveButtonPanel, BorderLayout.SOUTH);
        
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        saveButtonPanel.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                List<InitialInventory> dataFromTable = getDataFromTable();
                if (dataFromTable != null){
                    insertInitialInventoryDataToDB(dataFromTable);
                }
            }
        });
    }
    
    private List<InitialInventory> getDataFromTable(){   
        if (checkNotNullData()){
            JOptionPane.showMessageDialog(this, "* Empty value is not acceptable!", "Warning", 0);
        }else if (checkInvalidData()){
            JOptionPane.showMessageDialog(this, "Bal or Sub Quantity data type is invalid!", "Warning", 0);
        }else {
            List<String> ilList = new ArrayList<>();
            if (ilList != null || !ilList.isEmpty()){
                ilList.clear();
            }
            for (int i = 0; i<inventoryTable.getRowCount(); i++){
                String locationName = inventoryTable.getValueAt(i, 9).toString().toUpperCase();
                
                int record = 0;
                for (InventoryLocation l : dbAllLocationInfo){
                    if (!l.getName().equals(locationName)){
                        record++;
                    }else {
                        break;
                    }
                    if (record == dbAllLocationInfo.size()){
                        ilList.add(locationName);
                    }
                }
            }
            
            if (!ilList.isEmpty() && ilList != null){
                for (String location : ilList){
                    new JDBCTemplate().insertLocationDataIntoDB(location);
                }
            }

            getDBAllLocationInfo();

            
            List<InitialInventory> initialInventoryList = new ArrayList<>();
            for (int i = 0; i<inventoryTable.getRowCount(); i++){
                InitialInventory initialInventory = new InitialInventory();
                
                String invetoryID = inventoryTable.getValueAt(i, 0).toString();
                String balQuantity = inventoryTable.getValueAt(i, 4).toString();
                String unit = inventoryTable.getValueAt(i, 5).toString();
                String subQuantity = inventoryTable.getValueAt(i, 6).toString();
                String subUnit = inventoryTable.getValueAt(i, 7).toString();
                String remark = inventoryTable.getValueAt(i, 8).toString();
                String locationName = inventoryTable.getValueAt(i, 9).toString().toUpperCase();
                ilList.add(locationName);
                
                for (Inventory inv : dbAllInventoryInfo){
                    if (inv.getInventory_number().equals(invetoryID)){
                        initialInventory.setInventory_id(inv.getInventory_id());
                        break;
                    }
                }
                
                initialInventory.setQuantity(Double.parseDouble(balQuantity));
                initialInventory.setUnit(unit);
                initialInventory.setQuantity_per_unit(Double.parseDouble(subQuantity));
                initialInventory.setQuantity_unit(subUnit);
                initialInventory.setRemark(remark);
                
                for (InventoryLocation il : dbAllLocationInfo){
                    if (il.getName().equals(locationName)){
                        initialInventory.setInventory_location_id(il.getInventory_location_id());
                    }
                }
                
                initialInventoryList.add(initialInventory);
            }
            return initialInventoryList;
        }  
        return null;
    }
    
    private boolean checkNotNullData(){
        for (int i = 0; i<inventoryTable.getRowCount(); i++){
            String balQuantity = inventoryTable.getValueAt(i,4).toString();
            String location = inventoryTable.getValueAt(i,9).toString();
            
            if (balQuantity.isEmpty() || location.isEmpty()){
                return true;
            }
        }
        return false;
    }
    
    private boolean checkInvalidData(){
        for (int i = 0; i<inventoryTable.getRowCount(); i++){
            String balQuantity = inventoryTable.getValueAt(i,4).toString();
            String subQuantity = inventoryTable.getValueAt(i,6).toString();
            
            try{
                Double.parseDouble(balQuantity);
                Double.parseDouble(subQuantity);
            }catch (Exception ex){
                return true;
            }
        }
        return false;
    }
    
    private void insertInitialInventoryDataToDB(List<InitialInventory> dataFromTable){
        int result = JOptionPane.showConfirmDialog(this, "Do you want save them?", "Saving Notice", 2);
        if (result == 0){
            int result_count = new JDBCTemplate().insertDataIntoDBInitialInventory(dataFromTable);
            if (result_count > 0){
                JOptionPane.showMessageDialog(this, "Save Sucessfully", "Notice", 1);
                updateInitialInventory();
            }
        }
    }
    
    public void updateInitialInventory(){
        getDBAllInventoryInfo();
        getDBAllInitialInventoryInfo();
        saveButton.setEnabled(false);
        
        tableModel.setRowCount(0);
        
        Object[][] updataNewRows = getInventoryData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                tableModel.addRow(updataNewRows[i]);
            }
        }
        
        if (updataNewRows == null || Arrays.asList(updataNewRows).isEmpty()){
            saveButton.setEnabled(false);
        }else {
            saveButton.setEnabled(true);
        }
    }
}
