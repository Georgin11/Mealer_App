package com.example.Mealer_App.structure;

import static com.example.Mealer_App.structure.UserType.ADMIN;

public class Admin extends User {

    public UserType typeOfUser = ADMIN;
    public Admin(String username, String password) {
        super(username, password);
    }

}
