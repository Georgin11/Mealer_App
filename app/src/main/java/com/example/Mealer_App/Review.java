package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Review extends AppCompatActivity {

    int tipAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    public void onTipClick1(View View) {
        findViewById(R.id.Tip1).setBackgroundResource(R.drawable.tip_clicked);
        findViewById(R.id.Tip2).setBackgroundResource(R.drawable.food_back_a);
        findViewById(R.id.Tip3).setBackgroundResource(R.drawable.food_back_a);

        tipAmount = 1;
    }

    public void onTipClick2(View View) {
        findViewById(R.id.Tip1).setBackgroundResource(R.drawable.food_back_a);
        findViewById(R.id.Tip2).setBackgroundResource(R.drawable.tip_clicked);
        findViewById(R.id.Tip3).setBackgroundResource(R.drawable.food_back_a);

        tipAmount = 5;
    }

    public void onTipClick3(View View) {
        findViewById(R.id.Tip1).setBackgroundResource(R.drawable.food_back_a);
        findViewById(R.id.Tip2).setBackgroundResource(R.drawable.food_back_a);
        findViewById(R.id.Tip3).setBackgroundResource(R.drawable.tip_clicked);

        tipAmount = 10;
    }
}