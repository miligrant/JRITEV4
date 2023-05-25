package com.example.jritev4;

public class user {
    private String userId;
    private String username;
    private String email;
    private double profileRating;

    // Empty constructor (required by Firebase)
    public user() {
    }

    public user(String userId, String username, String email, double profileRating) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profileRating = profileRating;
    }

    // Getter and setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for profileRating
    public double getProfileRating() {
        return profileRating;
    }

    public void setProfileRating(double profileRating) {
        this.profileRating = profileRating;
    }
}
