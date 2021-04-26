package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

public class FinancialInstitutionModel {

    String financial_institution_background_image,financial_institution_image,financial_institution_name;

    public FinancialInstitutionModel() {
    }

    public FinancialInstitutionModel(String financial_institution_background_image, String financial_institution_image, String financial_institution_name) {
        this.financial_institution_background_image = financial_institution_background_image;
        this.financial_institution_image = financial_institution_image;
        this.financial_institution_name = financial_institution_name;
    }

    public String getFinancial_institution_background_image() {
        return financial_institution_background_image;
    }

    public void setFinancial_institution_background_image(String financial_institution_background_image) {
        this.financial_institution_background_image = financial_institution_background_image;
    }

    public String getFinancial_institution_image() {
        return financial_institution_image;
    }

    public void setFinancial_institution_image(String financial_institution_image) {
        this.financial_institution_image = financial_institution_image;
    }

    public String getFinancial_institution_name() {
        return financial_institution_name;
    }

    public void setFinancial_institution_name(String financial_institution_name) {
        this.financial_institution_name = financial_institution_name;
    }
}
