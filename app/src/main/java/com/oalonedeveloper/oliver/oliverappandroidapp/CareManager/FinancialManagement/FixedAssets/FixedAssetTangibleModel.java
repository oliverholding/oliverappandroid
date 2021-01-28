package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets;

public class FixedAssetTangibleModel {

    String asset_depreciation_rate,asset_name,asset_price,asset_purchased_day,asset_purchased_month,asset_purchased_year,asset_quantity,asset_type;

    public FixedAssetTangibleModel() {
    }

    public FixedAssetTangibleModel(String asset_depreciation_rate, String asset_name, String asset_price, String asset_purchased_day, String asset_purchased_month, String asset_purchased_year, String asset_quantity, String asset_type) {
        this.asset_depreciation_rate = asset_depreciation_rate;
        this.asset_name = asset_name;
        this.asset_price = asset_price;
        this.asset_purchased_day = asset_purchased_day;
        this.asset_purchased_month = asset_purchased_month;
        this.asset_purchased_year = asset_purchased_year;
        this.asset_quantity = asset_quantity;
        this.asset_type = asset_type;
    }

    public String getAsset_depreciation_rate() {
        return asset_depreciation_rate;
    }

    public void setAsset_depreciation_rate(String asset_depreciation_rate) {
        this.asset_depreciation_rate = asset_depreciation_rate;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_price() {
        return asset_price;
    }

    public void setAsset_price(String asset_price) {
        this.asset_price = asset_price;
    }

    public String getAsset_purchased_day() {
        return asset_purchased_day;
    }

    public void setAsset_purchased_day(String asset_purchased_day) {
        this.asset_purchased_day = asset_purchased_day;
    }

    public String getAsset_purchased_month() {
        return asset_purchased_month;
    }

    public void setAsset_purchased_month(String asset_purchased_month) {
        this.asset_purchased_month = asset_purchased_month;
    }

    public String getAsset_purchased_year() {
        return asset_purchased_year;
    }

    public void setAsset_purchased_year(String asset_purchased_year) {
        this.asset_purchased_year = asset_purchased_year;
    }

    public String getAsset_quantity() {
        return asset_quantity;
    }

    public void setAsset_quantity(String asset_quantity) {
        this.asset_quantity = asset_quantity;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }
}
