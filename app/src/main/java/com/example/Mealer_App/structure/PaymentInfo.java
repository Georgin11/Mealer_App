package com.example.Mealer_App.structure;

public class PaymentInfo {

    private String cardHolderName;
    private int cardNumber;
    private int cvv;
    private Address billingAddress;

    public PaymentInfo(String cardHolderName, int cardNumber, int cvv, Address billingAddress) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.billingAddress = billingAddress;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }
}
