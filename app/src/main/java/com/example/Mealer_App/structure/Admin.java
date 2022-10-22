package com.example.Mealer_App.structure;

import static com.example.Mealer_App.structure.userType.ADMIN;

public class Admin extends User {

    public userType typeOfUser = ADMIN;
    public Admin(String username, String password) {
        super(username, password);
    }

}
