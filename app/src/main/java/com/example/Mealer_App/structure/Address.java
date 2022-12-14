package com.example.Mealer_App.structure;

public class Address {

    private String street;
    private int number;
    private String postal;
    private String city;
    private int DB_id;

    public Address(String street, int number, String postalCode, String city) {

        this.street = street;
        this.number = number;
        this.postal = postalCode;
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public int getNumber() {
        return this.number;
    }

    public String getPostal() {
        return this.postal;
    }

    public String getCity() {
        return this.city;
    }

    public int getDB_id() {
        return DB_id;
    }

    public void setStreet(String newStreet) {
        this.street = newStreet;
    }

    public void setCity(String newCity) {
        this.city = newCity;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public void setPostal(String postalCode) {
        this.postal = postalCode;
    }

    public void setDB_id(int DB_id) {
        this.DB_id = DB_id;
    }

    public boolean addressEquals(Address second) {
        if (this.street.equals(second.getStreet()) && this.number == second.number && this.postal.equals(second.getPostal())) {
            return true;
        }
        return false;
    }

}

