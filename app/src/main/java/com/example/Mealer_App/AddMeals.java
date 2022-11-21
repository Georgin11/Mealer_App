package com.example.Mealer_App;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class AddMeals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal);

        Spinner spinnerMealType = (Spinner) findViewById(R.id.MealTypeSpinner);
        ArrayAdapter<String> mealTypeAdapter = new ArrayAdapter<>(AddMeals.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Typeofmeals));
        mealTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeAdapter);

        Spinner spinnerCuisineType = (Spinner) findViewById(R.id.CuisineSpinner);
        ArrayAdapter<String> cuisineTypeAdapter = new ArrayAdapter<>(AddMeals.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Cuisines));
        cuisineTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCuisineType.setAdapter(cuisineTypeAdapter);

    }
    public void GoBack1(View view) {
        finish();
    }



}
