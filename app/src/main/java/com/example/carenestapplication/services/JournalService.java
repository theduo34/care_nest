package com.example.carenestapplication.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carenestapplication.models.JournalEntry;
import com.example.carenestapplication.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class JournalService {
    private final SQLiteDatabase db;

    public JournalService(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // Add new journal entry
    public long addJournalEntry(JournalEntry entry) {
        ContentValues values = new ContentValues();
        values.put("user_id", entry.getUserId());
        values.put("entry_date", entry.getEntryDate());
        values.put("mood", entry.getMood());
        values.put("symptoms", entry.getSymptoms());
        values.put("notes", entry.getNotes());
       // values.put("created_at", entry.getCreatedAt());
        return db.insert("journal_entries", null, values);
    }

    // Get all journal entries for a user (sorted by newest first)
    public List<JournalEntry> getEntriesByUserId(int userId) {
        List<JournalEntry> entries = new ArrayList<>();

        Cursor cursor = db.query("journal_entries", null, "user_id = ?",
                new String[]{String.valueOf(userId)}, null, null, "entry_date DESC");

        if (cursor.moveToFirst()) {
            do {
                JournalEntry entry = new JournalEntry();
                entry.setJournalId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                entry.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
                entry.setEntryDate(cursor.getString(cursor.getColumnIndexOrThrow("entry_date")));
                entry.setMood(cursor.getString(cursor.getColumnIndexOrThrow("mood")));
                entry.setSymptoms(cursor.getString(cursor.getColumnIndexOrThrow("symptoms")));
                entry.setNotes(cursor.getString(cursor.getColumnIndexOrThrow("notes")));
                entry.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
                entries.add(entry);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return entries;
    }

    // Update an entry
    public void updateJournalEntry(JournalEntry entry) {
        ContentValues values = new ContentValues();
        values.put("entry_date", entry.getEntryDate());
        values.put("mood", entry.getMood());
        values.put("symptoms", entry.getSymptoms());
        values.put("notes", entry.getNotes());

        db.update("journal_entries", values, "id = ?", new String[]{String.valueOf(entry.getJournalId())});
    }

    // Delete an entry
    public void deleteJournalEntry(int journalId) {
        db.delete("journal_entries", "id = ?", new String[]{String.valueOf(journalId)});
    }
}
