package com.example.Mealer_App.structure;


public class Cook extends NonAdmin {

    private String bio;
    private boolean voidCheque;
    private int suspensionLength;
    private double rating;

    public Cook(String bio, boolean voidCheque, String fName, String lName, String email, Address address, String username, String password) {
        super(fName, lName, email, address, username, password);
        this.bio = bio;
        this.voidCheque = voidCheque;
        suspensionLength = 0;

    }

    public String getBio() {
        return bio;
    }

    public boolean hasVoidCheque() {
        return voidCheque;
    }

    public void setSuspension(int numDays) {
        suspensionLength = numDays;
    }

    public int getSuspensionLength() {
        return suspensionLength;
    }

    public double getRating() {
        return rating;
    }

    public boolean equals(Cook c) {
        return (username.equals(c.username));
    }

    public void setRating(double newRating) {
        rating = newRating;
    }
}
