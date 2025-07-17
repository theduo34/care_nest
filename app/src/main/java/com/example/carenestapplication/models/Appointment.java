package com.example.carenestapplication.models;

public class Appointment {
    private int appointmentId;
    private int userId;
    private String title;
    private String date;
    private String time;
    private String location;
    private String notes;

    private String doctorName;
    private String createdAt;

    public Appointment() { }

    public Appointment(int appointmentId, int userId, String title, String date, String time,String doctorName, String location, String notes, String createdAt) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.doctorName = doctorName;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
