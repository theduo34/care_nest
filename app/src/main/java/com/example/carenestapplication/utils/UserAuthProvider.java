package com.example.carenestapplication.utils;

import com.example.carenestapplication.models.User;
import com.example.carenestapplication.models.UserProfile;

public class UserAuthProvider {

    private static UserAuthProvider instance;
    private User currentUser;
    private UserProfile currentUserProfile;

    private UserAuthProvider() {}

    public static synchronized UserAuthProvider getInstance() {
        if (instance == null) {
            instance = new UserAuthProvider();
        }
        return instance;
    }

    // Setters
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setCurrentUserProfile(UserProfile profile) {
        this.currentUserProfile = profile;
    }

    // Getters
    public User getCurrentUser() {
        return currentUser;
    }

    public UserProfile getCurrentUserProfile() {
        return currentUserProfile;
    }

    // Clear user info on logout
    public void clear() {
        currentUser = null;
        currentUserProfile = null;
    }
}
