package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

public class SupplierModel  {
    String supplier_name,supplier_document_number,supplier_email,supplier_phone,supplier_contact,supplier_id;

    public SupplierModel() {
    }

    public SupplierModel(String supplier_name, String supplier_document_number, String supplier_email, String supplier_phone, String supplier_contact, String supplier_id) {
        this.supplier_name = supplier_name;
        this.supplier_document_number = supplier_document_number;
        this.supplier_email = supplier_email;
        this.supplier_phone = supplier_phone;
        this.supplier_contact = supplier_contact;
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_document_number() {
        return supplier_document_number;
    }

    public void setSupplier_document_number(String supplier_document_number) {
        this.supplier_document_number = supplier_document_number;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public String getSupplier_contact() {
        return supplier_contact;
    }

    public void setSupplier_contact(String supplier_contact) {
        this.supplier_contact = supplier_contact;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }
}
