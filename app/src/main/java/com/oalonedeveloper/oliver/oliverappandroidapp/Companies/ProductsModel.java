package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

public class ProductsModel {

    String product_image,product_currency,product_description,product_measure,uid,code,product_name,product_price,product_stock;

    public ProductsModel() {
    }

    public ProductsModel(String product_image, String product_currency, String product_description, String product_measure, String uid, String code, String product_name, String product_price, String product_stock) {
        this.product_image = product_image;
        this.product_currency = product_currency;
        this.product_description = product_description;
        this.product_measure = product_measure;
        this.uid = uid;
        this.code = code;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }
}
