package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Cook;

public class CookRegistration extends AppCompatActivity {

    EditText textUsername, textFirstName, textLastName, textEmail, textPassword,
            textStreetNum, textStreetName, textCity, textPostalCode, textBio;

    int SELECT_PICTURE = 200;

    public static boolean cheque = false;
    public static Cook cook;

    void imageChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        textUsername = findViewById(R.id.editTextCookUsername);
        textFirstName = findViewById(R.id.editTextCookFirstName);
        textLastName = findViewById(R.id.editTextCookLastName);
        textEmail = findViewById(R.id.editTextCookEmailAddress);
        textPassword = findViewById(R.id.editTextCookPassword);
        textStreetNum = findViewById(R.id.editTextCookStreetNo);
        textStreetName = findViewById(R.id.editTextStreetCook);
        textCity = findViewById(R.id.editTextCookCity);
        textPostalCode = findViewById(R.id.editTextCookPostal);
        textBio = findViewById(R.id.editTextCookBio);

    }

    public void GoBack(View view) {
        finish();
    }

    public void onAddCheque(View view){

        Validators validate = new Validators();
        Database databaseHelper = new Database( CookRegistration.this);


        if(!validate.validateUsername(textUsername, databaseHelper) | !validate.validateName(textFirstName) |
                !validate.validateName(textLastName) | !validate.validateEmail(textEmail) |
                !validate.validatePassword(textPassword) | !validate.validateAddress(textStreetName, textStreetNum, textPostalCode, textCity) |
                !validate.validateBio(textBio)) {
            return;
        }

        String username = textUsername.getText().toString().trim();
        String firstName = textFirstName.getText().toString().trim();
        String lastName = textLastName.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        String street = textStreetName.getText().toString().trim();
        int streetNum = Integer.parseInt(textStreetNum.getText().toString().trim());
        String postalCode = textPostalCode.getText().toString().trim();
        String city = textCity.getText().toString().trim();

        Address address = new Address(street, streetNum, postalCode, city);

        String bio = textBio.getText().toString();

        if(!cheque){
            // upload cheque
            imageChooser();
        }

        cook = new Cook(bio, true, firstName, lastName, email, address, username, password);

        boolean success =  databaseHelper.addOne(CookRegistration.this);

        if(success) {
            Toast.makeText(CookRegistration.this, "Successfully registered!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CookRegistration.this, "Error during registration", Toast.LENGTH_LONG).show();
        }

        cheque = true;
        finish();
        cheque = false;
    }
}