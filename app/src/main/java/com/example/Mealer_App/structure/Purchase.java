package com.example.Mealer_App.structure;

public class Purchase {

    private String chef;
    private String customer;
    private String food;
    private int id;
    private int status;
    private int quantity;
    private float subtotal;

    //status of zero means that the purchase is currently pending. 1 means approved and -1 is rejected.
    public Purchase(String cook, String client, String meal, int numItems, float amtPaid) {
        chef = cook;
        customer = client;
        food = meal;
        status = 0;
        quantity = numItems;
        subtotal = amtPaid;
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

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

}
