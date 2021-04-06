package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class LoanBankAccountModel {

    String account_cc,account_cci,account_currency,financial_institution_name;

    public LoanBankAccountModel() {
    }

    public LoanBankAccountModel(String account_cc, String account_cci, String account_currency, String financial_institution_name) {
        this.account_cc = account_cc;
        this.account_cci = account_cci;
        this.account_currency = account_currency;
        this.financial_institution_name = financial_institution_name;
    }

    public String getAccount_cc() {
        return account_cc;
    }

    public void setAccount_cc(String account_cc) {
        this.account_cc = account_cc;
    }

    public String getAccount_cci() {
        return account_cci;
    }

    public void setAccount_cci(String account_cci) {
        this.account_cci = account_cci;
    }

    public String getAccount_currency() {
        return account_currency;
    }

    public void setAccount_currency(String account_currency) {
        this.account_currency = account_currency;
    }

    public String getFinancial_institution_name() {
        return financial_institution_name;
    }

    public void setFinancial_institution_name(String financial_institution_name) {
        this.financial_institution_name = financial_institution_name;
    }
}
