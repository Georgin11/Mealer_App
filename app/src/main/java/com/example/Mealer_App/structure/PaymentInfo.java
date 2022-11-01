package com.example.Mealer_App.structure;

import java.math.BigInteger;

public class PaymentInfo {

    private String cardHolderName;
    private BigInteger cardNumber;
    private int cvv;
    private Address billingAddress;

    public PaymentInfo(String cardHolderName, BigInteger cardNumber, int cvv, Address billingAddress) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.billingAddress = billingAddress;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }
}
