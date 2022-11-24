package com.example.Mealer_App;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class AddMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Spinner mySpinner = (Spinner) findViewById(R.id.MealTypeSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddMeal.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Typeofmeals));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Spinner mySpinner2 = (Spinner) findViewById(R.id.CuisineSpinner);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(AddMeal.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Cuisines));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

    }
    public void GoBack1(View view) {
        finish();
    }



}
