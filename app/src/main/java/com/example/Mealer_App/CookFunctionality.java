package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

import java.util.List;

public class CookFunctionality extends AppCompatActivity {

    private TextView textSuspensionLength, textTitle;
    private ListView lv_Menu;
    private Meal selectedMeal;
    public static String mealType, cuisineType;
    public Cook currentCook;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_landing_page);

        Database db = new Database(this);



        List<Cook> cooks = db.getCooks();
        currentCook = null;
        for(Cook cook : cooks) {
            if(username.equals(cook.getUsername())) {
                currentCook = cook;
            }
        }

        if(currentCook.getSuspensionLength() != 0) {
            setContentView(R.layout.activity_cook_suspension);

            String suspensionLength = "";
            String suspensionTitle = "";
            if(currentCook.getSuspensionLength() == -5) {
                suspensionTitle = "You have been permanently banned by our moderation team";
                suspensionLength = "The only available option for you at the moment is to log out and contact our team for any help you might need";
                textTitle = findViewById(R.id.text_suspensionTitle);
                textTitle.setText(suspensionTitle);
            } else {
                suspensionLength = "You have been suspended for the next " + currentCook.getSuspensionLength() + " days";
            }

            textSuspensionLength = findViewById(R.id.text_suspensionLength);
            textSuspensionLength.setText(suspensionLength);

        }

    }

    public void LogOut(View view) {
        finish();
    }

    public void ViewMeals(View view) {
        setContentView(R.layout.activity_cook_meals);
        lv_Menu = findViewById(R.id.list_Menu);
        Database db = new Database(this);
        List<Meal> menuList = db.getMealsOfCook(username);

        Button landingPage = findViewById(R.id.button6);

        ArrayAdapter<ClientReview> complaintArrayAdapter = new ArrayAdapter(this,
            android.R.layout.simple_list_item_1, menuList);

        lv_Menu.setAdapter(complaintArrayAdapter);

        landingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        lv_Menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("MissingInflatedId")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = menuList.get(position);
                mealPopup();
            }
        });
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void AddMeal(View View) {
        setContentView(R.layout.activity_add_meal);
        Database db = new Database(this);
        Button goBack = findViewById(R.id.button);
        Button addMeal = findViewById(R.id.btnAddMeal);
        Switch featured = findViewById(R.id.FeaturedMealCheck);

        Spinner mealTypeSpinner = findViewById(R.id.MealTypeSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Typeofmeals));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealTypeSpinner.setAdapter(myAdapter);
        mealTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                mealType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        Spinner cuisineTypeSpinner = findViewById(R.id.CuisineSpinner);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Cuisines));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuisineTypeSpinner.setAdapter(myAdapter2);
        cuisineTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                cuisineType = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        EditText mealName = findViewById(R.id.editText_MealName);
        EditText mealDescription = findViewById(R.id.MealDescription);
        EditText mealPrice = findViewById(R.id.editTextNumberDecimal);
        EditText mealAllergens = findViewById(R.id.editTextAllergens);
        EditText mealIngredients = findViewById(R.id.editTextIngredients);

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Validators val = new Validators();
                if(!val.validateMealInfo(mealName) ||
                        !val.validateMealInfo(mealDescription) ||
                        !val.validateMealPrice(mealPrice) ||
                        !val.validateMealInfo(mealIngredients)) {
                    return;
                }
                String name = mealName.getText().toString().trim();
                String description = mealDescription.getText().toString().trim();
                double price = Double.parseDouble(mealPrice.getText().toString().trim());
                String ingredients = mealIngredients.getText().toString().trim();
                String allergens = mealAllergens.getText().toString().trim();

                Meal newMeal = new Meal(name, cuisineType, mealType, ingredients, allergens, price, description, currentCook);
                if(featured.isChecked()) {
                    newMeal.setFeatured(true);
                }
                db.addOne(newMeal);
                ViewMeals(v);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                ViewMeals(v);
            }
        });

    }

    public void mealPopup() {
        setContentView(R.layout.dummy_meal_info);
        Database db = new Database(this);

        Button goBack = findViewById(R.id.btnReturn);
        Button update = findViewById(R.id.btnUpdateMeal);
        Button remove = findViewById(R.id.btnRemoveMeal);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch featured = findViewById(R.id.switchIsFeatured);
        featured.setChecked(selectedMeal.isFeatured());

        TextView name, description, cook, price, allergens;
        name = findViewById(R.id.infoMealName);
        description = findViewById(R.id.infoMealDescription);
        cook = findViewById(R.id.infoMealCookLink);
        price = findViewById(R.id.infoMealPrice);
        allergens = findViewById(R.id.infoAllergens);

        name.setText(selectedMeal.getMealName());
        description.setText(selectedMeal.getMealDescription());
        cook.setText(selectedMeal.getAssociatedCook().getUsername());
        price.setText(String.valueOf(selectedMeal.getMealPrice()));
        allergens.setText(selectedMeal.getListOfAllergens());



        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMeals(v);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = featured.isChecked();
                if(checked != selectedMeal.isFeatured()) {
                    selectedMeal.setFeatured(checked);
                    db.featureMeal(selectedMeal, checked);
                    selectedMeal.setFeatured(checked);
                    ViewMeals(v);
                } else {
                    Toast.makeText(CookFunctionality.this, "Cannot update if no change was made", Toast.LENGTH_SHORT).show();
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = featured.isChecked();
                if(checked) {
                    remove.setError("");
                    Toast.makeText(CookFunctionality.this, "Featured meals cannot be deleted", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean success = db.deleteMeal(selectedMeal);
                if(!success) {
                    Toast.makeText(CookFunctionality.this, "Could not delete meal", Toast.LENGTH_SHORT).show();
                }
                selectedMeal = null;
                ViewMeals(v);
            }
        });

    }

}
