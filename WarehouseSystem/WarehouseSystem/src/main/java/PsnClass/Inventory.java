/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class Inventory {
    private int inventory_id;
    private String inventory_number;
    private String name;
    private int categories_id;
    private int type_id;
    private double quantity;
    private String unit;
    private double quantity_per_unit;
    private String quantity_unit;
    private String remark;
    private double safety_level;

    public Inventory() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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

    public double getSafety_level() {
        return safety_level;
    }

    public void setSafety_level(double safety_level) {
        this.safety_level = safety_level;
    }

    @Override
    public String toString() {
        return "Inventory{" + "inventory_id=" + inventory_id + ", inventory_number=" + inventory_number + ", name=" + name + ", categories_id=" + categories_id + ", type_id=" + type_id + ", quantity=" + quantity + ", unit=" + unit + ", quantity_per_unit=" + quantity_per_unit + ", quantity_unit=" + quantity_unit + ", remark=" + remark + ", safety_level=" + safety_level + '}';
    }

    

}
