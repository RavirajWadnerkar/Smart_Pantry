package com.example.smart_pantry.model;

public class UserDetails {

    String name;
    String emailId;
    UserPreferenceData userPreferenceData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserPreferenceData getUserPreferenceData() {
        return userPreferenceData;
    }

    public void setUserPreferenceData(UserPreferenceData userPreferenceData) {
        this.userPreferenceData = userPreferenceData;
    }
}
