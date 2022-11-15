package com.example.Mealer_App;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Mealer_App.structure.*;

import java.math.BigInteger;


public class ClientRegistration extends AppCompatActivity {


    EditText textUsername, textFirstName, textLastName, textEmail, textPassword,
            textStreetName, textStreetNum, textPostalCode, textCity;

    EditText textCardholder, textCardNumber, textCVV, textPaymentStreetAddress,
            textPaymentStreetNum, textPaymentPostal, textPaymentCity;

    private String username, firstName, lastName, email, password;
    private Address userAddress;

    public static Client client;

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

        username = textUsername.getText().toString().trim();
        firstName = textFirstName.getText().toString().trim();
        lastName = textLastName.getText().toString().trim();
        email = textEmail.getText().toString().trim();
        password = textPassword.getText().toString().trim();

        userAddress = new Address(street, streetNum, postalCode, city);

        setContentView(R.layout.activity_credit_card_info);
    }

    public void onSave(View view) {

        Validators validate = new Validators();
        Database databaseHelper = new Database(ClientRegistration.this);

        textCardholder = findViewById(R.id.editTextCardholder);
        textCardNumber = findViewById(R.id.editTextCardNumber);
        textCVV = findViewById(R.id.editTextCVV);
        textPaymentStreetAddress = findViewById(R.id.editTextBillingAddress);
        textPaymentStreetNum = findViewById(R.id.editTextStreetNumber);
        textPaymentPostal = findViewById(R.id.editTextPostal);
        textPaymentCity = findViewById(R.id.editTextCity);

        PaymentInfo newPaymentInfo;

        if(!validate.validateName(textCardholder) | !validate.validateCardNumber(textCardNumber) |
                !validate.validateCVV(textCVV) | !validate.validateAddress(textPaymentStreetAddress, textPaymentStreetNum, textPaymentPostal, textPaymentCity)) {
            return;
        }

        String cardholder = textCardholder.getText().toString();
        BigInteger cardNumber = new BigInteger(textCardNumber.getText().toString());
        int cvv = Integer.parseInt(textCVV.getText().toString());

        String streetName = textPaymentStreetAddress.getText().toString();
        int streetNum = Integer.parseInt(textPaymentStreetNum.getText().toString());
        String postal = textPaymentPostal.getText().toString();
        String city = textPaymentCity.getText().toString();

        Address paymentAddress = new Address(streetName, streetNum, postal, city);

        newPaymentInfo = new PaymentInfo(cardholder, cardNumber, cvv, userAddress);

        client = new Client(newPaymentInfo, firstName, lastName, email, userAddress, username, password);

        boolean success =  databaseHelper.addOne(client);

        if(success) {
            Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error during registration", Toast.LENGTH_LONG).show();
        }


        finish();
    }

    public void GoBack() {
        return;
    }
}

