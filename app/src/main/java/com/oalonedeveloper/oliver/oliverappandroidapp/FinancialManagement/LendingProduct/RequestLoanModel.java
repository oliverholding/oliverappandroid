package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class RequestLoanModel {

    String customer_id,document_number,product_id,product_img,product_name,request_day,request_month,request_state,requested_date,requested_time;

    int desgravamen_rate,product_fixed_fee,product_tea,request_year;

    public RequestLoanModel() {
    }

    public RequestLoanModel(String customer_id, String document_number, String product_id, String product_img, String product_name, String request_day, String request_month, String request_state, String requested_date, String requested_time, int desgravamen_rate, int product_fixed_fee, int product_tea, int request_year) {
        this.customer_id = customer_id;
        this.document_number = document_number;
        this.product_id = product_id;
        this.product_img = product_img;
        this.product_name = product_name;
        this.request_day = request_day;
        this.request_month = request_month;
        this.request_state = request_state;
        this.requested_date = requested_date;
        this.requested_time = requested_time;
        this.desgravamen_rate = desgravamen_rate;
        this.product_fixed_fee = product_fixed_fee;
        this.product_tea = product_tea;
        this.request_year = request_year;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRequest_day() {
        return request_day;
    }

    public void setRequest_day(String request_day) {
        this.request_day = request_day;
    }

    public String getRequest_month() {
        return request_month;
    }

    public void setRequest_month(String request_month) {
        this.request_month = request_month;
    }

    public String getRequest_state() {
        return request_state;
    }

    public void setRequest_state(String request_state) {
        this.request_state = request_state;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    public String getRequested_time() {
        return requested_time;
    }

    public void setRequested_time(String requested_time) {
        this.requested_time = requested_time;
    }

    public int getDesgravamen_rate() {
        return desgravamen_rate;
    }

    public void setDesgravamen_rate(int desgravamen_rate) {
        this.desgravamen_rate = desgravamen_rate;
    }

    public int getProduct_fixed_fee() {
        return product_fixed_fee;
    }

    public void setProduct_fixed_fee(int product_fixed_fee) {
        this.product_fixed_fee = product_fixed_fee;
    }

    public int getProduct_tea() {
        return product_tea;
    }

    public void setProduct_tea(int product_tea) {
        this.product_tea = product_tea;
    }

    public int getRequest_year() {
        return request_year;
    }

    public void setRequest_year(int request_year) {
        this.request_year = request_year;
    }
}
