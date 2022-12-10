package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.Purchase;

import java.util.List;

public class ClientLandingPage extends AppCompatActivity {

    private Client currentClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_landing_page);
        ImageView profileViewer = findViewById(R.id.settings_icon);

        Database db = new Database(this);
        currentClient = null;
        List<Client> clients = db.getClients();

        for(Client c: clients) {
            if(c.getUsername().equals(username)) {
                currentClient = c;
                break;
            }
        }

        profileViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewProfile();
            }
        });

    }

    public void viewProfile() {
        setContentView(R.layout.activity_client_profile);
        Database db = new Database(this);

        List<Purchase> purchases = db.getPurchases(currentClient.getUsername());
        int numApprovedPurchases = 0;
        for(Purchase p: purchases) {
            if(p.getStatus() == 1) {
                numApprovedPurchases++;
            }
        }

        TextView username, fullName, amtOfPurchases;
        Button home = findViewById(R.id.btnBackHome);

        username = findViewById(R.id.txtClientUsername);
        fullName = findViewById(R.id.txtClientFullName);
        amtOfPurchases = findViewById(R.id.txtAmountOfPurchases);

        String usernameText = "Profile of: " + currentClient.getUsername();
        String fullNameText = "Full Name: " + currentClient.getfName() + " " + currentClient.getlName();
        String numSalesText = "Number of Completed Sales: " + numApprovedPurchases;
        username.setText(usernameText);
        fullName.setText(fullNameText);
        amtOfPurchases.setText(numSalesText);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }

    public void LogOut(View view) {
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onReviewClick(View view){
        Intent intent = new Intent(getApplicationContext(), ClientReview.class);
        startActivityForResult(intent, 0);
    }

    public void order(View view) {
        Intent intent = new Intent(getApplicationContext(), ClientFunctionality.class);
        startActivityForResult(intent, 0);
    }

    public void onInstagramClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

}
