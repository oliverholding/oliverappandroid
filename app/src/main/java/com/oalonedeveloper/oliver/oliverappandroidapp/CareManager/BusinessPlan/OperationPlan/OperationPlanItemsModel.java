package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan;

public class OperationPlanItemsModel {
    String name,measurement,quantity;

    public OperationPlanItemsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public OperationPlanItemsModel(String name, String measurement, String quantity) {
        this.name = name;
        this.measurement = measurement;
        this.quantity = quantity;
    }
}
