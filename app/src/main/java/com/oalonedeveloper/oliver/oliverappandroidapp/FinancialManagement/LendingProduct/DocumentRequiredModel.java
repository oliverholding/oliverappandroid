package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class DocumentRequiredModel {

    String doc_description,doc_tittle,step;

    public DocumentRequiredModel() {
    }

    public DocumentRequiredModel(String doc_description, String doc_tittle, String step) {
        this.doc_description = doc_description;
        this.doc_tittle = doc_tittle;
        this.step = step;
    }

    public String getDoc_description() {
        return doc_description;
    }

    public void setDoc_description(String doc_description) {
        this.doc_description = doc_description;
    }

    public String getDoc_tittle() {
        return doc_tittle;
    }

    public void setDoc_tittle(String doc_tittle) {
        this.doc_tittle = doc_tittle;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
