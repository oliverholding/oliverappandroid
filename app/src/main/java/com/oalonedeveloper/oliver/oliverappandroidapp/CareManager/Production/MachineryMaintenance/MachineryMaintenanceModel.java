package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.MachineryMaintenance;

public class MachineryMaintenanceModel {

    String concept,asset,day,month,year;

    public MachineryMaintenanceModel() {
    }

    public MachineryMaintenanceModel(String concept, String asset, String day, String month, String year) {
        this.concept = concept;
        this.asset = asset;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
