package com.example.Mealer_App;

import static com.example.Mealer_App.ClientRegistration.currentClient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.*;

import java.math.BigInteger;

public class CreditCardInfo extends AppCompatActivity {

    EditText textCardholder, textCardNumber, textCVV, textStreetAddress, textStreetNumber, textPostal, textCity;

    public static PaymentInfo newPaymentInfo;
    public Client client;
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
    }

    public void GoBack(View view) {
        finish();
    }

    public void onSave(View view){



        Validators validate = new Validators();
        Database dbHelper = new Database(CreditCardInfo.this);

        if(!validate.validateName(textCardholder) | !validate.validateCardNumber(textCardNumber) |
            !validate.validateCVV(textCVV) | !validate.validateAddress(textStreetAddress, textStreetNumber, textPostal, textCity)) {
            return;
        }

        String cardholder = textCardholder.getText().toString();
        BigInteger cardNumber = new BigInteger(textCardNumber.getText().toString());
        int cvv = Integer.parseInt(textCVV.getText().toString());

        String streetName = textStreetAddress.getText().toString();
        int streetNum = Integer.parseInt(textStreetNumber.getText().toString());
        String postal = textPostal.getText().toString();
        String city = textCity.getText().toString();

        Address address = new Address(streetName, streetNum, postal, city);

        newPaymentInfo = new PaymentInfo(cardholder, cardNumber, cvv, address);
        client = currentClient;
        client.setPaymentInfo(newPaymentInfo);

        boolean paymentInfoCheck = (newPaymentInfo != null);

        if(paymentInfoCheck) {
            Toast.makeText(CreditCardInfo.this, "Payment info is not null", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CreditCardInfo.this, "payment info is null", Toast.LENGTH_LONG).show();
        }

        boolean success =  dbHelper.addOne(CreditCardInfo.this);

        if(success) {
            Toast.makeText(CreditCardInfo.this, "Successfully inserted into DB", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CreditCardInfo.this, "Error inserting into DB", Toast.LENGTH_LONG).show();
        }


        return;
    }
}