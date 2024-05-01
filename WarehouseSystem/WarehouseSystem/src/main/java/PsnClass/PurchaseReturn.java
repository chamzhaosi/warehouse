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
public class PurchaseReturn {
    int purchase_return_id;
    String purchase_return_number;
    Date purchase_return_date;
    int purchase_invoice_id;
    int inventory_id;
    double quantity;
    String unit;
    String remark;

    public PurchaseReturn() {
    }

    public int getPurchase_return_id() {
        return purchase_return_id;
    }

    public void setPurchase_return_id(int purchase_return_id) {
        this.purchase_return_id = purchase_return_id;
    }

    public String getPurchase_return_number() {
        return purchase_return_number;
    }

    public void setPurchase_return_number(String purchase_return_number) {
        this.purchase_return_number = purchase_return_number;
    }

    public Date getPurchase_return_date() {
        return purchase_return_date;
    }

    public void setPurchase_return_date(Date purchase_return_date) {
        this.purchase_return_date = purchase_return_date;
    }

    public int getPurchase_invoice_id() {
        return purchase_invoice_id;
    }

    public void setPurchase_invoice_id(int purchase_invoice_id) {
        this.purchase_invoice_id = purchase_invoice_id;
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
        return "PurchaseReturn{" + "purchase_return_id=" + purchase_return_id + ", purchase_return_number=" + purchase_return_number + ", purchase_return_date=" + purchase_return_date + ", purchase_invoice_id=" + purchase_invoice_id + ", inventory_id=" + inventory_id + ", quantity=" + quantity + ", unit=" + unit + ", remark=" + remark + '}';
    }

    
}
