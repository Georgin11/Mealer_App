package com.example.Mealer_App;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ClientLandingPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_landing_page);


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
