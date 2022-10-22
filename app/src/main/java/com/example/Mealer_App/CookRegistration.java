package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.Cook;

public class CookRegistration extends AppCompatActivity {

    //
    int SELECT_PICTURE = 200;
    //

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

    }

    public void onAddCheque(View view){

        EditText textUsername = (EditText)findViewById(R.id.editTextCookUsername);
        String username = textUsername.getText().toString();

        EditText textFirstName = (EditText)findViewById(R.id.editTextCookFirstName);
        String firstName = textFirstName.getText().toString();

        EditText textLastName = (EditText)findViewById(R.id.editTextCookLastName);
        String lastName = textLastName.getText().toString();

        EditText textEmail = (EditText)findViewById(R.id.editTextCookEmailAddress);
        String email = textEmail.getText().toString();

        EditText textPassword = (EditText)findViewById(R.id.editTextCookPassword);
        String password = textPassword.getText().toString();

        EditText textStreetNum = (EditText)findViewById(R.id.editTextCookStreetNo);
        int streetNum = Integer.valueOf(textStreetNum.getText().toString());

        EditText textAddress = (EditText)findViewById(R.id.editTextStreetCook);
        String street = textAddress.getText().toString();

        EditText textApt = (EditText)findViewById(R.id.editTextCookApt);
        int apt = Integer.valueOf(textApt.getText().toString());

        EditText textCity = (EditText)findViewById(R.id.editTextCookCity);
        String city = textCity.getText().toString();

        EditText textPostalCode = (EditText)findViewById(R.id.editTextCookPostal);
        String postalCode = textPostalCode.getText().toString();

        EditText textBio = (EditText)findViewById(R.id.editTextCookBio);
        String bio = textBio.getText().toString();

        if(cheque == false){
            // upload cheque
            imageChooser();
        }

        Address address = new Address(street, streetNum, postalCode, city, apt);
        cooks[numCooks++] = new Cook(bio, true, firstName, lastName, email, address, username, password);
        cheque = true;
        finish();
        cheque = false;
    }
}