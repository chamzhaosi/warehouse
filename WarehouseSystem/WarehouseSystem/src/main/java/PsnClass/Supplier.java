/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PsnClass;

/**
 *
 * @author user
 */
public class Supplier {
    private int supplier_id;
    private String supplier_number;
    private String company_name;
    private String SSM;
    private String email;
    private String company_phone;
    private String mobile_phone;
    private String address;
    private String person_in_charge;


    public Supplier() {
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_number() {
        return supplier_number;
    }

    public void setSupplier_number(String supplier_number) {
        this.supplier_number = supplier_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getSSM() {
        return SSM;
    }

    public void setSSM(String SSM) {
        this.SSM = SSM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplier_id=" + supplier_id + ", supplier_number=" + supplier_number + ", company_name=" + company_name + ", SSM=" + SSM + ", email=" + email + ", company_phone=" + company_phone + ", mobile_phone=" + mobile_phone + ", address=" + address + ", person_in_charge=" + person_in_charge + '}';
    }
    

}
