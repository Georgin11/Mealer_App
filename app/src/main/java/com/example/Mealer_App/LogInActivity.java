package com.example.Mealer_App;

import static com.example.Mealer_App.ClientRegistration.clients;
import static com.example.Mealer_App.ClientRegistration.numClients;
import static com.example.Mealer_App.CookRegistration.cooks;
import static com.example.Mealer_App.CookRegistration.numCooks;
import static com.example.Mealer_App.structure.userType.ADMIN;

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
        EditText textUsername = (EditText)findViewById(R.id.editTextTextPersonName);
        String username = textUsername.getText().toString();

        EditText textPassword = (EditText)findViewById(R.id.editTextTextPassword);
        String password = textPassword.getText().toString();

        if(username.equals(administrator.getUsername()) && password.equals(administrator.getPassword())) {
            typeOfUser = ADMIN;
        }

        for(int i = 0; i < numClients; i++) {
            if (username.equals(clients[i].getUsername()) && password.equals(clients[i].getPassword())) {
                typeOfUser = userType.CLIENT;
                break;
            }
        }

        for(int i = 0; i < numCooks; i++) {
            if (username.equals(cooks[i].getUsername()) && password.equals(cooks[i].getPassword())) {
                typeOfUser = userType.COOK;
                break;
            }
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
