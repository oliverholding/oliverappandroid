package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

public class KardexModel {

    String operation_date,operation_day,operation_month,operation_time,operation_type,operation_year,product_id,product_price,stock,warehouse_destination_id,warehouse_origin_id;

    public KardexModel() {
    }

    public KardexModel(String operation_date, String operation_day, String operation_month, String operation_time, String operation_type, String operation_year, String product_id, String product_price, String stock, String warehouse_destination_id, String warehouse_origin_id) {
        this.operation_date = operation_date;
        this.operation_day = operation_day;
        this.operation_month = operation_month;
        this.operation_time = operation_time;
        this.operation_type = operation_type;
        this.operation_year = operation_year;
        this.product_id = product_id;
        this.product_price = product_price;
        this.stock = stock;
        this.warehouse_destination_id = warehouse_destination_id;
        this.warehouse_origin_id = warehouse_origin_id;
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

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getOperation_year() {
        return operation_year;
    }

    public void setOperation_year(String operation_year) {
        this.operation_year = operation_year;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getWarehouse_destination_id() {
        return warehouse_destination_id;
    }

    public void setWarehouse_destination_id(String warehouse_destination_id) {
        this.warehouse_destination_id = warehouse_destination_id;
    }

    public String getWarehouse_origin_id() {
        return warehouse_origin_id;
    }

    public void setWarehouse_origin_id(String warehouse_origin_id) {
        this.warehouse_origin_id = warehouse_origin_id;
    }
}
