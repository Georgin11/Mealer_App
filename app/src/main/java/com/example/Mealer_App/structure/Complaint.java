package com.example.Mealer_App.structure;

public class Complaint {

    private String message;
    private String clientUsername;
    private String cookUsername;
    private int rating;

    public Complaint(String message, String clientUsername, String cookUsername, int rating) {
        this.message = message;
        this.clientUsername = clientUsername;
        this.cookUsername = cookUsername;
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getCookUsername() {
        return cookUsername;
    }

    public int getRating() {
        return rating;
    }
}
