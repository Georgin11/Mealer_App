package com.example.Mealer_App.structure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Purchase {

    private String chef;
    private String customer;
    private String mealName;
    private int id;
    private int status;
    private int quantity;
    private double subtotal;
    private final double unitPrice;

    //status of zero means that the purchase is currently pending. 1 means approved and -1 is rejected.
    public Purchase(String cook, String client, String meal, int numItems, double mealPrice) {
        chef = cook;
        customer = client;
        mealName = meal;
        status = 0;
        quantity = numItems;
        unitPrice = mealPrice;
        subtotal = (mealPrice * numItems);
        BigDecimal bd = BigDecimal.valueOf(subtotal);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        subtotal = bd.doubleValue();
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void approvePurchase() {
        status = 1;
    }

    public void rejectPurchase() {
        status = -1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }



    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPostTaxTotal() {
        return (subtotal * 1.13);
    }

    public String toString() {
        String s = "\n";

        s += mealName + "\nAmount of: " + quantity + "\n" + "Subtotal: " + subtotal + "\nStatus: ";
        switch (status) {
            case -1:
                s += "Rejected";
                break;
            case 0:
                s += "Pending";
                break;
            case 1:
                s += "Approved";
                break;
        }
        s += "\n";
        return s;
    }
}
