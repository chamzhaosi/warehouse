/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class SO_SI {
    private int SO_ID;
    private String salesOrderNumber;
    private int SI_ID;
    private String salesInvoiceNumber;
    private String customerName;

    public SO_SI() {
    }

    public int getSO_ID() {
        return SO_ID;
    }

    public void setSO_ID(int SO_ID) {
        this.SO_ID = SO_ID;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public int getSI_ID() {
        return SI_ID;
    }

    public void setSI_ID(int SI_ID) {
        this.SI_ID = SI_ID;
    }

    public String getSalesInvoiceNumber() {
        return salesInvoiceNumber;
    }

    public void setSalesInvoiceNumber(String salesInvoiceNumber) {
        this.salesInvoiceNumber = salesInvoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "SO_SI{" + "SO_ID=" + SO_ID + ", salesOrderNumber=" + salesOrderNumber + ", SI_ID=" + SI_ID + ", salesInvoiceNumber=" + salesInvoiceNumber + ", supplierName=" + customerName + '}';
    }
    
    
}
