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

        String regex = "^[a-zA-Z0-9_ ]*$"; //Allows names to contain ' and -. Must be 1 to 15 characters long

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
        // "{8,20}" Means that the String should be at least 8 characters long and at most 20 characters long
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        if (password.isEmpty()) {
            textPassword.setError("Password cannot be empty");
            return false;
        } else if(!m.matches()) {
            textPassword.setError("Password must include at least:" +
                                    "\n- 1 digit" +
                                    "\n- 1 lowercase and uppercase letter" +
                                    "\n- 1 special character" +
                                    "\n- no white spaces" +
                                    "\n- 8-20 characters long");
            return false;
        } else {
            textPassword.setError(null);
            return true;
        }
    }

    public boolean validateNumber(EditText textStreetNum) {
        String input = textStreetNum.getText().toString();
        if(input.isEmpty()) {
            textStreetNum.setError("Number cannot be empty");
            return false;
        }
        int num = Integer.parseInt(input);
        if(num <= 0) {
            textStreetNum.setError("Number Invalid");
            return false;
        }

        textStreetNum.setError(null);
        return true;
    }

    public boolean validateCVV(EditText textCVV) {
        String input = textCVV.getText().toString();
        if(input.isEmpty()) {
            textCVV.setError("Number cannot be empty");
            return false;
        }
        int cvv = Integer.parseInt(input);
        if(cvv > 999 || cvv < 100) {
            textCVV.setError("Must be 3 digits");
            return false;
        }

        textCVV.setError(null);
        return true;
    }

    public boolean validateCardNumber(EditText textCardNumber) {
        char[] input = textCardNumber.getText().toString().toCharArray();
        if(input.length == 0) {
            textCardNumber.setError("Number cannot be empty");
            return false;
        } else if(input.length > 16 || input.length < 15) {
            textCardNumber.setError("Card Number must be 15-16 digits");
            return false;
        }

        textCardNumber.setError(null);
        return true;
    }

    public boolean validatePostal(EditText textPostal) {
        String postal = textPostal.getText().toString().trim().toUpperCase();

        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(postal);

        if(postal.isEmpty()) {
            textPostal.setError("Postal code cannot be empty");
            return false;
        } else if (!m.matches()) {
            textPostal.setError("Postal code is invalid");
            return false;
        } else {
            textPostal.setError(null);
            return true;
        }
    }

    public boolean validateBio(EditText textBio) {
        String bio = textBio.getText().toString().trim();

        if(bio.isEmpty()) {
            textBio.setError("Bio cannot be empty");
            return false;
        } else {
            textBio.setError(null);
            return true;
        }
    }

}
