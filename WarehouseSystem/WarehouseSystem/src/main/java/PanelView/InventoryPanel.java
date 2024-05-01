/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelView;

import PsnClass.Type;
import PsnClass.Categories;
import DBConnector.JDBCTemplate;
import LengthRestricted.LengthRestrictedDocument;
import PsnClass.Inventory;
import Table.JTableBg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
public class InventoryPanel extends JPanel{
    private JPanel showEditPanel = null;
    private JPanel inventoryPanel = null;
    private JPanel addInventoryPanel = null;
    private DefaultTableModel tableModel = null;
    private JTable invetoryTable = null;
    private JTextField inventory_number_text = null;
    private JTextField inventory_name_text = null;
    private JComboBox inventory_categories_selected = null;
    private JComboBox inventory_type_selected = null;
    private JTextField inventory_quantity_text = null;
    private JTextField inventory_safety_level_text = null;
    private JTextField inventory_unit_text = null;
    private JTextField inventory_sub_quantity_text = null;
    private JTextField inventory_sub_unit_text = null;
    private JTextArea inventory_remark_textArea = null;
    
    private List<Type> dbAllTypeInfo = null;
    private List<Categories> dbAllCategoriesInfo = null;
    private List<Inventory> dbAllInventoryInfo = null;

    public InventoryPanel(){
        
        this.setBorder( new EmptyBorder (10,10,10,10));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(2,1,10,10));
        getDBAllTypeInfo();
        getDBAllCategoriesInfo();
        getDBAllInventoryInfo();
        setShowTable();
        setAddPanel();
    }
    
    private void getDBAllTypeInfo(){
        dbAllTypeInfo = new JDBCTemplate().getDBALLTypeInfo("type_table", "name");
    }
    
    private void getDBAllCategoriesInfo(){
        dbAllCategoriesInfo = new JDBCTemplate().getDBALLCategoriesInfo("categories_table", "name");
    }
    
    private void getDBAllInventoryInfo(){
        dbAllInventoryInfo = new JDBCTemplate().getDBAllInventoryInfo("inventory_table", "inventory_number");
    }
    
    private void setShowTable(){
        showEditPanel = new JPanel();
        showEditPanel.setBorder(new LineBorder(Color.GRAY));
        showEditPanel.setLayout(new BorderLayout());
        showEditPanel.setBackground(new Color(125, 149, 232));
        
        JLabel inventoryLabel = new JLabel("<< Inventory >>" , SwingUtilities.CENTER);
        inventoryLabel.setFont(new Font("", Font.BOLD, 30));
        showEditPanel.add(inventoryLabel, BorderLayout.NORTH);

        setInventoryTable();
        setEditButton();
    }
    
    private void setInventoryTable(){
        String columns[] = 
        {"ID", "Name", "Category", "Type", "Quantity", "Safety Level", "Unit", "Sub Quantity", "Sub unit", "Remak"};

        Object [][] data = getInventoryData();
        
        final Class[] columnClass = new Class[]{
            String.class, String.class, String.class, String.class, double.class, double.class, String.class, double.class, 
                String.class, String.class};
        
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
        
        invetoryTable = new JTableBg(tableModel);
        invetoryTable.setCellSelectionEnabled(true);
        invetoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ListSelectionModel selectionModel = invetoryTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(invetoryTable);
        sp.setBackground(new Color(142, 162, 232));
        sp.setBorder(new EmptyBorder(50,0,0,0));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        showEditPanel.add(sp, BorderLayout.CENTER);
        this.add(showEditPanel);   
    }

    public void updateInventory(){
        getDBAllInventoryInfo();
        
        tableModel.setRowCount(0);
        
        Object[][] updataNewRows = getInventoryData();
        
        if (updataNewRows != null){
            for (int i=0; i<updataNewRows.length; i++){
                tableModel.addRow(updataNewRows[i]);
            }
        }
    }
    
    private Object[][] getInventoryData(){
        if (dbAllInventoryInfo != null){
            Object [][] data = new Object[dbAllInventoryInfo.size()][10];
            for (int i = 0; i< dbAllInventoryInfo.size(); i++){
                data[i][0] = dbAllInventoryInfo.get(i).getInventory_number();
                data[i][1] = dbAllInventoryInfo.get(i).getName();
                
                int categories_id = dbAllInventoryInfo.get(i).getCategories_id();
                for (Categories c : dbAllCategoriesInfo){
                    if(categories_id == c.getCategories_id()){
                        data[i][2] = c.getName();
                        break;
                    }
                }

                int type_id = dbAllInventoryInfo.get(i).getType_id();
                for (Type t : dbAllTypeInfo){
                    if(type_id == t.getType_id()){
                        data[i][3] = t.getName();
                        break;
                    }
                }

                data[i][4] = dbAllInventoryInfo.get(i).getQuantity();
                data[i][5] = dbAllInventoryInfo.get(i).getSafety_level();
                data[i][6] = dbAllInventoryInfo.get(i).getUnit();
                data[i][7] = dbAllInventoryInfo.get(i).getQuantity_per_unit();
                data[i][8] = dbAllInventoryInfo.get(i).getQuantity_unit();
                data[i][9] = dbAllInventoryInfo.get(i).getRemark();
            }

            return data;
        }
        
        return null;
    }
    
    private void setEditButton(){
        JPanel editButtonPanel = new JPanel();
        JButton editButtion = new JButton("Edit");
        editButtion.setPreferredSize(new Dimension(100, 50));
        
        editButtonPanel.add(editButtion);
        showEditPanel.add(editButtonPanel, BorderLayout.SOUTH);
        
        editButtion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = invetoryTable.getSelectedRow();
                if (index != -1){
                    setEditTextField(index);
                }
            }
        });
    }
    
    private void setEditTextField(int index){
        inventory_number_text.setText(dbAllInventoryInfo.get(index).getInventory_number());
        inventory_number_text.setEnabled(false);
        
        inventory_name_text.setText(dbAllInventoryInfo.get(index).getName());
        
        String typeName = null;
        for (Type t : dbAllTypeInfo){
            if (t.getType_id() == dbAllInventoryInfo.get(index).getType_id()){
                typeName = t.getName();
                break;
            }
        }
        
        
        String categoriesName = null;
        for (Categories c : dbAllCategoriesInfo){
            if (c.getCategories_id() == dbAllInventoryInfo.get(index).getCategories_id()){
                categoriesName = c.getName();
                break;
            }
        }
        
        inventory_categories_selected.setSelectedItem(categoriesName);
        inventory_type_selected.setSelectedItem(typeName);
        inventory_quantity_text.setText(dbAllInventoryInfo.get(index).getQuantity()+"");
        inventory_safety_level_text.setText(dbAllInventoryInfo.get(index).getSafety_level()+"");
        inventory_unit_text.setText(dbAllInventoryInfo.get(index).getUnit());
        inventory_sub_quantity_text.setText(dbAllInventoryInfo.get(index).getQuantity_per_unit()+"");
        inventory_sub_unit_text.setText(dbAllInventoryInfo.get(index).getQuantity_unit());
        inventory_remark_textArea.setText(dbAllInventoryInfo.get(index).getRemark());
    }
    
    private void setAddPanel(){
        inventoryPanel = new JPanel();
        inventoryPanel.setBorder(new LineBorder(Color.GRAY));
        inventoryPanel.setLayout(new BorderLayout());
        
        addInventoryPanel = new JPanel();
        addInventoryPanel.setBackground(new Color(142, 162, 232));
        addInventoryPanel.setLayout(new BorderLayout());
        
        setAddInventoryLabel();
        setAddInventoryDetailPanel();

        inventoryPanel.add(addInventoryPanel, BorderLayout.CENTER);
        this.add(inventoryPanel);
        
        setSaveButton();
    }
    
    private void setAddInventoryLabel(){
        JLabel addInventoryLabel = new JLabel("Edit and Add new inventory", JLabel.CENTER);
        addInventoryLabel.setFont(new Font("", Font.BOLD, 30));
        
        addInventoryPanel.add(addInventoryLabel, BorderLayout.NORTH);
    }
    
    private void setAddInventoryDetailPanel(){
        JPanel addInventoryDetailPanel = new JPanel();
        addInventoryDetailPanel.setBorder(new EmptyBorder(10,10,10,10));
        addInventoryDetailPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JScrollPane sp = new JScrollPane(addInventoryDetailPanel);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                
        addInventoryPanel.add(sp, BorderLayout.CENTER);
        
        JLabel inventory_number = new JLabel("ID *: ");
        inventory_number_text = new JTextField(20);
        inventory_number_text.setDocument(new LengthRestrictedDocument(20));
        
        JLabel inventory_name = new JLabel ("Name *: ");
        inventory_name_text = new JTextField(50);
        inventory_name_text.setDocument(new LengthRestrictedDocument(50));
        
        JLabel inventory_categories = new JLabel ("Category *:");
        inventory_categories_selected = new JComboBox();
        
        JButton addCategories = new JButton("Add");
        
        JLabel inventory_type = new JLabel ("Type *: ");
        inventory_type_selected = new JComboBox();
        
        JButton addType = new JButton("Add");
        
        JLabel inventory_quantity = new JLabel("Quantity*: ");
        inventory_quantity_text = new JTextField(8);
        inventory_quantity_text.setDocument(new LengthRestrictedDocument(8));
        
        JLabel inventory_safetyLevel = new JLabel("Safety Level: ");
        inventory_safety_level_text = new JTextField(8);
        inventory_safety_level_text.setDocument(new LengthRestrictedDocument(8));
        
        JCheckBox safetyLevel_checkBox = new JCheckBox("Set",true);
        
        JLabel inventory_unit = new JLabel("Unit *: ");
        inventory_unit_text = new JTextField(10);
        inventory_unit_text.setDocument(new LengthRestrictedDocument(10));
        
        JLabel inventory_quantity_per_unit = new JLabel("Sub Quantity: ");
        inventory_sub_quantity_text = new JTextField(8);
        inventory_sub_quantity_text.setDocument(new LengthRestrictedDocument(8));
   
        JLabel inventory_quantity_unit = new JLabel("Sub Unit: ");
        inventory_sub_unit_text = new JTextField(10);
        inventory_sub_unit_text.setDocument(new LengthRestrictedDocument(10));
        
        JLabel inventory_remark = new JLabel("Remark: ");
        inventory_remark_textArea = new JTextArea(5,10);
        inventory_remark_textArea.setBorder(new LineBorder(Color.GRAY));
        inventory_remark_textArea.setDocument(new LengthRestrictedDocument(200));
        
        gbc.fill =  GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addInventoryDetailPanel.add(inventory_number,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addInventoryDetailPanel.add(inventory_number_text,gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addInventoryDetailPanel.add(inventory_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addInventoryDetailPanel.add(inventory_name_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        addInventoryDetailPanel.add(inventory_categories, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addInventoryDetailPanel.add(inventory_categories_selected, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        addInventoryDetailPanel.add(addCategories, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        addInventoryDetailPanel.add(inventory_type, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addInventoryDetailPanel.add(inventory_type_selected, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        addInventoryDetailPanel.add(addType, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        addInventoryDetailPanel.add(inventory_quantity, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        addInventoryDetailPanel.add(inventory_quantity_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        addInventoryDetailPanel.add(inventory_safetyLevel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        addInventoryDetailPanel.add(inventory_safety_level_text, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        addInventoryDetailPanel.add(safetyLevel_checkBox, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        addInventoryDetailPanel.add(inventory_unit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        addInventoryDetailPanel.add(inventory_unit_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 7;
        addInventoryDetailPanel.add(inventory_quantity_per_unit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        addInventoryDetailPanel.add(inventory_sub_quantity_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 8;
        addInventoryDetailPanel.add(inventory_quantity_unit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        addInventoryDetailPanel.add(inventory_sub_unit_text, gbc);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 9;
        addInventoryDetailPanel.add(inventory_remark, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        addInventoryDetailPanel.add(inventory_remark_textArea, gbc);
        
        setTypeComBoxItem();
        setCategoriesComBoxItem();
        
        addType.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setAddTypeDialog();
                setTypeComBoxItem();
            }
        });
        
        addCategories.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setAddCategoriesDialog();
                setCategoriesComBoxItem();
            }
        
        });
        
        safetyLevel_checkBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(safetyLevel_checkBox.isSelected()){
                    inventory_safety_level_text.setEnabled(true);
                }else {
                    inventory_safety_level_text.setEnabled(false);
                    inventory_safety_level_text.setText("");
                }
            }
        });
    }
    
    private void setAddTypeDialog(){
        String showInputDialog = JOptionPane.showInputDialog(this, "Please enter a new type", "Enter a new type", 1);
        if (showInputDialog != null){
            showInputDialog = showInputDialog.toUpperCase();
        }
       
        boolean check = false;
        if (dbAllTypeInfo != null){
            for (Type t : dbAllTypeInfo){
                if (showInputDialog != null){
                    if (showInputDialog.equals(t.getName())){
                        JOptionPane.showMessageDialog(this, "Item already exist", "Notice", 1);
                        check= true;
                        break;
                    }
                }else{
                    check= true;
                }
        }
        }
        if (!check && showInputDialog != null){
            int update_count = new JDBCTemplate().insertTypeDataIntoDB(showInputDialog);
        
            if (update_count > 0){
                JOptionPane.showMessageDialog(this, "Update sucessfully", "Notice", 1);
            }   
        }
    }
    
    private void setAddCategoriesDialog(){
        String showInputDialog = JOptionPane.showInputDialog(this, "Please enter a new category", "Enter a new category", 1);
        if (showInputDialog != null){
            showInputDialog = showInputDialog.toUpperCase();
        }
       
        boolean check = false;
        if (dbAllCategoriesInfo != null){
            for (Categories c : dbAllCategoriesInfo){
                if (showInputDialog != null){
                    if (showInputDialog.equals(c.getName())){
                        JOptionPane.showMessageDialog(this, "Item already exist", "Notice", 1);
                        check= true;
                        break;
                    }
                }else{
                    check= true;
                }
            }
        }
        if (!check && showInputDialog != null){
            int update_count = new JDBCTemplate().insertCategoriesDataIntoDB(showInputDialog);
        
            if (update_count > 0){
                JOptionPane.showMessageDialog(this, "Update sucessfully", "Notice", 1);
            }   
        }
    }    
    
    private void setTypeComBoxItem(){
        getDBAllTypeInfo();
        inventory_type_selected.removeAllItems();
        for (Type t: dbAllTypeInfo){
            inventory_type_selected.addItem(t.getName());
        }
    }
    
    private void setCategoriesComBoxItem(){
        getDBAllCategoriesInfo();
        inventory_categories_selected.removeAllItems();
        for (Categories c: dbAllCategoriesInfo){
            inventory_categories_selected.addItem(c.getName());
        }
    }
    
    private void setSaveButton(){
        JPanel saveButtonPanel = new JPanel();
        JButton saveButtion = new JButton("Save");
        saveButtion.setPreferredSize(new Dimension(100, 50));
        
        saveButtonPanel.add(saveButtion);
        inventoryPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        
        saveButtion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inventory_number_text.isEnabled()){
                    setSaveButtonListener();
                }else {
                    setEditSaveButtonListener();
                }
            }
        });  
    }
    
    private void setSaveButtonListener(){
        if(checkNotNullData() == false){
            boolean checkInfoWithDB = new JDBCTemplate().CompareInfoWithDBInventory(
                    "inventory_table", "inventory_number", inventory_number_text.getText());
            if (checkInfoWithDB==true){
                int result = JOptionPane.showConfirmDialog(addInventoryPanel, "Do you want save it?", "Saving Notice", 2);
                  if (result == 0){
                    Inventory newInventory = getComponentData();
                    int update_count = new JDBCTemplate().insertDataIntoDBInventory(newInventory);
                    if (update_count > 0){
                        JOptionPane.showMessageDialog(addInventoryPanel, "Add Sucessfully", "Notice", 1);
                        refreshTextField();
                        updateInventory();
                    }
                  }
            }else {
                JOptionPane.showMessageDialog(addInventoryPanel, "Duplicate ID is not acceptable.", "Warning", 0);
            }

        }else {
            JOptionPane.showMessageDialog(addInventoryPanel, "* Empty value is not acceptable or invalid input", "Warning", 0);
        }
        
    }
    
    private void setEditSaveButtonListener(){
        if (checkNotNullData() == false){
            int result = JOptionPane.showConfirmDialog(addInventoryPanel, "Do you want save it?", "Editing Notice", 2);
            if (result == 0){
                Inventory componentData = getComponentData();
                int update_count = new JDBCTemplate().updateInventoryDataIntoDB(componentData);
                if (update_count > 0){
                    JOptionPane.showMessageDialog(addInventoryPanel, "Edit Sucessfully", "Notice", 1);
                        refreshTextField();
                        updateInventory();
                }
            }
        }else {
            JOptionPane.showMessageDialog(addInventoryPanel, "* Empty value is not acceptable or invalid input", "Warning", 0);
        }
    }
    
    private boolean checkNotNullData(){
        if (inventory_number_text.getText().equals("")){
            return true;
        }
        
        if (inventory_name_text.getText().equals((""))){
            return true;
        }
        
        if (inventory_type_selected.getSelectedIndex() == -1){
            return true;
        }
        
        if (inventory_categories_selected.getSelectedIndex() == -1){
            return true;
        }
        
        try{
            Double.parseDouble(inventory_quantity_text.getText());
            
            if (inventory_safety_level_text.getText().equals("")){
                inventory_safety_level_text.setText("0.0");
            }
            Double.parseDouble(inventory_safety_level_text.getText());
            
            if (inventory_sub_quantity_text.getText().equals("")){
                inventory_sub_quantity_text.setText("0.0");
            }
            Double.parseDouble(inventory_sub_quantity_text.getText());
        }catch (Exception ex){
            refreshTextField();
            return true;
        }
        
  
        if (inventory_quantity_text.getText().equals("")){
            return true;
        }
        
        if (inventory_unit_text.getText().equals("")){
            return true;
        }
        return false;
    }
    
    private Inventory getComponentData(){
        Inventory newInventory = new Inventory();
        
        String typeName = (String)inventory_type_selected.getSelectedItem();
        int type_id = 0;
        for (Type t : dbAllTypeInfo){
            if (typeName.equals(t.getName())){
                type_id = t.getType_id();
                break;
            }
        }
        
        String categoriesName = (String)inventory_categories_selected.getSelectedItem();
        int categories_id = 0;
        for (Categories c : dbAllCategoriesInfo){
            if (categoriesName.equals(c.getName())){
                categories_id = c.getCategories_id();
                break;
            }
        }
        

        newInventory.setInventory_number(inventory_number_text.getText());
        newInventory.setName(inventory_name_text.getText());
        newInventory.setType_id(type_id);
        newInventory.setCategories_id(categories_id);
        newInventory.setQuantity(Double.parseDouble(inventory_quantity_text.getText()));       
        newInventory.setUnit(inventory_unit_text.getText());
        
        if (inventory_safety_level_text.getText().equals("")){
            newInventory.setSafety_level(0.0);
        }else{
            newInventory.setSafety_level(Double.parseDouble(inventory_safety_level_text.getText()));
        }
        
        if (inventory_sub_quantity_text.getText().equals("")){
            newInventory.setQuantity_per_unit(0.0);
        }else {
            newInventory.setQuantity_per_unit(Double.parseDouble(inventory_sub_quantity_text.getText()));
        }
        
        if (inventory_sub_unit_text.getText().equals("")){
            newInventory.setQuantity_unit("null");
        }else{
            newInventory.setQuantity_unit(inventory_sub_unit_text.getText());
        }
        
        if (inventory_remark_textArea.getText().equals("")){
            newInventory.setRemark("null");
        }else {
            newInventory.setRemark(inventory_remark_textArea.getText());
        }
        
        return newInventory;
    }
    
    private void refreshTextField(){
        inventory_number_text.setText("");
        inventory_number_text.setEnabled(true);
        inventory_number_text.setDocument(new LengthRestrictedDocument(20));
        
        inventory_name_text.setText("");
        inventory_name_text.setDocument(new LengthRestrictedDocument(50));
        
        inventory_quantity_text.setText("");
        inventory_quantity_text.setDocument(new LengthRestrictedDocument(8));
        
        inventory_safety_level_text.setText("");
        inventory_quantity_text.setDocument(new LengthRestrictedDocument(8));
        
        inventory_unit_text.setText("");
        inventory_unit_text.setDocument(new LengthRestrictedDocument(10));
        
        inventory_sub_quantity_text.setText("0.0");
        inventory_sub_quantity_text.setDocument(new LengthRestrictedDocument(8));
        
        inventory_sub_unit_text.setText("null");
        inventory_sub_unit_text.setDocument(new LengthRestrictedDocument(10));
        
        inventory_remark_textArea.setText("null");
        inventory_remark_textArea.setDocument(new LengthRestrictedDocument(200));
    }
    
}
