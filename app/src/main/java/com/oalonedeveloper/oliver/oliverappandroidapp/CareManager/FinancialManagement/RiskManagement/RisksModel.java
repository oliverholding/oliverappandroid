package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement;

public class RisksModel {

    String risk_area,risk_name,risk_cause,risk_impact,risk_probability,risk_solution;

    public RisksModel() {
    }

    public RisksModel(String risk_area, String risk_name, String risk_cause, String risk_impact, String risk_probability, String risk_solution) {
        this.risk_area = risk_area;
        this.risk_name = risk_name;
        this.risk_cause = risk_cause;
        this.risk_impact = risk_impact;
        this.risk_probability = risk_probability;
        this.risk_solution = risk_solution;
    }

    public String getRisk_area() {
        return risk_area;
    }

    public void setRisk_area(String risk_area) {
        this.risk_area = risk_area;
    }

    public String getRisk_name() {
        return risk_name;
    }

    public void setRisk_name(String risk_name) {
        this.risk_name = risk_name;
    }

    public String getRisk_cause() {
        return risk_cause;
    }

    public void setRisk_cause(String risk_cause) {
        this.risk_cause = risk_cause;
    }

    public String getRisk_impact() {
        return risk_impact;
    }

    public void setRisk_impact(String risk_impact) {
        this.risk_impact = risk_impact;
    }

    public String getRisk_probability() {
        return risk_probability;
    }

    public void setRisk_probability(String risk_probability) {
        this.risk_probability = risk_probability;
    }

    public String getRisk_solution() {
        return risk_solution;
    }

    public void setRisk_solution(String risk_solution) {
        this.risk_solution = risk_solution;
    }
}
