package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

public class MyProductsModel {

    String amount,financial_institution_id,operation_id,product_type;

    public MyProductsModel() {
    }

    public MyProductsModel(String amount, String financial_institution_id, String operation_id, String product_type) {
        this.amount = amount;
        this.financial_institution_id = financial_institution_id;
        this.operation_id = operation_id;
        this.product_type = product_type;

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFinancial_institution_id() {
        return financial_institution_id;
    }

    public void setFinancial_institution_id(String financial_institution_id) {
        this.financial_institution_id = financial_institution_id;
    }

    public String getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }


}
