package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders;

public class ProductMaterialsModel {

    String item_name,item_measure,item_image,item_price,item_quantity,item_id;

    public ProductMaterialsModel() {
    }

    public ProductMaterialsModel(String item_name, String item_measure, String item_image, String item_price, String item_quantity, String item_id) {
        this.item_name = item_name;
        this.item_measure = item_measure;
        this.item_image = item_image;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_measure() {
        return item_measure;
    }

    public void setItem_measure(String item_measure) {
        this.item_measure = item_measure;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
}
