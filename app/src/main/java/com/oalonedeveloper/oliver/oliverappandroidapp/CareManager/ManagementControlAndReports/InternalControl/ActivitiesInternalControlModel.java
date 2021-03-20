package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.InternalControl;

public class ActivitiesInternalControlModel {

    String activity_name,text_date,time_start,time_end,date_timestamp;

    public ActivitiesInternalControlModel() {
    }

    public ActivitiesInternalControlModel(String activity_name, String text_date, String time_start, String time_end, String date_timestamp) {
        this.activity_name = activity_name;
        this.text_date = text_date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.date_timestamp = date_timestamp;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getText_date() {
        return text_date;
    }

    public void setText_date(String text_date) {
        this.text_date = text_date;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getDate_timestamp() {
        return date_timestamp;
    }

    public void setDate_timestamp(String date_timestamp) {
        this.date_timestamp = date_timestamp;
    }
}
