package com.example.Mealer_App.structure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Review {

    private String title;
    private String message;
    private String clientUsername;
    private String cookUsername;
    private int daysSuspended;
    private double rating;
    private int DB_id;

    public Review(String title, String message, String clientUsername, String cookUsername, double rating) {
        this.title = title;
        this.message = message;
        this.clientUsername = clientUsername;
        this.cookUsername = cookUsername;
        this.daysSuspended = -1;

        this.rating = rating;
        BigDecimal bd = BigDecimal.valueOf(this.rating);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.rating = bd.doubleValue();


    }

    public String getTitle() { return  title; }

    public String getMessage() { return message; }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getCookUsername() {
        return cookUsername;
    }

    public int getDaysSuspended() { return daysSuspended; }

    public double getRating() {
        return rating;
    }

    public int getDB_id() { return DB_id; }

    public void setDaysSuspended(int numDays) { daysSuspended = numDays; }

    public void setDB_id(int complaintId) { DB_id = complaintId; }


    public String toString() {
        String text = "";
        text += clientUsername + " left a complaint about " + cookUsername + ".\n\n";
        text += "\"" + title + "\"\n";
        return text;
    }

}
