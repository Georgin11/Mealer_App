package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.typeOfUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Mealer_App.structure.userType;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);
        TextView type = (TextView) findViewById(R.id.userSpecification);
        if(typeOfUser == userType.CLIENT) {
            type.setText("Client");
        } else if (typeOfUser == userType.ADMIN) {
            type.setText("Admin");
        } else if (typeOfUser == userType.COOK) {
            type.setText("Cook");
        }
    }
    public void LogOut(View View) {
        //Application Context and Activity
        typeOfUser = null;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}