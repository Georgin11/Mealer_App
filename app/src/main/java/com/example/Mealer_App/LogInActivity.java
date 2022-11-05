package com.example.Mealer_App;

import static com.example.Mealer_App.structure.userType.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.userType;

public class LogInActivity extends AppCompatActivity {

    public static userType typeOfUser = null;

    EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        textUsername = findViewById(R.id.editTextTextPersonName);
        textPassword = findViewById(R.id.editTextTextPassword);
    }

    public void GoBack(View view) {

        finish();
    }

    public void NoAccount(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivityForResult(intent, 0);
    }


    public void LogIn(View View) {

        Database dbHelper = new Database(LogInActivity.this);

        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();
        Intent intent = null;

        if(dbHelper.checkAdminExists(username) && dbHelper.matchPassword(username, password)) {
            typeOfUser = ADMIN;
            intent = new Intent(getApplicationContext(), AdminLandingPage.class);
        }

        if(dbHelper.checkCookExists(username) && dbHelper.matchPassword(username, password)) {
            typeOfUser = COOK;
        }

        if(dbHelper.checkClientExists(username) && dbHelper.matchPassword(username, password)) {
            typeOfUser = CLIENT;
        }


        if(typeOfUser == null) {
            textUsername.setText("");
            textPassword.setText("");
            Toast.makeText(this, "Username and/or password are not valid", Toast.LENGTH_SHORT).show();
            return;
        } else if(typeOfUser != ADMIN){
            intent = new Intent(getApplicationContext(), WelcomePage.class);
        }
        startActivityForResult(intent, 0);
    }
}
