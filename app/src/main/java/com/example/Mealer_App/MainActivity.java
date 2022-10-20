package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Mealer_App.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnSignUp(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivityForResult(intent, 0);
    }

    public void OnLogIn(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivityForResult(intent, 0);
    }
}