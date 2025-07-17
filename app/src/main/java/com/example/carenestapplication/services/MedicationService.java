package com.example.carenestapplication.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carenestapplication.models.Medication;
import com.example.carenestapplication.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicationService {
    private final SQLiteDatabase db;

    public MedicationService(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // Add a new medication
    public long addMedication(Medication medication) {
        ContentValues values = new ContentValues();
        values.put("user_id", medication.getUserId());
        values.put("name", medication.getName());
        values.put("dosage", medication.getDosage());
        values.put("time", medication.getTime());
        values.put("start_date", medication.getStartDate());
        values.put("end_date", medication.getEndDate());
        values.put("notes", medication.getNotes());
        //values.put("created_at", medication.getCreatedAt());
        return db.insert("medications", null, values);
    }

    // Get all medications for a specific user
    public List<Medication> getMedicationsByUserId(int userId) {
        List<Medication> list = new ArrayList<>();
        Cursor cursor = db.query("medications", null, "user_id = ?",
                new String[]{String.valueOf(userId)}, null, null, "start_date ASC");

        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();
                medication.setMedicationId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                medication.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
                medication.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                medication.setDosage(cursor.getString(cursor.getColumnIndexOrThrow("dosage")));
                medication.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
                medication.setStartDate(cursor.getString(cursor.getColumnIndexOrThrow("start_date")));
                medication.setEndDate(cursor.getString(cursor.getColumnIndexOrThrow("end_date")));
                medication.setNotes(cursor.getString(cursor.getColumnIndexOrThrow("notes")));
                medication.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
                list.add(medication);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    // Update a medication
    public void updateMedication(Medication medication) {
        ContentValues values = new ContentValues();
        values.put("name", medication.getName());
        values.put("dosage", medication.getDosage());
        values.put("time", medication.getTime());
        values.put("start_date", medication.getStartDate());
        values.put("end_date", medication.getEndDate());
        values.put("notes", medication.getNotes());
        db.update("medications", values, "id = ?", new String[]{String.valueOf(medication.getMedicationId())});
    }

    // Delete a medication
    public void deleteMedication(int medicationId) {
        db.delete("medications", "id = ?", new String[]{String.valueOf(medicationId)});
    }
}
