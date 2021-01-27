package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

public class ObjectiveModel {

    String objective,objective_destination,objective_frequency,objective_priority,objective_type;

    public ObjectiveModel() {
    }

    public ObjectiveModel(String objective, String objective_destination, String objective_frequency, String objective_priority, String objective_type) {
        this.objective = objective;
        this.objective_destination = objective_destination;
        this.objective_frequency = objective_frequency;
        this.objective_priority = objective_priority;
        this.objective_type = objective_type;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getObjective_destination() {
        return objective_destination;
    }

    public void setObjective_destination(String objective_destination) {
        this.objective_destination = objective_destination;
    }

    public String getObjective_frequency() {
        return objective_frequency;
    }

    public void setObjective_frequency(String objective_frequency) {
        this.objective_frequency = objective_frequency;
    }

    public String getObjective_priority() {
        return objective_priority;
    }

    public void setObjective_priority(String objective_priority) {
        this.objective_priority = objective_priority;
    }

    public String getObjective_type() {
        return objective_type;
    }

    public void setObjective_type(String objective_type) {
        this.objective_type = objective_type;
    }
}
