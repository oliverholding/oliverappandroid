package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class FileUploadedModel {

    String url, file_name,doc_tittle;

    public FileUploadedModel() {
    }

    public FileUploadedModel(String url, String file_name, String doc_title) {
        this.url = url;
        this.file_name = file_name;
        this.doc_tittle = doc_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getDoc_tittle() {
        return doc_tittle;
    }

    public void setDoc_tittle(String doc_tittle) {
        this.doc_tittle = doc_tittle;
    }
}
