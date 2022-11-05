package com.example.Mealer_App;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Complaint;

public class AdminLandingPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);
        Database db = new Database(this);

    }

    public void logOut(View view) {
        finish();
    }
}
