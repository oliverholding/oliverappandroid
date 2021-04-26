package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class LoanBillsModel {

    String bill_amount,bill_currency,bill_expiration_day,bill_expiration_month,bill_expiration_year,bill_state;

    public LoanBillsModel() {
    }

    public LoanBillsModel(String bill_amount, String bill_currency, String bill_expiration_day, String bill_expiration_month, String bill_expiration_year, String bill_state) {
        this.bill_amount = bill_amount;
        this.bill_currency = bill_currency;
        this.bill_expiration_day = bill_expiration_day;
        this.bill_expiration_month = bill_expiration_month;
        this.bill_expiration_year = bill_expiration_year;
        this.bill_state = bill_state;
    }

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public String getBill_currency() {
        return bill_currency;
    }

    public void setBill_currency(String bill_currency) {
        this.bill_currency = bill_currency;
    }

    public String getBill_expiration_day() {
        return bill_expiration_day;
    }

    public void setBill_expiration_day(String bill_expiration_day) {
        this.bill_expiration_day = bill_expiration_day;
    }

    public String getBill_expiration_month() {
        return bill_expiration_month;
    }

    public void setBill_expiration_month(String bill_expiration_month) {
        this.bill_expiration_month = bill_expiration_month;
    }

    public String getBill_expiration_year() {
        return bill_expiration_year;
    }

    public void setBill_expiration_year(String bill_expiration_year) {
        this.bill_expiration_year = bill_expiration_year;
    }

    public String getBill_state() {
        return bill_state;
    }

    public void setBill_state(String bill_state) {
        this.bill_state = bill_state;
    }
}
