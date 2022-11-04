package com.example.Mealer_App.structure;

import com.example.Mealer_App.Database;

public class Complaint {

    private String title;
    private String message;
    private String clientUsername;
    private String cookUsername;
    private int daysSuspended;
    private int rating;

    public Complaint(String title, String message, String clientUsername, String cookUsername, int daysSuspended, int rating) {
        this.title = title;
        this.message = message;
        this.clientUsername = clientUsername;
        this.cookUsername = cookUsername;
        this.daysSuspended = daysSuspended;
        this.rating = rating;

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

    public int getRating() {
        return rating;
    }

    public void setDaysSuspended(int numDays) { daysSuspended = numDays; }

}
