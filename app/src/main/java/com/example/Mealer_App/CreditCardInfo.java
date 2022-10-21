package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreditCardInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_info);
    }

    public void onSave(View view){
        EditText textCardholder = (EditText)findViewById(R.id.editTextCardholder);
        String cardholder = textCardholder.getText().toString();

        EditText textCardNumber = (EditText)findViewById(R.id.editTextCardNumber);
        String cardNumber = textCardNumber.getText().toString();

        EditText textCVV = (EditText)findViewById(R.id.editTextCVV);
        String cvv = textCVV.getText().toString();

        EditText textStreetNumber = (EditText)findViewById(R.id.editTextStreetNumber);
        String streetNum2 = textStreetNumber.getText().toString();

        EditText textStreetAddress = (EditText)findViewById(R.id.editTextBillingAddress);
        String street2 = textStreetAddress.getText().toString();

        EditText textApt2 = (EditText)findViewById(R.id.editTextBillingApt);
        String apt2 = textApt2.getText().toString();

        EditText textCity2 = (EditText)findViewById(R.id.editTextCity);
        String city2 = textCity2.getText().toString();

        EditText textPostal = (EditText)findViewById(R.id.editTextBillingApt);
        String postal2 = textPostal.getText().toString();

        super.finish();
    }
}