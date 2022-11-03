package com.example.Mealer_App;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.Mealer_App.structure.*;


public class ClientRegistration extends AppCompatActivity {


    EditText textUsername, textFirstName, textLastName, textEmail, textPassword,
            textStreetName, textStreetNum, textPostalCode, textCity;

    public Complaint[] complaints;
    public static Client currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);

        textUsername = findViewById(R.id.editText_ClientUsername);
        textFirstName = findViewById(R.id.editText_ClientName);
        textLastName = findViewById(R.id.editText_ClientLastName);
        textEmail = findViewById(R.id.editTextEmailAddress);
        textPassword = findViewById(R.id.editText_ClientPassword);

        textStreetName = findViewById(R.id.editText_ClientStreet);
        textStreetNum = findViewById(R.id.editText_ClientNumber);
        textPostalCode = findViewById(R.id.editText_ClientPostal);
        textCity = findViewById(R.id.editText_ClientCity);
    }

    public void GoBack(View view) {
        finish();
    }

    public void onContinue(View view){

        Validators validate = new Validators();
        Database databaseHelper = new Database(ClientRegistration.this);

        if(!validate.validateUsername(textUsername, databaseHelper) | !validate.validateName(textFirstName) |
                !validate.validateName(textLastName) | !validate.validateEmail(textEmail) |
                !validate.validatePassword(textPassword) | !validate.validateAddress(textStreetName, textStreetNum, textPostalCode, textCity)) {
            return;
        }


        String street = textStreetName.getText().toString().trim();
        int streetNum = Integer.parseInt(textStreetNum.getText().toString().trim());
        String postalCode = textPostalCode.getText().toString().trim();
        String city = textCity.getText().toString().trim();

        String username = textUsername.getText().toString().trim();
        String firstName = textFirstName.getText().toString().trim();
        String lastName = textLastName.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        Address address = new Address(street, streetNum, postalCode, city);
        currentClient = new Client(null, firstName, lastName, email, address, username, password);
        Intent intent = new Intent(getApplicationContext(), CreditCardInfo.class);
        startActivity(intent);

        finish();

    }
}

