package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

public class CompaniesModel {

    String company_name,company_ruc,company_verification,company_image;

    public CompaniesModel() {
    }

    public CompaniesModel(String company_name, String company_ruc, String company_verification, String company_image) {
        this.company_name = company_name;
        this.company_ruc = company_ruc;
        this.company_verification = company_verification;
        this.company_image = company_image;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_ruc() {
        return company_ruc;
    }

    public void setCompany_ruc(String company_ruc) {
        this.company_ruc = company_ruc;
    }

    public String getCompany_verification() {
        return company_verification;
    }

    public void setCompany_verification(String company_verification) {
        this.company_verification = company_verification;
    }

    public String getCompany_image() {
        return company_image;
    }

    public void setCompany_image(String company_image) {
        this.company_image = company_image;
    }
}
