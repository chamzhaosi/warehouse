/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnector;

import PsnClass.Categories;
import PsnClass.Type;
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
import UtilsTools.DruidUtils_Root;
import UtilsTools.DruidUtils_User;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user
 */
public class JDBCTemplate {
    JdbcTemplate template = null;

    public JDBCTemplate(){
        File file = new File("..\\WarehouseSystem\\src\\main\\resources\\"+DruidUtils_User.PROPERTIES_USER);
        if (file.exists()){
            template = new JdbcTemplate(DruidUtils_User.getDataSource());
        }
    }
    
    public boolean CompareInfoWithDBSupplier (String tableName, String columnName, String itemFind){
        String sql = "select * from " + tableName + " where " + columnName + " = ?";

        List<Supplier> query = template.query(sql, new BeanPropertyRowMapper<>(Supplier.class), itemFind);
       
        return query.isEmpty()? true : false;
    }
    
    public boolean CompareInfoWithDBCustomer (String tableName, String columnName, String itemFind){
        String sql = "select * from " + tableName + " where " + columnName + " = ?";

        List<Customer> query = template.query(sql, new BeanPropertyRowMapper<>(Customer.class), itemFind);
       
        return query.isEmpty()? true : false;
    }
    
    public boolean CompareInfoWithDBInventory (String tableName, String columnName, String itemFind){
        String sql = "select * from " + tableName + " where " + columnName + " = ?";

        List<Inventory> query = template.query(sql, new BeanPropertyRowMapper<>(Inventory.class), itemFind);
       
        return query.isEmpty()? true : false;
    }
    
    public boolean CompareInfoWithDBEmployee (String tableName, String columnName, String itemFind){
        String sql = "select * from " + tableName + " where " + columnName + " = ?";

        List<Employee> query = template.query(sql, new BeanPropertyRowMapper<>(Employee.class), itemFind);
       
        return query.isEmpty()? true : false;
    }
    
    public int insertDataIntoDBSupplier (Supplier newSupplier){ 
        String supplier_number = newSupplier.getSupplier_number().toUpperCase();
        String company_name = newSupplier.getCompany_name().toUpperCase();
        String ssm = newSupplier.getSSM().toUpperCase();
        String email = newSupplier.getEmail().toUpperCase();
        String company_phone = newSupplier.getCompany_phone().toUpperCase();
        String mobile_phone = newSupplier.getMobile_phone().toUpperCase();
        String address = newSupplier.getAddress().toUpperCase();
        String person_in_charge = newSupplier.getPerson_in_charge().toUpperCase();
        
        String sql = "insert into supplier_table values (null, ?, ?, ?, ?, ?, ?, ?, ?, null)";
        
        int update_count = template.update(sql, supplier_number, company_name, ssm, email, company_phone, 
                mobile_phone, address, person_in_charge);
        
        return update_count;
    }
    
    public int insertDataIntoDBCustomer (Customer newCustomer){ 
        String supplier_number = newCustomer.getCustomer_number().toUpperCase();
        String company_name = newCustomer.getCompany_name().toUpperCase();
        String ssm = newCustomer.getSSM().toUpperCase();
        String email = newCustomer.getEmail().toUpperCase();
        String company_phone = newCustomer.getCompany_phone().toUpperCase();
        String mobile_phone = newCustomer.getMobile_phone().toUpperCase();
        String address = newCustomer.getAddress().toUpperCase();
        String person_in_charge = newCustomer.getPerson_in_charge().toUpperCase();
        
        String sql = "insert into customer_table values (null, ?, ?, ?, ?, ?, ?, ?, ?, null)";
        
        int update_count = template.update(sql, supplier_number, company_name, ssm, email, company_phone, 
                mobile_phone, address, person_in_charge);
        
        return update_count;
    }
    
    public int insertTypeDataIntoDB(String type){
        String sql = "insert into type_table values (null,?)";
        
        int update_count = template.update(sql, type.toUpperCase());
        
        return update_count;
    }
    
    public int insertCategoriesDataIntoDB(String type){
        String sql = "insert into categories_table values (null,?)";

        int update_count = template.update(sql, type.toUpperCase());

        return update_count;
    }
    
    public int insertDataIntoDBInventory (Inventory newInventory){ 
        String inventory_number = newInventory.getInventory_number().toUpperCase();
        String name = newInventory.getName().toUpperCase();
        int categories_id = newInventory.getCategories_id();
        int type_id = newInventory.getType_id();
        double quantity = newInventory.getQuantity();
        String unit = newInventory.getUnit().toUpperCase();
        double quantity_per_unit = newInventory.getQuantity_per_unit();
        String quantity_unit = newInventory.getQuantity_unit().toUpperCase();
        String remark = newInventory.getRemark().toUpperCase();
        double safety_level = newInventory.getSafety_level();
        
        String sql = "insert into inventory_table values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int update_count = template.update(sql, inventory_number, name, categories_id, type_id, quantity, unit, 
                quantity_per_unit, quantity_unit, remark, safety_level);
        
        return update_count;
    }
    
    public int insertDataIntoDBEmployee (Employee newEmployee){ 
        String employee_number = newEmployee.getStaff_number().toUpperCase();
        String name = newEmployee.getName().toUpperCase();
        String identity_number = newEmployee.getIdentity_number();
        String mobile_phone = newEmployee.getMobile_phone();
        String emergency_phone = newEmployee.getEmergency_phone().toUpperCase();
        String address = newEmployee.getAddress();
        String position = newEmployee.getPosition().toUpperCase();
        
        String sql = "insert into staff_table values (null, ?, ?, ?, ?, ?, ?, ?)";
        
        int update_count = template.update(sql, employee_number, name, identity_number, mobile_phone, 
                emergency_phone, address, position);
        
        return update_count;
    }

    public int insertDataIntoDBPurchaseOrder (List<PurchaseOrder> purchaseOrderList){
        int update_count = 0;
        
        for (PurchaseOrder po : purchaseOrderList){
            String purchase_order_number = po.getPurchase_order_number().toUpperCase();
            int inventory_id = po.getInventory_id();
            double quantity = po.getQuantity();
            String unit = po.getUnit().toUpperCase();
            Date order_date = po.getOrder_date();
            Date expect_received_date = po.getExpect_received_date();
            int staff_id = po.getStaff_id();
            int be_cleared = po.getBe_cleared();
            
            String sql = "insert into purchase_order_table values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            update_count = template.update(sql, purchase_order_number, inventory_id, quantity, unit, order_date, 
                    expect_received_date, staff_id, be_cleared);
        }
        return update_count;
    }
    
    public int insertDataIntoDBPurchaseInvoice (List<PurchaseInvoice> purchaseInvoiceList){
        int update_count = 0;

        for (PurchaseInvoice pi : purchaseInvoiceList){
            String purchase_invoice_number = pi.getPurchase_invoice_number().toUpperCase();
            Date invoice_date = pi.getPurchase_invoice_date();
            int received_id = pi.getReceived_id();
            double unit_price = pi.getUnit_price();
            double total_price = pi.getTotal_price();
            int staff_id = pi.getStaff_id();
            int be_cleared = pi.getBe_cleared();

            String sql = "insert into purchase_invoice_table values(null, ?, ?, ?, ?, ?, ?, ?)";

            update_count = template.update(sql, purchase_invoice_number, invoice_date, received_id, unit_price, total_price, 
                    staff_id, be_cleared);
        }
        return update_count;
    }
    
    public int insertDataIntoDBPurchaseReturn (List<PurchaseReturn> purchaseReturnList){
        int update_count = 0;

        for (PurchaseReturn pr : purchaseReturnList){
            String purchase_return_number = pr.getPurchase_return_number().toUpperCase();
            Date purchase_return_date = pr.getPurchase_return_date();
            int purchase_invoice_id = pr.getPurchase_invoice_id();
            int inventory_id = pr.getInventory_id();
            double quantity = pr.getQuantity();
            String unit = pr.getUnit();
            String remark = pr.getRemark().toUpperCase();

            String sql = "insert into purchase_return_table values(null, ?, ?, ?, ?, ?, ?, ?)";

            update_count = template.update(sql, purchase_return_number, purchase_return_date, purchase_invoice_id, 
                    inventory_id, quantity, unit, remark);
        }
        return update_count;
    }
    
    public int insertDataIntoDBSalesOrder (List<SalesOrder> salesOrderList){
        int update_count = 0;
        
        for (SalesOrder so : salesOrderList){
            String sales_order_number = so.getSales_order_number().toUpperCase();
            Date sales_order_date = so.getSales_order_date();
            int customer_id = so.getCustomer_id();
            int inventory_id = so.getInventory_id();
            double quantity = so.getQuantity();
            String unit = so.getUnit().toUpperCase();
            Date delivery_date = so.getDelivery_date();
            int staff_id = so.getStaff_id();
            int be_cleared = so.getBe_cleared();
            
            
            String sql = "insert into sales_order_table values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            update_count = template.update(sql, sales_order_number, sales_order_date, customer_id, inventory_id, 
                    quantity, unit, delivery_date, staff_id, be_cleared);
        }
        return update_count;
    }
    
    public int insertDataIntoDBSalesInvoice (List<SalesInvoice> salesInvoiceList){
        int update_count = 0;

        for (SalesInvoice si : salesInvoiceList){
            String sales_invoice_number = si.getSales_invoice_number().toUpperCase();
            int sales_order_id = si.getSales_order_id();
            Date sales_invoice_date = si.getSales_invoice_date();
            Double unit_price = si.getUnit_price();
            Double total_price = si.getTotal_price();
            int staff_id = si.getStaff_id();
            int be_cleared = si.getBe_cleared();

            String sql = "insert into sales_invoice_table values(null, ?, ?, ?, ?, ?, ?, ?)";

            update_count = template.update(sql, sales_invoice_number, sales_order_id, sales_invoice_date, 
                    unit_price, total_price, staff_id, be_cleared);
        }
        return update_count;
    }
    
    public int insertDataIntoDBSalesReturn (List<SalesReturn> salesReturnList){
        int update_count = 0;

        for (SalesReturn sr : salesReturnList){
            String sales_return_number = sr.getSales_return_number().toUpperCase();
            Date sales_return_date = sr.getSales_return_date();
            int sales_invoice_id = sr.getSales_invoice_id();
            int inventory_id = sr.getInventory_id();
            double quantity = sr.getQuantity();
            String unit = sr.getUnit().toUpperCase();
            String remark = sr.getRemark().toUpperCase();

            String sql = "insert into sales_return_table values(null, ?, ?, ?, ?, ?, ?, ?)";

            update_count = template.update(sql, sales_return_number, sales_return_date, sales_invoice_id, 
                    inventory_id, quantity, unit, remark);
        }
        return update_count;
    }
    
    public int insertDataIntoDBReceived (List<Received> receivedList){
        int update, update_count = 0;
        
        for (Received r : receivedList){
            String received_number = r.getReceived_number().toUpperCase();
            int supplier_id = r.getSupplier_id();
            int purchase_order_id = r.getPurchase_order_id();
            Date received_date = r.getReceived_date();
            Date expired_date = r.getExpired_date();
            int inventory_location_id = r.getInventory_location_id();
            int staff_id = r.getStaff_id();
            int be_cleared = r.getBe_cleared();
            
            String sql = "insert into received_table values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            update = template.update(sql, received_number, supplier_id, purchase_order_id, received_date,
                    expired_date, inventory_location_id, staff_id, be_cleared);
            
            update_count =+ update;
        }
        return update_count;
    }
    
    public int insertDataIntoDBInitialInventory (List<InitialInventory> initialInventoryList){
        int update, update_count = 0;
        
        for (InitialInventory il : initialInventoryList){
            int inventory_id = il.getInventory_id();
            double quantity = il.getQuantity();
            String unit = il.getUnit();
            double quantity_per_unit = il.getQuantity_per_unit();
            String quantity_unit = il.getQuantity_unit();
            String remark = il.getRemark().toUpperCase();
            int inventory_location_id = il.getInventory_location_id();
            
            String sql = "insert into initial_inventory_table values(null, ?, ?, ?, ?, ?, ?, ?)";
            
            update = template.update(sql, inventory_id, quantity, unit, quantity_per_unit,
                    quantity_unit, remark, inventory_location_id);
            
            update_count =+ update;
        }
        return update_count;
    }
    
    public int insertLocationDataIntoDB(String location){
        String sql = "insert into inventory_location_table values (null,?)";

        int update_count = template.update(sql, location.toUpperCase());

        return update_count;
    }
    
    public List<Supplier> getDBAllSupplierInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<Supplier> querySupplier = template.query(sql, new BeanPropertyRowMapper<>(Supplier.class));
        
        if (!querySupplier.isEmpty()){
            return querySupplier;
        }else{
            return null;
        }
        
    }
    
    public List<Customer> getDBAllCustomerInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<Customer> queryCustomer = template.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        
        if (!queryCustomer.isEmpty()){
            return queryCustomer;
        }else{
            return null;
        }
        
    }
    
    public List<Type> getDBALLTypeInfo(String tableName, String columnName){
        String sql = "select * from "+tableName+" order by "+columnName+" ASC";
        
        List<Type> query = template.query(sql, new BeanPropertyRowMapper<>(Type.class));
        
        return query;
    }
    
    public List<Categories> getDBALLCategoriesInfo(String tableName, String columnName){
        String sql = "select * from "+tableName+" order by "+columnName+" ASC";
        
        List<Categories> query = template.query(sql, new BeanPropertyRowMapper<>(Categories.class));
        
        return query;
    }
    
    public List<Inventory> getDBAllInventoryInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<Inventory> queryInventory = template.query(sql, new BeanPropertyRowMapper<>(Inventory.class));
        
        if (!queryInventory.isEmpty()){
            return queryInventory;
        }else{
            return null;
        }
        
    }
    
    public List<InitialInventory> getDBAllInitialInventoryInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<InitialInventory> queryInitialInventory = template.query(sql, new BeanPropertyRowMapper<>(InitialInventory.class));
        
        if (!queryInitialInventory.isEmpty()){
            return queryInitialInventory;
        }else{
            return null;
        }
        
    }
    
    public List<Employee> getDBAllEmployeeInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<Employee> queryEmployee = template.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        
        if (!queryEmployee.isEmpty()){
            return queryEmployee;
        }else{
            return null;
        }
        
    }
    
    public List<PurchaseOrder> getDBAllPurchaseOrdersInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<PurchaseOrder> queryPurchaseOrder = template.query(sql, new BeanPropertyRowMapper<>(PurchaseOrder.class));
        
        if (!queryPurchaseOrder.isEmpty()){
            return queryPurchaseOrder;
        }else{
            return null;
        }
        
    }
    
    public List<PurchaseOrder> getDBAllPurchaseOrdersInfo(String tableName, String columnName, int cleared){

        String sql ="select * from " + tableName + " where be_cleared = " + cleared +" order by " + columnName + " ASC";

        List<PurchaseOrder> queryPurchaseOrder = template.query(sql, new BeanPropertyRowMapper<>(PurchaseOrder.class));

        if (!queryPurchaseOrder.isEmpty()){
            return queryPurchaseOrder;
        }else{
            return null;
        }

}
    
    public List<PurchaseInvoice> getDBAllPurchaseInvoiceInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<PurchaseInvoice> queryPurchaseInvoice = template.query(sql, new BeanPropertyRowMapper<>(PurchaseInvoice.class));
        
        if (!queryPurchaseInvoice.isEmpty()){
            return queryPurchaseInvoice;
        }else{
            return null;
        }
        
    }
    
    public List<PurchaseInvoice> getDBAllPurchaseInvoiceInfo(String tableName, String columnName, int cleared){
        
        String sql ="select * from " + tableName + " where be_cleared = " + cleared +" order by " + columnName + " ASC";
        
        List<PurchaseInvoice> queryPurchaseInvoice = template.query(sql, new BeanPropertyRowMapper<>(PurchaseInvoice.class));
        
        if (!queryPurchaseInvoice.isEmpty()){
            return queryPurchaseInvoice;
        }else{
            return null;
        }
        
    }
    
    public List<PurchaseReturn> getDBAllPurchaseReturnInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<PurchaseReturn> queryPurchaseReturn = template.query(sql, new BeanPropertyRowMapper<>(PurchaseReturn.class));
        
        if (!queryPurchaseReturn.isEmpty()){
            return queryPurchaseReturn;
        }else{
            return null;
        }
        
    }
    
    public List<SalesOrder> getDBAllSalesOrdersInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<SalesOrder> querySalesOrder = template.query(sql, new BeanPropertyRowMapper<>(SalesOrder.class));
        
        if (!querySalesOrder.isEmpty()){
            return querySalesOrder;
        }else{
            return null;
        }
        
    }
    
    public List<SalesOrder> getDBAllSalesOrdersInfo(String tableName, String columnName, int cleared){

        String sql ="select * from " + tableName + " where be_cleared = " + cleared +" order by " + columnName + " ASC";

        List<SalesOrder> querySalesOrder = template.query(sql, new BeanPropertyRowMapper<>(SalesOrder.class));

        if (!querySalesOrder.isEmpty()){
            return querySalesOrder;
        }else{
            return null;
        }

}
    
    public List<SalesInvoice> getDBAllSalesInvoiceInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<SalesInvoice> querySalesInvoice = template.query(sql, new BeanPropertyRowMapper<>(SalesInvoice.class));
        
        if (!querySalesInvoice.isEmpty()){
            return querySalesInvoice;
        }else{
            return null;
        }
        
    }
    
    public List<SalesInvoice> getDBAllSalesInvoiceInfo(String tableName, String columnName, int cleared){
        
        String sql ="select * from " + tableName + " where be_cleared = " + cleared +" order by " + columnName + " ASC";
        
        List<SalesInvoice> querySalesInvoice = template.query(sql, new BeanPropertyRowMapper<>(SalesInvoice.class));
        
        if (!querySalesInvoice.isEmpty()){
            return querySalesInvoice;
        }else{
            return null;
        }
        
    }
    
    public List<SalesReturn> getDBAllSalesReturnInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<SalesReturn> querySalesReturn = template.query(sql, new BeanPropertyRowMapper<>(SalesReturn.class));
        
        if (!querySalesReturn.isEmpty()){
            return querySalesReturn;
        }else{
            return null;
        }
        
    }
    
    public List<Received> getDBAllReceivedInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<Received> queryReceived = template.query(sql, new BeanPropertyRowMapper<>(Received.class));
        
        if (!queryReceived.isEmpty()){
            return queryReceived;
        }else{
            return null;
        }
        
    }
    
    public List<Received> getDBAllReceivedInfo(String tableName, String columnName, int cleared){
        
        String sql ="select * from " + tableName + " where be_cleared = " + cleared +" order by " + columnName + " ASC";
        
        List<Received> queryReceived = template.query(sql, new BeanPropertyRowMapper<>(Received.class));
        
        if (!queryReceived.isEmpty()){
            return queryReceived;
        }else{
            return null;
        }
        
    }
    
    public List<InventoryLocation> getDBAllLocationInfo(String tableName, String columnName){
        
        String sql ="select * from " + tableName + " order by " + columnName + " ASC";
        
        List<InventoryLocation> queryLocation = template.query(sql, new BeanPropertyRowMapper<>(InventoryLocation.class));
        
        if (!queryLocation.isEmpty()){
            return queryLocation;
        }else{
            return null;
        }
        
    }
    
    public Supplier getDBSupplierInfo(String columnName, String selectedValue){
        String sql = "select * from supplier_table where " + columnName + " = ?";
        
        List<Supplier> query = template.query(sql, new BeanPropertyRowMapper<>(Supplier.class), selectedValue);
 
        return query.get(0);
    }
    
    public Customer getDBCustomerInfo(String columnName, String selectedValue){
        String sql = "select * from customer_table where " + columnName + " = ?";
        
        List<Customer> query = template.query(sql, new BeanPropertyRowMapper<>(Customer.class), selectedValue);
 
        return query.get(0);
    }
    
    public Employee getDBEmployeeInfo(String columnName, String selectedValue){
        String sql = "select * from staff_table where " + columnName + " = ?";
        
        List<Employee> query = template.query(sql, new BeanPropertyRowMapper<>(Employee.class), selectedValue);
 
        return query.get(0);
    }
    
    public int updateSupplierDataIntoDB(Supplier editSupplier){
        String supplier_number = editSupplier.getSupplier_number().toUpperCase();
        String company_name = editSupplier.getCompany_name().toUpperCase();
        String ssm = editSupplier.getSSM().toUpperCase();
        String email = editSupplier.getEmail().toUpperCase();
        String company_phone = editSupplier.getCompany_phone().toUpperCase();
        String mobile_phone = editSupplier.getMobile_phone().toUpperCase();
        String address = editSupplier.getAddress().toUpperCase();
        String person_in_charge = editSupplier.getPerson_in_charge().toUpperCase();
        
        String sql = "update supplier_table set company_name = ?, SSM = ?, "
                + "email = ?, company_phone = ?, mobile_phone = ?, address = ?, person_in_charge = ? "
                + "where supplier_number = ?" ;
        
        int updateCount = template.update(sql, company_name, ssm, email, company_phone, mobile_phone,
                address, person_in_charge, supplier_number);
        
        return updateCount;
    }
    
    public int updateCustomerDataIntoDB(Customer editCustomer){
        String customer_number = editCustomer.getCustomer_number().toUpperCase();
        String company_name = editCustomer.getCompany_name().toUpperCase();
        String ssm = editCustomer.getSSM().toUpperCase();
        String email = editCustomer.getEmail().toUpperCase();
        String company_phone = editCustomer.getCompany_phone().toUpperCase();
        String mobile_phone = editCustomer.getMobile_phone().toUpperCase();
        String address = editCustomer.getAddress().toUpperCase();
        String person_in_charge = editCustomer.getPerson_in_charge().toUpperCase();
        
        String sql = "update customer_table set company_name = ?, SSM = ?, "
                + "email = ?, company_phone = ?, mobile_phone = ?, address = ?, person_in_charge = ? "
                + "where customer_number = ?" ;
        
        int updateCount = template.update(sql, company_name, ssm, email, company_phone, mobile_phone,
                address, person_in_charge, customer_number);
        
        return updateCount;
    }
    
    public int updateInventoryDataIntoDB(Inventory editInventory){
        String inventory_number = editInventory.getInventory_number().toUpperCase();
        String name = editInventory.getName().toUpperCase();
        int type_id = editInventory.getType_id();
        double quantity = editInventory.getQuantity();
        String unit = editInventory.getUnit().toUpperCase();
        double quantity_per_unit = editInventory.getQuantity_per_unit();
        String quantity_unit = editInventory.getQuantity_unit().toUpperCase();
        String remark = editInventory.getRemark().toUpperCase();
        double safety_level = editInventory.getSafety_level();
        
        String sql = "update inventory_table set name =?, type_id=?, quantity =?,"
                + " unit = ?, quantity_per_unit = ?, quantity_unit = ?, remark=?, safety_level=? where inventory_number = ?" ;
        
        int updateCount = template.update(sql, name, type_id, quantity, unit, quantity_per_unit, 
                quantity_unit, remark, safety_level, inventory_number);
        
        return updateCount;
    }
    
    public int updateEmployeeDataIntoDB(Employee editEmployee){
        String employee_number = editEmployee.getStaff_number().toUpperCase();
        String employee_name = editEmployee.getName().toUpperCase();
        String identity_number = editEmployee.getIdentity_number().toUpperCase();
        String mobile_phone = editEmployee.getMobile_phone().toUpperCase();
        String emergency_phone = editEmployee.getEmergency_phone().toUpperCase();
        String address = editEmployee.getAddress().toUpperCase();
        String employee_position = editEmployee.getPosition().toUpperCase();
        
        String sql = "update staff_table set name = ?, identity_number = ?, "
                + "mobile_phone = ?, emergency_phone = ?, address = ?, position = ? "
                + "where staff_number = ?" ;
        
        int updateCount = template.update(sql, employee_name, identity_number, mobile_phone, emergency_phone,
                address, employee_position, employee_number);
        
        return updateCount;
    }
    
    public int updatePurchaseOrderDataIntoDB(List<String> POList , int value){
        int update, update_count = 0;
        
        for (String POID : POList){
            
            String sql = "update purchase_order_table set be_cleared = ? where purchase_order_number = ?";
            
            update = template.update(sql, value, POID);
            update_count += update;
        }
        
        return update_count;
    }
    
    public int updatePurchaseInvoiceDataIntoDB(List<Integer> PIList, int value){
        int update, update_count = 0;
        
        for (int PIID : PIList){
            
            String sql = "update purchase_invoice_table set be_cleared = ? where purchase_invoice_id = ?";
            
            update = template.update(sql, value, PIID);
            update_count += update;
        }
        
        return update_count;
    }
    
    public int updateReceivedDataIntoDB(List<String> RevList, int value){
        int update, update_count = 0;
        
        for (String RevID : RevList){
            
            String sql = "update received_table set be_cleared = ? where received_number = ?";
            
            update = template.update(sql, value, RevID);
            update_count += update;
        }
        
        return update_count;
    }
    
    public int updateSalesOrderDataIntoDB(List<String> SOList, int value){
        int update, update_count = 0;
        
        for (String SOID : SOList){
            
            String sql = "update sales_order_table set be_cleared = ? where sales_order_number = ?";
            
            update = template.update(sql, value, SOID);
            update_count += update;
        }
        
        return update_count;
    }
    
    public int updateSalesInvoiceDataIntoDB(List<Integer> SIList, int value){
        int update, update_count = 0;
        
        for (int SIID : SIList){
            
            String sql = "update sales_invoice_table set be_cleared = ? where sales_invoice_id = ?";
            
            update = template.update(sql, value, SIID);
            update_count += update;
        }
        
        return update_count;
    }
    
    public String convertToCipher(String password){
       JdbcTemplate template = new JdbcTemplate(DruidUtils_Root.getDataSource());
       
       String sql = "select password('" + password + "')";
       
       String pwd = template.queryForObject(sql, String.class);
       
       return pwd;
   }
    
    public List<Map<String, Object>> getDBNamePassword(String username){
        JdbcTemplate templateRoot = new JdbcTemplate(DruidUtils_Root.getDataSource());
       
        String sql = "select user, password from user where user = ?";

        List<Map<String, Object>> queryForList = templateRoot.queryForList(sql, username);

        return queryForList;
    } 
    
    public int deleteItemFromDB(String tableName, String columnName, String value){
        String sql = "delete from " +tableName + " where " + columnName + " = \"" + value +"\"";
        
        int update = template.update(sql);
        
        return update;
    }
}
