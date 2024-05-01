/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class InitialInventory {
    int initial_inventory_id;
    int inventory_id;
    double quantity;
    String unit;
    double quantity_per_unit;
    String quantity_unit;
    String remark;
    int inventory_location_id;

    public InitialInventory() {
    }

    public int getInitial_inventory_id() {
        return initial_inventory_id;
    }

    public void setInitial_inventory_id(int initial_inventory_id) {
        this.initial_inventory_id = initial_inventory_id;
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

    public double getQuantity_per_unit() {
        return quantity_per_unit;
    }

    public void setQuantity_per_unit(double quantity_per_unit) {
        this.quantity_per_unit = quantity_per_unit;
    }

    public String getQuantity_unit() {
        return quantity_unit;
    }

    public void setQuantity_unit(String quantity_unit) {
        this.quantity_unit = quantity_unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getInventory_location_id() {
        return inventory_location_id;
    }

    public void setInventory_location_id(int inventory_location_id) {
        this.inventory_location_id = inventory_location_id;
    }

    @Override
    public String toString() {
        return "InitialInventory{" + "initial_inventory_id=" + initial_inventory_id + ", inventory_id=" + inventory_id + ", quantity=" + quantity + ", unit=" + unit + ", quantity_per_unit=" + quantity_per_unit + ", quantity_unit=" + quantity_unit + ", remark=" + remark + ", inventory_location_id=" + inventory_location_id + '}';
    }


}
