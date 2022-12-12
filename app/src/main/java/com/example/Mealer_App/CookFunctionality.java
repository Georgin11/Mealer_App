package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;
import com.example.Mealer_App.structure.Purchase;
import com.example.Mealer_App.structure.Review;

import java.util.ArrayList;
import java.util.List;

public class CookFunctionality extends AppCompatActivity {

    private TextView textSuspensionLength, textTitle;
    private ListView lv_Menu;
    private Meal selectedMeal;
    public static String mealType, cuisineType;
    public Cook currentCook;
    public Purchase selectedPurchase;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_landing_page);

        ImageView profileViewer = findViewById(R.id.settings_icon2);

        Database db = new Database(this);

        ImageView mail = findViewById(R.id.mail_icon2);

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

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCookMessages();
            }
        });

        profileViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewProfile();
            }
        });

    }

    public void viewProfile() {
        setContentView(R.layout.activity_cook_personal_profile);
        RatingBar ratingCook = findViewById(R.id.ratingCook);
        TextView username, fullName, numSales;
        Database db = new Database(this);

        List<Purchase> sales = db.getSales(currentCook.getUsername());
        int numCompletedSales = 0;
        for(Purchase sale: sales) {
            if(sale.getStatus() == 1) {
                numCompletedSales++;
            }
        }

        username = findViewById(R.id.txtUsername);
        fullName = findViewById(R.id.txtFullName);
        numSales = findViewById(R.id.txtAmtOfSales);

        String usernameText = "Profile of: " + currentCook.getUsername();
        String fullNameText = "Full Name: " + currentCook.getfName() + " " + currentCook.getlName();
        String numSalesText = "Number of Completed Sales: " + numCompletedSales;
        username.setText(usernameText);
        fullName.setText(fullNameText);
        numSales.setText(numSalesText);
        ratingCook.setRating((float) currentCook.getRating());
        ratingCook.setIsIndicator(true);

    }

    public void HomePage(View view) {
        recreate();
    }

    public void viewCookMessages() {
        setContentView(R.layout.activity_cook_messages);
        Database db = new Database(this);
        Button seeReviews = findViewById(R.id.btnSeeReviews);

        ListView viewMessages = findViewById(R.id.lv_Reviews);

        List<Purchase> cookSales = db.getSales(currentCook.getUsername());
        List<Purchase> pendingSales = new ArrayList<>();
        for(Purchase sale : cookSales) {
            if(sale.getStatus() == 0) {
                pendingSales.add(sale);
            }
        }

        ArrayAdapter<Purchase> saleArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, pendingSales);

        viewMessages.setAdapter(saleArrayAdapter);

        viewMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPurchase = pendingSales.get(position);
                viewPurchaseRequest();
            }
        });

        seeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewReviews();
            }
        });


    }

    public void viewPurchaseRequest() {
        setContentView(R.layout.activity_purchase_request);
        Database db = new Database(this);
        TextView mealInfo;
        Button backToRequests, approve, reject;

        mealInfo = findViewById(R.id.txtPurchaseInfo);


        String info = selectedPurchase.getCustomer() + " would like " +
                selectedPurchase.getQuantity() + " orders of " +
                selectedPurchase.getMealName() + ".\nThe subtotal would come to $" +
                selectedPurchase.getSubtotal();
        mealInfo.setText(info);


        backToRequests = findViewById(R.id.btnBackToPurchaseRequests);
        approve = findViewById(R.id.btnApprove);
        reject = findViewById(R.id.btnReject);

        backToRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCookMessages();
            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPurchase.approvePurchase();
                db.approvePurchase(selectedPurchase);
                viewCookMessages();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPurchase.rejectPurchase();
                db.rejectPurchase(selectedPurchase);
                viewCookMessages();
            }
        });

    }

    public void LogOut(View view) {
        finish();
    }

    public void ViewReviews() {
        setContentView(R.layout.activity_cook_reviews);
        Database db = new Database(this);

        ListView viewReviews = findViewById(R.id.lv_Reviews);
        List<Review> reviews = db.getCookReviews(currentCook.getUsername());
        ArrayAdapter<Purchase> reviewArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, reviews);

        viewReviews.setAdapter(reviewArrayAdapter);

        viewReviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewReview(reviews.get(position));
            }
        });

    }

    public void viewReview(Review r) {
        setContentView(R.layout.activity_cook_review);

        Button reviews = findViewById(R.id.btnReviews);
        TextView title, client, cook, body;

        title = findViewById(R.id.text_complaintTitle);
        client = findViewById(R.id.text_associatedClient);
        cook = findViewById(R.id.text_associatedCook);
        body = findViewById(R.id.text_complaintMessage);


        title.setText(r.getTitle());
        client.setText(r.getClientUsername());
        cook.setText(r.getCookUsername());
        body.setText(r.getMessage());

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewReviews();
            }
        });
    }

    public void ViewMeals(View view) {
        setContentView(R.layout.activity_cook_meals);
        lv_Menu = findViewById(R.id.list_Menu);
        Database db = new Database(this);
        List<Meal> menuList = db.getMealsOfCook(username);

        Button landingPage = findViewById(R.id.button6);

        ArrayAdapter<Meal> complaintArrayAdapter = new ArrayAdapter(this,
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
        setContentView(R.layout.activity_meal_info);
        Database db = new Database(this);

        Button goBack = findViewById(R.id.btnReturn);
        Button update = findViewById(R.id.btnUpdateMeal);
        Button remove = findViewById(R.id.btnRemoveMeal);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch featured = findViewById(R.id.switchIsFeatured);
        featured.setChecked(selectedMeal.isFeatured());

        TextView name, description, cook, price, allergens, cuisine, mealType;
        name = findViewById(R.id.infoMealName);
        description = findViewById(R.id.infoMealDescription);
        cook = findViewById(R.id.infoMealCookLink);
        price = findViewById(R.id.infoMealPrice);
        allergens = findViewById(R.id.infoAllergens);
        cuisine = findViewById(R.id.infoCuisineType);
        mealType = findViewById(R.id.infoMealType);

        String mealPrice = "$" + String.valueOf(selectedMeal.getMealPrice());
        name.setText(selectedMeal.getMealName());
        description.setText(selectedMeal.getMealDescription());
        cook.setText(selectedMeal.getAssociatedCook().getUsername());
        price.setText(mealPrice);
        allergens.setText(selectedMeal.getListOfAllergens());
        cuisine.setText(selectedMeal.getMealCuisine());
        mealType.setText(selectedMeal.getMealCourse());



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

    public void onInstagramClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }


}
