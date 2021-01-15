package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

public class InternalCoordinationModel {

    String coordination_area,coordination_denomination,coordination_immediate_boss_name;

    public InternalCoordinationModel() {
    }

    public InternalCoordinationModel(String coordination_area, String coordination_denomination, String coordination_immediate_boss_name) {
        this.coordination_area = coordination_area;
        this.coordination_denomination = coordination_denomination;
        this.coordination_immediate_boss_name = coordination_immediate_boss_name;
    }

    public String getCoordination_area() {
        return coordination_area;
    }

    public void setCoordination_area(String coordination_area) {
        this.coordination_area = coordination_area;
    }

    public String getCoordination_denomination() {
        return coordination_denomination;
    }

    public void setCoordination_denomination(String coordination_denomination) {
        this.coordination_denomination = coordination_denomination;
    }

    public String getCoordination_immediate_boss_name() {
        return coordination_immediate_boss_name;
    }

    public void setCoordination_immediate_boss_name(String coordination_immediate_boss_name) {
        this.coordination_immediate_boss_name = coordination_immediate_boss_name;
    }
}
