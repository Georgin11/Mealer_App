package com.example.Mealer_App;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {


    EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        textUsername = findViewById(R.id.editTextTextPersonName);
        textPassword = findViewById(R.id.editTextTextPassword);

        textUsername.setText("");
        textPassword.setText("");
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
            intent = new Intent(getApplicationContext(), AdminLandingPage.class);
            startActivityForResult(intent, 0);
        } else if(dbHelper.checkCookExists(username) && dbHelper.matchPassword(username, password)) {
            intent = new Intent(getApplicationContext(), CookLandingPage.class);
            startActivityForResult(intent, 0);
        } else if(dbHelper.checkClientExists(username) && dbHelper.matchPassword(username, password)) {
            intent = new Intent(getApplicationContext(), ClientLandingPage.class);
            startActivityForResult(intent, 0);
        } else {
            textUsername.setText("");
            textPassword.setText("");
            Toast.makeText(this, "Username and/or password are not valid", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
