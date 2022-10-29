package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.Mealer_App.structure.*;

public class CreditCardInfo extends AppCompatActivity {

    EditText textCardholder, textCardNumber, textCVV, textStreetAddress, textStreetNumber, textPostal, textCity, textApt;

    public static PaymentInfo newPaymentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_info);

        textCardholder = findViewById(R.id.editTextCardholder);
        textCardNumber = findViewById(R.id.editTextCardNumber);
        textCVV = findViewById(R.id.editTextCVV);
        textStreetAddress = findViewById(R.id.editTextBillingAddress);
        textStreetNumber = findViewById(R.id.editTextStreetNumber);
        textPostal = findViewById(R.id.editTextPostal);
        textCity = findViewById(R.id.editTextCity);
        textApt = findViewById(R.id.editTextBillingApt);
    }

    public void GoBack(View view) {
        finish();
    }

    public void onSave(View view){

        Validators validate = new Validators();

        if(!validate.validateName(textCardholder) | !validate.validateCardNumber(textCardNumber) |
            !validate.validateCVV(textCVV) | !validate.validateName(textStreetAddress) |
                !validate.validateNumber(textStreetNumber) | !validate.validatePostal(textPostal) |
                !validate.validateName(textCity)) {
            return;
        }

        String cardholder = textCardholder.getText().toString();
        int cardNumber = Integer.parseInt(textCardNumber.getText().toString());
        int cvv = Integer.parseInt(textCVV.getText().toString());

        String streetName = textStreetAddress.getText().toString();
        int streetNum = Integer.parseInt(textStreetNumber.getText().toString());
        String postal = textPostal.getText().toString();
        String city = textCity.getText().toString();
        int apt = 0;
        if(!textApt.getText().toString().isEmpty()) {
            apt = Integer.parseInt(textApt.getText().toString());
        }

        Address address = new Address(streetName, streetNum, postal, city, apt);

        newPaymentInfo = new PaymentInfo(cardholder, cardNumber, cvv, address);
        super.finish();
    }
}