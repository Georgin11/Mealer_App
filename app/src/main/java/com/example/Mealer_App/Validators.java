package com.example.Mealer_App;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {


    public Validators() {

    }
    public boolean validateUsername(EditText textUsername) {
        String username = textUsername.getText().toString().trim();

        String regex = "^[A-Za-z]\\w{5,29}$"; // "[A-Za-z]" Means that the String must begin with
        // either an uppercase or lowercase character
        // "\w{5,29}" Means that the String must be between
        // 5 and 29 characters excluding the first one

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);

        if (username.isEmpty()) {
            textUsername.setError("Username cannot be empty");
            return false;
        } else if(!m.matches()) {
            textUsername.setError("Username is invalid");
            return false;
        } else {
            textUsername.setError(null);
            return true;
        }
    }

    public boolean validateName(EditText textName) {
        String name = textName.getText().toString().trim().toLowerCase();

        String regex = "^[a-z'-]" //Allows names to contain ' and -
                + "{1,15}$"; // Must be 1 to 15 characters long

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);

        if(name.isEmpty()) {
            textName.setError("Name cannot be empty");
            return false;
        } else if (!m.matches()) {
            textName.setError("Name is invalid");
            return false;
        } else {
            textName.setError(null);
            return true;
        }
    }

    public boolean validateEmail(EditText textEmail) {
        String emailAddress = textEmail.getText().toString().trim();

        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" // For the part before the '@' symbol, it allows numbers
                // 0-9, upper/lowercase letters, no more than 64 characters,
                // periods are not allowed at the start, end or consecutively.
                // Hyphens and apostrophes are allowed as well
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; //For the domain, numerical values of 0-9 are allowed,
        // as well as any upper/lowercase letters, and hyphens/dots
        // are not allowed at the end or start

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(emailAddress);
        if (emailAddress.isEmpty()) {
            textEmail.setError("Email cannot be empty");
            return false;
        } else if(!m.matches()) {
            textEmail.setError("Email is invalid");
            return false;
        } else {
            textEmail.setError(null);
            return true;
        }
    }

    public boolean validatePassword(EditText textPassword) {
        String password = textPassword.getText().toString().trim();

        String regex = "^(?=.*[0-9])" //Means that a digit should appear at least once in the String
                + "(?=.*[a-z])(?=.*[A-Z])" //Means that both a lower case and an upper case letter should appear
                + "(?=.*[@#$%^&+=])" //Means a special character should appear at least once
                + "(?=\\S+$).{8,20}$"; // "(?=\S+$)" Means that no white spaces can occur in the String
        // "{8,20}" Means that the String should be at least 8
        //characters long and at most 20 characters long
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        if (password.isEmpty()) {
            textPassword.setError("Password cannot be empty");
            return false;
        } else if(!m.matches()) {
            textPassword.setError("Password is invalid");
            return false;
        } else {
            textPassword.setError(null);
            return true;
        }
    }
}
