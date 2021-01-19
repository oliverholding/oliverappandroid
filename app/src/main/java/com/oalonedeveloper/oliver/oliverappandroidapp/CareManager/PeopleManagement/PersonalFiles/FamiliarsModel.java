package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles;

public class FamiliarsModel {

    String familiar_type,familiar_name,familiar_bth_day,familiar_bth_month,familiar_bth_year,familiar_bth_civil_state,familiar_document_number;

    public FamiliarsModel() {
    }

    public FamiliarsModel(String familiar_type, String familiar_name, String familiar_bth_day, String familiar_bth_month, String familiar_bth_year, String familiar_bth_civil_state, String familiar_document_number) {
        this.familiar_type = familiar_type;
        this.familiar_name = familiar_name;
        this.familiar_bth_day = familiar_bth_day;
        this.familiar_bth_month = familiar_bth_month;
        this.familiar_bth_year = familiar_bth_year;
        this.familiar_bth_civil_state = familiar_bth_civil_state;
        this.familiar_document_number = familiar_document_number;
    }

    public String getFamiliar_type() {
        return familiar_type;
    }

    public void setFamiliar_type(String familiar_type) {
        this.familiar_type = familiar_type;
    }

    public String getFamiliar_name() {
        return familiar_name;
    }

    public void setFamiliar_name(String familiar_name) {
        this.familiar_name = familiar_name;
    }

    public String getFamiliar_bth_day() {
        return familiar_bth_day;
    }

    public void setFamiliar_bth_day(String familiar_bth_day) {
        this.familiar_bth_day = familiar_bth_day;
    }

    public String getFamiliar_bth_month() {
        return familiar_bth_month;
    }

    public void setFamiliar_bth_month(String familiar_bth_month) {
        this.familiar_bth_month = familiar_bth_month;
    }

    public String getFamiliar_bth_year() {
        return familiar_bth_year;
    }

    public void setFamiliar_bth_year(String familiar_bth_year) {
        this.familiar_bth_year = familiar_bth_year;
    }

    public String getFamiliar_bth_civil_state() {
        return familiar_bth_civil_state;
    }

    public void setFamiliar_bth_civil_state(String familiar_bth_civil_state) {
        this.familiar_bth_civil_state = familiar_bth_civil_state;
    }

    public String getFamiliar_document_number() {
        return familiar_document_number;
    }

    public void setFamiliar_document_number(String familiar_document_number) {
        this.familiar_document_number = familiar_document_number;
    }
}
