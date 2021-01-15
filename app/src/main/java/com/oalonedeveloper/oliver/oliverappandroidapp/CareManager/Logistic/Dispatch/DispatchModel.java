package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Dispatch;

public class DispatchModel {

    String dispatch_address,dispatch_bill_number,dispatch_customer_id,dispatch_department,dispatch_district,dispatch_province,dispatch_shift,dispatch_state;

    public DispatchModel() {
    }

    public DispatchModel(String dispatch_address, String dispatch_bill_number, String dispatch_customer_id, String dispatch_department, String dispatch_district, String dispatch_province, String dispatch_shift, String dispatch_state) {
        this.dispatch_address = dispatch_address;
        this.dispatch_bill_number = dispatch_bill_number;
        this.dispatch_customer_id = dispatch_customer_id;
        this.dispatch_department = dispatch_department;
        this.dispatch_district = dispatch_district;
        this.dispatch_province = dispatch_province;
        this.dispatch_shift = dispatch_shift;
        this.dispatch_state = dispatch_state;
    }

    public String getDispatch_address() {
        return dispatch_address;
    }

    public void setDispatch_address(String dispatch_address) {
        this.dispatch_address = dispatch_address;
    }

    public String getDispatch_bill_number() {
        return dispatch_bill_number;
    }

    public void setDispatch_bill_number(String dispatch_bill_number) {
        this.dispatch_bill_number = dispatch_bill_number;
    }

    public String getDispatch_customer_id() {
        return dispatch_customer_id;
    }

    public void setDispatch_customer_id(String dispatch_customer_id) {
        this.dispatch_customer_id = dispatch_customer_id;
    }

    public String getDispatch_department() {
        return dispatch_department;
    }

    public void setDispatch_department(String dispatch_department) {
        this.dispatch_department = dispatch_department;
    }

    public String getDispatch_district() {
        return dispatch_district;
    }

    public void setDispatch_district(String dispatch_district) {
        this.dispatch_district = dispatch_district;
    }

    public String getDispatch_province() {
        return dispatch_province;
    }

    public void setDispatch_province(String dispatch_province) {
        this.dispatch_province = dispatch_province;
    }

    public String getDispatch_shift() {
        return dispatch_shift;
    }

    public void setDispatch_shift(String dispatch_shift) {
        this.dispatch_shift = dispatch_shift;
    }

    public String getDispatch_state() {
        return dispatch_state;
    }

    public void setDispatch_state(String dispatch_state) {
        this.dispatch_state = dispatch_state;
    }
}
