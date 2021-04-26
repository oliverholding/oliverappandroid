package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

public class FunctionJobModel {

    String function_description,function_score;

    public FunctionJobModel() {
    }

    public FunctionJobModel(String function_description, String function_score) {
        this.function_description = function_description;
        this.function_score = function_score;
    }

    public String getFunction_description() {
        return function_description;
    }

    public void setFunction_description(String function_description) {
        this.function_description = function_description;
    }

    public String getFunction_score() {
        return function_score;
    }

    public void setFunction_score(String function_score) {
        this.function_score = function_score;
    }
}
