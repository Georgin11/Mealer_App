package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Mealer_App.structure.Complaint;


public class MainActivity extends AppCompatActivity {

    public Complaint complaint1 = new Complaint("Worst Meal I've had in a long time",
            "I found tons of hair in my ravioli and it is completely unacceptable. The chef simply called me a moron",
            "fuadthabet",
            "gordonramsay1",
            2);

    public Complaint complaint2 = new Complaint("I could not recommend this any less",
            "We received our food cold and raw. The chef accused us of lying for a free meal",
            "fuadthabet",
            "gordonramsay1",
            1);

    public Complaint complaint3 = new Complaint("Nothing to complain about here!",
            "I loved the food and this complaint is just to test dismissing complaints",
            "fuadthabet",
            "gordonramsay1",
            4);

    public Complaint[] complaints = new Complaint[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        complaints[0] = complaint1;
        complaints[1] = complaint2;
        complaints[2] = complaint3;
        //Database db = new Database(MainActivity.this);
        //boolean success = db.presetComplaints(MainActivity.this);
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