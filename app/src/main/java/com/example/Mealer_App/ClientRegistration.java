package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ClientRegistration extends AppCompatActivity {

    public static boolean payment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);
    }

    public void onContinue(View view){

        EditText textFirstName = (EditText)findViewById(R.id.editText_ClientName);
        String firstName = textFirstName.getText().toString();

        EditText textLastName = (EditText)findViewById(R.id.editText_ClientLastName);
        String lastName = textLastName.getText().toString();

        EditText textEmail = (EditText)findViewById(R.id.editTextEmailAddress);
        String email = textEmail.getText().toString();

        EditText textPassword = (EditText)findViewById(R.id.editText_ClientPassword);
        String password = textPassword.getText().toString();

        EditText textStreetNum = (EditText)findViewById(R.id.editText_ClientNumber);
        String streetNum = textStreetNum.getText().toString();

        EditText textAddress = (EditText)findViewById(R.id.editText_ClientStreet);
        String street = textAddress.getText().toString();

        EditText textApt = (EditText)findViewById(R.id.editText_ClientApt);
        String apt = textApt.getText().toString();

        EditText textCity = (EditText)findViewById(R.id.editText_ClientCity);
        String city = textCity.getText().toString();

        EditText textPostalCode = (EditText)findViewById(R.id.editText_ClientPostal);
        String postalCode = textPostalCode.getText().toString();

        if(payment == false){
            Intent intent = new Intent(getApplicationContext(), CreditCardInfo.class);
            startActivity(intent);
        }
        payment = true;
        finish();
        payment = false;
        // allows user to re-enter the loop from signup to registration to credit card again

    }



}