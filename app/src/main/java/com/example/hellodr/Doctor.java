package com.example.hellodr;

public class Doctor {

    String FirstName, LastName,speciality,experience,region;

    public Doctor(){}

    public Doctor(String firstName, String lastName, String speciality, String experience, String region) {
        FirstName = firstName;
        LastName = lastName;
        this.speciality = speciality;
        this.experience = experience;
        this.region = region;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
