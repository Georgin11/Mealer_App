package com.example.Mealer_App.structure;

public class Review {

    private String title;
    private String message;
    private String clientUsername;
    private String cookUsername;
    private int daysSuspended;
    private int rating;
    private int DB_id;

    public Review(String title, String message, String clientUsername, String cookUsername, int rating) {
        this.title = title;
        this.message = message;
        this.clientUsername = clientUsername;
        this.cookUsername = cookUsername;
        this.daysSuspended = -1;
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
