/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class Received_PO_PI {
    private int PO_ID;
    private String purchaseOrderNumber;
    private int REC_ID;
    private String receivedNumber;
    private int PI_ID;
    private String purchaseInvoiceNumber;
    private String supplierName;

    public Received_PO_PI() {
    }

    public int getPO_ID() {
        return PO_ID;
    }

    public void setPO_ID(int PO_ID) {
        this.PO_ID = PO_ID;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String PurchaseOrderNumber) {
        this.purchaseOrderNumber = PurchaseOrderNumber;
    }

    public int getREC_ID() {
        return REC_ID;
    }

    public void setREC_ID(int REC_ID) {
        this.REC_ID = REC_ID;
    }

    public String getReceivedNumber() {
        return receivedNumber;
    }

    public void setReceivedNumber(String receivedNumber) {
        this.receivedNumber = receivedNumber;
    }

    public int getPI_ID() {
        return PI_ID;
    }

    public void setPI_ID(int PI_ID) {
        this.PI_ID = PI_ID;
    }

    public String getPurchaseInvoiceNumber() {
        return purchaseInvoiceNumber;
    }

    public void setPurchaseInvoiceNumber(String PurchaseInvoiceNumber) {
        this.purchaseInvoiceNumber = PurchaseInvoiceNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Received_PO_PI{" + "PO_ID=" + PO_ID + ", PurchaseOrderNumber=" + purchaseOrderNumber + ", REC_ID=" + REC_ID + ", receivedNumber=" + receivedNumber + ", PI_ID=" + PI_ID + ", PurchaseInvoiceNumber=" + purchaseInvoiceNumber + ", supplierName=" + supplierName + '}';
    }

    
    
}
