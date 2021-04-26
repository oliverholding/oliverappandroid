package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

public class MySubjectsModel {

    String subject_id;
    int progress;

    public MySubjectsModel() {
    }

    public MySubjectsModel(String subject_id, int progress) {
        this.subject_id = subject_id;
        this.progress = progress;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
