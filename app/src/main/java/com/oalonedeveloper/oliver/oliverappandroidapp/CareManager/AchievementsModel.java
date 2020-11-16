package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

public class AchievementsModel {

    String score,message;

    public AchievementsModel() {
    }

    public AchievementsModel(String score, String message) {
        this.score = score;
        this.message = message;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
