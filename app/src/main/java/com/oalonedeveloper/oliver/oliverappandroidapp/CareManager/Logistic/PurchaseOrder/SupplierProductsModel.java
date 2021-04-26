package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

public class SupplierProductsModel {

    String item_description,item_name,item_price,item_quantity,item_total;

    public SupplierProductsModel() {
    }

    public SupplierProductsModel(String item_description, String item_name, String item_price, String item_quantity, String item_total) {
        this.item_description = item_description;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_total = item_total;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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

    public String getItem_total() {
        return item_total;
    }

    public void setItem_total(String item_total) {
        this.item_total = item_total;
    }
}
