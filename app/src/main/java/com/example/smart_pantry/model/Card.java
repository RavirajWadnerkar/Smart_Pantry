package com.example.smart_pantry.model;

public class Card {
    private String imageResource;
    private String title;

    private Recipe recipe;

    public Card(String imageResource, String title, Recipe recipe) {
        this.imageResource = imageResource;
        this.title = title;
        this.recipe = recipe;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
