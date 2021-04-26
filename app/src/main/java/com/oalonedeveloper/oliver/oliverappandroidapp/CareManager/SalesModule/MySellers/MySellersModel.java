package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.MySellers;

public class MySellersModel {

    String seller_name,seller_email,seller_document_number,seller_phone;

    public MySellersModel() {
    }

    public MySellersModel(String seller_name, String seller_email, String seller_document_number, String seller_phone) {
        this.seller_name = seller_name;
        this.seller_email = seller_email;
        this.seller_document_number = seller_document_number;
        this.seller_phone = seller_phone;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getSeller_document_number() {
        return seller_document_number;
    }

    public void setSeller_document_number(String seller_document_number) {
        this.seller_document_number = seller_document_number;
    }

    public String getSeller_phone() {
        return seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        this.seller_phone = seller_phone;
    }
}
