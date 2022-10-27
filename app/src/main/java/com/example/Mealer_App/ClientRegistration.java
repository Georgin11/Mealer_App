package com.example.Mealer_App;

import static com.example.Mealer_App.CreditCardInfo.newPaymentInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Mealer_App.structure.*;


public class ClientRegistration extends AppCompatActivity {

    EditText textUsername, textFirstName, textLastName, textEmail, textPassword;

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


    }

    public void onContinue(View view){

        Validators validate = new Validators();

        if(!validate.validateUsername(textUsername) | !validate.validateName(textFirstName) | !validate.validateName(textLastName) | !validate.validateEmail(textEmail) | !validate.validatePassword(textPassword)) {
            return;
        }

        String username = textUsername.getText().toString();
        String firstName = textFirstName.getText().toString();
        String lastName = textLastName.getText().toString();
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        if(!payment){
            Intent intent = new Intent(getApplicationContext(), CreditCardInfo.class);
            startActivity(intent);
        }

        Address address = null;
        boolean addressValid = false;
        while(!addressValid) {
            try {
                EditText textAddress = findViewById(R.id.editText_ClientStreet);
                String street = textAddress.getText().toString();

                EditText textStreetNum = findViewById(R.id.editText_ClientNumber);
                int streetNum = Integer.parseInt(textStreetNum.getText().toString());

                EditText textPostalCode = findViewById(R.id.editText_ClientPostal);
                String postalCode = textPostalCode.getText().toString();

                EditText textCity = findViewById(R.id.editText_ClientCity);
                String city = textCity.getText().toString();

                EditText textApt = findViewById(R.id.editText_ClientApt);
                int apt = Integer.parseInt(textApt.getText().toString());

                address = new Address(street, streetNum, postalCode, city, apt);
                addressValid = true;
            } catch(Exception e) {
                Toast.makeText(ClientRegistration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        clients[numClients++] = new Client(newPaymentInfo, firstName, lastName, email, address, username, password);

        payment = true;
        finish();


        payment = false;
        // allows user to re-enter the loop from signup to registration to credit card again

    }
}