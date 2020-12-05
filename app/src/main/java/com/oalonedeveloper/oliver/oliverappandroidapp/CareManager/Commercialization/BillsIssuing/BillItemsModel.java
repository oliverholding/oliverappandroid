package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

public class BillItemsModel {
    String code,discount,measure,name,price,quantity,total;

    public BillItemsModel() {
    }

    public BillItemsModel(String code, String discount, String measure, String name, String price, String quantity, String total) {
        this.code = code;
        this.discount = discount;
        this.measure = measure;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
