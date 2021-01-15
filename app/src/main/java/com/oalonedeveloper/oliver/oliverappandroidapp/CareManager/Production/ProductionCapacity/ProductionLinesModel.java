package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCapacity;

public class ProductionLinesModel {

    String production_line_name,production_man_time,production_real_production,production_theoretical_capacity;

    public ProductionLinesModel() {
    }

    public ProductionLinesModel(String production_line_name, String production_man_time, String production_real_production, String production_theoretical_capacity) {
        this.production_line_name = production_line_name;
        this.production_man_time = production_man_time;
        this.production_real_production = production_real_production;
        this.production_theoretical_capacity = production_theoretical_capacity;
    }

    public String getProduction_line_name() {
        return production_line_name;
    }

    public void setProduction_line_name(String production_line_name) {
        this.production_line_name = production_line_name;
    }

    public String getProduction_man_time() {
        return production_man_time;
    }

    public void setProduction_man_time(String production_man_time) {
        this.production_man_time = production_man_time;
    }

    public String getProduction_real_production() {
        return production_real_production;
    }

    public void setProduction_real_production(String production_real_production) {
        this.production_real_production = production_real_production;
    }

    public String getProduction_theoretical_capacity() {
        return production_theoretical_capacity;
    }

    public void setProduction_theoretical_capacity(String production_theoretical_capacity) {
        this.production_theoretical_capacity = production_theoretical_capacity;
    }
}
