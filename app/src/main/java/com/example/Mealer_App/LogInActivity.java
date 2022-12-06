package com.example.Mealer_App;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.User;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    public static User loggedInUser;
    EditText textUsername, textPassword;
    public static String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        textUsername = findViewById(R.id.editTextTextPersonName);
        textPassword = findViewById(R.id.editTextTextPassword);

        textUsername.setText("");
        textPassword.setText("");
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

        username = textUsername.getText().toString();
        password = textPassword.getText().toString();
        Intent intent = null;


        if(dbHelper.checkAdminExists(username) && dbHelper.matchPassword(username, password)) {
            intent = new Intent(getApplicationContext(), AdminLandingPage.class);
            startActivityForResult(intent, 0);
        } else if(dbHelper.checkCookExists(username) && dbHelper.matchPassword(username, password)) {
            List<Cook> cooks = dbHelper.getCooks();
            for(Cook cook: cooks) {
                if(cook.getUsername().equals(username)) {
                    loggedInUser = cook;
                }
            }
            intent = new Intent(getApplicationContext(), CookLandingPage.class);
            startActivityForResult(intent, 0);
        } else if(dbHelper.checkClientExists(username) && dbHelper.matchPassword(username, password)) {
            List<Client> clients = dbHelper.getClients();
            for(Client client: clients) {
                if(client.getUsername().equals(username)) {
                    loggedInUser = client;
                }
            }
            intent = new Intent(getApplicationContext(), ClientLandingPage.class);
            startActivityForResult(intent, 0);
        } else {
            textUsername.setText("");
            textPassword.setText("");
            Toast.makeText(this, "Username and/or password are not valid", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
