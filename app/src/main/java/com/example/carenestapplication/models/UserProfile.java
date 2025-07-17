package com.example.carenestapplication.models;

public class UserProfile {

    private int profileId;
    private int userId;
    private int age;
    private String gender;
    private String healthConditions;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String profileImageUri;
    private String createdAt;

    public UserProfile() { }

    public UserProfile(int profileId, int userId, int age, String gender, String healthConditions,
                       String emergencyContactName, String emergencyContactPhone, String profileImageUri, String createdAt) {
        this.profileId = profileId;
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.healthConditions = healthConditions;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.profileImageUri = profileImageUri;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(String healthConditions) {
        this.healthConditions = healthConditions;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }

    public void setProfileImageUri(String profileImageUri) {
        this.profileImageUri = profileImageUri;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
