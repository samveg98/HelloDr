package com.example.hellodr;

public class Appointment {

    String dfname, dlname, date, slot, location;

    public Appointment(){}

    public String getDfname() {
        return dfname;
    }

    public void setDfname(String dfname) {
        this.dfname = dfname;
    }

    public String getDlname() {
        return dlname;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Appointment(String dfname, String dlname, String date, String slot, String location) {
        this.dfname = dfname;
        this.dlname = dlname;
        this.date = date;
        this.slot = slot;
        this.location = location;
    }
}
