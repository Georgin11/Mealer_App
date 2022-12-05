package com.example.Mealer_App;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TestClientFunctionality extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    Button returnToList;
    private Meal selectedMeal;
    private List<Meal> finalMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_list_meals);
        ListView lv_meals = findViewById(R.id.lv_Meals);

        Database dbHelper = new Database(this);
        List<Meal> meals = dbHelper.getAllMeals();

        if(meals.isEmpty()) {
            List<Cook> cooks = dbHelper.getCooks();
            Meal meal1 = new Meal("Pizza a la Mac",
                    "Dinner",
                    "Italian",
                    "Cheese, dough, tomato sauce, basil, olive oil, mozzarella",
                    "Dairy",
                    (float) 19.99,
                    "Its pizza. And its just that simple",
                    cooks.get(1));
            Meal meal2 = new Meal("Mac's Mac & Cheese",
                    "Lunch",
                    "American",
                    "Macaroni, cheese",
                    "Dairy",
                    (float) 500.01,
                    "Its mac & cheese. And its just that simple",
                    cooks.get(1));
            Meal meal3 = new Meal("Lamb sauce",
                    "Dinner",
                    "Mediterranean",
                    "None",
                    "Dairy",
                    (float) 0,
                    "Finally found the lamb sauce and im giving it away for free :)",
                    cooks.get(0));
            meal1.setFeatured(true);
            meal2.setFeatured(true);
            meal3.setFeatured(true);
            dbHelper.addOne(meal1);
            dbHelper.addOne(meal2);
            dbHelper.addOne(meal3);
            meals = dbHelper.getAllMeals();
        }
        finalMeals = new ArrayList<>();
        for(Meal meal: meals) {
            if(meal.isFeatured() && meal.getAssociatedCook().getSuspensionLength() == 0) {
                finalMeals.add(meal);
            }
        }
        ArrayAdapter<Meal> mealArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, finalMeals);

        lv_meals.setAdapter(mealArrayAdapter);


        lv_meals.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("MissingInflatedId")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = finalMeals.get(position);
                selectMeal(selectedMeal);
            }
        });
    }

    public void selectMeal(Meal selectedMeal) {
        setContentView(R.layout.dummy_meal_screen);

        TextView mealTitle = findViewById(R.id.txtMealName);
        TextView mealDescription = findViewById(R.id.txtMealDescription);
        TextView mealType = findViewById(R.id.txtMealType);
        TextView mealCuisineType = findViewById(R.id.txtCuisineType);
        TextView mealAllergens = findViewById(R.id.txtAllergens);
        TextView mealPrice = findViewById(R.id.txtMealPrice);
        TextView mealCook = findViewById(R.id.textMealCookLink);

        String cookName = selectedMeal.getAssociatedCook().getfName() + " " + selectedMeal.getAssociatedCook().getlName();
        String allergens = "";
        if(selectedMeal.getListOfAllergens().equals("")) {
            allergens = "Contains: " + selectedMeal.getListOfAllergens();
        }

        String price = "$" + selectedMeal.getMealPrice();
        mealTitle.setText(selectedMeal.getMealName());
        mealDescription.setText(selectedMeal.getMealDescription());
        mealType.setText(selectedMeal.getMealCourse());
        mealCuisineType.setText(selectedMeal.getMealCuisine());
        mealAllergens.setText(allergens);
        mealPrice.setText(price);
        mealCook.setText(cookName);

        mealCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCookProfile(selectedMeal.getAssociatedCook());
            }
        });

    }

    @SuppressLint("MissingInflatedId")
    public void viewCookProfile(Cook cook) {
        setContentView(R.layout.dummy_cook_profile);
        Database db = new Database(this);

        List<Meal> cookMeals = new ArrayList<>();
        for(Meal meal: finalMeals) {
            if(meal.getAssociatedCook().getUsername().equals(cook.getUsername())) {
                cookMeals.add(meal);
            }
        }
        ListView lvMeals = findViewById(R.id.lv_CookMeals);
        TextView cookName = findViewById(R.id.txtCookName);
        TextView rating = findViewById(R.id.txtRating);
        TextView bio = findViewById(R.id.txtCookBio);

        ArrayAdapter<Meal> mealArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cookMeals);

        lvMeals.setAdapter(mealArrayAdapter);

        lvMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("MissingInflatedId")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = cookMeals.get(position);
                selectMeal(selectedMeal);
            }
        });
        String cookRating = String.valueOf(db.getCookRating(cook.getUsername()));
        String fullName = cook.getfName() + " " + cook.getlName();
        cookName.setText(fullName);
        rating.setText(cookRating);
        bio.setText(cook.getBio());


    }

    public void returnToMeals(View view) {
        recreate();
    }

    public void leaveCookProfile(View view) {
        selectMeal(selectedMeal);
    }

    public void backToLandingPage(View view) {
        finish();
    }

}