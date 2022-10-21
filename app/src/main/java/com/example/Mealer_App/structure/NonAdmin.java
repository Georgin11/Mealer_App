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

    public boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        name = name.toLowerCase().trim();
        String regex = "^[a-z,.'-]" //Allows names to contain , . ' and -
                        + "{1,15}$"; // Must be 1 to 15 characters long

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);

        return m.matches();
    }

    public boolean validateEmail(String emailAddress) {
        if (emailAddress == null) {
            return false;
        }

        emailAddress = emailAddress.toLowerCase();
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" // For the part before the '@' symbol, it allows numbers
                                                                          // 0-9, upper/lowercase letters, no more than 64 characters,
                                                                          // periods are not allowed at the start, end or consecutively.
                                                                          // Hyphens and apostraphes are allowed as well
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; //For the domain, numerical values of 0-9 are allowed,
                                                                            // as well as any upper/lowercase letters, and hyphens/dots
                                                                            // are not allowed at the end or start

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(emailAddress);

        return m.matches();
    }




}
