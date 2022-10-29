package com.example.Mealer_App;

import static com.example.Mealer_App.CreditCardInfo.newPaymentInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.Mealer_App.structure.*;


public class ClientRegistration extends AppCompatActivity {

    EditText textUsername, textFirstName, textLastName, textEmail, textPassword, textStreetName,
            textStreetNum, textPostalCode, textCity, textApt;

    public static boolean payment = false;
    public static Client[] clients = new Client[10];
    public static int numClients = 0;
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
        textApt = findViewById(R.id.editText_ClientApt);
    }

    public void GoBack(View view) {
        finish();
    }

    public void onContinue(View view){

        Validators validate = new Validators();

        if(!validate.validateUsername(textUsername) | !validate.validateName(textFirstName) |
                !validate.validateName(textLastName) | !validate.validateEmail(textEmail) |
                !validate.validatePassword(textPassword) | !validate.validateName(textStreetName) |
                !validate.validateNumber(textStreetNum) | !validate.validatePostal(textPostalCode) |
                !validate.validateName(textCity)) {
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

        if(!payment) {
            Intent intent = new Intent(getApplicationContext(), CreditCardInfo.class);
            startActivity(intent);
        }

        clients[numClients++] = new Client(newPaymentInfo, firstName, lastName, email, address, username, password);

        payment = true;
        finish();


        payment = false;
        // allows user to re-enter the loop from signup to registration to credit card again

    }
}