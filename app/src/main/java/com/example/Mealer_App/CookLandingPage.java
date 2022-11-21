package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Complaint;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

import java.util.ArrayList;
import java.util.List;

public class CookLandingPage extends AppCompatActivity {

    // **** CODE MOVED TO CookMeals

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_landing_page);
    }

    public void GoBack1(View view) {
        finish();
    }

    public void onMealsClick(View view){
        Intent intent = new Intent(getApplicationContext(), CookMeals.class);
        startActivity(intent);
    }
    public void onInstagramClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

}
