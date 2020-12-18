package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule;

public class CustomerScheduleModel {

    String date,time,customer_name,customer_id,schedule_state;

    public CustomerScheduleModel() {
    }

    public CustomerScheduleModel(String date, String time, String customer_name, String customer_id, String schedule_state) {
        this.date = date;
        this.time = time;
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.schedule_state = schedule_state;
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

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSchedule_state() {
        return schedule_state;
    }

    public void setSchedule_state(String schedule_state) {
        this.schedule_state = schedule_state;
    }
}
