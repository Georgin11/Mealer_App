package com.example.Mealer_App.structure;


public class Meal {

    private String mealName, ListOfIngredients, ListOfAllergens, mealDescription, mealCourse, mealCuisine;
    private Cook associatedCook;
    private double mealPrice;
    private boolean isFeatured;
    private int DB_id;

    public Meal(String name, String course, String cuisine, String ingredients, String allergens, double price, String description, Cook cook) {
        mealName = name;
        mealCourse = course;
        mealCuisine = cuisine;
        ListOfIngredients = ingredients;
        ListOfAllergens = allergens;
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

    public String getListOfIngredients() {
        return ListOfIngredients;
    }

    public void setListOfIngredients(String listOfIngredients) {
        ListOfIngredients = listOfIngredients;
    }

    public String getListOfAllergens() {
        return ListOfAllergens;
    }

    public void setListOfAllergens(String listOfAllergens) {
        ListOfAllergens = listOfAllergens;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public Cook getAssociatedCook() {
        return associatedCook;
    }

    public void setAssociatedCook(Cook associatedCook) {
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

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
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
                ListOfIngredients.equals(meal.getListOfIngredients()) &&
                ListOfAllergens.equals(meal.getListOfAllergens()) &&
                mealPrice == meal.getMealPrice() &&
                mealDescription.equals(meal.getMealDescription()) &&
                associatedCook.equals(meal.getAssociatedCook()));
    }

    public String toString() {
        String s = "";
        if(!isFeatured) {
            s += "**" + mealName + "**\t";
        } else {
            s += mealName;
        }

        s += "\n\nBy: " + associatedCook.getfName() + " " + associatedCook.getlName() + "         Rating: " + associatedCook.getRating() + "/5\n";

        return s;
    }
}
