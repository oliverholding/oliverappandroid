package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.FODA;

public class FodaModel {

    String item_name,item_score,item_weight,item_weighted_value,item_type;

    public FodaModel() {
    }

    public FodaModel(String item_name, String item_score, String item_weight, String item_weighted_value, String item_type) {
        this.item_name = item_name;
        this.item_score = item_score;
        this.item_weight = item_weight;
        this.item_weighted_value = item_weighted_value;
        this.item_type = item_type;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_score() {
        return item_score;
    }

    public void setItem_score(String item_score) {
        this.item_score = item_score;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }

    public String getItem_weighted_value() {
        return item_weighted_value;
    }

    public void setItem_weighted_value(String item_weighted_value) {
        this.item_weighted_value = item_weighted_value;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
}
