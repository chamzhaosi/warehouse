/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

import java.util.Date;

/**
 *
 * @author user
 */
public class SalesOrder {
    int sales_order_id;
    String sales_order_number;
    Date sales_order_date;
    int customer_id;
    int inventory_id;
    double quantity;
    String unit;
    int staff_id;
    Date delivery_date;
    int be_cleared;

    public SalesOrder() {
    }

    public int getSales_order_id() {
        return sales_order_id;
    }

    public void setSales_order_id(int sales_order_id) {
        this.sales_order_id = sales_order_id;
    }

    public String getSales_order_number() {
        return sales_order_number;
    }

    public void setSales_order_number(String sales_order_number) {
        this.sales_order_number = sales_order_number;
    }

    public Date getSales_order_date() {
        return sales_order_date;
    }

    public void setSales_order_date(Date sales_order_date) {
        this.sales_order_date = sales_order_date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public int getBe_cleared() {
        return be_cleared;
    }

    public void setBe_cleared(int be_cleared) {
        this.be_cleared = be_cleared;
    }
    @Override
    public String toString() {
        return "SalesOrder{" + "sales_order_id=" + sales_order_id + ", sales_order_number=" + sales_order_number + ", sales_order_date=" + sales_order_date + ", customer_id=" + customer_id + ", inventory_id=" + inventory_id + ", quantity=" + quantity + ", unit=" + unit + ", staff_id=" + staff_id + ", delivery_date=" + delivery_date + ", be_cleared=" + be_cleared + '}';
    }
}
