package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class DocumentRequiredModel {

    String doc_description,doc_title,step;

    public DocumentRequiredModel() {
    }

    public DocumentRequiredModel(String doc_description, String doc_tittle, String step) {
        this.doc_description = doc_description;
        this.doc_title = doc_tittle;
        this.step = step;
    }

    public String getDoc_description() {
        return doc_description;
    }

    public void setDoc_description(String doc_description) {
        this.doc_description = doc_description;
    }

    public String getDoc_title() {
        return doc_title;
    }

    public void setDoc_title(String doc_title) {
        this.doc_title = doc_title;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
