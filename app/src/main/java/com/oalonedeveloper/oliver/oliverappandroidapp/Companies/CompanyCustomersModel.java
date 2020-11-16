package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

public class CompanyCustomersModel {

    String customer_email,customer_name,customer_phone,customer_type;

    public CompanyCustomersModel() {
    }

    public CompanyCustomersModel(String customer_email, String customer_name, String customer_phone, String customer_type) {
        this.customer_email = customer_email;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_type = customer_type;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }
}
