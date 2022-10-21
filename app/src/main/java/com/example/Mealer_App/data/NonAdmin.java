package com.example.Mealer_App.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonAdmin {
    protected String fName;
    protected String lName;
    protected String address;

    public String getfName() {
        return this.fName;
    }

    public String getlName() {
        return this.lName;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean validatefName(String fName) {
        fName = fName.toLowerCase();
        String regex = "^[a-z,.'-]" //Allows names to contain , . ' and -
                        + "{1,15}$"; // Must be 1 to 15 characters long

        Pattern p = Pattern.compile(regex);

        if (fName == null) {
            return false;
        }

        Matcher m = p.matcher(fName);

        return m.matches();
    }


}
