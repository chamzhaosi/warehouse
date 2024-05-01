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
public class PurchaseInvoice {
    int purchase_invoice_id;
    String purchase_invoice_number;
    Date purchase_invoice_date;
    int received_id;
    double unit_price;
    double total_price;
    int staff_id;
    int be_cleared;

    public PurchaseInvoice() {
    }

    public int getPurchase_invoice_id() {
        return purchase_invoice_id;
    }

    public void setPurchase_invoice_id(int purchase_invoice_id) {
        this.purchase_invoice_id = purchase_invoice_id;
    }

    public String getPurchase_invoice_number() {
        return purchase_invoice_number;
    }

    public void setPurchase_invoice_number(String purchase_invoice_number) {
        this.purchase_invoice_number = purchase_invoice_number;
    }

    public Date getPurchase_invoice_date() {
        return purchase_invoice_date;
    }

    public void setPurchase_invoice_date(Date purchase_invoice_date) {
        this.purchase_invoice_date = purchase_invoice_date;
    }

    public int getReceived_id() {
        return received_id;
    }

    public void setReceived_id(int received_id) {
        this.received_id = received_id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
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
        return "PurchaseInvoice{" + "purchase_invoice_id=" + purchase_invoice_id + ", purchase_invoice_number=" + purchase_invoice_number + ", purchase_invoice_date=" + purchase_invoice_date + ", received_id=" + received_id + ", unit_price=" + unit_price + ", total_price=" + total_price + ", staff_id=" + staff_id + ", be_cleared=" + be_cleared + '}';
    }

    
}
