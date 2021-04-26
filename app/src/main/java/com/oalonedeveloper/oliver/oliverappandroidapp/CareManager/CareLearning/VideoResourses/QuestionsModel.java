package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

public class QuestionsModel {

    String question,uid;

    public QuestionsModel() {
    }

    public QuestionsModel(String question, String uid) {
        this.question = question;
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
