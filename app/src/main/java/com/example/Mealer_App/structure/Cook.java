package com.example.Mealer_App.structure;

import static com.example.Mealer_App.structure.UserType.COOK;

public class Cook extends NonAdmin{

    private String bio;
    private boolean voidCheque;
    private int suspensionLength;

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
}
