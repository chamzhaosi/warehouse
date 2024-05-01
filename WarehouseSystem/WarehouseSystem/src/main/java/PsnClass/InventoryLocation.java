/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class InventoryLocation {
    int inventory_location_id;
    String name; 

    public InventoryLocation() {
    }

    public int getInventory_location_id() {
        return inventory_location_id;
    }

    public void setInventory_location_id(int inventory_location_id) {
        this.inventory_location_id = inventory_location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InventoryLocation{" + "inventory_location_id=" + inventory_location_id + ", name=" + name + '}';
    }
    
}
