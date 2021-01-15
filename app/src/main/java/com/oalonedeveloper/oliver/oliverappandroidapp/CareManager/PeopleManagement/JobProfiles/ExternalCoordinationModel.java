package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

public class ExternalCoordinationModel {

    String coordination_company,coordination_denomination,coordination_email,coordination_immediate_boss_name,coordination_phone;

    public ExternalCoordinationModel() {
    }

    public ExternalCoordinationModel(String coordination_company, String coordination_denomination, String coordination_email, String coordination_immediate_boss_name, String coordination_phone) {
        this.coordination_company = coordination_company;
        this.coordination_denomination = coordination_denomination;
        this.coordination_email = coordination_email;
        this.coordination_immediate_boss_name = coordination_immediate_boss_name;
        this.coordination_phone = coordination_phone;
    }

    public String getCoordination_company() {
        return coordination_company;
    }

    public void setCoordination_company(String coordination_company) {
        this.coordination_company = coordination_company;
    }

    public String getCoordination_denomination() {
        return coordination_denomination;
    }

    public void setCoordination_denomination(String coordination_denomination) {
        this.coordination_denomination = coordination_denomination;
    }

    public String getCoordination_email() {
        return coordination_email;
    }

    public void setCoordination_email(String coordination_email) {
        this.coordination_email = coordination_email;
    }

    public String getCoordination_immediate_boss_name() {
        return coordination_immediate_boss_name;
    }

    public void setCoordination_immediate_boss_name(String coordination_immediate_boss_name) {
        this.coordination_immediate_boss_name = coordination_immediate_boss_name;
    }

    public String getCoordination_phone() {
        return coordination_phone;
    }

    public void setCoordination_phone(String coordination_phone) {
        this.coordination_phone = coordination_phone;
    }
}
