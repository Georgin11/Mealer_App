package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Mealer_App.structure.Complaint;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

import java.util.ArrayList;
import java.util.List;

public class CookLandingPage extends AppCompatActivity {

    private ListView lv_Menu;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView textSuspensionLength, textTitle;
    public static int menuPosition = -1;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_landing_page);

        Database dbHelper = new Database(this);
        lv_Menu = findViewById(R.id.list_Menu);

        Database db = new Database(this);

        List<Meal> menuList = db.getMealsOfCook(username);

        ArrayAdapter<Complaint> complaintArrayAdapter = new ArrayAdapter(CookLandingPage.this,
            android.R.layout.simple_list_item_1, menuList);

        lv_Menu.setAdapter(complaintArrayAdapter);


        List<Cook> cooks = dbHelper.getCooks();
        Cook currentCook = null;
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

    public void GoBack1(View view) {
        finish();
    }

    public void AddMealC(View View) {
        Intent intent = new Intent(getApplicationContext(), AddMeals.class);
        startActivityForResult(intent, 0);
    }
    public void RemoveMealC(View View) {
        Intent intent = new Intent(getApplicationContext(), RemoveMeals.class);
        startActivityForResult(intent, 0);
    }

    public void onInstagramClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

}
