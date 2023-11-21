package com.example.smart_pantry.model;

public class Card {
    private int imageResource;
    private String title;

    public Card(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}
