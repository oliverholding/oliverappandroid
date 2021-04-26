package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

public class PurchaseOrdersModel {

    String operation_date,operation_day,operation_month,operation_time,operation_year,purchase_order_id,purchase_order_supplier_id,purchase_order_total_amount,purchase_order_state;

    public PurchaseOrdersModel() {
    }

    public PurchaseOrdersModel(String operation_date, String operation_day, String operation_month, String operation_time, String operation_year, String purchase_order_id, String purchase_order_supplier_id, String purchase_order_total_amount, String purchase_order_state) {
        this.operation_date = operation_date;
        this.operation_day = operation_day;
        this.operation_month = operation_month;
        this.operation_time = operation_time;
        this.operation_year = operation_year;
        this.purchase_order_id = purchase_order_id;
        this.purchase_order_supplier_id = purchase_order_supplier_id;
        this.purchase_order_total_amount = purchase_order_total_amount;
        this.purchase_order_state = purchase_order_state;
    }

    public String getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(String operation_date) {
        this.operation_date = operation_date;
    }

    public String getOperation_day() {
        return operation_day;
    }

    public void setOperation_day(String operation_day) {
        this.operation_day = operation_day;
    }

    public String getOperation_month() {
        return operation_month;
    }

    public void setOperation_month(String operation_month) {
        this.operation_month = operation_month;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }

    public String getOperation_year() {
        return operation_year;
    }

    public void setOperation_year(String operation_year) {
        this.operation_year = operation_year;
    }

    public String getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(String purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public String getPurchase_order_supplier_id() {
        return purchase_order_supplier_id;
    }

    public void setPurchase_order_supplier_id(String purchase_order_supplier_id) {
        this.purchase_order_supplier_id = purchase_order_supplier_id;
    }

    public String getPurchase_order_total_amount() {
        return purchase_order_total_amount;
    }

    public void setPurchase_order_total_amount(String purchase_order_total_amount) {
        this.purchase_order_total_amount = purchase_order_total_amount;
    }

    public String getPurchase_order_state() {
        return purchase_order_state;
    }

    public void setPurchase_order_state(String purchase_order_state) {
        this.purchase_order_state = purchase_order_state;
    }


}
