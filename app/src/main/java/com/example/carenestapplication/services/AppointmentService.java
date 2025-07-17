package com.example.carenestapplication.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carenestapplication.models.Appointment;
import com.example.carenestapplication.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final SQLiteDatabase db;

    public AppointmentService(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public long addAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put("user_id", appointment.getUserId());
        values.put("doctor_name", appointment.getDoctorName());
        values.put("date", appointment.getDate());
        values.put("time", appointment.getTime());
        values.put("location", appointment.getLocation());
        values.put("notes", appointment.getNotes());
        return db.insert("appointments", null, values);
    }

    public List<Appointment> getAppointmentsByUserId(int userId) {
        List<Appointment> list = new ArrayList<>();
        Cursor cursor = db.query("appointments", null, "user_id=?",
                new String[]{String.valueOf(userId)}, null, null, "date ASC");

        while (cursor.moveToNext()) {
            Appointment appt = new Appointment();
            appt.setAppointmentId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            appt.setUserId(userId);
            appt.setDoctorName(cursor.getString(cursor.getColumnIndexOrThrow("doctor_name")));
            appt.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            appt.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
            appt.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));
            appt.setNotes(cursor.getString(cursor.getColumnIndexOrThrow("notes")));
            list.add(appt);
        }
        cursor.close();
        return list;
    }

    public void deleteAppointment(int id) {
        db.delete("appointments", "id = ?", new String[]{String.valueOf(id)});
    }

    public void updateAppointment(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put("doctor_name", appointment.getDoctorName());
        values.put("date", appointment.getDate());
        values.put("time", appointment.getTime());
        values.put("location", appointment.getLocation());
        values.put("notes", appointment.getNotes());
        db.update("appointments", values, "id = ?", new String[]{String.valueOf(appointment.getAppointmentId())});
    }
}
