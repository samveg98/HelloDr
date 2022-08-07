package com.example.hellodr;

public class Reminder {

    String days,medName,time;

    public Reminder(){}

    public Reminder(String medName, String days, String time) {
        this.medName = medName;
        this.days = days;
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
