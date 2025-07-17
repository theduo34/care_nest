package com.example.carenestapplication.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carenestapplication.models.UserProfile;
import com.example.carenestapplication.utils.DatabaseHelper;

public class UserProfileService {

    private final SQLiteDatabase db;

    public UserProfileService(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // Add a new profile
    public long addUserProfile(UserProfile profile) {
        ContentValues values = new ContentValues();
        values.put("user_id", profile.getUserId());
        values.put("age", profile.getAge());
        values.put("gender", profile.getGender());
        values.put("health_conditions", profile.getHealthConditions());
        values.put("emergency_contact_name", profile.getEmergencyContactName());
        values.put("emergency_contact_phone", profile.getEmergencyContactPhone());
        values.put("profile_image_uri", profile.getProfileImageUri());
        values.put("created_at", profile.getCreatedAt()); // Optional if default

        return db.insert("user_profiles", null, values);
    }

    // Get profile by user ID
    public UserProfile getProfileByUserId(int userId) {
        Cursor cursor = db.query("user_profiles", null, "user_id = ?",
                new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor.moveToFirst()) {
            UserProfile profile = new UserProfile();
            profile.setProfileId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            profile.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
            profile.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("age")));
            profile.setGender(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
            profile.setHealthConditions(cursor.getString(cursor.getColumnIndexOrThrow("health_conditions")));
            profile.setEmergencyContactName(cursor.getString(cursor.getColumnIndexOrThrow("emergency_contact_name")));
            profile.setEmergencyContactPhone(cursor.getString(cursor.getColumnIndexOrThrow("emergency_contact_phone")));
            profile.setProfileImageUri(cursor.getString(cursor.getColumnIndexOrThrow("profile_image_uri")));
           // profile.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));

            cursor.close();
            return profile;
        }

        return null;
    }

    // Update user profile
    public void updateUserProfile(UserProfile profile) {
        ContentValues values = new ContentValues();
        values.put("age", profile.getAge());
        values.put("gender", profile.getGender());
        values.put("health_conditions", profile.getHealthConditions());
        values.put("emergency_contact_name", profile.getEmergencyContactName());
        values.put("emergency_contact_phone", profile.getEmergencyContactPhone());
        values.put("profile_image_uri", profile.getProfileImageUri());

        db.update("user_profiles", values, "user_id = ?", new String[]{String.valueOf(profile.getUserId())});
    }

    // Delete user profile
    public void deleteUserProfile(int userId) {
        db.delete("user_profiles", "user_id = ?", new String[]{String.valueOf(userId)});
    }
}
