package com.example.Mealer_App.structure;

public class Cook extends NonAdmin{

    private String bio;
    private boolean voidCheque;

    public Cook(String bio, boolean voidCheque, String fName, String lName, String email, Address address, String username, String password) {
        super(fName, lName, email, address, username, password);
        this.bio = bio;
        this.voidCheque = voidCheque;

    }

    public String getBio() {
        return bio;
    }

    public boolean isVoidCheque() {
        return voidCheque;
    }
}
