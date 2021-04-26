package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class FinancialProductModel {
    String product,product_short_description,product_name,product_img;

    public FinancialProductModel() {
    }

    public FinancialProductModel(String product, String product_short_description, String product_name, String product_img) {
        this.product = product;
        this.product_short_description = product_short_description;
        this.product_name = product_name;
        this.product_img = product_img;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct_short_description() {
        return product_short_description;
    }

    public void setProduct_short_description(String product_short_description) {
        this.product_short_description = product_short_description;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}
