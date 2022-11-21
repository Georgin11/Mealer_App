package com.example.Mealer_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Admin;
import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.Complaint;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.CuisineType;
import com.example.Mealer_App.structure.FoodCourse;
import com.example.Mealer_App.structure.PaymentInfo;
import com.example.Mealer_App.structure.Meal;

import java.math.BigInteger;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public Admin admin = new Admin("wassim", "the-system-must");

    public Address address1 = new Address("Apple Road", 12, "K2T0K5", "Ottawa");
    public Address address2 = new Address("Laurier Avenue", 74, "K2L5N0", "Ottawa");
    public Address address3 = new Address("Rideau Street", 634, "K2M6J5", "Ottawa");
    public Address address4 = new Address("Macara Crescent", 63, "K2K5T6", "Ottawa");
    public Address address5 = new Address("Line Road", 43, "K2K5L6", "Ottawa");

    public PaymentInfo paymentInfo1 = new PaymentInfo("Fuad Thabet", new BigInteger("2845562846236472"), 432, address1);

    public Client client1 = new Client(paymentInfo1,
            "Fuad",
            "Thabet",
            "thabetfuad@gmail.com",
            address2,
            "fuadthabet",
            "Strongpassword1!");

    public Cook cook1 = new Cook("You know who I am",
            true,
            "Gordon",
            "Ramsay",
            "gramsay123@gmail.com",
            address3,
            "GordonRamsay1",
            "Thebestchef12$$");

    public Cook cook2 = new Cook("Bringing you the best meals your mom wishes she could make",
            true,
            "Mac",
            "Sajder",
            "macskitchen@gmail.com",
            address4,
            "Mac-n-Cheese",
            "ReallyGoodPassword2#");

    public Cook cook3 = new Cook("My food is better than Mac's",
            true,
            "Georgin",
            "Binoy",
            "gramsay123@gmail.com",
            address5,
            "GeorginMeals",
            "YummyFood1@");

    public Complaint complaint1 = new Complaint("Worst Meal I've had in a long time",
            "I found tons of hair in my ravioli and it is completely unacceptable. The chef simply called me a moron",
            "fuadthabet",
            "GordonRamsay1",
            2);

    public Complaint complaint2 = new Complaint("I could not recommend this any less",
            "We received our food cold and raw. The chef accused us of lying for a free meal",
            "fuadthabet",
            "Mac-n-Cheese",
            1);

    public Complaint complaint3 = new Complaint("Nothing to complain about here!",
            "I loved the food and this complaint is just to test dismissing complaints",
            "fuadthabet",
            "GeorginMeals",
            4);

    public Meal meal1 = new Meal("Mac's Mac-n-Cheese",
            FoodCourse.LUNCH.toString(),
            CuisineType.FRENCH.toString(),
            "Macaroni, cheese, milk, salt",
            "Dairy products", (float) 12.99,
            "The best Mac-n-Cheese you'll ever taste",
            cook2.getUsername());

    public Complaint[] complaints = new Complaint[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database dbHelper = new Database(MainActivity.this);

        if(dbHelper.getNumComplaints() == 0) {
            dbHelper.addOne(admin);
            dbHelper.addOne(client1);
            dbHelper.addOne(cook1);
            dbHelper.addOne(cook2);
            dbHelper.addOne(cook3);
            dbHelper.addOne(meal1);

            complaints[0] = complaint1;
            complaints[1] = complaint2;
            complaints[2] = complaint3;
            for (Complaint complaint : complaints) {
                dbHelper.addOne(complaint);
            }
        }
        List<Meal> meals = dbHelper.getMealsOfCook(meal1.getAssociatedCook());
        for(int i = 0; i < meals.toArray().length; i++) {
            if(meals.get(i).equals(meal1)) {
                meal1.setDB_id(i+1);
                break;
            }
        }
        dbHelper.featureMeal(meal1, true);
    }
    public void OnSignUp(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivityForResult(intent, 0);
    }

    public void OnLogIn(View View) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivityForResult(intent, 0);
    }
}