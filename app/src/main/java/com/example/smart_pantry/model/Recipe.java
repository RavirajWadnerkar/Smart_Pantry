package com.example.smart_pantry.model;

import java.io.Serializable;

public class Recipe implements Serializable {

    String totalTime;

    String recipeName;

    String ingredients;

    String instructions;

    String imageURL;

    public Recipe() {
    }

    public Recipe(String totalTime, String recipeName, String ingredients, String instructions, String imageURL) {
        this.totalTime = totalTime;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageURL = imageURL;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
