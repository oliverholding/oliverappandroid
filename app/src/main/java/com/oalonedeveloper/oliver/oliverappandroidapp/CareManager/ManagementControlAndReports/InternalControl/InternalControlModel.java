package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.InternalControl;

public class InternalControlModel {

    String internal_control_name,internal_control_area,internal_control_objective;

    public InternalControlModel() {
    }

    public InternalControlModel(String internal_control_name, String internal_control_area, String internal_control_objective) {
        this.internal_control_name = internal_control_name;
        this.internal_control_area = internal_control_area;
        this.internal_control_objective = internal_control_objective;
    }

    public String getInternal_control_name() {
        return internal_control_name;
    }

    public void setInternal_control_name(String internal_control_name) {
        this.internal_control_name = internal_control_name;
    }

    public String getInternal_control_area() {
        return internal_control_area;
    }

    public void setInternal_control_area(String internal_control_area) {
        this.internal_control_area = internal_control_area;
    }

    public String getInternal_control_objective() {
        return internal_control_objective;
    }

    public void setInternal_control_objective(String internal_control_objective) {
        this.internal_control_objective = internal_control_objective;
    }
}
