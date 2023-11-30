package com.example.smart_pantry.model;

public class RecipeTab {

    private String title;
    private Recipe recipe;

    public RecipeTab(String title, Recipe recipe) {
        this.title = title;
        this.recipe = recipe;
    }

    public String getTitle() {
        return title;
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
