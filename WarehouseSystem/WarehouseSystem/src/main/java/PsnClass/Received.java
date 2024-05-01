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
public class Received {
    int received_id;
    String received_number;
    int supplier_id;
    int purchase_order_id;
    Date received_date;
    Date expired_date;
    int inventory_location_id;
    int staff_id;
    int be_cleared; 

    public Received() {
    }

    public int getReceived_id() {
        return received_id;
    }

    public void setReceived_id(int received_id) {
        this.received_id = received_id;
    }

    public String getReceived_number() {
        return received_number;
    }

    public void setReceived_number(String received_number) {
        this.received_number = received_number;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(int purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public int getInventory_location_id() {
        return inventory_location_id;
    }

    public void setInventory_location_id(int inventory_location_id) {
        this.inventory_location_id = inventory_location_id;
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
        return "Received{" + "received_id=" + received_id + ", received_number=" + received_number + ", supplier_id=" + supplier_id + ", purchase_order_id=" + purchase_order_id + ", received_date=" + received_date + ", expired_date=" + expired_date + ", inventory_location_id=" + inventory_location_id + ", staff_id=" + staff_id + ", be_cleared=" + be_cleared + '}';
    }

   
    
}
