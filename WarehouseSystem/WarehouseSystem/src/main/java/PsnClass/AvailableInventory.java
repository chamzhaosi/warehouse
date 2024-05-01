/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class AvailableInventory {
    int inventory_id;
    String inventory_number;
    String inventory_name;
    String category;
    String type;
    double quantity;
    String unit;
    double safety_quantity;
    String inventory_location_name;

    public AvailableInventory() {
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(String inventory_number) {
        this.inventory_number = inventory_number;
    }

    public String getInventory_name() {
        return inventory_name;
    }

    public void setInventory_name(String inventory_name) {
        this.inventory_name = inventory_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getSafety_quantity() {
        return safety_quantity;
    }

    public void setSafety_quantity(double safety_quantity) {
        this.safety_quantity = safety_quantity;
    }

    public String getInventory_location_name() {
        return inventory_location_name;
    }

    public void setInventory_location_name(String inventory_location_name) {
        this.inventory_location_name = inventory_location_name;
    }

    @Override
    public String toString() {
        return "AvailableInventory{" + "inventory_id=" + inventory_id + ", inventory_number=" + inventory_number + ", inventory_name=" + inventory_name + ", category=" + category + ", type=" + type + ", quantity=" + quantity + ", unit=" + unit + ", safety_quantity=" + safety_quantity + ", inventory_location_name=" + inventory_location_name + '}';
    }
}
