package com.example.Mealer_App;

import static org.junit.Assert.assertEquals;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.PaymentInfo;

import org.junit.Test;

import java.math.BigInteger;

public class UnitTest_Deliverable3 {

    String cardholder = "John";
    BigInteger cardNumber = BigInteger.valueOf(12341234);
    int cvv = 123;
    Address userAddress = new Address("Main Street", 1, "L1L2M2", "Metropolis");

    PaymentInfo checkPayment = new PaymentInfo(cardholder, cardNumber, cvv, userAddress);

    @Test
    public void checkCardHolderName(){
        assertEquals("John", checkPayment.getCardHolderName());
    }

    @Test
    public void checkCardNumber(){
        assertEquals(BigInteger.valueOf(12341234), checkPayment.getCardNumber());
    }

    @Test
    public void checkBillingAddress_City(){
        assertEquals("Metropolis", checkPayment.getBillingAddress().getCity());
    }

    @Test
    public void checkBillingAddress_StreetName(){
        assertEquals("Main Street", checkPayment.getBillingAddress().getStreet());
    }
}
