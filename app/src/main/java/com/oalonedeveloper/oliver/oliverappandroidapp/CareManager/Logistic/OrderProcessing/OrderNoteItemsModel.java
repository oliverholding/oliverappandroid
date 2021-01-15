package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing;

public class OrderNoteItemsModel {

    String name,price,product_id,quantity,total;

    public OrderNoteItemsModel() {
    }

    public OrderNoteItemsModel(String name, String price, String product_id, String quantity, String total) {
        this.name = name;
        this.price = price;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
