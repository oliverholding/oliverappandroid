package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan;

public class AnnualPlanModel {
    String management_area,management_area_type,management_description,main_objective,linked_objective,initiative_1,initiative_2,resources,expected_result,responsable,start_date,end_date,
            kp1,kp2,kp1_progress,kp2_progress,goal_ready;

    public AnnualPlanModel() {
    }

    public AnnualPlanModel(String management_area, String management_area_type, String management_description, String main_objective, String linked_objective, String initiative_1, String initiative_2, String resources, String expected_result, String responsable, String start_date, String end_date, String kp1, String kp2, String kp1_progress, String kp2_progress, String goal_ready) {
        this.management_area = management_area;
        this.management_area_type = management_area_type;
        this.management_description = management_description;
        this.main_objective = main_objective;
        this.linked_objective = linked_objective;
        this.initiative_1 = initiative_1;
        this.initiative_2 = initiative_2;
        this.resources = resources;
        this.expected_result = expected_result;
        this.responsable = responsable;
        this.start_date = start_date;
        this.end_date = end_date;
        this.kp1 = kp1;
        this.kp2 = kp2;
        this.kp1_progress = kp1_progress;
        this.kp2_progress = kp2_progress;
        this.goal_ready = goal_ready;
    }

    public String getManagement_area() {
        return management_area;
    }

    public void setManagement_area(String management_area) {
        this.management_area = management_area;
    }

    public String getManagement_area_type() {
        return management_area_type;
    }

    public void setManagement_area_type(String management_area_type) {
        this.management_area_type = management_area_type;
    }

    public String getManagement_description() {
        return management_description;
    }

    public void setManagement_description(String management_description) {
        this.management_description = management_description;
    }

    public String getMain_objective() {
        return main_objective;
    }

    public void setMain_objective(String main_objective) {
        this.main_objective = main_objective;
    }

    public String getLinked_objective() {
        return linked_objective;
    }

    public void setLinked_objective(String linked_objective) {
        this.linked_objective = linked_objective;
    }

    public String getInitiative_1() {
        return initiative_1;
    }

    public void setInitiative_1(String initiative_1) {
        this.initiative_1 = initiative_1;
    }

    public String getInitiative_2() {
        return initiative_2;
    }

    public void setInitiative_2(String initiative_2) {
        this.initiative_2 = initiative_2;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getExpected_result() {
        return expected_result;
    }

    public void setExpected_result(String expected_result) {
        this.expected_result = expected_result;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getKp1() {
        return kp1;
    }

    public void setKp1(String kp1) {
        this.kp1 = kp1;
    }

    public String getKp2() {
        return kp2;
    }

    public void setKp2(String kp2) {
        this.kp2 = kp2;
    }

    public String getKp1_progress() {
        return kp1_progress;
    }

    public void setKp1_progress(String kp1_progress) {
        this.kp1_progress = kp1_progress;
    }

    public String getKp2_progress() {
        return kp2_progress;
    }

    public void setKp2_progress(String kp2_progress) {
        this.kp2_progress = kp2_progress;
    }

    public String getGoal_ready() {
        return goal_ready;
    }

    public void setGoal_ready(String goal_ready) {
        this.goal_ready = goal_ready;
    }
}
