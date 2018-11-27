package com.example.mylenovo.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    // Class to add a new entry

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    // When saved-button clicked
    public void addEntry(View view) {
        EditText et_title = findViewById(R.id.et_titel);
        EditText et_mood = findViewById(R.id.et_mood);
        EditText et_content = findViewById(R.id.et_omschrijving);

        // Get input from user
        String title = et_title.getText().toString();
        String mood = et_mood.getText().toString();
        String content = et_content.getText().toString();

        // Put input into JournalEntry-object
        JournalEntry entry = new JournalEntry(title, content, mood);
        // Ad entry to database
        EntryDatabase.getInstance(this).insert(entry);

        // Go back to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
