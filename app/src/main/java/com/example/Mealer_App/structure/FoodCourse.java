package com.example.Mealer_App.structure;

public enum FoodCourse {
    BREAKFAST ("Breakfast"),
    LUNCH ("Lunch"),
    DINNER ("Dinner"),
    APPETIZER ("Appetizer"),
    MAIN_DISH ("Main Dish"),
    DESSERT ("Dessert"),
    SOUP ("Soup"),
    ENTREES ("Entrees");

    private final String course;

    FoodCourse(String s) {
        course = s;
    }

    public boolean equals(String otherCourse) {
        return course.equals(otherCourse);
    }

    public String toString() {
        return course;
    }
}
