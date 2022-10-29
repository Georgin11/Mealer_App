package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Cook;

public class CookRegistration extends AppCompatActivity {

    EditText textUsername, textFirstName, textLastName, textEmail, textPassword,
            textStreetNum, textStreetName, textApt, textCity, textPostalCode, textBio;

    int SELECT_PICTURE = 200;

    public static boolean cheque = false;
    public static Cook[] cooks = new Cook[10];
    public static int numCooks = 0;

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
        textApt = findViewById(R.id.editTextCookApt);
        textCity = findViewById(R.id.editTextCookCity);
        textPostalCode = findViewById(R.id.editTextCookPostal);
        textBio = findViewById(R.id.editTextCookBio);

    }

    public void GoBack(View view) {

        finish();
    }

    public void onAddCheque(View view){

        Validators validate = new Validators();

        if(!validate.validateUsername(textUsername) | !validate.validateName(textFirstName) |
                !validate.validateName(textLastName) | !validate.validateEmail(textEmail) |
                !validate.validatePassword(textPassword) | !validate.validateName(textStreetName) |
                !validate.validateNumber(textStreetNum) | !validate.validatePostal(textPostalCode) |
                !validate.validateName(textCity) | !validate.validateBio(textBio)) {
            return;
        }

        String username = textUsername.getText().toString();
        String firstName = textFirstName.getText().toString();
        String lastName = textLastName.getText().toString();
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        String street = textStreetName.getText().toString();
        int streetNum = Integer.parseInt(textStreetNum.getText().toString());
        String postalCode = textPostalCode.getText().toString();
        String city = textCity.getText().toString();
        int apt = 0;
        if(!textApt.getText().toString().isEmpty()) {
            apt = Integer.parseInt(textApt.getText().toString());
        }

        Address address = new Address(street, streetNum, postalCode, city, apt);

        String bio = textBio.getText().toString();

        if(!cheque){
            // upload cheque
            imageChooser();
        }

        cooks[numCooks++] = new Cook(bio, true, firstName, lastName, email, address, username, password);
        cheque = true;
        finish();
        cheque = false;
    }
}