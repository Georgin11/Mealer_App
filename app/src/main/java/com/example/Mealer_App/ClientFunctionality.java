package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.loggedInUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.R.id;
import com.example.Mealer_App.R.layout;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;
import com.example.Mealer_App.structure.Purchase;

import java.util.ArrayList;
import java.util.List;

public class ClientFunctionality extends AppCompatActivity {

    private Meal selectedMeal;
    private List<Meal> finalMeals;

    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list_meals);
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
                    19.99,
                    "Its pizza. And its just that simple",
                    cooks.get(1));
            Meal meal2 = new Meal("Mac's Mac & Cheese",
                    "Lunch",
                    "American",
                    "Macaroni, cheese",
                    "Dairy",
                    500.01,
                    "Its mac & cheese. And its just that simple",
                    cooks.get(1));
            Meal meal3 = new Meal("Lamb sauce",
                    "Dinner",
                    "Mediterranean",
                    "None",
                    "Dairy",
                    0,
                    "Finally found the lamb sauce and im giving it away for free :)",
                    cooks.get(0));
            meal1.setDB_id(1);
            meal2.setDB_id(2);
            meal3.setDB_id(3);

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
                selectMeal();
            }
        });

        // ****
        SearchView searchView = findViewById(id.searchBar);
        ListView listView = findViewById(id.lv_Meals);

        ArrayList<Meal> arrayList = new ArrayList<>(finalMeals);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void selectMeal() {
        setContentView(layout.activity_meal_screen);

        Button placeOrder = findViewById(id.btnOrder);
        TextView mealTitle = findViewById(id.txtMealName);
        TextView mealDescription = findViewById(id.txtMealDescription);
        TextView mealType = findViewById(id.txtMealType);
        TextView mealCuisineType = findViewById(id.txtCuisineType);
        TextView mealAllergens = findViewById(id.txtAllergens);
        TextView mealPrice = findViewById(id.txtMealPrice);
        TextView mealCook = findViewById(id.textMealCookLink);


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

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });



    }

    public void placeOrder() {
        setContentView(layout.activity_place_order);


        Button checkout = findViewById(id.btnCheckout);
        Button backToMeal = findViewById(id.btnGoBackToMeal);
        EditText quantity = findViewById(id.txt_quantity);
        TextView title = findViewById(id.txt_title);

        String s = "Order of: " + selectedMeal.getMealName();
        title.setText(s);

        backToMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMeal();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validators validate = new Validators();
                if(!validate.validateOrderQuantity(quantity)) {
                    return;
                }
                int amount = Integer.parseInt(quantity.getText().toString().trim());
                goToCheckout(amount);
            }
        });
    }

    public void goToCheckout(int quantity) {
        setContentView(layout.activity_checkout);
        Database db = new Database(this);
        Button backToCart = findViewById(id.btnBackToCart);
        Button completePurchase = findViewById(id.btnCompletePurchase);
        TextView orderInfo = findViewById(id.txtPurchaseRecap);

        String confirmationText = "Are you sure you would like to purchase " +
                quantity + " orders of " +
                selectedMeal.getAssociatedCook().getUsername() + "'s " +
                selectedMeal.getMealName() + "?";

        orderInfo.setText(confirmationText);

        backToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

        completePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Purchase newPurchase = new Purchase(selectedMeal.getAssociatedCook().getUsername(),
                        loggedInUser.getUsername(),
                        selectedMeal.getMealName(),
                        quantity,
                        selectedMeal.getMealPrice());
                boolean success = db.addOne(newPurchase);
                if(!success) {
                    Toast.makeText(ClientFunctionality.this, "error making purchase request", Toast.LENGTH_SHORT).show();
                }
                orderConfirmation();
            }
        });
    }

    public void orderConfirmation() {
        setContentView(layout.activity_order_confirmation);

        Button homePage = findViewById(id.btnConfirmationExit);

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientLandingPage.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    public void viewCookProfile(Cook cook) {
        setContentView(layout.activity_cook_profile);
        Database db = new Database(this);

        List<Meal> cookMeals = new ArrayList<>();
        for(Meal meal: finalMeals) {
            if(meal.getAssociatedCook().getUsername().equals(cook.getUsername())) {
                cookMeals.add(meal);
            }
        }
        ListView lvMeals = findViewById(id.lv_CookMeals);
        TextView cookName = findViewById(id.txtCookName);
        TextView rating = findViewById(id.txtRating);
        TextView bio = findViewById(id.txtCookBio);

        ArrayAdapter<Meal> mealArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cookMeals);

        lvMeals.setAdapter(mealArrayAdapter);

        lvMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("MissingInflatedId")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = cookMeals.get(position);
                selectMeal();
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
        selectMeal();
    }

    public void backToLandingPage(View view) {
        finish();
    }

}