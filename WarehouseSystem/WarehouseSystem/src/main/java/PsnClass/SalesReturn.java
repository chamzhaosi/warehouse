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
public class SalesReturn {
    int sales_return_id;
    String sales_return_number;
    Date sales_return_date;
    int sales_invoice_id;
    int inventory_id;
    double quantity;
    String unit;
    String remark;

    public SalesReturn() {
    }

    public int getSales_return_id() {
        return sales_return_id;
    }

    public void setSales_return_id(int sales_return_id) {
        this.sales_return_id = sales_return_id;
    }

    public String getSales_return_number() {
        return sales_return_number;
    }

    public void setSales_return_number(String sales_return_number) {
        this.sales_return_number = sales_return_number;
    }

    public Date getSales_return_date() {
        return sales_return_date;
    }

    public void setSales_return_date(Date sales_return_date) {
        this.sales_return_date = sales_return_date;
    }

    public int getSales_invoice_id() {
        return sales_invoice_id;
    }

    public void setSales_invoice_id(int sales_invoice_id) {
        this.sales_invoice_id = sales_invoice_id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SalesReturn{" + "sales_return_id=" + sales_return_id + ", sales_return_number=" + sales_return_number + ", sales_return_date=" + sales_return_date + ", sales_invoice_id=" + sales_invoice_id + ", inventory_id=" + inventory_id + ", quantity=" + quantity + ", unit=" + unit + ", remark=" + remark + '}';
    }
    
    
}
