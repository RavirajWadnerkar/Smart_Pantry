package com.example.smart_pantry.model;

public class UserDetails {

    String name;
    String email;
    UserPreferenceData userPreferenceData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserPreferenceData getUserPreferenceData() {
        return userPreferenceData;
    }

    public void setUserPreferenceData(UserPreferenceData userPreferenceData) {
        this.userPreferenceData = userPreferenceData;
    }
}
