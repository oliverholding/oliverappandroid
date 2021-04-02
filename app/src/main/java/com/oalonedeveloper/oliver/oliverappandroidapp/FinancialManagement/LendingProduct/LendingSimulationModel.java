package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

public class LendingSimulationModel {

    String quote_number,expiration_date, balance, amortization, interest, desgravamen_insurance,fixed_fee, quote_payment;

    public LendingSimulationModel() {
    }

    public LendingSimulationModel(String quote_number, String expiration_date, String balance, String amortization, String interest, String desgravamen_insurance, String fixed_fee, String quote_payment) {
        this.quote_number = quote_number;
        this.expiration_date = expiration_date;
        this.balance = balance;
        this.amortization = amortization;
        this.interest = interest;
        this.desgravamen_insurance = desgravamen_insurance;
        this.fixed_fee = fixed_fee;
        this.quote_payment = quote_payment;
    }

    public String getQuote_number() {
        return quote_number;
    }

    public void setQuote_number(String quote_number) {
        this.quote_number = quote_number;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAmortization() {
        return amortization;
    }

    public void setAmortization(String amortization) {
        this.amortization = amortization;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getDesgravamen_insurance() {
        return desgravamen_insurance;
    }

    public void setDesgravamen_insurance(String desgravamen_insurance) {
        this.desgravamen_insurance = desgravamen_insurance;
    }

    public String getFixed_fee() {
        return fixed_fee;
    }

    public void setFixed_fee(String fixed_fee) {
        this.fixed_fee = fixed_fee;
    }

    public String getQuote_payment() {
        return quote_payment;
    }

    public void setQuote_payment(String quote_payment) {
        this.quote_payment = quote_payment;
    }
}
