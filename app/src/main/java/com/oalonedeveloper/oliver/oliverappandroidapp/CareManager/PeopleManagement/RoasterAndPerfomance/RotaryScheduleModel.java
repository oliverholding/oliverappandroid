package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

public class RotaryScheduleModel {

    String text_date,time_end,time_start,date;

    public RotaryScheduleModel() {
    }

    public RotaryScheduleModel(String text_date, String time_end, String time_start, String date) {
        this.text_date = text_date;
        this.time_end = time_end;
        this.time_start = time_start;
        this.date = date;
    }

    public String getText_date() {
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
