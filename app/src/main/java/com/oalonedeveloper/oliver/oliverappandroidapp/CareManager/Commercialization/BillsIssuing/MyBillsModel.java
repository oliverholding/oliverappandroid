package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

public class MyBillsModel {

    String bill_amount,customer_name,bill_id,state;

    public MyBillsModel() {
    }

    public MyBillsModel(String bill_amount, String customer_name, String bill_id, String state) {
        this.bill_amount = bill_amount;
        this.customer_name = customer_name;
        this.bill_id = bill_id;
        this.state = state;
    }

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
