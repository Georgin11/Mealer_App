package com.example.Mealer_App.data;

import java.util.regex.*;

public abstract class User {
    protected String username;
    protected String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }


    public boolean validatePassword(String password) {

        String regex = "^(?=.*[0-9])" //Means that a digit should appear at least once in the String
                + "(?=.*[a-z])(?=.*[A-Z])" //Means that both a lower case and an upper case letter should appear
                + "(?=.*[@#$%^&+=])" //Means a special character should appear at least once
                + "(?=\\S+$).{8,20}$"; // "(?=\S+$)" Means that no white spaces can occur in the String
                                       // "{8,20}" Means that the String should be at least 8
                                       //characters long and at most 20 characters long
        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();
    }

    public boolean validateUsername(String username) {
        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{5,29}$"; // "[A-Za-z]" Means that the String must begin with
                                              // either an uppercase or lowercase character
                                              // "\w{5,29}" Means that the String must be between
                                              // 5 and 29 characters excluding the first one

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        if (username == null) {
            return false;
        }

        //Pattern.matcher() method checks for matching between passed in String and the compiled ReGex
        Matcher m = p.matcher(username);

        return m.matches();
    }

}
