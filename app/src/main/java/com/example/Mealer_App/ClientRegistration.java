package com.example.Mealer_App;

import static com.example.Mealer_App.CreditCardInfo.newPaymentInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.Mealer_App.structure.*;

public class ClientRegistration extends AppCompatActivity {

    public static boolean payment = false;
    public static Client[] clients = new Client[10];
    public static int numClients = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);
    }

    public void onContinue(View view){

        EditText textUsername = (EditText)findViewById(R.id.editText_ClientUsername);
        String username = textUsername.getText().toString();

        EditText textFirstName = (EditText)findViewById(R.id.editText_ClientName);
        String firstName = textFirstName.getText().toString();

        EditText textLastName = (EditText)findViewById(R.id.editText_ClientLastName);
        String lastName = textLastName.getText().toString();

        EditText textEmail = (EditText)findViewById(R.id.editTextEmailAddress);
        String email = textEmail.getText().toString();

        EditText textPassword = (EditText)findViewById(R.id.editText_ClientPassword);
        String password = textPassword.getText().toString();

        EditText textStreetNum = (EditText)findViewById(R.id.editText_ClientNumber);
        int streetNum = Integer.valueOf(textStreetNum.getText().toString());

        EditText textAddress = (EditText)findViewById(R.id.editText_ClientStreet);
        String street = textAddress.getText().toString();

        EditText textApt = (EditText)findViewById(R.id.editText_ClientApt);
        int apt = Integer.valueOf(textApt.getText().toString());

        EditText textCity = (EditText)findViewById(R.id.editText_ClientCity);
        String city = textCity.getText().toString();

        EditText textPostalCode = (EditText)findViewById(R.id.editText_ClientPostal);
        String postalCode = textPostalCode.getText().toString();

        if(payment == false){
            Intent intent = new Intent(getApplicationContext(), CreditCardInfo.class);
            startActivity(intent);
        }
        Address address = new Address(street, streetNum, postalCode, city, apt);
        clients[numClients++] = new Client(newPaymentInfo, firstName, lastName, email, address, username, password);

        payment = true;
        finish();
        payment = false;
        // allows user to re-enter the loop from signup to registration to credit card again

        Database databaseHelper = new Database(ClientRegistration.this);
    }



}