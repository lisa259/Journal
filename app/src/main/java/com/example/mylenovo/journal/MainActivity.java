package com.example.mylenovo.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set listview (activity_main) and connect clicklisteners
        ListView lv_entries = findViewById(R.id.listview);
        lv_entries.setOnItemClickListener(new EntryClickListener());
        lv_entries.setOnItemLongClickListener(new EntryLongClickListener());

        // create database
        db = EntryDatabase.getInstance(getApplicationContext());

        // put all the current information in database in adapter
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter = new EntryAdapter(this, cursor);

        // put adapter items in listview
        lv_entries.setAdapter(adapter);
    }

    // when + button is clicked, go to InputActivity
    public void Clicked1(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    // when back-button clicked, stays on the activity_main screen
    @Override
    public void onBackPressed() {
    }

    // when single-clicked on entry in listview
    private class EntryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // get information of clicked entry from adapter
            Cursor item = (Cursor) adapterView.getItemAtPosition(i);
            String title = item.getString(item.getColumnIndex("title"));
            String content = item.getString(item.getColumnIndex("content"));
            String mood = item.getString(item.getColumnIndex("mood"));
            String date = item.getString(item.getColumnIndex("date"));

            // save retrieved information in intent and go to DetailActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("mood", mood);
            intent.putExtra("date", date);
            startActivity(intent);
        }
    }

    // When long-clicked on entry in listview
    private class EntryLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            // get id from clicked entry
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            final long id = cursor.getLong(cursor.getColumnIndex("_id"));

            // call delete-method on entry
            EntryDatabase.delete(id);
            updateData();
            return false;
        }
    }

    // update adapter(listview)
    private void updateData() {
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter.swapCursor(cursor);
    }

    // reload screen
    protected void onResume() {
        super.onResume();
        updateData();
    }
}
