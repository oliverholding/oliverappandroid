package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

public class WarehousesModel {

    String warehouse_name,warehouse_destination;

    public WarehousesModel() {
    }

    public WarehousesModel(String warehouse_name, String warehouse_destination) {
        this.warehouse_name = warehouse_name;
        this.warehouse_destination = warehouse_destination;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWarehouse_destination() {
        return warehouse_destination;
    }

    public void setWarehouse_destination(String warehouse_destination) {
        this.warehouse_destination = warehouse_destination;
    }
}
