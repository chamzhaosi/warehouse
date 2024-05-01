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
public class PurchaseOrder {
    int purchase_order_id;
    String purchase_order_number;
    int inventory_id;
    double quantity;
    String unit;
    Date order_date;
    Date expect_received_date;
    int staff_id;
    int be_cleared;

    public PurchaseOrder() {
    }

    public int getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(int purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public String getPurchase_order_number() {
        return purchase_order_number;
    }

    public void setPurchase_order_number(String purchase_order_number) {
        this.purchase_order_number = purchase_order_number;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getExpect_received_date() {
        return expect_received_date;
    }

    public void setExpect_received_date(Date expect_received_date) {
        this.expect_received_date = expect_received_date;
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
        return "PurchaseOrder{" + "purchase_order_id=" + purchase_order_id + ", purchase_order_number=" + purchase_order_number + ", inventory_id=" + inventory_id + ", quantity=" + quantity + ", unit=" + unit + ", order_date=" + order_date + ", expect_received_date=" + expect_received_date + ", staff_id=" + staff_id + ", be_cleared=" + be_cleared + '}';
    }

   
}
