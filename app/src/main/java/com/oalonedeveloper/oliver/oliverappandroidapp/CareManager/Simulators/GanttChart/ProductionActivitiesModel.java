package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.GanttChart;

public class ProductionActivitiesModel {

    String activity_name,activity_time;

    public ProductionActivitiesModel() {
    }

    public ProductionActivitiesModel(String activity_name, String activity_time) {
        this.activity_name = activity_name;
        this.activity_time = activity_time;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }
}
