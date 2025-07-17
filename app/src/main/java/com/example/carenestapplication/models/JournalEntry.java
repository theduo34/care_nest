package com.example.carenestapplication.models;

public class JournalEntry {
    private int journalId;
    private int userId;
    private String entryDate;
    private String mood;
    private String symptoms;
    private String notes;
    private String createdAt;

    public JournalEntry() { }

    public JournalEntry(int journalId, int userId, String entryDate, String mood, String symptoms, String notes, String createdAt) {
        this.journalId = journalId;
        this.userId = userId;
        this.entryDate = entryDate;
        this.mood = mood;
        this.symptoms = symptoms;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
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
