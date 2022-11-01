package com.example.Mealer_App.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonAdmin extends User{
    protected String fName;
    protected String lName;
    protected String email;
    protected Address address;

    public NonAdmin(String fName, String lName, String email, Address address, String username, String password) {
        super(username, password);
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.address = address;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() { return email; }

    public Address getAddress() { return address; }






}
