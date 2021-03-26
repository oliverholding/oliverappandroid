package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

public class SubjectModel {

    String position,subject_category,subject_image,subject_name;

    public SubjectModel() {
    }

    public SubjectModel(String position, String subject_category, String subject_image, String subject_name) {
        this.position = position;
        this.subject_category = subject_category;
        this.subject_image = subject_image;
        this.subject_name = subject_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSubject_category() {
        return subject_category;
    }

    public void setSubject_category(String subject_category) {
        this.subject_category = subject_category;
    }

    public String getSubject_image() {
        return subject_image;
    }

    public void setSubject_image(String subject_image) {
        this.subject_image = subject_image;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
