package com.example.Mealer_App;

import static org.junit.Assert.assertEquals;

import com.example.Mealer_App.structure.Purchase;

import org.junit.Test;

public class UnitTest_Deliverable4 {

    String cook = "John";
    String client = "Doe";
    String meal = "Pizza";
    int numItems = 5;
    float amtPaid = 10;

    Purchase checkPurchase = new Purchase(cook, client, meal, numItems,amtPaid);

    @Test
    public void getChefName(){
        assertEquals("John", checkPurchase.getChef());
    }

    @Test
    public void getCustomerName(){
        assertEquals("Doe", checkPurchase.getCustomer());
    }

    @Test
    public void getFood(){
        assertEquals("Pizza", checkPurchase.getFood());
    }

    @Test
    public void getQuantity(){
        assertEquals(5, checkPurchase.getQuantity());
    }

}
