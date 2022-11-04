package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Mealer_App.R;

public class Sign_Up extends AppCompatActivity {

    public static boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void GoBack(View view) {
        //Creating a Return intent to pass to the Main Activity
        Intent returnIntent = new Intent();
        //Figuring out which image was clicked
        finish();
    }

    public void onClient(View view){
        Intent intentA = new Intent(getApplicationContext(), ClientRegistration.class);

        if(!clicked){
            startActivity(intentA);
        }
        clicked = true;
        finish();
        clicked = false;
        // allows user to re-enter
    }

    public void onCook(View view){
        Intent intentB = new Intent(getApplicationContext(), CookRegistration.class);

        if(!clicked){
            startActivity(intentB);
        }
        clicked = true;
        finish();
        clicked = false;
    }

}