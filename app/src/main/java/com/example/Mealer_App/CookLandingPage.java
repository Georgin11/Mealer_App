package com.example.Mealer_App;

import static com.example.Mealer_App.LogInActivity.username;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Mealer_App.structure.Cook;
import java.util.List;

public class CookLandingPage extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView textSuspensionLength, textTitle;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_landing_page);

        Database dbHelper = new Database(this);
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

    public void LogOut(View view) {
        finish();
    }


}
