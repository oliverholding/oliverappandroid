package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders;

public class ChainModel {

    String product_id,product_name,product_image,product_quantity_production,state,date,time,code,product_currency,product_description,product_measure,product_price;

    public ChainModel() {
    }

    public ChainModel(String product_id, String product_name, String product_image, String product_quantity_production, String state, String date, String time, String code, String product_currency, String product_description, String product_measure, String product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_quantity_production = product_quantity_production;
        this.state = state;
        this.date = date;
        this.time = time;
        this.code = code;
        this.product_currency = product_currency;
        this.product_description = product_description;
        this.product_measure = product_measure;
        this.product_price = product_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_quantity_production() {
        return product_quantity_production;
    }

    public void setProduct_quantity_production(String product_quantity_production) {
        this.product_quantity_production = product_quantity_production;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct_currency() {
        return product_currency;
    }

    public void setProduct_currency(String product_currency) {
        this.product_currency = product_currency;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_measure() {
        return product_measure;
    }

    public void setProduct_measure(String product_measure) {
        this.product_measure = product_measure;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
