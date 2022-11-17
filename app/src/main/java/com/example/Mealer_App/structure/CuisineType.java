package com.example.Mealer_App.structure;

public enum CuisineType {
    ITALIAN ("Italian"),
    CHINESE ("Chinese"),
    JAPANESE ("Japanese"),
    INDIAN ("Indian"),
    MEXICAN ("Mexican"),
    SPANISH ("Spanish"),
    GREEK ("Greek"),
    AMERICAN ("American"),
    THAI ("Thai"),
    FRENCH ("French"),
    MIDDLE_EASTERN ("Middle Eastern"),
    MEDITERRANEAN ("Mediterranean");

    private final String cuisine;

    CuisineType(String s) {
        cuisine = s;
    }

    public boolean equals(String otherMeal) {
        return cuisine.equals(otherMeal);
    }

    public String toString() {
        return this.cuisine;
    }
}
