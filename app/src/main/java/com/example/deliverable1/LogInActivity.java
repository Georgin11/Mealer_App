package com.example.deliverable1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }
    public void GoBack(View view) {
        //Creating a Return intent to pass to the Main Activity
        Intent returnIntent = new Intent();
        finish();
    }

    public void NoAccount(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivityForResult(intent, 0);
    }

    public void LogIn(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
        startActivityForResult(intent, 0);
    }
}