package com.example.Mealer_App.structure;


import com.example.Mealer_App.Database;

import java.util.List;

public class Meal {

    private String mealName, mealIngredients, mealAllergens, mealDescription, associatedCook, mealCourse, mealCuisine;
    private float mealPrice;
    private boolean isFeatured;
    private int DB_id;

    public Meal(String name, String course, String cuisine, String ingredients, String allergens, float price, String description, String cook) {
        mealName = name;
        mealCourse = course;
        mealCuisine = cuisine;
        mealIngredients = ingredients;
        mealAllergens = allergens;
        mealPrice = price;
        mealDescription = description;
        associatedCook = cook;
        isFeatured = false;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(String mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public String getMealAllergens() {
        return mealAllergens;
    }

    public void setMealAllergens(String mealAllergens) {
        this.mealAllergens = mealAllergens;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public String getAssociatedCook() {
        return associatedCook;
    }

    public void setAssociatedCook(String associatedCook) {
        this.associatedCook = associatedCook;
    }

    public String getMealCourse() {
        return mealCourse;
    }

    public void setMealCourse(FoodCourse mealCourse) {
        this.mealCourse = mealCourse.toString();
    }

    public String getMealCuisine() {
        return mealCuisine;
    }

    public void setMealCuisine(CuisineType mealCuisine) {
        this.mealCuisine = mealCuisine.toString();
    }

    public float getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(float mealPrice) {
        this.mealPrice = mealPrice;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public int getDB_id() {
        return DB_id;
    }

    public void setDB_id(int id) {
        DB_id = id;
    }

    public boolean equals(Meal meal) {
        return (mealName.equals(meal.getMealName()) &&
                mealCourse.equals(meal.getMealCourse()) &&
                mealCuisine.equals(meal.mealCuisine) &&
                mealIngredients.equals(meal.getMealIngredients()) &&
                mealAllergens.equals(meal.getMealAllergens()) &&
                mealPrice == meal.getMealPrice() &&
                mealDescription.equals(meal.getMealDescription()) &&
                associatedCook.equals(meal.getAssociatedCook()));
    }

    public String toString() {
        String s = "";

        if(!isFeatured) {
            s += "**" + mealName + "**\t" + mealPrice;
        } else {
            s += mealName;
        }
        s += "\n\t" + mealDescription + "\nBy: " + associatedCook;

        return s;
    }
}
