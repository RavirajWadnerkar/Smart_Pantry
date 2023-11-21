package com.example.smart_pantry.model;

public class UserPreferenceData {

    boolean isVeg;
    boolean isNonVeg;
    MealType mealType;
    Cuisine cuisine;

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public boolean isNonVeg() {
        return isNonVeg;
    }

    public void setNonVeg(boolean nonVeg) {
        isNonVeg = nonVeg;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
