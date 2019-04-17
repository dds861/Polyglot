package com.english.learnfast.Models;

public class LessonMain {

    private String number;
    private String description;
    private double rate;

    public LessonMain(String number, String description, double rate) {
        this.number = number;
        this.description = description;
        this.rate = rate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
