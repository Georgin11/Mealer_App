package com.example.Mealer_App.structure;

public class Purchase {

    private String chef;
    private String customer;
    private String mealName;
    private int id;
    private int status;
    private int quantity;
    private double subtotal;
    private int tip;
    private double unitPrice;

    //status of zero means that the purchase is currently pending. 1 means approved and -1 is rejected.
    public Purchase(String cook, String client, String meal, int numItems, double mealPrice) {
        chef = cook;
        customer = client;
        mealName = meal;
        status = 0;
        quantity = numItems;
        unitPrice = mealPrice;
        subtotal = (mealPrice * numItems);
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

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
        subtotal += tip;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPostTaxTotal() {
        return (subtotal * 1.13);
    }
}
