package com.example.carenestapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "carenest.db";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table (already existing)
        String createUsersTable = "CREATE TABLE users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "first_name TEXT, " +
                "last_name TEXT, " +
                "email TEXT UNIQUE, " +
                "phone_number TEXT, " +
                "password TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createUsersTable);

        // Appointments table
        String createAppointmentsTable = "CREATE TABLE appointments (" +
                "appt_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "doctor_name TEXT, " +
                "date TEXT, " +
                "time TEXT, " +
                "location TEXT, " +
                "notes TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(user_id) REFERENCES users(user_id))";
        db.execSQL(createAppointmentsTable);

        // Medications table
        String createMedicationsTable = "CREATE TABLE medications (" +
                "med_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "name TEXT, " +
                "dosage TEXT, " +
                "times_per_day INTEGER, " +
                "time_list TEXT, " +
                "start_date TEXT, " +
                "end_date TEXT, " +
                "notes TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(user_id) REFERENCES users(user_id))";
        db.execSQL(createMedicationsTable);

        // Journal entries table
        String createJournalTable = "CREATE TABLE journals (" +
                "journal_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "entry_date TEXT, " +
                "mood TEXT, " +
                "symptoms TEXT, " +
                "note TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(user_id) REFERENCES users(user_id))";
        db.execSQL(createJournalTable);

        // User Profile table
        String createProfileTable = "CREATE TABLE user_profiles (" +
                "profile_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "age INTEGER, " +
                "gender TEXT, " +
                "health_conditions TEXT, " +
                "emergency_contact_name TEXT, " +
                "emergency_contact_phone TEXT, " +
                "profile_image_uri TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "modified_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(user_id) REFERENCES users(user_id))";
        db.execSQL(createProfileTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS appointments");
        db.execSQL("DROP TABLE IF EXISTS medications");
        db.execSQL("DROP TABLE IF EXISTS journals");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
