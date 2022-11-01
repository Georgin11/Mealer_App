package com.example.Mealer_App;

import static com.example.Mealer_App.structure.userType.ADMIN;
import static com.example.Mealer_App.structure.userType.CLIENT;
import static com.example.Mealer_App.structure.userType.COOK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.Admin;
import com.example.Mealer_App.structure.userType;

public class LogInActivity extends AppCompatActivity {

    public static Admin administrator = new Admin("wassim", "the-system-must");
    public static userType typeOfUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }
    public void GoBack(View view) {

        finish();
    }

    public void NoAccount(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivityForResult(intent, 0);
    }


    public void LogIn(View View) {

        Database dbHelper = new Database(LogInActivity.this);

        EditText textUsername = (EditText)findViewById(R.id.editTextTextPersonName);
        String username = textUsername.getText().toString();

        EditText textPassword = (EditText)findViewById(R.id.editTextTextPassword);
        String password = textPassword.getText().toString();

        if(username.equals(administrator.getUsername()) && password.equals(administrator.getPassword())) {
            typeOfUser = ADMIN;
        }

        if(dbHelper.checkCookExists(username) && dbHelper.matchPassword(username, password)) {
            typeOfUser = COOK;
        }

        if(dbHelper.checkClientExists(username) && dbHelper.matchPassword(username, password)) {
            typeOfUser = CLIENT;
        }

        Intent intent;
        if(typeOfUser == null) {
            intent = new Intent(getApplicationContext(), Sign_Up.class);
            Toast.makeText(LogInActivity.this, "Account does not exist.", Toast.LENGTH_LONG).show();
        } else {
            intent = new Intent(getApplicationContext(), WelcomePage.class);
        }
        startActivityForResult(intent, 0);
    }
}
