package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing;

public class OrderNoteModel {

    String order_processing_state,order_processing_customer_id,operation_date,order_processing_total_amount;

    public OrderNoteModel() {
    }

    public OrderNoteModel(String order_processing_state, String order_processing_customer_id, String operation_date, String order_processing_total_amount) {
        this.order_processing_state = order_processing_state;
        this.order_processing_customer_id = order_processing_customer_id;
        this.operation_date = operation_date;
        this.order_processing_total_amount = order_processing_total_amount;
    }

    public String getOrder_processing_state() {
        return order_processing_state;
    }

    public void setOrder_processing_state(String order_processing_state) {
        this.order_processing_state = order_processing_state;
    }

    public String getOrder_processing_customer_id() {
        return order_processing_customer_id;
    }

    public void setOrder_processing_customer_id(String order_processing_customer_id) {
        this.order_processing_customer_id = order_processing_customer_id;
    }

    public String getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(String operation_date) {
        this.operation_date = operation_date;
    }

    public String getOrder_processing_total_amount() {
        return order_processing_total_amount;
    }

    public void setOrder_processing_total_amount(String order_processing_total_amount) {
        this.order_processing_total_amount = order_processing_total_amount;
    }
}
