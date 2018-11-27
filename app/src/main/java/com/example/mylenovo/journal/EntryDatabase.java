package com.example.mylenovo.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class EntryDatabase extends SQLiteOpenHelper {
    // Database class

    private static EntryDatabase instance;

    // Constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create database table
        String dbCreate = "CREATE TABLE entries ( _id INTEGER PRIMARY KEY, " +
                "title text, content text, mood text, date DATETIME DEFAULT (datetime('now','localtime')));";
        db.execSQL(dbCreate);
    }

    // insert new entry to table
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("mood", entry.getMood());
        values.put("content", entry.getContent());
        db.insert("entries", null, values);
    }

    // Check if database already exists, if not: create new database
    public static EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance != null) {
            return EntryDatabase.instance;
        } else {
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
            return EntryDatabase.instance;
        }
    }

    // Drop table and create new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries;");
        onCreate(db);
    }

    // Select everything from table return as Cursor
    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase database = instance.getWritableDatabase();
        return database.rawQuery("SELECT * FROM entries", null);
    }

    // Delete one entrie-item in table
    static void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete("entries", "_id = " + id, null);
    }
}
