package com.example.carenestapplication.models;

public class Medication {
    private int medicationId;
    private int userId;
    private String name;
    private String dosage;
    private String time;
    private String startDate;
    private String endDate;
    private String notes;
    private String createdAt;

    public Medication() { }

    public Medication(int medicationId, int userId, String name, String dosage, String time, String startDate, String endDate, String notes, String createdAt) {
        this.medicationId = medicationId;
        this.userId = userId;
        this.name = name;
        this.dosage = dosage;
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
