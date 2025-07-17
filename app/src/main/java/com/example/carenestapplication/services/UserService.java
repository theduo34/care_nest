package com.example.carenestapplication.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.carenestapplication.models.User;
import com.example.carenestapplication.utils.DatabaseHelper;

public class UserService {
    private final DatabaseHelper dbHelper;

    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Register a new user
    public boolean registerUser(User user) {
        if (userExists(user.getEmail())) return false;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("first_name", user.getFirstName());
        values.put("last_name", user.getLastName());
        values.put("email", user.getEmail());
        values.put("phone_number", user.getPhoneNumber());
        values.put("password", hashPassword(user.getPassword()));

        long result = db.insert("users", null, values);
        db.close();
        return result != -1;
    }

    // Check if user already exists
    public boolean userExists(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("users", null,
                "email" + "=?", new String[]{email},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    // Authenticate user
    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "users",
                null,
                "email=? AND password=?",
                new String[]{email, hashPassword(password)},
                null, null, null
        );

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isValid;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("users", null, "email=?", new String[]{email},
                null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            user = extractUser(cursor);
        }

        cursor.close();
        db.close();
        return user;
    }

    private User extractUser(Cursor cursor) {
        User user = new User();
        user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
        user.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow("first_name")));
        user.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("last_name")));
        user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
        user.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow("phone_number")));
        user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
        user.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
        user.setModifiedAt(cursor.getString(cursor.getColumnIndexOrThrow("modified_at")));
        return user;
    }
}
