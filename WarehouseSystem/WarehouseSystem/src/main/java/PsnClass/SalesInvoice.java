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
public class SalesInvoice {
    int sales_invoice_id;
    String sales_invoice_number;
    int sales_order_id;
    Date sales_invoice_date;
    Double unit_price;
    Double total_price;
    int staff_id;
    int be_cleared;

    public SalesInvoice() {
    }

    public int getSales_invoice_id() {
        return sales_invoice_id;
    }

    public void setSales_invoice_id(int sales_invoice_id) {
        this.sales_invoice_id = sales_invoice_id;
    }

    public String getSales_invoice_number() {
        return sales_invoice_number;
    }

    public void setSales_invoice_number(String sales_invoice_number) {
        this.sales_invoice_number = sales_invoice_number;
    }

    public int getSales_order_id() {
        return sales_order_id;
    }

    public void setSales_order_id(int sales_order_id) {
        this.sales_order_id = sales_order_id;
    }

    public Date getSales_invoice_date() {
        return sales_invoice_date;
    }

    public void setSales_invoice_date(Date sales_invoice_date) {
        this.sales_invoice_date = sales_invoice_date;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getBe_cleared() {
        return be_cleared;
    }

    public void setBe_cleared(int be_cleared) {
        this.be_cleared = be_cleared;
    }

    @Override
    public String toString() {
        return "SalesInvoice{" + "sales_invoice_id=" + sales_invoice_id + ", sales_invoice_number=" + sales_invoice_number + ", sales_order_id=" + sales_order_id + ", sales_invoice_date=" + sales_invoice_date + ", unit_price=" + unit_price + ", total_price=" + total_price + ", staff_id=" + staff_id + ", be_cleared=" + be_cleared + '}';
    }
    
    
}
