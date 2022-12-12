package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Mealer_App.structure.Purchase;
import com.example.Mealer_App.structure.Review;

import java.util.List;

public class ClientReview extends AppCompatActivity {

    private Purchase selectedPurchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_purchases);
        ListView lvPurchases = findViewById(R.id.lv_Purchases);
        Button backToHomePage = findViewById(R.id.btnLandingPage);

        Database db = new Database(this);
        List<Purchase> clientPurchases = db.getPurchases(username);

        ArrayAdapter<Purchase> mealArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, clientPurchases);

        lvPurchases.setAdapter(mealArrayAdapter);


        lvPurchases.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("MissingInflatedId")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(clientPurchases.get(position).getStatus() == 1) {
                    selectedPurchase = clientPurchases.get(position);
                    selectPurchase();
                } else {
                    Toast.makeText(ClientReview.this, "Purchase is not currently approved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientLandingPage.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void selectPurchase() {
        setContentView(R.layout.activity_select_purchase);
        TextView mealName, subtotal;
        Button review, backToPurchases;

        mealName = findViewById(R.id.textPurchaseMealName);
        subtotal = findViewById(R.id.textPurchaseSubtotal);

        review = findViewById(R.id.btnReview);
        backToPurchases = findViewById(R.id.btnPurchases);

        String name = selectedPurchase.getQuantity() +
                " orders of " + selectedPurchase.getChef() +
                "'s " + selectedPurchase.getMealName();
        mealName.setText(name);
        subtotal.setText(String.valueOf(selectedPurchase.getSubtotal()));

        backToPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveReview();
            }
        });
    }

    public void leaveReview() {
        setContentView(R.layout.activity_review);
        Button backToSelectedPurchase, submitReview;
        EditText reviewTitle, reviewBody;
        RatingBar reviewRating;
        Database db = new Database(this);

        backToSelectedPurchase = findViewById(R.id.ReviewBack);
        submitReview = findViewById(R.id.ReviewSubmit);
        reviewTitle = findViewById(R.id.editTextTitle);
        reviewBody = findViewById(R.id.ReviewComment);
        reviewRating = findViewById(R.id.ratingBar);



        backToSelectedPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPurchase();
            }
        });

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double rating = reviewRating.getRating();
                String title = reviewTitle.getText().toString().trim();
                String body = reviewBody.getText().toString().trim();
                if(rating < 3) {
                    Review r = new Review(title, body, selectedPurchase.getCustomer(), selectedPurchase.getChef(), rating);

                    db.addOne(r);
                    recreate();
                }
                if(title.isEmpty() || body.isEmpty()) {
                    reviewTitle.setError("Please leave some feedback for the chef!");
                    reviewBody.setError("Please leave some feedback for the chef!");
                    return;
                }
                Review r = new Review(title, body, selectedPurchase.getCustomer(), selectedPurchase.getChef(), rating);

                db.addOne(r);

                recreate();

            }
        });

    }

}